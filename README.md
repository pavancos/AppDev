### **MarkMeDb.java (Database Helper)**
```java
package com.example.sqlite7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class MarkMeDb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "markme.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "event";
    private static final String KEY_ID = "name";
    private static final String KEY_DATE = "date";

    public MarkMeDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " TEXT PRIMARY KEY, " + KEY_DATE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addEvent(String name, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + "=?", new String[]{name});
        if (cursor.getCount() > 0) return;
        ContentValues values = new ContentValues();
        values.put(KEY_ID, name);
        values.put(KEY_DATE, date);
        db.insert(TABLE_NAME, null, values);
        cursor.close();
        db.close();
    }

    List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM event", null);
        while (cursor.moveToNext()) events.add(new Event(cursor.getString(0), cursor.getString(1)));
        cursor.close();
        return events;
    }

    void deleteEvent(String name) {
        this.getWritableDatabase().delete(TABLE_NAME, KEY_ID + " = ?", new String[]{name});
    }

    void updateEvent(String name, String date) {
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, date);
        this.getWritableDatabase().update(TABLE_NAME, values, KEY_ID + " = ?", new String[]{name});
    }
}
```

---

### **MainActivity.java**
```java
package com.example.sqlite7;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MarkMeDb db;
    EditText name, date;
    Button createEventBtn, updateEventBtn, deleteEventBtn, showEventsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MarkMeDb(this);
        name = findViewById(R.id.eventName);
        date = findViewById(R.id.editDate);

        createEventBtn = findViewById(R.id.CreateButton);
        createEventBtn.setOnClickListener(v -> addEvent());

        showEventsBtn = findViewById(R.id.ShowEvents);
        showEventsBtn.setOnClickListener(v -> getAllEvents());

        updateEventBtn = findViewById(R.id.UpdateEvent);
        updateEventBtn.setOnClickListener(v -> updateEvent());

        deleteEventBtn = findViewById(R.id.DeleteEvents);
        deleteEventBtn.setOnClickListener(v -> deleteEvent());
    }

    void addEvent() {
        String eventName = name.getText().toString().trim();
        String eventDate = date.getText().toString().trim();
        if (eventName.isEmpty() || eventDate.isEmpty()) {
            Toast.makeText(this, "Enter event details", Toast.LENGTH_SHORT).show();
            return;
        }
        db.addEvent(eventName, eventDate);
        Toast.makeText(this, "Event Created", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    void deleteEvent() {
        db.deleteEvent(name.getText().toString());
        Toast.makeText(this, "Event Deleted", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    void updateEvent() {
        db.updateEvent(name.getText().toString(), date.getText().toString());
        Toast.makeText(this, "Event Updated", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    void getAllEvents() {
        List<Event> events = db.getAllEvents();
        if (events.isEmpty()) {
            Toast.makeText(this, "No Events Found", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("All Events");

        ScrollView scrollView = new ScrollView(this);
        TextView textView = new TextView(this);
        StringBuilder eventList = new StringBuilder();
        for (Event event : events) eventList.append("Name: ").append(event.getName()).append("\nDate: ").append(event.getDate()).append("\n-------------------\n");
        textView.setText(eventList.toString());
        scrollView.addView(textView);

        builder.setView(scrollView);
        builder.setPositiveButton("Close", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    void clearFields() {
        name.setText("");
        date.setText("");
    }
}
```

---

### **activity_main.xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/Title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Event Database"
        android:textSize="34sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <EditText
        android:id="@+id/eventName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Event Name"
        app:layout_constraintTop_toBottomOf="@id/Title" />

    <EditText
        android:id="@+id/editDate"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Event Date"
        android:inputType="date"
        app:layout_constraintTop_toBottomOf="@id/eventName" />

    <Button
        android:id="@+id/CreateButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Create Event"
        app:layout_constraintTop_toBottomOf="@id/editDate" />

    <Button
        android:id="@+id/ShowEvents"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Show Events"
        app:layout_constraintTop_toBottomOf="@id/CreateButton" />

    <Button
        android:id="@+id/UpdateEvent"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Update Event"
        app:layout_constraintTop_toBottomOf="@id/ShowEvents" />

    <Button
        android:id="@+id/DeleteEvents"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Delete Event"
        app:layout_constraintTop_toBottomOf="@id/UpdateEvent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

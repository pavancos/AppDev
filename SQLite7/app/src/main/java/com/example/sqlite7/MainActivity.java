package com.example.sqlite7;

import static com.example.sqlite7.R.*;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqlite7.MarkMeDb;
import com.example.sqlite7.Event;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MarkMeDb db;
    Event event;
    EditText name, date;
    String eventName, eventDate;
    Button createEventBtn, updateEventBtn, deleteEventBtn, showEventsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = new MarkMeDb(this);
        name = findViewById(R.id.eventName);
        date = findViewById(R.id.editDate);
        createEventBtn = findViewById(R.id.CreateButton);
        createEventBtn.setOnClickListener(v->{
            addEvent();
        });

        showEventsBtn=findViewById(id.ShowEvents);
        showEventsBtn.setOnClickListener(v->{
            getAllEvents();
        });

        updateEventBtn = findViewById(id.UpdateEvent);
        updateEventBtn.setOnClickListener(v->{
            updateEvent();
        });

        deleteEventBtn = findViewById(id.DeleteEvents);
        deleteEventBtn.setOnClickListener(v->{
            deleteEvent();
        });


    }

    public void addEvent(){
        eventName = name.getText().toString().trim();
        eventDate = date.getText().toString().trim();
        if (eventName.isEmpty() || eventDate.isEmpty()) {
            Toast.makeText(this, "Please enter event details", Toast.LENGTH_SHORT).show();
            return;
        }
        event = new Event(eventName, eventDate);
        db.addEvent(event.getName(), event.getDate());
        Toast.makeText(this, "Event is Created", Toast.LENGTH_SHORT).show();
        clearFields();
    }


    public void deleteEvent(){
        eventName = name.getText().toString();
        db.deleteEvent(eventName);
        Toast.makeText(this, "Event is deleted", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    public void updateEvent(){
        eventName = name.getText().toString();
        eventDate = date.getText().toString();
        event = new Event(eventName, eventDate);
        db.updateEvent(eventName, eventDate);
        Toast.makeText(this, "Event is updated", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    public void clearFields(){
        name.setText("");
        date.setText("");
    }

    public void getAllEvents() {
        List<Event> events = db.getAllEvents();
        if (events.isEmpty()) {
            Toast.makeText(this, "No events found", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("All Events");

        ScrollView scrollView = getScrollView(events);

        builder.setView(scrollView);
        builder.setPositiveButton("Close", (dialog, which) -> dialog.dismiss());
        builder.show();
        clearFields();
    }

    @NonNull
    private ScrollView getScrollView(List<Event> events) {
        ScrollView scrollView = new ScrollView(this);
        TextView textView = new TextView(this);
        StringBuilder eventList = new StringBuilder();

        for (Event event : events) {
            eventList.append("Name: ").append(event.getName())
                    .append("\nDate: ").append(event.getDate())
                    .append("\n-------------------\n");
        }

        textView.setText(eventList.toString());
        textView.setPadding(20, 20, 20, 20);
        scrollView.addView(textView);
        return scrollView;
    }
}
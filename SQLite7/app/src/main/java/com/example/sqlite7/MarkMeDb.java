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
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + KEY_ID + " TEXT PRIMARY KEY, "
                + KEY_DATE + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addEvent(String name, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + "=?", new String[]{name});

        if (cursor.getCount() > 0) {
            System.out.println("Event already exists");
            cursor.close();
            db.close();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(KEY_ID, name);
        values.put(KEY_DATE, date);
        db.insert(TABLE_NAME, null, values);
        cursor.close();
        db.close();
    }


    List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM event", null);

        if (cursor.moveToFirst()) {
            do {
                events.add(new Event(cursor.getString(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return events;
    }


    void deleteEvent(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_NAME, KEY_ID + " = ?", new String[]{name});
        if (rowsAffected == 0) {
            System.out.println("No event found to delete");
        }
        db.close();
    }

    void updateEvent(String name, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, date);
        int rowsAffected = db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[]{name});
        if (rowsAffected == 0) {
            System.out.println("No event found to update");
        }
        db.close();
    }
}
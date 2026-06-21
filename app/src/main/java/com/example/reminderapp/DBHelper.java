package com.example.reminderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ReminderDB.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "reminder";

    private static final String COL_ID = "id";
    private static final String COL_TITLE = "title";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_DATE = "date";
    private static final String COL_TIME = "time";
    private static final String COL_CATEGORY = "category";
    private static final String COL_REPEAT = "repeat_type";
    private static final String COL_STATUS = "status";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COL_TITLE + " TEXT," +
                        COL_DESCRIPTION + " TEXT," +
                        COL_DATE + " TEXT," +
                        COL_TIME + " TEXT," +
                        COL_CATEGORY + " TEXT," +
                        COL_REPEAT + " TEXT," +
                        COL_STATUS + " TEXT" +
                        ")";

        db.execSQL(query);

        db.execSQL(
                "CREATE TABLE users(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "username TEXT," +
                        "email TEXT UNIQUE," +
                        "password TEXT)"
        );

    }
    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS reminder");
        db.execSQL("DROP TABLE IF EXISTS users");

        onCreate(db);
    }
    //=========================
    // INSERT
    //=========================

    public void insertReminder(String title,
                               String description,
                               String date,
                               String time,
                               String category,
                               String repeatType,
                               String status) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_TITLE, title);
        values.put(COL_DESCRIPTION, description);
        values.put(COL_DATE, date);
        values.put(COL_TIME, time);
        values.put(COL_CATEGORY, category);
        values.put(COL_REPEAT, repeatType);
        values.put(COL_STATUS, status);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    //=========================
    // GET ALL
    //=========================

    public ArrayList<Reminder> getAllReminder() {

        ArrayList<Reminder> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME +
                        " ORDER BY id DESC",
                null
        );

        if (cursor.moveToFirst()) {

            do {

                Reminder reminder = new Reminder();

                reminder.setId(
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow(COL_ID)
                        )
                );

                reminder.setTitle(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_TITLE)
                        )
                );

                reminder.setDescription(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_DESCRIPTION)
                        )
                );

                reminder.setDate(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_DATE)
                        )
                );

                reminder.setTime(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_TIME)
                        )
                );

                reminder.setCategory(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_CATEGORY)
                        )
                );

                reminder.setRepeatType(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_REPEAT)
                        )
                );

                reminder.setStatus(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_STATUS)
                        )
                );

                list.add(reminder);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return list;
    }

    //=========================
    // GET BY CATEGORY
    //=========================

    public ArrayList<Reminder> getReminderByCategory(String category) {

        ArrayList<Reminder> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME +
                        " WHERE " + COL_CATEGORY + "=? " +
                        " ORDER BY id DESC",
                new String[]{category}
        );

        if (cursor.moveToFirst()) {

            do {

                Reminder reminder = new Reminder();

                reminder.setId(
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow(COL_ID)
                        )
                );

                reminder.setTitle(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_TITLE)
                        )
                );

                reminder.setDescription(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_DESCRIPTION)
                        )
                );

                reminder.setDate(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_DATE)
                        )
                );

                reminder.setTime(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_TIME)
                        )
                );

                reminder.setCategory(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_CATEGORY)
                        )
                );

                reminder.setRepeatType(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_REPEAT)
                        )
                );

                reminder.setStatus(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_STATUS)
                        )
                );

                list.add(reminder);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return list;
    }

    //=========================
    // GET BY STATUS
    //=========================

    public ArrayList<Reminder> getReminderByStatus(String status) {

        ArrayList<Reminder> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME +
                        " WHERE " + COL_STATUS + "=? " +
                        " ORDER BY id DESC",
                new String[]{status}
        );

        if (cursor.moveToFirst()) {

            do {

                Reminder reminder = new Reminder();

                reminder.setId(
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow(COL_ID)
                        )
                );

                reminder.setTitle(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_TITLE)
                        )
                );

                reminder.setDescription(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_DESCRIPTION)
                        )
                );

                reminder.setDate(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_DATE)
                        )
                );

                reminder.setTime(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_TIME)
                        )
                );

                reminder.setCategory(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_CATEGORY)
                        )
                );

                reminder.setRepeatType(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_REPEAT)
                        )
                );

                reminder.setStatus(
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(COL_STATUS)
                        )
                );

                list.add(reminder);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return list;
    }

    //=========================
    // UPDATE
    //=========================

    public void updateReminder(int id,
                               String title,
                               String description,
                               String date,
                               String time,
                               String category,
                               String repeatType,
                               String status) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_TITLE, title);
        values.put(COL_DESCRIPTION, description);
        values.put(COL_DATE, date);
        values.put(COL_TIME, time);
        values.put(COL_CATEGORY, category);
        values.put(COL_REPEAT, repeatType);
        values.put(COL_STATUS, status);

        db.update(
                TABLE_NAME,
                values,
                COL_ID + "=?",
                new String[]{
                        String.valueOf(id)
                }
        );

        db.close();
    }

    //=========================
    // DELETE
    //=========================

    public void deleteReminder(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                TABLE_NAME,
                COL_ID + "=?",
                new String[]{
                        String.valueOf(id)
                }
        );

        db.close();

    }

    //=========================
    // UPDATE STATUS
    //=========================

    public void updateStatus(int id,
                             String status) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_STATUS, status);

        db.update(
                TABLE_NAME,
                values,
                COL_ID + "=?",
                new String[]{
                        String.valueOf(id)
                }
        );

        db.close();

    }

    public boolean registerUser(String username,
                                String email,
                                String password){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);

        long result = db.insert("users", null, cv);

        return result != -1;
    }


    public boolean loginUser(String email,
                             String password){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM users WHERE email=? AND password=?",
                new String[]{email,password}
        );

        boolean exists = cursor.getCount() > 0;

        cursor.close();

        return exists;
    }

}


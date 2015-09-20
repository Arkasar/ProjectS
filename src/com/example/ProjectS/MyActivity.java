package com.example.ProjectS;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mDatabaseHelper = new DatabaseHelper(this, "mydatabase.db", null, 1);

        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        // Задайте значения для каждого столбца
        newValues.put(DatabaseHelper.DAY_COLUMN, "Monday");
        newValues.put(DatabaseHelper.START_TIME_COLUMN, "10:10");
        newValues.put(DatabaseHelper.SUBJECT_COLUMN, "English");
        newValues.put(DatabaseHelper.CLASSROOM_COLUMN, "d209");
        // Вставляем данные в таблицу
        mSqLiteDatabase.insert("sch", null, newValues);
    }

    public void onClick(View view) {
        Cursor cursor = mSqLiteDatabase.query("sch", new String[] {DatabaseHelper.DAY_COLUMN,
                        DatabaseHelper.START_TIME_COLUMN, DatabaseHelper.SUBJECT_COLUMN, DatabaseHelper.CLASSROOM_COLUMN},
                null, null,
                null, null, null) ;

        cursor.moveToFirst();

        String day = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DAY_COLUMN));
        String start_time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.START_TIME_COLUMN));
        String subject = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SUBJECT_COLUMN));
        String classroom = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CLASSROOM_COLUMN));

        TextView infoTextView = (TextView)findViewById(R.id.textView);
        infoTextView.setText(day + " "
                + start_time + " "
                + subject + " "
                + classroom);

        // не забываем закрывать курсор
        cursor.close();
    }
}


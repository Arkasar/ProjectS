package com.example.ProjectS;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Аркадий on 20.09.2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {
    private static final String DATABASE_NAME = "schedule.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "sch";

    public static final String DAY_COLUMN = "day";
    public static final String START_TIME_COLUMN = "start_time";
    public static final String SUBJECT_COLUMN = "subject";
    public static final String CLASSROOM_COLUMN = "age";

    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " (" + BaseColumns._ID + " integer primary key autoincrement, "
            + DAY_COLUMN + " text not null, "
            + START_TIME_COLUMN + " text not null, "
            + SUBJECT_COLUMN + " text not null, "
            + CLASSROOM_COLUMN + " text not null);";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Запишем в журнал
        Log.w("SQLite", "Update from version " + oldVersion + " to version " + newVersion);

        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
        // Создаём новую таблицу
        onCreate(db);
    }
}

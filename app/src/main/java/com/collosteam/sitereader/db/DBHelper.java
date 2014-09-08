package com.collosteam.sitereader.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Collos on 7/16/2014.
 */
public class DBHelper extends SQLiteOpenHelper implements DBColumns {


    private static final String DATABASE_NAME = "my_db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE = "users" ;
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS "
            + TABLE + "("
            + _ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT UNIQUE, "
            + COL_PASS + " TEXT, "
            + COL_EMAIL + " TEXT, "
            + "UNIQUE (" + COL_NAME + ") ON CONFLICT IGNORE);" ;

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE ;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Удаляем предыдущую таблицу при апгрейде
        db.execSQL(SQL_DELETE_ENTRIES);
// Создаём новый экземпляр таблицы
        onCreate(db);
    }

}

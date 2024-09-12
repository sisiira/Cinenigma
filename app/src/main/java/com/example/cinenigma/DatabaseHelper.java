package com.example.cinenigma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Film.db";
    public static final String TABLE_NAME = "film_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITRE";
    public static final String COL_3 = "DATE";
    public static final String COL_4 = "HEURE";
    public static final String COL_5 = "SCENARIO";
    public static final String COL_6 = "REALISATION";
    public static final String COL_7 = "MUSIQUE";
    public static final String COL_8 = "CRITIQUE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITRE TEXT, DATE TEXT, HEURE TEXT, SCENARIO REAL, REALISATION REAL, MUSIQUE REAL, CRITIQUE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to insert data
    public boolean insertData(String titre, String date, String heure, double scenario, double realisation, double musique, String critique) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, titre);
        contentValues.put(COL_3, date);
        contentValues.put(COL_4, heure);
        contentValues.put(COL_5, scenario);
        contentValues.put(COL_6, realisation);
        contentValues.put(COL_7, musique);
        contentValues.put(COL_8, critique);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    // Method to get all data
    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}

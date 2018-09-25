package com.logvidmi.prototypepuzzle.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileInputStream;
import java.io.IOException;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "imagesDb";
    public static final String TABLE_IMAGES = "images";

    public static final String KEY_ID = "_id";
    public static final String KEY_IMAGE = "image";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_IMAGES + "(" + KEY_ID
                + " integer primary key," + KEY_IMAGE + " blob not null"+ ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_IMAGES);
        onCreate(db);
    }

    /**
     * Insert image into database.
     *
     * @param x Image location.
     * @param i Image number;
     * @return
     */
    public Boolean insertImage(String x, Integer i) {
        SQLiteDatabase db = this.getWritableDatabase();
        try  {
            FileInputStream fs = new FileInputStream(x);
            byte[] imgbyte = new byte[fs.available()];
            fs.read(imgbyte);
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_ID, i);
            contentValues.put(KEY_IMAGE, imgbyte);
            db.insert(TABLE_IMAGES, null, contentValues);
            fs.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

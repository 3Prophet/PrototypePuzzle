package com.logvidmi.prototypepuzzle.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.logvidmi.prototypepuzzle.model.IdentifiableImage;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Database handler to create an application database, update it upon the version change and
 * insert/read images to/from it.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "imagesDb";
    public static final String TABLE_IMAGES = "images";

    public static final String KEY_ID = "_id";
    public static final String KEY_IMAGE = "image";

    /**
     *
     * @param context Activity, from where the database is called.
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creating database to store images as blobs.
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_IMAGES + "(" + KEY_ID
                + " integer primary key," + KEY_IMAGE + " blob not null"+ ")");

    }

    /**
     * Recreating database on the version upgrade.
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_IMAGES);
        onCreate(db);
    }

    /**
     * Insert image located in a device's image store into the application datatbase.
     *
     * @param location Image location.
     * @return Boolean indicating whether the operation has succeeded.
     */
    public Boolean insertImage(String location) {

        try  {
            FileInputStream fs = new FileInputStream(location);
            byte[] imageBytes = new byte[fs.available()];
            fs.read(imageBytes);
            insertIntoDatabase(imageBytes);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Insert image from its Bitmap into the application datatbase.
     *
     * @param image Bitmap of an image.
     * @return Boolean indicating whether the operation has succeeded.
     */
    public Boolean insertImage(Bitmap image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 200, stream);
        byte[] imageBytes = stream.toByteArray();
        try {
            stream.close();
            insertIntoDatabase(imageBytes);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private void insertIntoDatabase(byte[] imageBytes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_IMAGE, imageBytes);
        db.insert(TABLE_IMAGES, null, contentValues);
    }

    public ArrayList<IdentifiableImage> getIdentifiableImagesFromDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<IdentifiableImage> imageList = new ArrayList<>();
        Bitmap bitmap = null;
        Cursor cursor = db.rawQuery("select * from " + TABLE_IMAGES, null);
        while (cursor.moveToNext()) {
            byte[] imageBytes = cursor.getBlob(1);
            bitmap = BitmapFactory.decodeByteArray(imageBytes,
                    0, imageBytes.length);
            long id = cursor.getLong(cursor.getColumnIndex(KEY_ID));

            imageList.add(new IdentifiableImage(bitmap, id));
        }
        cursor.close();
        return imageList;
    }

    /**
     * Retrieves all images from the application database.
     *
     * @return List of bitmaps.
     */
    public ArrayList<Bitmap> getImagesFromDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Bitmap> imageList = new ArrayList<>();
        Bitmap bitmap = null;
        Cursor cursor = db.rawQuery("select * from " + TABLE_IMAGES, null);
        while (cursor.moveToNext()) {
            byte[] imageBytes = cursor.getBlob(1);
            bitmap = BitmapFactory.decodeByteArray(imageBytes,
                    0, imageBytes.length);

            imageList.add(bitmap);
        }
        cursor.close();
        return imageList;
    }

    /**
     * Delete an image from the database.
     *
     * @param id Value of the key of an item to be deleted.
     */
    public void removeImage(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = KEY_ID+"=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        db.delete(TABLE_IMAGES, whereClause, whereArgs);
    }
}

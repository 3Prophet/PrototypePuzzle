package com.logvidmi.prototypepuzzle.services;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.logvidmi.prototypepuzzle.model.IdentifiableImage;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Database handler to create an application database, update it upon the version change and
 * insert/read images to/from it.
 * The database contains only ids and uris of images.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "imagesDb";
    public static final String TABLE_IMAGES = "images";

    public static final String KEY_ID = "_id";
    public static final String KEY_IMAGEURI = "image";

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private Context activity;

    /**
     * @param context Activity, from where the database is called.
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.activity = context;
    }

    /**
     * Creating database to store images as blobs.
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_IMAGES + "(" + KEY_ID
                + " integer primary key," + KEY_IMAGEURI + " string"+ ")");

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
     * Insert image URI into the application datatbase.
     *
     * @param imageUri URI of an image.
     * @return Boolean indicating whether the operation has succeeded.
     */
    public Boolean insertImage(Uri imageUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_IMAGEURI, imageUri.toString());
        db.insert(TABLE_IMAGES, null, contentValues);
        return true;
    }

    /**
     * Gets a list of identifiable images {@see com.logvidmi.prototypepuzzle.model.IdentifiableImage}
     */
    public ArrayList<IdentifiableImage> getIdentifiableImagesFromDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<IdentifiableImage> imageList = new ArrayList<>();
        Bitmap bitmap = null;
        Cursor cursor = db.rawQuery("select * from " + TABLE_IMAGES, null);
        getPermission();
        while (cursor.moveToNext()) {
            String imageUri = cursor.getString(cursor.getColumnIndex(KEY_IMAGEURI));
            try {
                getPermission();
                bitmap =  MediaStore.Images.Media.getBitmap(activity.getContentResolver(),
                        Uri.parse(imageUri));
                long id = cursor.getLong(cursor.getColumnIndex(KEY_ID));
                imageList.add(new IdentifiableImage(bitmap, id));
            } catch (IOException e) {
            }

        }
        cursor.close();
        return imageList;
    }

    /**
     * For all the uris in the database return the list of corresponding bitmaps.
     *
     * @return List of bitmaps.
     */
    public ArrayList<Bitmap> getImagesFromDatabase()  {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Bitmap> imageList = new ArrayList<>();
        Bitmap bitmap = null;
        Cursor cursor = db.rawQuery("select * from " + TABLE_IMAGES, null);
        int count = cursor.getCount();
        getPermission();
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(KEY_IMAGEURI);
            String imageUri = cursor.getString(index);
            try {
                getPermission();
                bitmap =  MediaStore.Images.Media.getBitmap(activity.getContentResolver(),
                            Uri.parse(imageUri));
                imageList.add(bitmap);// Permission is not granted
            } catch (IOException e) {
            }
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
        String idString = String.valueOf(id);
        String[] whereArgs = new String[] {idString};
        String whereClause = KEY_ID + "=?";
        db.delete(TABLE_IMAGES, whereClause, whereArgs);
    }

    /**
     * Obtains dynamic permission to read from external storage for retrieving images.
     */
    private void getPermission() {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    (Activity) activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}

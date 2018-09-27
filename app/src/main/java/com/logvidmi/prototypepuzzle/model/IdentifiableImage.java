package com.logvidmi.prototypepuzzle.model;

import android.graphics.Bitmap;

/**
 * Represents image from the database. It contains bitmap and id(primary key) and used
 * to identify the corresponding image in the database.
 */
public class IdentifiableImage  {

    private Bitmap bitmap;

    private long id;

    public IdentifiableImage(Bitmap bitmap, long id) {
        this.bitmap = bitmap;
        this.id = id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public long getId() {
        return id;
    }
}

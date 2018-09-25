package com.logvidmi.prototypepuzzle.controllers;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

public class SplitImageController {

    public SplitImageController(ImageView image) {
        //Getting the scaled bitmap of the source image
        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = drawable.getBitmap();


        /* Now the chunkedImages has all the small image chunks in the form of Bitmap class.
         * You can do what ever you want with this chunkedImages as per your requirement.
         * I pass it to a new Activity to show all small chunks in a grid for demo.
         * You can get the source code of this activity from my Google Drive Account.
         */

        //Start a new activity to show these chunks into a grid
        /*
        Intent intent = new Intent(ImageActivity.this, ChunkedImageActivity.class);
        intent.putParcelableArrayListExtra("image chunks", chunkedImages);
        startActivity(intent);*/
    }
}

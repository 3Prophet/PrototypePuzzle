package com.logvidmi.prototypepuzzle.services;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Splits an image into equal rectangular chunks. The number of chunks corresponds to rows*columns.
 */
public class ImageSplitter {

    private int rows;

    private int columns;

    private int chunkHeight;

    private int chunkWidth;

    public ImageSplitter(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public ArrayList<Bitmap> splitImage(Bitmap bitmap) {

        //The number of chunks
        int chunkNumbers = rows * columns;


        //To store all the small image chunks in bitmap format in this list
        ArrayList<Bitmap> chunkedImages = new ArrayList<Bitmap>(chunkNumbers);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

        chunkHeight = bitmap.getHeight()/rows;
        chunkWidth = bitmap.getWidth()/columns;

        //xCoord and yCoord are the pixel positions of the image chunks
        int yCoord = 0;
        for(int x = 0; x < rows; x++){
            int xCoord = 0;
            for(int y = 0; y < columns; y++){
                chunkedImages.add(Bitmap.createBitmap(scaledBitmap, xCoord, yCoord, chunkWidth, chunkHeight));
                xCoord += chunkWidth;
            }
            yCoord += chunkHeight;
        }

        return chunkedImages;
    }

    public int getChunkHeight() {
        return chunkHeight;
    }

    public int getChunkWidth() {
        return chunkWidth;
    }
}
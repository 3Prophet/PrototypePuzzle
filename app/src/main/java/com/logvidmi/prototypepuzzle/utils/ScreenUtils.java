package com.logvidmi.prototypepuzzle.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;

/**
 * Class which encapsulates screen dimensions as total width,
 * height and status bar height in pixels for a given context and
 * is used to resize Bitmaps to match screen size.
 */
public class ScreenUtils {

    private int screenWidth;

    private int screenHeight;

    private int statusBarHeight;

    public ScreenUtils(Context context) {
        calculateScreenDimensions(context);
    }

    private void calculateScreenDimensions(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;

        // Obtaining status bar height
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getStatusBarHeight() {
        return statusBarHeight;
    }

    /**
     * Returns bitmap rescaled to fit the size of android device screen.
     *
     * @param bitmap Original bitmap.
     * @return Rescaled bitmap.
     */
    public Bitmap resizeBitmap(Bitmap bitmap) {
        Bitmap resizedBitmap = null;
        // Getting bitmap dimensions
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        float bitmapAspectRatio = (float) bitmapWidth / bitmapHeight;

        float screenAspectRatio = (float) screenWidth / (screenHeight - statusBarHeight);
        float scale = 1;
        //
        if (bitmapAspectRatio > screenAspectRatio) {
            scale =(float) screenWidth / bitmapWidth;
            resizedBitmap = Bitmap.createScaledBitmap(bitmap,
                    screenWidth,
                    (int) (screenWidth / bitmapAspectRatio), true);
        } else {
            scale = ((float) (screenHeight-statusBarHeight) )/ bitmapHeight;
            resizedBitmap = Bitmap.createScaledBitmap(bitmap,
                    (int)(screenHeight * bitmapAspectRatio),
                    screenHeight, true);
        }
        return resizedBitmap;
    }
}

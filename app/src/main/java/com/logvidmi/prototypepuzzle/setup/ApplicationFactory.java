package com.logvidmi.prototypepuzzle.setup;

import android.content.Context;
import android.graphics.Bitmap;

import com.logvidmi.prototypepuzzle.R;
import com.logvidmi.prototypepuzzle.model.Puzzle;
import com.logvidmi.prototypepuzzle.services.DatabaseHandler;
import com.logvidmi.prototypepuzzle.services.ImageSplitter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Application Factory Singleton.
 */
public class ApplicationFactory {

    private static ApplicationFactory factory = null;

    private final static int ROWS = 3;

    private final static int COLUMNS = 3;

    /**
     * Image to be used in a Puzzle Game
     */
    private Bitmap puzzleImage;

    private ApplicationFactory() {}

    public static ApplicationFactory getApplicationFactory(){
        if (factory == null) {
            factory = new ApplicationFactory();
        }
        return factory;
    }

    public ImageSplitter getImageSplitter() {
        return new ImageSplitter(ROWS, COLUMNS);
    }

    public int getRows() {
        return ROWS;
    }

    public int getColumns() {
        return COLUMNS;
    }

    public int[] getPuzzleImages() {
        return  new int[] {R.drawable.blue_rose, R.drawable.red_heart};
    }

    public void setBitmapForPuzzleGame(Bitmap puzzleImage) {
        this.puzzleImage = puzzleImage;
    }

    public Puzzle getPuzzle() {
        return new Puzzle(puzzleImage, ROWS, COLUMNS);
    }

}

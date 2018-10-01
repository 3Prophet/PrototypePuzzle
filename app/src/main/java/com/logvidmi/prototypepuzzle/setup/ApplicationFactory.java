package com.logvidmi.prototypepuzzle.setup;

import android.content.Context;
import android.graphics.Bitmap;

import com.logvidmi.prototypepuzzle.R;
import com.logvidmi.prototypepuzzle.model.Puzzle;
import com.logvidmi.prototypepuzzle.services.DatabaseHandler;
import com.logvidmi.prototypepuzzle.services.ImageSplitter;
import com.logvidmi.prototypepuzzle.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Application Factory Singleton with different resources, which can be used
 * by the application.
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

    /**
     * @return Application factory singleton.
     */
    public static ApplicationFactory getApplicationFactory(){
        if (factory == null) {
            factory = new ApplicationFactory();
        }
        return factory;
    }

    /**
     * @return Image Splitter to split a bitmap into chunks.
     */
    public ImageSplitter getImageSplitter() {
        return new ImageSplitter(ROWS, COLUMNS);
    }

    /**
     * @return Number of rows in puzzle.
     */
    public int getRows() {
        return ROWS;
    }

    /**
     * @return Number of columns in puzzle.
     */
    public int getColumns() {
        return COLUMNS;
    }

    /**
     * Used by the application to set the bitmap to be used to create a puzzle.
     */
    public void setBitmapForPuzzleGame(Bitmap puzzleImage) {
        this.puzzleImage = puzzleImage;
    }

    /**
     * Creates puzzle with certain number of rows and columns.
     */
    public Puzzle getPuzzle(Context context) {
        ScreenUtils utils = new ScreenUtils(context);
        return new Puzzle(utils.resizeBitmap(puzzleImage), ROWS, COLUMNS);
    }

}

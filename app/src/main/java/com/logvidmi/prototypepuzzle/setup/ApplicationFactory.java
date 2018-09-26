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

    private int rows;

    private int columns;

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

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public ImageSplitter getImageSplitter() {
        return new ImageSplitter(rows, columns);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int[] getPuzzleImages() {
        return  new int[] {R.drawable.blue_rose, R.drawable.red_heart};
    }

    public void setBitmapForPuzzleGame(Bitmap puzzleImage) {
        this.puzzleImage = puzzleImage;
    }

    public Puzzle getPuzzle() {
        return new Puzzle(puzzleImage, rows, columns);
    }

}

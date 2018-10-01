package com.logvidmi.prototypepuzzle.model;

import android.graphics.Bitmap;

/**
 * Represents single tile in a puzzle. A tile has its index in a correctly
 * assembled puzzle.
 */
public class Tile {

    private int index;

    private Bitmap tileImage;

    private static int tileWidth;

    private static int tileHeight;

    /**
     * @param tileImage A bitmap image of a particular tile.
     * @param index Index of a tile in a correctly assembled puzzle.
     */
    public Tile(Bitmap tileImage, int index) {
        this.tileImage = tileImage;
        this.index = index;
    }

    public Bitmap getTileImage() {
        return tileImage;
    }

    public int getIndex() {
        return index;
    }
}

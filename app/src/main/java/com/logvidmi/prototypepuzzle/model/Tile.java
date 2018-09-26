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
    }

    public boolean isCorrectPosition(int index) {
        return this.index == index;
    }

    public Bitmap getTileImage() {
        return tileImage;
    }

    public static void setTileWidth(int tileWidth) {
        Tile.tileWidth = tileWidth;
    }

    public static void setTileHeight(int tileHeight) {
        Tile.tileHeight = tileHeight;
    }
    public static int getTileWidth() {
        return tileWidth;
    }

    public static int getTileHeight() {
        return tileHeight;
    }

    public int getIndex() {
        return index;
    }
}

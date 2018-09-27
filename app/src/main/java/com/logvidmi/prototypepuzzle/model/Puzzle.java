package com.logvidmi.prototypepuzzle.model;

import android.graphics.Bitmap;

import com.logvidmi.prototypepuzzle.services.ImageSplitter;
import com.logvidmi.prototypepuzzle.setup.ApplicationFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A puzzle, which consists of tiles. Each tile has an index. Tiles are randomized
 * so that the collection of indices is unordered. Puzzle is considered assembled, when
 * by swapping pairs of tiles one makes collection ordered.
 */
public class Puzzle {

    private final int rows;
    private final int columns;
    private final Bitmap image;
    private ArrayList<Integer> shuffledIndices;
    private ArrayList<Tile> solution;

    private ArrayList<Tile> randomizedTiles;

    /**
     * Puzzle is produced by cutting a bitmap image in a number of rectangular tiles and shuffling
     * them.
     *
     * @param image Bitmap image for puzzle.
     * @param rows Number of rows in the puzzle.
     * @param columns Numner of columns in the puzzle.
     */
    public Puzzle(Bitmap image, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.image = image;
        createNulledRandomizedTiles();
        this.solution = new ArrayList<>();
        this.shuffledIndices = new ArrayList<>();
        createShuffledArray();
        createPuzzleFromImage();
    }

    private void createNulledRandomizedTiles() {
        randomizedTiles = new ArrayList<>();
        for (int i = 0; i < getNrOfTiles(); i++) {
            randomizedTiles.add(null);
        }
    }

    private int getNrOfTiles() {
        return rows*columns;
    }

    /**
     * Creates puzzle from image.
     */
    private void createPuzzleFromImage() {
        ApplicationFactory factory = ApplicationFactory.getApplicationFactory();
        ImageSplitter splitter = factory.getImageSplitter();
        ArrayList<Bitmap> imagePieces = splitter.splitImage(image);
        for (int index = 0; index < getNrOfTiles(); index++) {
            Tile tile = new Tile(imagePieces.get(index), index);
            solution.add(index, tile);
            randomizedTiles.set(shuffledIndices.get(index), tile);
        }
    }

    /**
     * Swapping two tiles with different indices.
     * @param firstIndex
     * @param secondIndex
     */
    public void swapTiles(int firstIndex, int secondIndex) {
        Collections.swap(randomizedTiles, firstIndex, secondIndex);
    }

    private void createShuffledArray() {
        for (int index = 0; index < getNrOfTiles(); index++) {
            shuffledIndices.add(index);
        }
        Collections.shuffle(shuffledIndices);
    }

    /**
     * Puzzle is assembled when the collection of tile indices is ordered.
     *
     * @return Boolean indicating whether the puzzle is assembled.
     */
    public boolean isAssembled() {
        boolean isAssembled = true;
        for (int i = 0; i < randomizedTiles.size(); i++ ) {
            if (randomizedTiles.get(i).getIndex() != i ) {
                isAssembled = false;
            }
        }
        return isAssembled;
    }

    /**
     * @return A list with randomized tiles.
     */
    public ArrayList<Tile> getRandomizedTiles() {
        return randomizedTiles;
    }
}

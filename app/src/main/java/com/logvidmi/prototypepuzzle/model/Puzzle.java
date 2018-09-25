package com.logvidmi.prototypepuzzle.model;

import android.graphics.Bitmap;

import com.logvidmi.prototypepuzzle.services.ImageSplitter;
import com.logvidmi.prototypepuzzle.setup.ApplicationFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Puzzle {

    private final int rows;
    private final int columns;
    private final Bitmap image;
    private ArrayList<Integer> shuffledIndices;
    private ArrayList<Tile> solution;
    private ArrayList<Tile> placedTiles;

    private ArrayList<Tile> randomizedTiles;

    public Puzzle(Bitmap image, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.image = image;
        createNulledPlacedTiles();
        createNulledRandomizedTiles();
        this.solution = new ArrayList<>();
        this.shuffledIndices = new ArrayList<>();
        createShuffledArray();
        createPuzzleFromImage();
    }

    private void createNulledPlacedTiles() {
        placedTiles = new ArrayList<>();
        for (int i = 0; i < getNrOfTiles(); i++) {
            placedTiles.add(i, null);
        }
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

    private void createPuzzleFromImage() {
        ApplicationFactory factory = ApplicationFactory.getApplicationFactory();
        //factory.setRows(rows);
        //factory.setColumns(columns);
        ImageSplitter splitter = factory.getImageSplitter();
        ArrayList<Bitmap> imagePieces = splitter.splitImage(image);
        for (int index = 0; index < getNrOfTiles(); index++) {
            Tile tile = new Tile(imagePieces.get(index), index);
            solution.add(index, tile);

            randomizedTiles.set(shuffledIndices.get(index), tile);
        }
    }

    public void placeTile(int indexInRandomized, int positionIndex) throws IllegalStateException {
        Tile tile = randomizedTiles.get(indexInRandomized);
       // if (tile.isCorrectPosition(positionIndex)) {
            placedTiles.set(positionIndex, tile);
            randomizedTiles.set(indexInRandomized, null);
        //} else {
        //    throw new IllegalStateException("False position of a tile.");
        //}
    }

    private void createShuffledArray() {
        for (int index = 0; index < getNrOfTiles(); index++) {
            shuffledIndices.add(index);
        }
        Collections.shuffle(shuffledIndices);
    }

    public boolean isAssembled() {
        for (int index = 0; index < getNrOfTiles(); index++) {
            if (placedTiles.get(index) == null) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Tile> getRandomizedTiles() {
        return randomizedTiles;
    }
}

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

    private ArrayList<Tile> randomizedTiles;

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

    public void swapTiles(int firstIndex, int secondIndex) {
        Collections.swap(randomizedTiles, firstIndex, secondIndex);
    }

    private void createShuffledArray() {
        for (int index = 0; index < getNrOfTiles(); index++) {
            shuffledIndices.add(index);
        }
        Collections.shuffle(shuffledIndices);
    }

    public boolean isAssembled() {
        boolean isAssembled = true;
        for (int i = 0; i < randomizedTiles.size(); i++ ) {
            if (randomizedTiles.get(i).getIndex() != i ) {
                isAssembled = false;
            }
        }
        return isAssembled;
    }

    public ArrayList<Tile> getRandomizedTiles() {
        return randomizedTiles;
    }
}

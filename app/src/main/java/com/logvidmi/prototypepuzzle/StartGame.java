package com.logvidmi.prototypepuzzle;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import com.logvidmi.prototypepuzzle.adapters.PuzzleAdapter;
import com.logvidmi.prototypepuzzle.model.Puzzle;
import com.logvidmi.prototypepuzzle.model.Tile;
import com.logvidmi.prototypepuzzle.setup.ApplicationFactory;
import com.logvidmi.prototypepuzzle.utils.GestureDetectGridView;

import java.util.ArrayList;

/**
 * Activity where puzzle game is played.
 */
public class StartGame extends AppCompatActivity {

    private static GestureDetectGridView mGridView;

    private static final int COLUMNS = ApplicationFactory.getApplicationFactory().getColumns();
    private static final int ROWS = ApplicationFactory.getApplicationFactory().getRows();
    private static final int DIMENSIONS = COLUMNS * ROWS;

    private static int mColumnWidth, mColumnHeight;

    public static final String up = "up";
    public static final String down = "down";
    public static final String left = "left";
    public static final String right = "right";

    private static Puzzle puzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        init();
        setDimensions();
    }

    private void init() {
        mGridView = (GestureDetectGridView) findViewById(R.id.grid);
        mGridView.setNumColumns(COLUMNS);
        puzzle = ApplicationFactory.getApplicationFactory().getPuzzle(this);
    }

    private void setDimensions() {
        ViewTreeObserver vto = mGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mColumnWidth = puzzle.getBitmapWidth() / COLUMNS;
                mColumnHeight = puzzle.getBitmapHeight() / COLUMNS;
                display(getApplicationContext());
            }
        });
    }

    private  void display(Context context) {
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;

        for (Tile tile: puzzle.getRandomizedTiles()) {
            button = new Button(context);
            BitmapDrawable bitmap = new BitmapDrawable(getResources(), tile.getTileImage());
            button.setBackground(bitmap);
            buttons.add(button);
        }
        mGridView.setAdapter(new PuzzleAdapter(buttons, mColumnWidth, mColumnHeight));
    }

    private  void swap(Context context, int currentPosition, int swap) {
        puzzle.swapTiles(currentPosition, currentPosition + swap);
        display(context);
        if (isSolved())
            Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
    }

    public  void moveTiles(Context context, String direction, int position) {

        // Upper-left-corner tile
        if (position == 0) {

            if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-center tiles
        } else if (position > 0 && position < COLUMNS - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-right-corner tile
        } else if (position == COLUMNS - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Left-side tiles
        } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                position % COLUMNS == 0) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Right-side AND bottom-right-corner tiles
        } else if (position == COLUMNS * 2 - 1 || position == COLUMNS * 3 - 1) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) {

                // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                // right-corner tile.
                if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                        COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-left corner tile
        } else if (position == DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-center tiles
        } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Center tiles
        } else {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else swap(context, position, COLUMNS);
        }
    }

    private  boolean isSolved() {
        return puzzle.isAssembled();
    }
}
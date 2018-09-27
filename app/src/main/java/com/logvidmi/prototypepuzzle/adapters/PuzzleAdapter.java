package com.logvidmi.prototypepuzzle.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.logvidmi.prototypepuzzle.services.ImageSplitter;
import com.logvidmi.prototypepuzzle.setup.ApplicationFactory;

import java.util.ArrayList;

/**
 * Adapter to display Puzzle in a GridView.
 */
public class PuzzleAdapter extends BaseAdapter {
    private ArrayList<Button> mButtons = null;
    private int mColumnWidth, mColumnHeight;

    public PuzzleAdapter(ArrayList<Button> buttons, int columnWidth, int columnHeight) {
        mButtons = buttons;
        mColumnWidth = columnWidth;
        mColumnHeight = columnHeight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount() {
        return mButtons.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getItem(int position) {return (Object) mButtons.get(position);}

    /**
     * {@inheritDoc}
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;

        if (convertView == null) {
            button = mButtons.get(position);
        } else {
            button = (Button) convertView;
        }

        android.widget.AbsListView.LayoutParams params =
                new android.widget.AbsListView.LayoutParams(mColumnWidth, mColumnHeight);

        //android.widget.AbsListView.LayoutParams params =
        //        new android.widget.AbsListView.LayoutParams(ImageSplitter.getChunkWidth()/ApplicationFactory.getApplicationFactory().getRows(),
        //                ImageSplitter.getChunkHeight()/ ApplicationFactory.getApplicationFactory().getColumns());
        button.setLayoutParams(params);

        return button;
    }
}
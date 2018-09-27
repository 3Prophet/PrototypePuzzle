package com.logvidmi.prototypepuzzle.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.logvidmi.prototypepuzzle.ChooseImageForPuzzle;
import com.logvidmi.prototypepuzzle.fragments.ImageFragment;

import java.util.ArrayList;

public class EditImageAdapter extends FragmentPagerAdapter {

    private ArrayList<Bitmap> images;

    private long baseId = 0;

    public EditImageAdapter(FragmentManager fragmentManager, ArrayList<Bitmap> images) {
        super(fragmentManager);
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    /**
     * This method is called when notifyDataSetChanged() is called.
     */
    @Override
    public int getItemPosition(Object object) {
        // refresh all fragments when data set changed
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(images.get(position));
    }

    @Override
    public long getItemId(int position) {
        // Give an ID different from position when poition has been changed.
        return baseId + position;
    }

    /**
     * Notifies that the position of a fragment has been changed.
     * Creates a new ID for each position to force recreation of the fragment.
     *
     * @param n Number of items that had been changed.
     */
    public void notifyChangeInPosition(int n) {
        // Shift the ID returned by getItemId outside the range of all previous fragments.
        baseId += getCount() + n;
    }
}

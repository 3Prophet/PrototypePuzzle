package com.logvidmi.prototypepuzzle.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.logvidmi.prototypepuzzle.ChooseImageForPuzzle;

public class EditImageAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public EditImageAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    //this is called when notifyDataSetChanged() is called
    @Override
    public int getItemPosition(Object object) {
        // refresh all fragments when data set changed
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int i) {
        return null;
    }
}

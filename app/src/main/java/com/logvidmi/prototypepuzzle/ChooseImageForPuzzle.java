package com.logvidmi.prototypepuzzle;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.logvidmi.prototypepuzzle.adapters.ChooseImageForPuzzleAdapter;

public class ChooseImageForPuzzle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image_for_puzzle);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewImageForPuzzle);
        ChooseImageForPuzzleAdapter adapterView = new ChooseImageForPuzzleAdapter(this);
        mViewPager.setAdapter(adapterView);
    }
}

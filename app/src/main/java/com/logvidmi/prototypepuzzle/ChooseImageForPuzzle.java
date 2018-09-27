package com.logvidmi.prototypepuzzle;

import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.logvidmi.prototypepuzzle.adapters.ChooseImageForPuzzleAdapter;
import com.logvidmi.prototypepuzzle.services.DatabaseHandler;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Activity, where photos for playing Puzzle game can be selected.
 */
public class ChooseImageForPuzzle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image_for_puzzle);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewImageForPuzzle);
        ChooseImageForPuzzleAdapter adapterView = null;
        try {
            adapterView = new ChooseImageForPuzzleAdapter(this);
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Can't Retrieve images from database!",
                    Toast.LENGTH_SHORT).show();
        }
        mViewPager.setAdapter(adapterView);
    }
}

package com.logvidmi.prototypepuzzle;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.logvidmi.prototypepuzzle.adapters.ChooseImageForPuzzleAdapter;
import com.logvidmi.prototypepuzzle.adapters.EditImageAdapter;
import com.logvidmi.prototypepuzzle.setup.ApplicationFactory;

/**
 * Activity where photos can be selected and edited.
 */
public class EditPhotosActiviy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photos_activiy);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.edit_photos_pager);
        EditImageAdapter adapterView = new EditImageAdapter(this);
        mViewPager.setAdapter(adapterView);
    }

    private void setControllers() {
        Button easyButton = (Button) findViewById(R.id.delete_image_button);
            easyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

    }
}

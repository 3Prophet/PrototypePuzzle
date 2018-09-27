package com.logvidmi.prototypepuzzle;

import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.logvidmi.prototypepuzzle.adapters.EditImageAdapter;
import com.logvidmi.prototypepuzzle.services.DatabaseHandler;

import java.util.ArrayList;

/**
 * Activity where photos can be selected and edited.
 */
public class EditPhotosActiviy extends FragmentActivity {

    private ArrayList<Bitmap> images;

    EditImageAdapter editImageAdapter;

    ViewPager imageViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photos_activiy);

        DatabaseHandler dbHandler = new DatabaseHandler(this);
        this.images = dbHandler.getImagesFromDatabase();

        this.imageViewPager = (ViewPager) findViewById(R.id.edit_photos_pager);
        this.editImageAdapter = new EditImageAdapter(this.getSupportFragmentManager(),
                images);
        imageViewPager.setAdapter(editImageAdapter);
        setControllers();
    }

    private void setControllers() {
        Button deleteButton = (Button) findViewById(R.id.delete_image_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = imageViewPager.getCurrentItem();
                    images.remove(position);
                    editImageAdapter.notifyChangeInPosition(1);
                    editImageAdapter.notifyDataSetChanged();
                }
            });

    }
}
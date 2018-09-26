package com.logvidmi.prototypepuzzle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.logvidmi.prototypepuzzle.services.DatabaseHandler;


public class SelectPhotosSource extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private DatabaseHandler dbHandler;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_photos_source);
        dbHandler = new DatabaseHandler(this);
    }

    /**
     * Calling intent to take photo using available camera application.
     *
     * @param view
     */
    public void takePhoto(View view) {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(pictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * Retrieving image from the camera application and storing it in the application database.
     * {@inheritDoc}
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (data.getExtras().get("data") != null && data.getExtras().get("data") instanceof Bitmap) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                dbHandler = new DatabaseHandler(this);
                if (dbHandler.insertImage(bitmap)) {
                    Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Not Successfull", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

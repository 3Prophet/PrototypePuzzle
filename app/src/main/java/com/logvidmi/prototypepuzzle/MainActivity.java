package com.logvidmi.prototypepuzzle;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.logvidmi.prototypepuzzle.services.DatabaseHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Main activity of the application, where user can choose between
 * starting the game, adding new photos and editing existing photos.
 */
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PICK_PHOTO_CODE = 1034;

    private DatabaseHandler dbHandler;
    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setControllers();
    }

    private void setControllers() {

        Button playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playGameIntent = new Intent(MainActivity.this, ChooseImageForPuzzle.class);
                startActivity(playGameIntent);
            }
        });

        Button addPhotosButton = (Button) findViewById(R.id.add_photos_button);
        addPhotosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                }else{
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                }
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_PHOTO_CODE);

            }
        });

        Button editButton = (Button) findViewById(R.id.edit_existing_photos_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editPhotosIntent = new Intent(MainActivity.this, EditPhotosActiviy.class);
                startActivity(editPhotosIntent);
            }
        });
    }

    /**
     * Retrieving uri of the photo for the puzzle and storing it in a database.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            Uri photoUri = data.getData();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                final int takeFlags = data.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION;
                ContentResolver resolver = getContentResolver();
                resolver.takePersistableUriPermission(photoUri, takeFlags);
            }
            // Do something with the photo based on Uri
            // Load the selected image into a preview
            dbHandler = new DatabaseHandler(this);
            dbHandler.insertImage(photoUri);
            Toast.makeText(getApplicationContext(), "Successfull.", Toast.LENGTH_SHORT).show();
        }
        else  {
            Toast.makeText(this, "Image is not saved.", Toast.LENGTH_SHORT).show();
        }

    }

}

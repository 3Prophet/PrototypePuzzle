package com.logvidmi.prototypepuzzle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.logvidmi.prototypepuzzle.services.ImageSplitter;
import com.logvidmi.prototypepuzzle.setup.ApplicationFactory;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setActionListeners();
        /**
       ImageView view = (ImageView) findViewById(R.id.puzzle_view);
       view.setImageResource(R.drawable.blue_rose);


        //Getting the scaled bitmap of the source image
        BitmapDrawable drawable = (BitmapDrawable) view.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

       ApplicationFactory factory = ApplicationFactory.getApplicationFactory();
       factory.setColumns(3);
       factory.setRows(2);
       ImageSplitter splitter = factory.getImageSplitter();
       ArrayList<Bitmap> chunks =  (ArrayList<Bitmap>) splitter.splitImage(bitmap);
       view.setImageBitmap(chunks.get(0));
         */
    }

    private void setActionListeners() {
        Button easyButton = (Button) findViewById(R.id.add_photo);
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (data.getExtras().get("data") != null &&
                    data.getExtras().get("data") instanceof Bitmap) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void playGame(View view) {
        Intent playGameIntent = new Intent(this, ChoseLevel.class);
        startActivity(playGameIntent);
    }

    public void addPhotos(View view) {
        Intent addPhotosIntent = new Intent(this, SelectPhotosSource.class);
        startActivity(addPhotosIntent);
    }
}

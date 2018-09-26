package com.logvidmi.prototypepuzzle.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.logvidmi.prototypepuzzle.StartGame;
import com.logvidmi.prototypepuzzle.services.DatabaseHandler;
import com.logvidmi.prototypepuzzle.setup.ApplicationFactory;

import java.util.ArrayList;

/**
 * An Adapter with swipe functionality.
 * It is used to scrolling through images and starting starting Puzzle game on
 * the selected one.
 */
public class ChooseImageForPuzzleAdapter extends PagerAdapter {

    private Context context;

    /**
     * Array with bitmaps for images to be swiped through using adapter.
     */
    private ArrayList<Bitmap> imageBitmaps;

    /**
     * @param context Activity from which adapter is called.
     */
    public ChooseImageForPuzzleAdapter(Context context) {
        this.context = context;
        DatabaseHandler dbHandler = new DatabaseHandler(context);
        imageBitmaps = dbHandler.getImagesFromDatabase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount() {
        return imageBitmaps.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    /**
     * Instantinates image view for a bitmap and attaches a click listener to start
     * a Puzzle game from the selected image.
     * {@inheritDoc}
     */
    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        final ImageView mImageView = new ImageView(context);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView.setImageBitmap(imageBitmaps.get(i));
        mImageView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Setting image to be used in a Puzzle Game.
                        BitmapDrawable drawable = (BitmapDrawable) mImageView.getDrawable();
                        Bitmap bitmap = drawable.getBitmap();
                        ApplicationFactory.getApplicationFactory().setBitmapForPuzzleGame(bitmap);

                        // Starting Activity with Puzzle Game
                        Intent playGameIntent = new Intent(context, StartGame.class);
                        context.startActivity(playGameIntent);
                    }
                }
        );
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }

}


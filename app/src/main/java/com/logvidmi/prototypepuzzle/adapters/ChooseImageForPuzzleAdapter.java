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
import com.logvidmi.prototypepuzzle.setup.ApplicationFactory;

public class ChooseImageForPuzzleAdapter extends PagerAdapter {

    Context mContext;

    public ChooseImageForPuzzleAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return sliderImagesId.length;
    }

    private int[] sliderImagesId = ApplicationFactory.getApplicationFactory().getPuzzleImages();

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        final ImageView mImageView = new ImageView(mContext);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView.setImageResource(sliderImagesId[i]);
        mImageView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Setting image to be used in a Puzzle Game.
                        BitmapDrawable drawable = (BitmapDrawable) mImageView.getDrawable();
                        Bitmap bitmap = drawable.getBitmap();
                        ApplicationFactory.getApplicationFactory().setBitmapForPuzzleGame(bitmap);

                        // Starting Activity with Puzzle Game
                        Intent playGameIntent = new Intent(mContext, StartGame.class);
                        mContext.startActivity(playGameIntent);
                    }
                }
        );
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }

}


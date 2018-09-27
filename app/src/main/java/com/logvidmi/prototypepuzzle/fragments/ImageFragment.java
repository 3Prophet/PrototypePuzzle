package com.logvidmi.prototypepuzzle.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.logvidmi.prototypepuzzle.R;

/**
 * A Fragment for the bitmap image.
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageFragment extends Fragment {

    /**
     * The fragment initialization parameter.
     */
    private static final String BITMAP_PARAM = "bitmap";

    private Bitmap bitmap;

    public ImageFragment() { }


    /**
     * Factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param image
     * @return A new instance of fragment ImageFragment.
     */
    public static ImageFragment newInstance(Bitmap image) {
        ImageFragment fragment = new ImageFragment();
        fragment.bitmap = image;
        return fragment;
    }

    /**
     * Inflating layout fot the corresponding fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);
        Bitmap bitmap = this.bitmap;
        ImageView imageView = (ImageView) rootView.findViewById(R.id.image_view_from_fragment);
        imageView.setImageBitmap(bitmap);
        return rootView;
    }
}

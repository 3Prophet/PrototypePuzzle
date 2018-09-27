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
    // TODO: Rename and change types and number of parameters
    public static ImageFragment newInstance(Bitmap image) {
        ImageFragment fragment = new ImageFragment();
        fragment.bitmap = image;
        //Bundle args = new Bundle();
        //args.putParcelable(BITMAP_PARAM, image);
        //fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);
        Bitmap bitmap = this.bitmap;
        //Bitmap bitmap = getActivity().getIntent().getExtras().getParcelable(BITMAP_PARAM);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.image_view_from_fragment);
        imageView.setImageBitmap(bitmap);
        return rootView;
    }



}

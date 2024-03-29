/**
 * NIM  : 10118402
 * Nama : Yolanda Patricia
 * Kelas : IF-10
 * Tanggal Pengerjaan   : 8 Agustus 2021
 */

package com.example.uas_akb_10118402.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.uas_akb_10118402.R;

public class MainSlidePresenter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    private static int SplashTimeOut = 3000;

    public MainSlidePresenter (Context context) {this.context = context;}

    public int[] slide_images = {
            R.drawable.dago_bakery,
            R.drawable.tangkuban,
            R.drawable.kawahputih
    };

    //Array for heading
    public String[] slide_headings = {
            " ",
            " ",
            " "
    };

    @Override
    public int getCount() {
        return slide_images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.main_slide, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}

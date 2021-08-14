/**
 * NIM  : 10118402
 * Nama : Yolanda Patricia
 * Kelas : IF-10
 * Tanggal Pengerjaan   : 6 Agustus 2021
 */

package com.example.uas_akb_10118402.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.uas_akb_10118402.activities.SlideActivity;
import com.example.uas_akb_10118402.activities.SplashActivity;

public class SplashPresenter {
    Context context;

    public SplashPresenter(SplashActivity splashActivity) {
    }

    Intent getSlide = new Intent();

    public Intent setSlide() {
        Intent intent = new Intent(context, SlideActivity.class);
        return intent;
    }
}

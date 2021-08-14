/**
 * NIM  : 10118402
 * Nama : Yolanda Patricia
 * Kelas : IF-10
 * Tanggal Pengerjaan   : 9 Agustus 2021
 */

package com.example.uas_akb_10118402.layout;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface OnClickLayoutMarginItemListener {
    void onClick(Context context, View v, int position, int spanIndex, RecyclerView.State state);
}

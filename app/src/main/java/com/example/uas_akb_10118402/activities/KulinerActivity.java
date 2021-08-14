/**
 * NIM  : 10118402
 * Nama : Yolanda Patricia
 * Kelas : IF-10
 * Tanggal Pengerjaan   : 11 Agustus 2021
 */

package com.example.uas_akb_10118402.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.uas_akb_10118402.R;
import com.example.uas_akb_10118402.Tools;
import com.example.uas_akb_10118402.adapter.KulinerAdapter;
import com.example.uas_akb_10118402.layout.LayoutMarginDecoration;
import com.example.uas_akb_10118402.model.ModelKuliner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class KulinerActivity extends AppCompatActivity implements KulinerAdapter.onSelectData {

    RecyclerView rvWisata;
    KulinerAdapter kulinerAdapter;
    LayoutMarginDecoration gridMargin;
    List<ModelKuliner> lsKuliner = new ArrayList<>();
    DatabaseReference reference;
    Toolbar tbWisata;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuliner);

        tbWisata = findViewById(R.id.toolbar_wisata);
        tbWisata.setTitle("Daftar Wisata Kuliner");
        setSupportActionBar(tbWisata);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data...");

        rvWisata = findViewById(R.id.wisataList);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this,
                2, RecyclerView.VERTICAL, false);
        rvWisata.setLayoutManager(mLayoutManager);
        gridMargin = new LayoutMarginDecoration(2, Tools.dp2px(this, 4));
        rvWisata.addItemDecoration(gridMargin);
        rvWisata.setHasFixedSize(true);

        reference = FirebaseDatabase.getInstance("https://uas-akb-10118402-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("kuliner");

        kulinerAdapter = new KulinerAdapter(this, lsKuliner, this);
        rvWisata.setAdapter(kulinerAdapter);

        progressDialog.show();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelKuliner modelKuliner = dataSnapshot.getValue(ModelKuliner.class);
                    lsKuliner.add(modelKuliner);
                }
                kulinerAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onSelected(ModelKuliner modelKuliner) {
        Intent intent = new Intent(KulinerActivity.this, DetailKulinerActivity.class);
        intent.putExtra("detailData", modelKuliner);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
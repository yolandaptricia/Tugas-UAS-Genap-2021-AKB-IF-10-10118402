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
import com.example.uas_akb_10118402.adapter.RekreasiAdapter;
import com.example.uas_akb_10118402.layout.LayoutMarginDecoration;
import com.example.uas_akb_10118402.model.ModelRekreasi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RekreasiActivity extends AppCompatActivity implements RekreasiAdapter.onSelectData {

    RecyclerView rvWisata;
    RekreasiAdapter rekreasiAdapter;
    LayoutMarginDecoration gridMargin;
    List<ModelRekreasi> lsRekreasi = new ArrayList<>();
    DatabaseReference reference;
    Toolbar tbWisata;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekreasi);

        tbWisata = findViewById(R.id.toolbar_wisata);
        tbWisata.setTitle("Daftar Wisata Rekreasi");
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

        reference = FirebaseDatabase.getInstance("https://uas-akb-10118402-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Rekreasi");

        rekreasiAdapter = new RekreasiAdapter(this, lsRekreasi, this);
        rvWisata.setAdapter(rekreasiAdapter);

        progressDialog.show();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelRekreasi modelRekreasi = dataSnapshot.getValue(ModelRekreasi.class);
                    lsRekreasi.add(modelRekreasi);
                }
                rekreasiAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onSelected(ModelRekreasi modelRekreasi) {
        Intent intent = new Intent(RekreasiActivity.this, DetailRekreasiActivity.class);
        intent.putExtra("detailData", modelRekreasi);
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
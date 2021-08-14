/**
 * NIM  : 10118402
 * Nama : Yolanda Patricia
 * Kelas : IF-10
 * Tanggal Pengerjaan   : 13 Agustus 2021
 */

package com.example.uas_akb_10118402.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uas_akb_10118402.R;
import com.example.uas_akb_10118402.model.ModelKuliner;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class DetailKulinerActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView tvNama, tvDesc, tvAlamat, tvJam;
    ImageView imgWisata;
    Toolbar tbWisata;
    ModelKuliner modelKuliner = null;

    public MapView mapView;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kuliner);

        tbWisata = findViewById(R.id.toolbar_wisata);
        tbWisata.setTitle("Detail Wisata Budaya dan Sejarah");
        setSupportActionBar(tbWisata);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object object = getIntent().getSerializableExtra("detailData");

        if (object instanceof ModelKuliner) {
            modelKuliner = (ModelKuliner) object;
        }

        imgWisata = findViewById(R.id.imgWisata);
        tvNama = findViewById(R.id.tvNama);
        tvDesc = findViewById(R.id.tvDeskripsi);
        tvAlamat = findViewById(R.id.tvAlamat);
        tvJam = findViewById(R.id.tvJam);
        mapView = findViewById(R.id.mapView);

        if (modelKuliner != null) {
            tbWisata.setTitle(modelKuliner.getNama());
            Picasso.get()
                    .load(modelKuliner.getImage())
                    .into(imgWisata);
            tvNama.setText(modelKuliner.getNama());
            tvDesc.setText(modelKuliner.getDesc());
            tvAlamat.setText(modelKuliner.getAlamat());
            tvJam.setText(modelKuliner.getJam());

            mapView.onCreate(null);
            mapView.getMapAsync(this);

        } else {
            tvNama.setText("Gaada datanyaa");
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setMapToolbarEnabled(false);
        LatLng newLocation = new LatLng(modelKuliner.getLatitude(), modelKuliner.getLongitude());
        mMap.addMarker(new MarkerOptions().position(newLocation).title(modelKuliner.getNama()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newLocation));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 15));

        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.setTrafficEnabled(true);
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
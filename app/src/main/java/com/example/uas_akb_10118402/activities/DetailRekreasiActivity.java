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
import com.example.uas_akb_10118402.model.ModelBudaya;
import com.example.uas_akb_10118402.model.ModelRekreasi;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class DetailRekreasiActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView tvNama, tvDesc, tvAlamat, tvJam;
    ImageView imgWisata;
    Toolbar tbWisata;
    ModelRekreasi modelRekreasi = null;

    public MapView mapView;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rekreasi);

        tbWisata = findViewById(R.id.toolbar_wisata);
        tbWisata.setTitle("Detail Wisata Budaya dan Sejarah");
        setSupportActionBar(tbWisata);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object object = getIntent().getSerializableExtra("detailData");

        if (object instanceof ModelRekreasi) {
            modelRekreasi = (ModelRekreasi) object;
        }

        imgWisata = findViewById(R.id.imgWisata);
        tvNama = findViewById(R.id.tvNama);
        tvDesc = findViewById(R.id.tvDeskripsi);
        tvAlamat = findViewById(R.id.tvAlamat);
        tvJam = findViewById(R.id.tvJam);
        mapView = findViewById(R.id.mapView);

        if (modelRekreasi != null) {
            tbWisata.setTitle(modelRekreasi.getNama());
            Picasso.get()
                    .load(modelRekreasi.getImage())
                    .into(imgWisata);
            tvNama.setText(modelRekreasi.getNama());
            tvDesc.setText(modelRekreasi.getDesc());
            tvAlamat.setText(modelRekreasi.getAlamat());
            tvJam.setText(modelRekreasi.getJam());

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
        LatLng newLocation = new LatLng(modelRekreasi.getLatitude(), modelRekreasi.getLongitude());
        mMap.addMarker(new MarkerOptions().position(newLocation).title(modelRekreasi.getNama()));
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
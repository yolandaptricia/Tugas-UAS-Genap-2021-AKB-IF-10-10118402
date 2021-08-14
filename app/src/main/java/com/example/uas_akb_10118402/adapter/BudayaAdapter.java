/**
 * NIM  : 10118402
 * Nama : Yolanda Patricia
 * Kelas : IF-10
 * Tanggal Pengerjaan   : 11 Agustus 2021
 */

package com.example.uas_akb_10118402.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_akb_10118402.R;
import com.example.uas_akb_10118402.model.ModelBudaya;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BudayaAdapter extends RecyclerView.Adapter<BudayaAdapter.ViewHolder> {
    List<ModelBudaya> items;
    BudayaAdapter.onSelectData onSelectData;
    Context mContext;

    public interface onSelectData {
        void onSelected(ModelBudaya modelBudaya);
    }

    public BudayaAdapter(Context context, List<ModelBudaya> items, BudayaAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ModelBudaya data = items.get(position);

        //Get Image
        Picasso.get()
                .load(data.getImage())
                .into(holder.imgBudaya);
        holder.nama.setText(data.getNama());
        holder.kota.setText(data.getKota());
        holder.jam_operasional.setText(data.getJam());
        holder.cvWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView kota;
        TextView jam_operasional;
        TextView nama;
        CardView cvWisata;
        ImageView imgBudaya;

        public ViewHolder(View itemView) {
            super(itemView);

            cvWisata = (CardView) itemView.findViewById(R.id.cvWisata);
            imgBudaya = itemView.findViewById(R.id.imgWisata);
            nama = itemView.findViewById(R.id.tvNama);
            kota = itemView.findViewById(R.id.tvKota);
            jam_operasional = itemView.findViewById(R.id.tvJam);
        }
    }
}

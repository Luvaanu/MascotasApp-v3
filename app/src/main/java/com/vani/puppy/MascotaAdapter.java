package com.vani.puppy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    private ArrayList<Mascota> mascotas;

    public MascotaAdapter(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mascota, parent, false);
        return new MascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MascotaViewHolder holder, int position) {
        Mascota mascota = mascotas.get(position);

        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvNombre.setText(mascota.getNombre());
        holder.tvRating.setText(String.valueOf(mascota.getRating()));

        // Click en hueso blanco → suma rating
        holder.imgHuesoBlanco.setOnClickListener(v -> {
            int nuevoRating = mascota.getRating() + 1;
            mascota.setRating(nuevoRating);
            holder.tvRating.setText(String.valueOf(nuevoRating));

            ConstructorMascotas constructor = new ConstructorMascotas(v.getContext());
            constructor.guardarMascotaConRating(mascota);
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        ImageView imgFoto;
        ImageView imgHuesoBlanco;
        ImageView imgHuesoAmarillo;
        TextView tvNombre;
        TextView tvRating;

        public MascotaViewHolder(View itemView) {
            super(itemView);

            imgFoto = itemView.findViewById(R.id.imgFoto);
            imgHuesoBlanco = itemView.findViewById(R.id.imgHuesoBlanco);
            imgHuesoAmarillo = itemView.findViewById(R.id.imgHuesoAmarillo);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvRating = itemView.findViewById(R.id.tvRating);
        }
    }
}
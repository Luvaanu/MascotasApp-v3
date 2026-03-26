package com.vani.puppy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.vani.puppy.ConstructorMascotas;
import com.vani.puppy.FotoMascota;
import com.vani.puppy.FotoMascotaAdapter;
import com.vani.puppy.Mascota;
import com.vani.puppy.R;

import java.util.ArrayList;

public class PerfilMascotaFragment extends Fragment {

    private ShapeableImageView imgPerfil;
    private TextView tvNombrePerfil;
    private RecyclerView rvPerfil;
    private ArrayList<FotoMascota> fotos;
    private FotoMascotaAdapter adapter;

    public PerfilMascotaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_perfil_mascota, container, false);

        imgPerfil = v.findViewById(R.id.imgPerfil);
        tvNombrePerfil = v.findViewById(R.id.tvNombrePerfil);
        rvPerfil = v.findViewById(R.id.rvPerfil);

        rvPerfil.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        fotos = new ArrayList<>();
        adapter = new FotoMascotaAdapter(fotos);
        rvPerfil.setAdapter(adapter);

        cargarPerfilYFotos();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        cargarPerfilYFotos();
    }

    public void cargarPerfilYFotos() {
        if (getActivity() == null) return;

        fotos.clear();

        ConstructorMascotas constructor = new ConstructorMascotas(getActivity());
        ArrayList<Mascota> mascotasDB = constructor.obtenerTopMascotas();

        if (!mascotasDB.isEmpty()) {
            Mascota mascotaTop = mascotasDB.get(0);
            imgPerfil.setImageResource(mascotaTop.getFoto());
            tvNombrePerfil.setText(mascotaTop.getNombre());

            for (Mascota mascota : mascotasDB) {
                fotos.add(new FotoMascota(mascota.getFoto(), mascota.getRating()));
            }
        } else {
            imgPerfil.setImageResource(R.drawable.mascota6);
            tvNombrePerfil.setText("Ronny");
        }

        adapter.notifyDataSetChanged();
    }
}
package com.vani.puppy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vani.puppy.ConstructorMascotas;
import com.vani.puppy.Mascota;
import com.vani.puppy.MascotaAdapter;
import com.vani.puppy.R;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {

    private RecyclerView rvMascotas;
    private ArrayList<Mascota> mascotas;
    private MascotaAdapter adapter;

    public RecyclerViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        rvMascotas = v.findViewById(R.id.rvMascotas);
        rvMascotas.setLayoutManager(new LinearLayoutManager(getActivity()));

        mascotas = new ArrayList<>();
        adapter = new MascotaAdapter(mascotas);
        rvMascotas.setAdapter(adapter);

        cargarMascotas();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        cargarMascotas();
    }

    private void cargarMascotas() {
        if (getActivity() == null) return;

        ConstructorMascotas constructor = new ConstructorMascotas(getActivity());
        constructor.insertarMascotasIniciales();

        mascotas.clear();
        mascotas.addAll(constructor.obtenerMascotas());

        adapter.notifyDataSetChanged();
    }
}
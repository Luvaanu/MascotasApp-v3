package com.vani.puppy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;

import com.vani.puppy.adapter.PageAdapter;
import com.vani.puppy.fragments.RecyclerViewFragment;
import com.vani.puppy.fragments.PerfilMascotaFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecyclerViewFragment recyclerViewFragment;
    private PerfilMascotaFragment perfilMascotaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        setUpViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    // 🔹 ViewPager + Fragments
    private void setUpViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        recyclerViewFragment = new RecyclerViewFragment();
        perfilMascotaFragment = new PerfilMascotaFragment();

        fragments.add(recyclerViewFragment);
        fragments.add(perfilMascotaFragment);

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Inicio");
        tabLayout.getTabAt(1).setText("Perfil");

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                if (position == 1 && perfilMascotaFragment != null) {
                    perfilMascotaFragment.cargarPerfilYFotos();
                }
            }
        });
    }

    // 🔹 Acciones del menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_contacto) {
            Intent intent = new Intent(this, ContactoActivity.class);
            startActivity(intent);
            return true;
        }

        if (item.getItemId() == R.id.menu_acerca) {
            Intent intent = new Intent(this, AcercaDeActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
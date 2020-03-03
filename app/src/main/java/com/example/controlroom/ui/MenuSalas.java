package com.example.controlroom.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.controlroom.R;
import com.example.model.SalaModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MenuSalas extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
/*

    private List<SalaModel> salas;
    private Context context;

    public MenuSalas(List<SalaModel> listaSalas, Context context) {
        this.salas = listaSalas;
        this.context = context;
    }
*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_salas);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Salas()).commit();

        }


        }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_salas:
                            selectedFragment = new Salas();
                            break;
                        case R.id.nav_perfil:
                            selectedFragment = new Perfil();
                            break;
                         case R.id.nav_calendario:
                            selectedFragment = new Calendario();
                            break;
                        case R.id.nav_reservas:
                            selectedFragment = new MinhasReservas();
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selectedFragment).commit();

                    return true;
                }
            };
}



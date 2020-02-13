package com.example.controlroom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import com.example.controlroom.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;


public class DescricaoSala extends AppCompatActivity {

    private TextView id_capacidade;
    private TextView id_tamanho;
    private TextView id_multimidia;
    private TextView id_arcondicionado;
    private SharedPreferences preferences;
    public static final String userPreferences = "userPreferences";
    private ImageButton return_button4;
    private ImageButton btn_calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descricao_sala);

        id_capacidade = (TextView) findViewById(R.id.id_titulocapacidade);
        id_tamanho = (TextView) findViewById(R.id.id_titulotamanho);
        id_multimidia = (TextView) findViewById(R.id.id_titulo_multimidia);
        id_arcondicionado = (TextView) findViewById(R.id.id_titulo_arcond);
        return_button4 = findViewById(R.id.return_button4);
        btn_calendario = findViewById(R.id.btn_calendario);


        inserirDados();
        telaCalendario();

        return_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClass(MenuSalas.class);
            }
        });
    }

    public void inserirDados() {

        preferences = getSharedPreferences(userPreferences, Context.MODE_PRIVATE);


    }

    private void startClass(Class classe) {
        Intent intent = new Intent(this, classe);
        startActivity(intent);
        this.finish();

    }


    private void telaCalendario() {
        btn_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClass(Calendario.class);

            }

        });

    }
}




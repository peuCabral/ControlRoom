package com.example.controlroom.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controlroom.R;

public class DescricaoSala extends AppCompatActivity {

    private TextView id_capacidade;
    private TextView id_tamanho;
    private TextView id_multimidia;
    private TextView id_arcondicionado;
    private SharedPreferences preferences;
    public static final String userPreferences = "userPreferences";
    private ImageButton return_button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descricao_sala);

        id_capacidade = (TextView) findViewById(R.id.id_titulocapacidade);
        id_tamanho = (TextView) findViewById(R.id.id_titulotamanho);
        id_multimidia = (TextView) findViewById(R.id.id_titulo_multimidia);
        id_arcondicionado = (TextView) findViewById(R.id.id_titulo_arcond);


        inserirDados();

    }
    public void inserirDados() {

        preferences = getSharedPreferences(userPreferences, Context.MODE_PRIVATE);


    }


}

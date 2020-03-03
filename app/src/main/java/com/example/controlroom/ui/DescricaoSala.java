package com.example.controlroom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import com.example.controlroom.R;
import com.example.model.SalaModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;


public class DescricaoSala extends AppCompatActivity {

    private TextView id_capacidade;
    private TextView id_tamanho;
    private TextView id_multimidia;
    private TextView id_arcondicionado;
    private TextView id_nome_sala;
    private SharedPreferences preferences;
    public static final String userPreferences = "userPreferences";
    private ImageButton btn_reserva;
    private ImageButton btn_calendario;
    SalaModel salaSelecionada = new SalaModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descricao_sala);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        id_capacidade = (TextView) findViewById(R.id.id_capacidade);
        id_tamanho = (TextView) findViewById(R.id.id_tamanho);
        id_multimidia = (TextView) findViewById(R.id.id_multimidia);
        id_arcondicionado = (TextView) findViewById(R.id.id_arcondicionado);
        btn_calendario = findViewById(R.id.btn_calendario);
        id_nome_sala = findViewById(R.id.id_nome_sala);
        btn_reserva = findViewById(R.id.btn_reserva);

        Intent intent = getIntent();
        salaSelecionada = (SalaModel) intent.getSerializableExtra("salaSelecionada");

        id_nome_sala.setText(salaSelecionada.getNomeSala());
        id_capacidade.setText(salaSelecionada.getCapacidade());
        id_tamanho.setText(salaSelecionada.getTamanhoSala());

        String recebeAr = salaSelecionada.getMultimidia();
        String recebeMulti = salaSelecionada.getArCondicionado();

        if (recebeAr.equals("true")) {
            id_arcondicionado.setText("Sim");
        } else if (recebeAr.equals("false")) {
            id_arcondicionado.setText("Não");

        }

        if (recebeMulti.equals("true")) {
            id_multimidia.setText("Sim");
        } else if (recebeMulti.equals("false")) {
            id_multimidia.setText("Não");

        }


        inserirDados();


        btn_reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClass(Reservador.class);
            }
        });
    }

    public void inserirDados() {


        preferences = getSharedPreferences(userPreferences, Context.MODE_PRIVATE);


    }

    private void startClass(Class classe) {
        Intent intent = new Intent(this, classe);
        intent.putExtra("salaSelecionada", salaSelecionada);
        startActivity(intent);
        this.finish();

    }



    public boolean onSupportNavigateUp(){

        onBackPressed();

        return  true;
    }

    }



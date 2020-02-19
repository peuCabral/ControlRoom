package com.example.controlroom.ui;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ImageButton;
import android.view.View;


import com.example.controlroom.R;
import android.os.Bundle;

//
//import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Date;
import java.util.TimeZone;
//import android.widget.ImageView;

//import com.example.controlroom.R;
//import java.util.ArrayList;
//import java.util.List;
//
public class Reservador extends AppCompatActivity {

  private TextView id_nome_reservador;
  private Date id_data_reserva;
  private TimeZone id_hora_inicio;
  private TextView id_hora_final;
  private ImageButton btn_reserva_confirmada;
  private ImageButton btn_return_descricao;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.reserva);

    iniciaComponentes();
  }

  private void iniciaComponentes() {

      id_nome_reservador = (TextView) findViewById(R.id.id_nome_reservador);
    //  id_data_reserva = (Date) findViewById(R.id.id_data_reserva);
     // id_hora_inicio = (TimeZone) findViewById(R.id.id_hora_inicio);

  }
}
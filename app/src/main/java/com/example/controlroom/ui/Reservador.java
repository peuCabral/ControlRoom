package com.example.controlroom.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.controlroom.R;
import com.example.services.VerificadorReserva;

//
//import android.graphics.drawable.Drawable;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
//import android.widget.ImageView;

//import com.example.controlroom.R;
//import java.util.ArrayList;
//import java.util.List;
//
public class Reservador extends AppCompatActivity {

    private EditText id_nome_reservador;
    private TextView id_data_reserva;
    private TextView id_hora_inicio;
    private TextView id_hora_final;
    private SharedPreferences preferences;
    public static final String userPreferences = "userPreferences";
    private ImageButton btn_reserva_confirmada;
    private ImageButton btn_return_descricao;
    private TimePickerDialog timePicker;
    private DatePickerDialog datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserva);

        iniciaComponentes();
    }

    private void iniciaComponentes() {

        id_nome_reservador = (EditText) findViewById(R.id.id_descricao_reserva);
        id_data_reserva = (TextView) findViewById(R.id.id_data_reserva);
        id_hora_inicio = (TextView) findViewById(R.id.id_hora_inicio);
        id_hora_final = (TextView) findViewById(R.id.id_hora_final);
        btn_reserva_confirmada = (ImageButton) findViewById(R.id.btn_reserva_confirmada);

        escolherHorario();
        reservaData();

        salvarReserva();


    }

    private void escolherHorario() {
        id_hora_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                timePicker = new TimePickerDialog(Reservador.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                id_hora_inicio.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                timePicker.show();
            }
        });

        id_hora_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                timePicker = new TimePickerDialog(Reservador.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                id_hora_final.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                timePicker.show();
            }
        });

    }
    private void reservaData() {

        id_data_reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                // date picker dialog
                datePicker = new DatePickerDialog(Reservador.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String monthStr = String.valueOf(monthOfYear + 1);
                                if (monthStr.length() < 2) {
                                    monthStr = "0" + monthStr;
                                }

                                id_data_reserva.setText(dayOfMonth + "/" + (monthStr) + "/" + year);
                            }
                        }, year, month, day);
                datePicker.show();
                Toast.makeText(getApplication(), "Selected Date: " + id_data_reserva.getText(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private  void salvarReserva(){
        btn_reserva_confirmada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descricao= id_nome_reservador.getText().toString();
                String data= id_data_reserva.getText().toString();
                String horaInicio= id_hora_inicio.getText().toString();
                String horaFinal= id_hora_final.getText().toString();

                System.out.println(data);
                System.out.println(horaInicio);


                createJson(descricao, data, horaInicio, horaFinal);

            }
        });
    }
    private void createJson(String tituloReuniao, String horarioMarcadoInicial, String horarioMarcadoFinal, String dataMarcada) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String datHoraInicioStr = dataMarcada + " " + horarioMarcadoInicial;
        String datHoraFimStr = dataMarcada + " " + horarioMarcadoFinal;



        Date dateHoraFim = null, dateHoraInicio = null;
        try {
            dateHoraFim = simpleDateFormat.parse(datHoraInicioStr);
            dateHoraInicio = simpleDateFormat.parse(datHoraFimStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        JSONObject reservaJson = new JSONObject();

        preferences = getSharedPreferences(userPreferences, Context.MODE_PRIVATE);


        try {
            reservaJson.put("id_usuario", preferences.getString("userId", null));
            reservaJson.put("descricao", tituloReuniao);
            reservaJson.put("data_hora_inicio", dateHoraInicio.getTime());
            reservaJson.put("data_hora_fim", dateHoraFim.getTime());


            System.out.println(reservaJson.toString());

            String reservaEncoded = Base64.encodeToString(reservaJson.toString().getBytes("UTF-8"), Base64.NO_WRAP);
            System.out.println(reservaEncoded);

            String respostaMetodo = new VerificadorReserva().execute(reservaEncoded).get();

            if (respostaMetodo.equals("Reserva realizada com sucesso")) {

                Toast.makeText(Reservador.this, "Reserva realizada com sucesso!", Toast.LENGTH_SHORT).show();

                onBackPressed();

            } else {
                Toast.makeText(
                        Reservador.this,
                        "A reserva não foi realizada!",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();


        }

    }
}
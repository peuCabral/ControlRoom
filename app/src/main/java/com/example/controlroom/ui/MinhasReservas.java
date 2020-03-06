package com.example.controlroom.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.controlroom.R;
import com.example.services.VerificadorSalasReservadas;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MinhasReservas extends Fragment {

    private View view;
    private TextView dataAtual, quantReunioes, textSemReunioes;
    private int contReunioes;

    private List<Reservador> reservas = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView listRerservas;
    private List<String> itemReserva = new ArrayList<>();
    private SharedPreferences preferences;
    public static final String userPreferences = "userPreferences";

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private String requestReservas;
    private View floatingActionButton;

    //private FloatingActionButton floatingActionButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.minhas_reservas, container, false);

        iniciaComponentes();


        return view;
    }

    private void iniciaComponentes() {

        dataAtual = view.findViewById(R.id. id_data_reserva);
        dataAtual.setText(getData());

        //quantReunioes = view.findViewById(R.id.textQuantReunioes);
        listRerservas = view.findViewById(R.id.listReservas); // aquiPode ser aleterado paara (nav_reservas)  <----

        //quantReunioes = view.findViewById(R.id.textQuantReunioes);

        //mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);

        //textSemReunioes = view.findViewById(R.id.textSemReservas);

        //loatingActionButton = view.findViewById(R.id.floatingActionButton);

        exibirReservas();

        //excluirReservas();

        atualizarReservas();
        //arrumar

        criarReserva();
    }

    private void criarReserva() {

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ActivityReuniao.class);// aquiPode ser aleterado paara nav_reservas???

                String data = getDataAtual();
                intent.putExtra("dataSelecionada", data);

                JSONObject.chama= onCreateView();  // MUDAR METODO chama

                startActivity(intent);

            }
        });
    }

    private String getDataAtual() {
        Date data = new Date();
        Locale local = new Locale("pt", "BR");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY", local);

        return dateFormat.format(data);
    }

    private void atualizarReservas() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e(getClass().getSimpleName(), "refresh");

                listRerservas.setVisibility(View.INVISIBLE);

                contReunioes = 0;
                textSemReunioes.setVisibility(View.INVISIBLE);
                exibirReservas();

                if (requestReservas != null) {
                    listRerservas.setVisibility(View.VISIBLE);
                    mSwipeRefreshLayout.setRefreshing(false);

                } else {
                    mSwipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getContext(), "Carregamento incompleto", Toast.LENGTH_LONG);
                }
            }
        });
    }


//    private void excluirReservas() {
//        listRerservas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Toast.makeText(getActivity(), "Click list" + position, Toast.LENGTH_LONG).show();
//
//                try {
//                    JSONObject reservaJson = new JSONObject();
//
//
//                    String reservaEncoded = Base64.encodeToString(reservaJson.toString().getBytes("UTF-8"), Base64.NO_WRAP);
//                    System.out.println(reservaEncoded);
//
//                    //String cancelarReserva = new RequestCancelarReserva().execute(reservaEncoded).get();
//
//                  //  if (cancelarReserva.equals("A reserva foi cancelada com sucesso")) {
//
//                        reservas.remove(position);
//                        // adapter.clear();
//                       // AdapterReservasUser adapter = new AdapterReservasUser(reservas, getActivity());
//                        listRerservas.setAdapter(adapter);
//
//                    } else {
//                        Toast.makeText(getContext(), "", Toast.LENGTH_LONG).show();
//                    }
//
//                } catch (Exception e) {

                //}

            //}

       // });
  //  }

    private String getData() {
        Date data = new Date();
        Locale local = new Locale("pt", "BR");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd',' EEEE", local);
        return dateFormat.format(data);

    }

    private void exibirReservas() {

        preferences = getActivity().getSharedPreferences(userPreferences, Context.MODE_PRIVATE);
        System.out.println(preferences.getString("userId", null));

        requestReservas = null;
        try {
            requestReservas = new VerificadorSalasReservadas().execute(preferences.getString("userId", null)).get();

            System.out.println(requestReservas);

            JSONArray reservasJson = new JSONArray(requestReservas);

            if (reservasJson.length() > 0) {

                for (int i = 0; i < reservasJson.length(); i++) {
                    contReunioes++;

                    JSONObject jsonObjectReserva = reservasJson.getJSONObject(i);

                    if (jsonObjectReserva.has("idUsuario") && jsonObjectReserva.has("id")) {

                        //I/System.out: [{"ativo":true,"dataAlteracao":"2020-02-19T17:00:15Z[UTC]","dataCriacao":"2020-02-19T17:00:15Z[UTC]","dataHoraFim":"2020-02-19T20:00:00Z[UTC]","dataHoraInicio":"2020-02-19T21:00:00Z[UTC]","descricao":"reserva","id":1,"idSala":1,"idUsuario":7,"nomeOrganizador":"Verônica Souza"},{"ativo":true,"dataAlteracao":"2020-02-19T17:01:54Z[UTC]","dataCriacao":"2020-02-19T17:01:54Z[UTC]","dataHoraFim":"2020-02-19T21:01:00Z[UTC]","dataHoraInicio":"2020-02-19T22:01:00Z[UTC]","descricao":"reserva","id":2,"idSala":1,"idUsuario":7,"nomeOrganizador":"Verônica Souza"},{"ativo":true,"dataAlteracao":"2020-02-19T17:04:09Z[UTC]","dataCriacao":"2020-02-19T17:04:09Z[UTC]","dataHoraFim":"2020-02-19T20:04:00Z[UTC]","dataHoraInicio":"2020-02-19T21:04:00Z[UTC]","descricao":"re","id":3,"idSala":1,"idUsuario":7,"nomeOrganizador":"Verônica Souza"},{"ativo":true,"dataAlteracao":"2020-02-19T17:07:15Z[UTC]","dataCriacao":"2020-02-19T17:07:15Z[UTC]","dataHoraFim":"2020-02-19T20:07:00Z[UTC]","dataHoraInicio":"2020-02-19T21:07:00Z[UTC]","descricao":"reservar","id":4,"idSala":1,"idUsuario":7,"nomeOrganizador":"Verônica Souza"},{"ativo":true,"dataAlteracao":"2020-02-20T14:16:57Z[UTC]","dataCriacao":"2020-02-20T14:16:57Z[UTC]","dataHoraFim":"2020-02-20T17:16:00Z[UTC]","dataHoraInicio":"2020-02-20T17:30:00Z[UTC]","descricao":"desc","id":5,"idSala":1,"idUsuario":7,"nomeOrganizador":"Verônica Souza"}]
                        int idUser = jsonObjectReserva.getInt("idUsuario");
                        int id_sala = jsonObjectReserva.getInt("id_sala");
                        int idReserva = jsonObjectReserva.getInt("id");
                        String descricaoReserva = jsonObjectReserva.getString("descricao");
                        String dataHoraInicio = jsonObjectReserva.getString("dataHoraInicio");
                        String dataHoraFim = jsonObjectReserva.getString("dataHoraFim");

                        Reservador newReserva = new Reservador();

                        newReserva.setid_sala(id_sala);
                        newReserva.setDescricaoReserva(descricaoReserva);
                        newReserva.setIdUser(idUser);

                        newReserva.setNomeSala("Sala para reuniao");

                        System.out.println("data inicio");

                        //data
                        String data = dataHoraInicio.split("T")[0];
                        newReserva.setDataReserva(data.split("-")[2] + "/" + data.split("-")[1]);

                        //hour//

                        String horarioInicioSplit = dataHoraInicio.split("T")[1];
                        String horarioInicioStr = horarioInicioSplit.split(":00Z")[0];

                        String horarioFimSplit = dataHoraFim.split("T")[1];
                        String horarioFimStr = horarioFimSplit.split(":00Z")[0];

                        newReserva.setHorarioInicio(horarioFimStr);
                        newReserva.setHorarioFinal(horarioInicioStr);

                        reservas.add(newReserva);
                        itemReserva.add(descricaoReserva);

                    }

                }

                if (contReunioes > 1) {
                    quantReunioes.setText(contReunioes + " " + "reuniões");
                } else {
                    quantReunioes.setText(contReunioes + " " + "reunião");
                }
                listRerservas = view.findViewById(R.id.listReservas);

                AdapterReservasUser adapter = new AdapterReservasUser(reservas, getActivity());
                listRerservas.setAdapter(adapter);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //newReserva.setHorarioInicio(horarioFimStr);

    }


}
package com.example.controlroom.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.adapter.Adapter;
import com.example.adapter.SalaAdapter;
import com.example.adapter.TinyDB;
import com.example.controlroom.R;
import com.example.model.SalaModel;
import com.example.services.VerificadorSalas;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Salas extends Fragment {


    private SharedPreferences preferences;
    public static final String userPreferences = "userPreferences";

    private List<String> nomeSalas = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView listSalas;
    private TinyDB tinyDB;


    private ArrayList<SalaModel> salas = new ArrayList<SalaModel>();
    //private Context context;


    private View view;

   /* public Salas(List<SalaModel> listaSalas) {
        this.salas = listaSalas;
        this.context = context;
    }*/



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_list, container, false);

        preferences = getActivity().getSharedPreferences(userPreferences, Context.MODE_PRIVATE);

        listSalas = view.findViewById(R.id.lista_eventos);

        tinyDB = new TinyDB(getContext());

        inserirSalas();
        abrirListaDetalhes();

        return view;


    }

    private void abrirListaDetalhes() {
        listSalas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), DescricaoSala.class);
                intent.putExtra("salaSelecionada", salas.get(position));
                startActivity(intent);


            }
        });
    }

    private void inserirSalas() {

        String requestSalas = null;
        try {
            requestSalas = new VerificadorSalas().execute(preferences.getString("userIdEmpresa", null)).get();

            System.out.println(requestSalas);

            JSONArray salasJson = new JSONArray(requestSalas);

            if (salasJson.length() > 0) {

                for (int i = 0; i < salasJson.length(); i++) {
                    JSONObject salaJSon = salasJson.getJSONObject(i);

                    int id = salaJSon.getInt("id");
                    String nome = salaJSon.getString("nome");
                    String dimensao = salaJSon.getString("areaDaSala");
                    String capacidade = salaJSon.getString("quantidadePessoasSentadas");
                    String multimidia = salaJSon.getString("possuiMultimidia");
                    String arcondicionado = salaJSon.getString("possuiMultimidia");
                    String localizacao = salaJSon.getString("localizacao");

                    SalaModel newSala = new SalaModel();

                    newSala.setId(id);
                    newSala.setNomeSala(nome);
                    newSala.setTamanhoSala(dimensao);
                    newSala.setCapacidade(capacidade);
                    newSala.setLocalizacao(localizacao);
                    newSala.setArCondicionado(arcondicionado);
                    newSala.setMultimidia(multimidia);

                    salas.add(newSala);
//                    nomeSalas.add(newSala.getNomeSala());

                }

                tinyDB.putListSalaObject("salas", salas);

                listSalas = view.findViewById(R.id.lista_eventos);
               SalaAdapter adapter = new SalaAdapter(salas, getActivity());
//             adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, nomeSalas);
                listSalas.setAdapter(adapter);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void abrirClasse(Class classe) {
        Intent intent = new Intent(getActivity(), classe);
        startActivity(intent);
        getActivity().finish();
    }
}
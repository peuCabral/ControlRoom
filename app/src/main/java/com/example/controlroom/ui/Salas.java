package com.example.controlroom.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.controlroom.R;
import com.example.model.SalaModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class Salas  extends Fragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;

        preferences = getSharedPreferences(userPreferences, Context.MODE_PRIVATE);

    }

    private void inserirSalas() {

        String requestSalas = null;
        try {
            requestSalas = new RequestSalas().execute(preferences.getString("userIdEmpresa", null)).get();

            System.out.println(requestSalas);

            JSONArray salasJson = new JSONArray(requestSalas);

            if (salasJson.length() > 0) {

                for (int i = 0; i < salasJson.length(); i++) {
                    JSONObject salaJSon = salasJson.getJSONObject(i);

                    String nome = salaJSon.getString("nome");
                    String dimensao = salaJSon.getString("areaDaSala");
                    String capacidade = salaJSon.getString("quantidadePessoasSentadas");
                    String multimidia = salaJSon.getString("possuiMultimidia");
                    String arcondicionado = salaJSon.getString("possuiMultimidia");
                    String localizacao = salaJSon.getString("localizacao");

                    SalaModel newSala = new SalaModel();

                    newSala.setNomeSala(nome);

                    newSala.setDimensaoSala(dimensao);
                    newSala.setCapacidade(capacidade);
                    newSala.setLocalizacao(localizacao);
                    newSala.setArCondicionado(arcondicionado);
                    newSala.setMultimidia(multimidia);

                    salas.add(newSala);
                    nomeSalas.add(newSala.getNomeSala());


                }
                listSalas = view.findViewById(R.id.lista_salas_listview);
                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, nomeSalas);
                listSalas.setAdapter(adapter);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

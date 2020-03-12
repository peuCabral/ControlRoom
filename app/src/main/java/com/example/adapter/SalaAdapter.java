package com.example.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.controlroom.R;
import com.example.model.SalaModel;

import java.util.ArrayList;
import java.util.List;

public class SalaAdapter extends BaseAdapter {

    private List<SalaModel> salas ;
    private Activity act;
    private TinyDB tinyDB;

    public SalaAdapter(List<SalaModel> salas, Activity act) {
        this.salas = salas;
        this.act = act;
    }

    @Override
    public int getCount() {
        return salas.size();
    }

    @Override
    public Object getItem(int position) {
        return salas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.unidade_sala, parent, false);


        SalaModel sala = salas.get(position);

        TextView nomeSala = (TextView)
                view.findViewById(R.id.nome_sala);

        TextView capacidade = (TextView)
                view.findViewById(R.id.id_capacidade);

        ImageView imageView = (ImageView)
                view.findViewById(R.id.unidade_evento_imagem);

        imageView.setImageResource(R.drawable.logo_salas);



        tinyDB = new TinyDB(parent.getContext());
        ArrayList<SalaModel> salas = tinyDB.getListSalaObject("salas");

        for (int i = 0; i < salas.size(); i++) {

            if (salas.get(i).getId() == sala.getId()) {

                nomeSala.setText(salas.get(i).getNomeSala());
                capacidade.setText("Capacidade Sala: "+salas.get(i).getCapacidade() + " pessoas");

            }


        }


        return view;
    }


}
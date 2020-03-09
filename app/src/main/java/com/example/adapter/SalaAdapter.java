package com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.controlroom.R;
import com.example.model.SalaModel;

import java.util.ArrayList;
import java.util.List;

public class SalaAdapter extends BaseAdapter {

    private List<SalaModel> salas = null;
    private Activity act;
    private Context context;
    private TinyDB tinyDB;


    public SalaAdapter(List<SalaModel> salas, Activity act, Context context) {
        this.salas = salas;
        this.act = act;
        this.context = context;
    }

    public SalaAdapter(List<SalaModel> salas, FragmentActivity activity) {
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
        View view = act.getLayoutInflater().inflate(R.layout.unidade_evento, parent, false);


        tinyDB = new TinyDB(parent.getContext());
        ArrayList<SalaModel> salas = tinyDB.getListSalaObject("salas");

        SalaModel sala = salas.get(position);

        TextView nomeSala = (TextView)
                view.findViewById(R.id.nome_sala);

        TextView capacidade = (TextView)
                view.findViewById(R.id.id_capacidade);


        nomeSala.setText(sala.getNomeSala());
        capacidade.setText(sala.getCapacidade());


        return view;
    }


}
package com.example.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.controlroom.R;
import com.example.model.ControlSalas;
import com.example.model.SalaModel;

import java.util.ArrayList;
import java.util.List;

public class ReservaAdapter extends BaseAdapter {

    private List<ControlSalas> reservas ;
    private Activity act;
    private TinyDB tinyDB;

    public ReservaAdapter(List<ControlSalas> reservas, Activity act) {
        this.reservas = reservas;
        this.act = act;
    }

    @Override
    public int getCount() {
        return reservas.size();
    }

    @Override
    public Object getItem(int position) {
        return reservas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.unidade_reserva, parent, false);


        ControlSalas reserva = reservas.get(position);

        TextView nome_sala = (TextView)
                view.findViewById(R.id.nome_sala);

        TextView descricao_reserva = (TextView)
                view.findViewById(R.id.descricao_reserva);

        TextView data = (TextView)
                view.findViewById(R.id.data_reserva);

        TextView horario_reserva = (TextView)
                view.findViewById(R.id.horario_reserva);

        ImageView imageView = (ImageView)
                view.findViewById(R.id.unidade_evento_imagem);

        imageView.setImageResource(R.drawable.logo_salas);



        tinyDB = new TinyDB(parent.getContext());
        ArrayList<SalaModel> salas = tinyDB.getListSalaObject("salas");

        for (int i = 0; i < salas.size(); i++) {

            if (salas.get(i).getId() == reserva.getIdSala()) {

                nome_sala.setText(salas.get(i).getNomeSala());

            }

        }

        descricao_reserva.setText(reserva.getDescricaoReserva());
        data.setText(reserva.getDataReserva());
        horario_reserva.setText(reserva.getHorarioInicio());


        return view;
    }


}
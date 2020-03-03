package com.example.controlroom.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.controlroom.R;

public class Perfil extends Fragment {

    private SharedPreferences preferences;
    private TextView nome_perfil, email_perfil, empresa_perfil;
    public static final String userPreferences = "userPreferences";
    private View view;
    private ImageButton btn_logout;
    //private ImageButton buttonReservas;

    private ImageButton btn_editar;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_perfil, container, false);

        nome_perfil = (TextView) view.findViewById(R.id.nome_perfil);
        email_perfil = (TextView) view.findViewById(R.id.email_perfil);
        empresa_perfil = (TextView)  view.findViewById(R.id.empresa_perfil);
        btn_logout = (ImageButton) view.findViewById(R.id.btn_logout);
        btn_editar = (ImageButton) view.findViewById(R.id.btn_editar);
        //buttonReservas =(ImageButton) view.findViewById(R.id.buttonReservas);
        
        inserirDados();
        logout();
        //editarDados();

        
        return view;
    }




   // }
    public void inserirDados() {

        preferences = getActivity().getSharedPreferences(userPreferences, Context.MODE_PRIVATE);

        String nome = preferences.getString("userName", null);
        String email = preferences.getString("userEmail", null);
        String empresa = preferences.getString("userNomeEmpresa", null);
        String empresa_id = preferences.getString("userIdEmpresa", null);


        System.out.println(nome);

        nome_perfil.setText(nome);
        email_perfil.setText(email);
        empresa_perfil.setText(empresa);



    }

    private void logout (){
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences(userPreferences, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("userEmail");
                editor.remove("userName");
                editor.remove("userIdEmpresa");

                editor.commit();
                abrirClasse(Cadastro_ou_Login.class);
            }
            
            
        });

    }

    private void abrirClasse(Class classe) {
        Intent intent = new Intent(getActivity(), classe);
        startActivity(intent);
        getActivity().finish();
    }


}
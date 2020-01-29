package com.example.controlroom.ui;

import android.util.Base64;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controlroom.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Cadastrar extends AppCompatActivity {

    private ImageButton return_button;
    private Button butao_cadastrar;
    private EditText nameText;
    private EditText emailText;
    private EditText senhaText;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        senhaText = findViewById(R.id.senhaText);

        return_button = findViewById(R.id.return_button);
        return_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastrar.this, Cadastro_ou_Login.class);
                startActivity(intent);
                finish();
            }
        });

        butao_cadastrar = findViewById(R.id.butao_cadastrar);
        butao_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = nameText.getText().toString();
                String email = emailText.getText().toString();
                String senha = senhaText.getText().toString();

                createJson(email, nome, senha);

                Intent intent = new Intent(Cadastrar.this, Login.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void createJson(String email, String nome, String senha) {

        JSONObject usuarioJson = new JSONObject();


        try {
            usuarioJson.put("email", email);
            usuarioJson.put("senha", senha);
            usuarioJson.put("nome", nome);


            System.out.println(usuarioJson.toString());

            String userEnconde = Base64.encodeToString(usuarioJson.toString().getBytes("UTF-8"), Base64.NO_WRAP);
            System.out.println(userEnconde);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
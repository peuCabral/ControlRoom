package com.example.controlroom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controlroom.R;
import com.example.services.Verificador;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private ImageButton btn_login;
    private  ImageButton return_button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciarComponentes();
    }

    private void iniciarComponentes() {

        btn_login = findViewById(R.id.btn_login);
        email = findViewById(R.id.loginEmail);
        senha = findViewById(R.id.loginSenha);
        return_button2 =findViewById(R.id.return_button2);

        realizandoLogin();

        return_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirClasse(Cadastro_ou_Login.class);
            }
        });

    }

    private void realizandoLogin() {
        btn_login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String emailStr = email.getText().toString().trim();
                String senhaStr = senha.getText().toString().trim();


                if (verificandoCampos() == true) {

                    try {
                        String authReturn = new Verificador().execute(emailStr, senhaStr).get();


                        if (authReturn.equalsIgnoreCase("Login efetuado com sucesso!")) {

                            Toast.makeText(getApplicationContext(), "Login realizado com sucesso", Toast.LENGTH_LONG).show();
                            abrirClasse(MenuSalas.class);

                        } else {
                            Toast.makeText(getApplicationContext(), "Login inválido!", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), " inválido", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else {

                }

            }
        });
    }



    private void abrirClasse(Class classe) {
        Intent intent = new Intent(this, classe);
        startActivity(intent);
        this.finish();

    }

    public boolean verificandoCampos() {
        boolean chave = true;

        if (email.getText().toString().trim().isEmpty() || !email.getText().toString().trim().contains("@")
                || !email.getText().toString().trim().contains(".")) {
            email.setError("Insira um e-mail válido!");
            chave = false;
        }

        if (senha.getText().toString().trim().length() <= 0) {
            senha.setError("Insira uma senha");
            chave = false;
        }

        return chave;
    }

}




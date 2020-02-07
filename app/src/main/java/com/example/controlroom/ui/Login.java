package com.example.controlroom.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controlroom.R;
import com.example.model.Empresa;
import com.example.model.Usuario;
import com.example.services.Verificador;

import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private ImageButton btn_login;

    private ImageButton return_button2;
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    private SharedPreferences preferences;
    public static final String userPreferences = "userPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences(userPreferences, Context.MODE_PRIVATE);

  /*/      if(preferences.contains("userEmail")){

        abrirClasse(MenuSalas.class);

    }/*/

        setContentView(R.layout.activity_login);

        iniciarComponentes();
    }

    private void iniciarComponentes() {

        btn_login = findViewById(R.id.btn_login);

        email = findViewById(R.id.loginEmail);
        senha = findViewById(R.id.loginSenha);
        return_button2 = findViewById(R.id.return_button2);

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

                        //Toast.makeText(getApplicationContext(),authReturn,Toast.LENGTH_LONG).show();

                        if (authReturn.length() > 0) {
                            Toast.makeText(getApplicationContext(), "Login realizado com sucesso", Toast.LENGTH_LONG).show();

                            JSONObject usuarioJSON = new JSONObject(authReturn);

                            if (usuarioJSON.has("email") && usuarioJSON.has("id") && usuarioJSON.has("nome") && usuarioJSON.has("idOrganizacao")) {
                                int id = usuarioJSON.getInt("id");
                                String nome = usuarioJSON.getString("nome");
                                String email = usuarioJSON.getString("email");

                                JSONObject organizacao = usuarioJSON.getJSONObject("idOrganizacao");
                                String nomeOrganizacao = organizacao.getString("nome");
                                String tipoOrganizacao = organizacao.getString("tipoOrganizacao");
                                int idOrganizacao = organizacao.getInt("id");


                                Usuario usuarioAuth = new Usuario();
                                usuarioAuth.setId(id);
                                usuarioAuth.setNomeUser(nome);
                                usuarioAuth.setEmailUser(email);
                                usuarioAuth.setId(idOrganizacao);

                                Empresa empresa = new Empresa();
                                empresa.setId(idOrganizacao);
                                empresa.setNomeEmpresa(nomeOrganizacao);
                                empresa.setTipoEmpresa(tipoOrganizacao);

                                salvarCredenciais(usuarioAuth, empresa);
                            }
                            Toast.makeText(getApplicationContext(), preferences.getString("userEmail", null), Toast.LENGTH_SHORT).show();

                            System.out.println(preferences.getString("userEmail", null));
                            System.out.println(preferences.getString("userName", null));
                            System.out.println(preferences.getString("userId", null));
                            System.out.println(preferences.getString("userIdOrganizacao", null));
                            System.out.println(preferences.getString("userNomeEmpresa", null));
                            System.out.println(preferences.getString("userTipoEmpresa", null));
                            System.out.println(preferences.getString("userIdEmpresa", null));

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

    private void salvarCredenciais(Usuario usuarioAuth, Empresa empresa) {

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("userEmail", usuarioAuth.getEmailUser());
        editor.putString("userName", usuarioAuth.getNomeUser());
        editor.putString("userId", Integer.toString(usuarioAuth.getId()));
        editor.putString("userIdOrganizacao", Integer.toString(usuarioAuth.getId()));
        editor.putString("userNomeEmpresa", empresa.getNomeEmpresa());
        editor.putString("userTipoEmpresa", empresa.getTipoEmpresa());
        editor.putString("userIdEmpresa", Integer.toString(empresa.getId()));

        editor.commit();

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

    public void Save(String email) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Email, email);
        editor.commit();
    }

}





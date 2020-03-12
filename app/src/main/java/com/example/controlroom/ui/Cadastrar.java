package com.example.controlroom.ui;

import android.util.Base64;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controlroom.R;
import com.example.model.Empresa;
import com.example.services.VerificadorCadastro;
import com.example.services.VerificadorEmpresa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Cadastrar extends AppCompatActivity {

    private Button butao_cadastrar;
    private EditText nameText;
    private EditText emailText;
    private EditText senhaText;
    private Spinner spinnerEmpresa;
    List<Empresa> empresaList = new ArrayList<>();
    List<String> empresaListaNomes = new ArrayList<>();
    private  String dominio;
    int idEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        StartComponents();

    }

    private void StartComponents() {

        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        senhaText = findViewById(R.id.senhaText);
        butao_cadastrar = findViewById(R.id.butao_cadastrar);
        spinnerEmpresa = findViewById(R.id.spinnerEmpresa);

        cadastrar();
        inicializaEmailFocusListener();

        clickSpinner();


    }


    private void cadastrar() {

        butao_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = nameText.getText().toString();
                String email = emailText.getText().toString();
                String senha = senhaText.getText().toString();


                if (verificarDados() == true) {
                    createJson(email, nome, senha, idEmpresa);

                    // startClass(Login.class);


                } else {

                }

            }
        });


    }

    private void createJson(String email, String nome, String senha, int idOrg) {

        JSONObject usuarioJson = new JSONObject();


        try {
            usuarioJson.put("email", email);
            usuarioJson.put("senha", senha);
            usuarioJson.put("nome", nome);
             usuarioJson.put("idOrganizacao", idOrg);


            System.out.println(usuarioJson.toString());

            String userEncoded = Base64.encodeToString(usuarioJson.toString().getBytes("UTF-8"), Base64.NO_WRAP);
            System.out.println(userEncoded);

            String respostaMetodo = new VerificadorCadastro().execute(userEncoded).get();

            if (respostaMetodo.equals("Usuário criado com sucesso")) {

                startClass(Login.class);
            } else {
                Toast.makeText(Cadastrar.this, "Campos inválidos!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(
                    Cadastrar.this,
                    "Erro ao efetuar cadastro!",
                    Toast.LENGTH_SHORT).show();
        }

    }


    private void inicializaEmailFocusListener() {

        emailText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if (!hasFocus) {
                    String emailAfterTextChanged = emailText.getText().toString();

                    if (emailAfterTextChanged.contains("@")) {
                        String[] emailCompleto = emailAfterTextChanged.split("@");
                        if (emailCompleto.length > 1) {
                             dominio = emailCompleto[1];
                            if (dominio.contains(".")) {
                                System.out.println("dominio: " + dominio);
                                try {
                                    String organizacoesStringFromServer = new VerificadorEmpresa().execute(dominio).get();
                                    System.out.println("Organizações em string: " + organizacoesStringFromServer);


                                    if (organizacoesStringFromServer.length() > 0) {

                                        JSONArray jsonArray = new JSONArray(organizacoesStringFromServer);


                                        if (jsonArray.length() > 0) {

                                            for (int i = 0; i < jsonArray.length(); i++) {

                                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                                if (jsonObject.has("id") && jsonObject.has("nome") && jsonObject.has("tipoOrganizacao")) {

                                                    int id = jsonObject.getInt("id");
                                                    String nome = jsonObject.getString("nome");
                                                    String tipoEmpresa = jsonObject.getString("tipoOrganizacao");

                                                    Empresa newEmpresa = new Empresa();

                                                    newEmpresa.setId(id);

                                                    newEmpresa.setNomeEmpresa(nome);

                                                    newEmpresa.setTipoEmpresa(tipoEmpresa);

                                                    empresaList.add(newEmpresa);

                                                    empresaListaNomes.add(newEmpresa.getNomeEmpresa());

                                                    ArrayAdapter<String>adapter = new ArrayAdapter<>(Cadastrar.this, android.R.layout.simple_spinner_item, empresaListaNomes);

                                                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                    spinnerEmpresa.setAdapter(adapter);

                                                    spinnerEmpresa.setVisibility(View.VISIBLE);


                                                }


                                            }


                                        } else {
                                            mostrarMensagem("Dominio de Email Invalido");

                                        }


                                    } else {
                                        Toast.makeText(getApplicationContext(), "Não pertence a nenhuma organização", Toast.LENGTH_LONG);

                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }


                        }
                    }
                }

            }
        });


    }

    private void clickSpinner(){

        spinnerEmpresa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idEmpresa = empresaList.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    private void startClass(Class classe) {
        Intent intent = new Intent(this, classe);
        startActivity(intent);
        this.finish();

    }

    public boolean verificarDados() {
        boolean chave = true;

        if (emailText.getText().toString().trim().isEmpty() || !emailText.getText().toString().trim().contains("@")
                || !emailText.getText().toString().trim().contains(".")|| emailText.getText().toString().trim().contains(dominio.toLowerCase())) {
            emailText.setError("Insira um e-mail válido!");
            chave = false;
        }

        if (senhaText.getText().toString().trim().length() < 8) {
            senhaText.setError("A senha deve ter no mínimo 8 caracteres!");
            chave = false;
        }


        if (nameText.getText().toString().trim().length() <= 0) {
            nameText.setError("Campo obrigatório");
            chave = false;

        }
        return chave;
    }


    public void mostrarMensagem(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    public boolean onSupportNavigateUp(){

        onBackPressed();

        return  true;
    }

}

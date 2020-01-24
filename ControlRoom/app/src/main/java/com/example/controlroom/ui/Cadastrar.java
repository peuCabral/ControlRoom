package com.example.controlroom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controlroom.R;

public class Cadastrar extends AppCompatActivity {

    private ImageButton return_button;
    private Button butao_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        return_button = findViewById(R.id.return_button);
        return_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastrar.this,Cadastro_ou_Login.class);
                startActivity(intent);
                finish();
            }
        });

        butao_confirm = findViewById(R.id.butao_confirm);
        butao_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastrar.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

                }
            }

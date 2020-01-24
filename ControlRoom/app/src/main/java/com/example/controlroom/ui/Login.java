package com.example.controlroom.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controlroom.R;

public class Login  extends AppCompatActivity{

    private ImageButton return_button2;
    private Button button_confirm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        return_button2 = findViewById(R.id.return_button2);
        return_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Cadastro_ou_Login.class);
                startActivity(intent);
                finish();
            }
        });
        button_confirm2 = findViewById(R.id.button_confirm2);
        button_confirm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,MenuSalas.class);
                startActivity(intent);
                finish();
            }
        });

    }
}









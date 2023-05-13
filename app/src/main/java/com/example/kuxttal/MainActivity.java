package com.example.kuxttal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imagen;
    Button inicio,registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagen=findViewById(R.id.kux);
        imagen.setImageResource(R.drawable.logo);

        inicio=findViewById(R.id.Inicio);
        registro=findViewById(R.id.registro);
        inicio.setOnClickListener(this);
        registro.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String cadena=((Button)view).getText().toString();
        if(cadena.equals("Registrarse")){
            Intent intento2=new Intent(this,registro.class);
            startActivity(intento2);
        }
        else if(cadena.equals("Iniciar Sesión")){
            Intent intento2=new Intent(this,iniicia.class);
            startActivity(intento2);
        }
    }
}
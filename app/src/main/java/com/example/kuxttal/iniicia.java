package com.example.kuxttal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class iniicia extends AppCompatActivity implements View.OnClickListener {
    EditText usu,contra;
    Button inicia;
    String usua, contra1;
    private RequestQueue rq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniicia);

        usu=findViewById(R.id.usu);
        contra=findViewById(R.id.contra);
        inicia=findViewById(R.id.Inicio);
        inicia.setOnClickListener(this);
        rq= Volley.newRequestQueue(getApplicationContext());

    }

    @Override
    public void onClick(View view) {
        contra1= contra.getText().toString();
        usua=usu.getText().toString();
        solicitar(view);
    }
    private void solicitar(View v){
        StringRequest str = new StringRequest(Request.Method.POST, "https://192.168.0.102:8080/Kuxttalinicia_3/IniciaSes1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    System.out.println("1");
                    Log.d("Erros: ", error.toString());
                } else if (error instanceof ServerError) {
                    System.out.println("2");
                } else if (error instanceof AuthFailureError) {
                    System.out.println("3");
                } else if (error instanceof ParseError) {
                    System.out.println("4");
                } else if (error instanceof NoConnectionError) {
                    System.out.println("5");
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getApplicationContext(),
                            "Oops. Timeout error!",
                            Toast.LENGTH_LONG).show();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> parametros=new HashMap<>();
                parametros.put("usua", usua);
                parametros.put("contra", contra1);
                parametros.put("caso","and");
                return parametros;

            }

        };
        str.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.add(str);
    }
}
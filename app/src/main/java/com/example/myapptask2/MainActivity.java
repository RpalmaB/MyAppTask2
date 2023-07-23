package com.example.myapptask2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;


public class MainActivity extends AppCompatActivity implements Asynchtask {

    private TextView txtR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtR = findViewById(R.id.txtR);
    }

    public void btLista(View view) {
        Map<String, String> dato = new HashMap<>();
        WebService ws = new WebService("https://jsonplaceholder.typicode.com/users",
                dato, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    public void btLimpiarLista(View view) {

        txtR.setText("");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        StringBuilder usuarioB = new StringBuilder();
        JSONArray JSONlt = new JSONArray(result);
        for (int i = 0; i < JSONlt.length(); i++) {
            JSONObject user = JSONlt.getJSONObject(i);
            String name = user.getString("name");
            String email = user.getString("email");
            String phone = user.getString("phone");
            String website = user.getString("website");
            String usuario = " (" + i +") "+ " " + name + ",  " + email
                    + ",  " + phone + ",  " + website + "\n";
            usuarioB.append(usuario);
        }

        // Mostrar el resultado en el TextView en el hilo principal (UI thread)
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtR.setText(usuarioB.toString());
            }
        });
    }}



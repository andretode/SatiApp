package com.example.andre.SatiApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.andre.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private final String BUTAO_INICIAR_IS_ENABLED = "butaoIniciarIsEnabled";
    private final String SATI_APP_PREFERENCES = "SatiAppPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarBotoes();
    }

    private void inicializarBotoes()
    {
        SharedPreferences sp = getSharedPreferences(SATI_APP_PREFERENCES, 0);
        SharedPreferences.Editor editor = sp.edit();
        String butaoIniciarIsEnabledStr = sp.getString(BUTAO_INICIAR_IS_ENABLED, "");
        if(butaoIniciarIsEnabledStr == "") {
            editor.putString(BUTAO_INICIAR_IS_ENABLED, "true");
            editor.commit();
            butaoIniciarIsEnabledStr = "true";
        }

        Button bIniciar = findViewById(R.id.iniciar);
        Button bParar = findViewById(R.id.parar);
        if(butaoIniciarIsEnabledStr == "true") {
            bIniciar.setEnabled(true);
            bParar.setEnabled(false);
        } else {
            bIniciar.setEnabled(false);
            bParar.setEnabled(true);
        }
    }

    public void iniciarServico(View view)
    {
        changeOnOff();
        Intent myIntent = new Intent(MainActivity.this, DaemonService.class);
        startService(myIntent);
    }

    public void pararServico(View view)
    {
        changeOnOff();
        Intent myIntent = new Intent(MainActivity.this, DaemonService.class);
        stopService(myIntent);
    }

    private void changeOnOff()
    {
        SharedPreferences sp = getSharedPreferences(SATI_APP_PREFERENCES, 0);
        SharedPreferences.Editor editor = sp.edit();
        Button bIniciar = findViewById(R.id.iniciar);
        Button bParar = findViewById(R.id.parar);

        if(bIniciar.isEnabled()) {
            bIniciar.setEnabled(false);
            bParar.setEnabled(true);
            editor.putString(BUTAO_INICIAR_IS_ENABLED, "false");
        } else {
            bIniciar.setEnabled(true);
            bParar.setEnabled(false);
            editor.putString(BUTAO_INICIAR_IS_ENABLED, "true");
        }
        editor.apply();
    }
}

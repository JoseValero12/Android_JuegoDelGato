package com.example.juegodelgato;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Marcador extends AppCompatActivity {

    private int puntosJugador1;
    private int puntosJugador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcador);

        Intent intent = getIntent();
        puntosJugador1 = intent.getIntExtra("puntosJugador1", 0);
        puntosJugador2 = intent.getIntExtra("puntosJugador2", 0);

        TextView marcadorJugadorX = findViewById(R.id.marcador_jugador_x);
        TextView marcadorJugadorO = findViewById(R.id.marcador_jugador_o);

        marcadorJugadorX.setText("Jugador X: " + puntosJugador1);
        marcadorJugadorO.setText("Jugador O: " + puntosJugador2);

        Button regresarButton = findViewById(R.id.regresar_button);
        regresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresarAMain();
            }
        });
    }

    private void regresarAMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

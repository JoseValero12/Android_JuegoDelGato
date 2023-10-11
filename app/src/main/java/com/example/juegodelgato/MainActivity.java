package com.example.juegodelgato;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] botones = new Button[3][3];
    private boolean turnoJugador1 = true;
    private int contadorRondas = 0;
    private int puntosJugador1 = 0;
    private int puntosJugador2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String botonID = "button" + (i * 3 + j + 1);
                int resID = getResources().getIdentifier(botonID, "id", getPackageName());
                botones[i][j] = findViewById(resID);
                botones[i][j].setOnClickListener(this);
            }
        }

        Button verMarcadorButton = findViewById(R.id.score_button);
        verMarcadorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verMarcador();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (turnoJugador1) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        contadorRondas++;

        if (verificarGanador()) {
            if (turnoJugador1) {
                jugador1Gana();
            } else {
                jugador2Gana();
            }
        } else if (contadorRondas == 9) {
            empate();
        } else {
            turnoJugador1 = !turnoJugador1;
        }
    }

    private boolean verificarGanador() {
        String[][] tablero = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = botones[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (tablero[i][0].equals(tablero[i][1]) && tablero[i][0].equals(tablero[i][2]) && !tablero[i][0].equals("")) {
                return true;
            }
            if (tablero[0][i].equals(tablero[1][i]) && tablero[0][i].equals(tablero[2][i]) && !tablero[0][i].equals("")) {
                return true;
            }
        }

        if (tablero[0][0].equals(tablero[1][1]) && tablero[0][0].equals(tablero[2][2]) && !tablero[0][0].equals("")) {
            return true;
        }

        return tablero[0][2].equals(tablero[1][1]) && tablero[0][2].equals(tablero[2][0]) && !tablero[0][2].equals("");
    }

    private void jugador1Gana() {
        puntosJugador1++;
        mostrarMensaje("¡Jugador X gana!");
        actualizarPuntajes();
        reiniciarTablero();
    }

    private void jugador2Gana() {
        puntosJugador2++;
        mostrarMensaje("¡Jugador O gana!");
        actualizarPuntajes();
        reiniciarTablero();
    }

    private void empate() {
        mostrarMensaje("¡Empate!");
        reiniciarTablero();
    }

    private void actualizarPuntajes() {
        TextView puntajeJugadorX = findViewById(R.id.player_x_score);
        TextView puntajeJugadorO = findViewById(R.id.player_o_score);

        puntajeJugadorX.setText("Jugador X: " + puntosJugador1);
        puntajeJugadorO.setText("Jugador O: " + puntosJugador2);
    }

    private void reiniciarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setText("");
            }
        }
        contadorRondas = 0;
        turnoJugador1 = true;
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void verMarcador() {
        Intent intent = new Intent(this, Marcador.class);
        intent.putExtra("puntosJugador1", puntosJugador1);
        intent.putExtra("puntosJugador2", puntosJugador2);
        startActivity(intent);
    }
}

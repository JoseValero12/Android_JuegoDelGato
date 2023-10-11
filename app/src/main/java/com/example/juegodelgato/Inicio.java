package com.example.juegodelgato;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Inicio extends AppCompatActivity {

    private Button startButton;
    private CountDownTimer countDownTimer;
    private int remainingSeconds = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        startButton = findViewById(R.id.startButton);
        startButton.setText("JUGAR");

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setEnabled(false);
                startCountdownTimer();
            }
        });
    }

    private void startCountdownTimer() {
        countDownTimer = new CountDownTimer(remainingSeconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingSeconds = (int) (millisUntilFinished / 1000);
                startButton.setText("Tiempo restante: " + remainingSeconds);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(Inicio.this, MainActivity.class);
                startActivity(intent);

                startButton.setEnabled(true);

                startButton.setText("JUGAR");
            }
        }.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}


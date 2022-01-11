package com.example.palestratiium;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class ModificaProfilo extends AppCompatActivity {

    TextView current_height;
    SeekBar select_height;
    TextView current_peso;
    SeekBar select_peso;

    int current_progress_height, current_progress_peso;
    String mint_progress_height = "160";
    String mint_progress_peso = "80";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_peso);

        /*current_height = findViewById(R.id.current_height);
        select_height = findViewById(R.id.seekbar_height);*/
        current_peso = findViewById(R.id.current_peso);
        select_peso = findViewById(R.id.seekbar_peso);

        select_height.setMax(260);
        select_height.setProgress(160);
        select_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current_progress_height = progress;
                mint_progress_height = String.valueOf(current_progress_height);
                current_height.setText(mint_progress_height);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        select_peso.setMax(200);
        select_peso.setProgress(80);
        select_peso.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current_progress_peso = progress;
                mint_progress_peso =  String.valueOf(current_progress_peso);
                current_peso.setText(mint_progress_peso);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
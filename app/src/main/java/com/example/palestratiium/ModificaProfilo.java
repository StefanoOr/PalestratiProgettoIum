package com.example.palestratiium;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class ModificaProfilo extends AppCompatActivity {

    TextView current_height;
    SeekBar select_height;

    int current_progress;
    String mint_progress = "160";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_profilo);

        current_height = findViewById(R.id.current_height);
        select_height = findViewById(R.id.seekbar_height);

        select_height.setMax(260);
        select_height.setProgress(160);
        select_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current_progress = progress;
                mint_progress = String.valueOf(current_progress);
                current_height.setText(mint_progress);
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
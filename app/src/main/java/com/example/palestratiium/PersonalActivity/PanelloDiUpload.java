package com.example.palestratiium.PersonalActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.palestratiium.Login;
import com.example.palestratiium.R;
import com.example.palestratiium.UserActivity.Home;
import com.example.palestratiium.classi.PersonalTrainer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

public class PanelloDiUpload extends AppCompatActivity {
    PersonalTrainer personal;
    Button aggiungiEsercizio,aggiungiSchedaAllenamento;
    public static final String EXTRA_PT = "package com.example.palestratiium";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panello_di_upload);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_PT);

        if(obj instanceof PersonalTrainer){
            personal = (PersonalTrainer) obj;

        }else{
            personal = new PersonalTrainer();
        }

        aggiungiEsercizio = findViewById(R.id.aggiungiEsercizio);
        aggiungiSchedaAllenamento = findViewById(R.id.aggiungiAllenamento);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.aggiungiVideo);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent showResult;
                switch (item.getItemId()) {
                    case R.id.profilo:

                        showResult = new Intent(PanelloDiUpload.this, ProfiloPersonalTrainer.class);
                        showResult.putExtra(EXTRA_PT, personal);
                        startActivity(showResult);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.aggiungiVideo:


                        return true;

                    case R.id.menuHome:
                        showResult = new Intent(PanelloDiUpload.this, HomePersonalTrainer.class);
                        showResult.putExtra(EXTRA_PT, personal);
                        startActivity(showResult);
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });

        

    aggiungiEsercizio.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent showResult = new Intent(PanelloDiUpload.this, Upload.class);
            showResult.putExtra(EXTRA_PT, personal);
            startActivity(showResult);
        }
    });


    aggiungiSchedaAllenamento.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent showResult = new Intent(PanelloDiUpload.this, CreazioneSchedaAllenamento.class);
            showResult.putExtra(EXTRA_PT, personal);
            startActivity(showResult);
        }
    });
    }

}
package com.example.palestratiium.PersonalActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.palestratiium.Login;
import com.example.palestratiium.R;
import com.example.palestratiium.UserActivity.Profilo;
import com.example.palestratiium.classi.PersonalTrainer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

public class ProfiloPersonalTrainer extends AppCompatActivity {
    public static final String EXTRA_PT = "package com.example.palestratiium";
    PersonalTrainer personal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo_personal_trainer);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_PT);

        if(obj instanceof PersonalTrainer){
            personal = (PersonalTrainer) obj;

        }else{
            personal = new PersonalTrainer();
        }


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.profilo);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent showResult;
                switch (item.getItemId()) {
                    case R.id.profilo:


                        return true;

                    case R.id.aggiungiVideo:

                        showResult = new Intent(ProfiloPersonalTrainer.this, PanelloDiUpload.class);
                        showResult.putExtra(EXTRA_PT, personal);
                        startActivity(showResult);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.menuHome:
                        showResult = new Intent(ProfiloPersonalTrainer.this, HomePersonalTrainer.class);
                        showResult.putExtra(EXTRA_PT, personal);
                        startActivity(showResult);
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });
    }
}
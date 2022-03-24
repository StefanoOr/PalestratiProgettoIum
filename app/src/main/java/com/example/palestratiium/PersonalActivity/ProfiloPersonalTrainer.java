package com.example.palestratiium.PersonalActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.palestratiium.Login;
import com.example.palestratiium.ModifyPassword;
import com.example.palestratiium.R;
import com.example.palestratiium.UserActivity.ProfiloUser;
import com.example.palestratiium.classi.PersonalTrainer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

public class ProfiloPersonalTrainer extends AppCompatActivity {
    public static final String EXTRA_PT = "package com.example.palestratiium";
    PersonalTrainer personal;
    Button logout;
    TextView modify_password;
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

        logout=findViewById(R.id.logoutPT);
         modify_password=findViewById(R.id.modify_password_pt);


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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personal = null;
                Intent login = new Intent(ProfiloPersonalTrainer.this, Login.class);
                login.putExtra(EXTRA_PT, personal);
                startActivity(login);
            }
        });


        modify_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showResult = new Intent(ProfiloPersonalTrainer.this, ModifyPassword.class);
                showResult.putExtra(EXTRA_PT, personal);
                startActivity(showResult);
            }
        });
    }
}


package com.example.palestratiium.UserActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.palestratiium.Login;
import com.example.palestratiium.PersonalActivity.HomePersonalTrainer;
import com.example.palestratiium.R;
import com.example.palestratiium.classi.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

public class WorkOut extends AppCompatActivity {
    public static final String EXTRA_USER = "package com.example.palestratiium";

    User user;
    public Button allenamenti;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_out);



        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);

        if(obj instanceof User){
            user = (User) obj;

        }else{
            user = new User();
        }


        allenamenti=findViewById(R.id.aggiungiAllenamento);



        allenamenti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent allenamenti = new Intent(WorkOut.this, ActivitySchedaAllenamentoUser.class);
                allenamenti.putExtra(EXTRA_USER, user);
                startActivity(allenamenti);
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.aggiungiVideo);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent showResult;
                switch (item.getItemId()) {
                    case R.id.profilo:

                        showResult = new Intent(WorkOut.this, ProfiloUser.class);
                        showResult.putExtra(EXTRA_USER, user);
                        startActivity(showResult);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.aggiungiVideo:


                        return true;

                    case R.id.menuHome:

                        showResult = new Intent(WorkOut.this, Home.class);
                        showResult.putExtra(EXTRA_USER, user);
                        startActivity(showResult);
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });



    }
}
package com.example.palestratiium.UserActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.palestratiium.Login;
import com.example.palestratiium.R;
import com.example.palestratiium.adapter.CoachAdapter;
import com.example.palestratiium.classi.PersonalTrainer;
import com.example.palestratiium.classi.User;
import com.example.palestratiium.classi.UserFactory;

import java.io.Serializable;
import java.util.ArrayList;

public class FiltroUser extends AppCompatActivity {
    Spinner spinnerCoach;
    String clickedCountryName;
    private CoachAdapter coachAdapter;
    PersonalTrainer coach;
    Button confermaButton;

    public static final String EXTRA_USER = "package com.example.palestratiium";
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_user);

        spinnerCoach = findViewById(R.id.spinner_coachUser);
        confermaButton=findViewById(R.id.buttonCOnfermaFiltro);


        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);
        

        if(obj instanceof User){
            user = (User) obj;

        }else{
            user = new User();
        }

        coachAdapter = new CoachAdapter(this, (ArrayList<PersonalTrainer>) UserFactory.getInstance().getPersonal());

        spinnerCoach.setAdapter(coachAdapter);
        spinnerCoach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                coach = (PersonalTrainer) parent.getItemAtPosition(position);
                PersonalTrainer clickedItem = (PersonalTrainer) parent.getItemAtPosition(position);
                clickedCountryName = clickedItem.getUsername();
                System.out.println(clickedCountryName);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        confermaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showResult = new Intent(FiltroUser.this, Home.class);
                showResult.putExtra(EXTRA_USER, user);
                startActivity(showResult);
            }
        });
    }
}
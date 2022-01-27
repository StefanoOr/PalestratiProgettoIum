package com.example.palestratiium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.palestratiium.classi.PersonalTrainer;
import com.example.palestratiium.classi.User;

import java.io.Serializable;

public class HomePersonalTrainer extends AppCompatActivity {

    PersonalTrainer personal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_personal_trainer);

        setContentView(R.layout.activity_home);



        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);

        if(obj instanceof PersonalTrainer){
            personal = (PersonalTrainer) obj;

        }else{
            personal = new PersonalTrainer();
        }

    }
}
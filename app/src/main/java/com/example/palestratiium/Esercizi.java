package com.example.palestratiium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;

import classi.User;

public class Esercizi extends AppCompatActivity {
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esercizi);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);

        if(obj instanceof User){
            user = (User) obj;
        }else{
            user = new User();
        }

    }
}
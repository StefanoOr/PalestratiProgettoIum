package com.example.palestratiium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import Classi.User;

public class Profilo extends AppCompatActivity {

    User user;
    TextView modify_password, username, nome;
    Button home;

    public static final String EXTRA_USER = "package com.example.BonusLogin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        modify_password = findViewById(R.id.modify_password);
        username = findViewById(R.id.textView9);
        nome = findViewById(R.id.textView11);
        home = findViewById(R.id.back_home);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);

        if(obj instanceof User){
            user = (User) obj;
        }else{
            user = new User();
        }

        username.setText(user.getUsername());
        nome.setText(user.getUsername());

        modify_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showResult = new Intent(Profilo.this, ModifyPassword.class);
                showResult.putExtra(EXTRA_USER, user);
                startActivity(showResult);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(Profilo.this, Home.class);
                home.putExtra(EXTRA_USER, user);
                startActivity(home);
            }
        });
    }
}
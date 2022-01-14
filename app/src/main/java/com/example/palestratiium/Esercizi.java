package com.example.palestratiium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

import com.example.palestratiium.classi.User;

public class Esercizi extends AppCompatActivity {

    User user;
    ImageView back;
    TextView nomeEsercizio,descrizioneEsercizio;

    public static final String EXTRA_USER = "package com.example.BonusLogin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esercizi);




        back = findViewById(R.id.back_button);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);

        String name = getIntent().getStringExtra("NAME");
        String descrizione = getIntent().getStringExtra("DESCRIPTION");


        nomeEsercizio= findViewById(R.id.esercizio_title);
        descrizioneEsercizio = findViewById(R.id.text_corpo_descrizione);

        nomeEsercizio.setText(name);
        descrizioneEsercizio.setText(descrizione);

        if(obj instanceof User){
            user = (User) obj;
        }else{
            user = new User();
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(Esercizi.this, Home.class);
                home.putExtra(EXTRA_USER, user);
                startActivity(home);
            }
        });

    }
}
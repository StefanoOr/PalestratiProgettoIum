package com.example.palestratiium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

import com.example.palestratiium.PersonalActivity.HomePersonalTrainer;
import com.example.palestratiium.UserActivity.Home;
import com.example.palestratiium.classi.Esercizio;
import com.example.palestratiium.classi.PersonalTrainer;
import com.example.palestratiium.classi.User;

public class Esercizi extends AppCompatActivity implements Serializable{

    User user;
    PersonalTrainer personalTrainer;
    ImageView back;
    TextView nomeEsercizio,descrizioneEsercizio,gruppoMuscolare;
    boolean isPt,isUser;
    public static final String EXTRA_USER = "package com.example.palestratiium";
    public static final String EXTRA_PT = "package com.example.palestratiium";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esercizi);


        isPt=false;
        isUser=false;

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);
        Serializable objT = intent.getSerializableExtra(Login.EXTRA_PT);

        String name = getIntent().getStringExtra("NAME");
        String descrizione = getIntent().getStringExtra("DESCRIPTION");
        String gruppo = getIntent().getStringExtra("GRUPPOMUSCOLARE");


        nomeEsercizio= findViewById(R.id.esercizio_title);
        descrizioneEsercizio = findViewById(R.id.text_corpo_descrizione);
        back = findViewById(R.id.back_button);
        gruppoMuscolare = findViewById(R.id.text_corpo_gruppo_muscolare);

        nomeEsercizio.setText(name);
        descrizioneEsercizio.setText(descrizione);
        gruppoMuscolare.setText(gruppo);

        if(obj instanceof User){
            user = (User) obj;
            isUser=true;
        }else{
            user = new User();
        }

        if(objT instanceof PersonalTrainer){
            personalTrainer = (PersonalTrainer) objT;
            isPt=true;

        }else{
            personalTrainer = new PersonalTrainer();
        }



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUser) {
                    Intent home = new Intent(Esercizi.this, Home.class);
                    home.putExtra(EXTRA_USER, user);
                    startActivity(home);
                }else if(isPt) {
                    Intent home = new Intent(Esercizi.this, HomePersonalTrainer.class);
                    home.putExtra(EXTRA_PT, personalTrainer);
                    startActivity(home);
                }
            }
        });

    }
}
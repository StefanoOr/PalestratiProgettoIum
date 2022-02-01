package com.example.palestratiium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.palestratiium.PersonalActivity.HomePersonalTrainer;
import com.example.palestratiium.UserActivity.Home;
import com.example.palestratiium.classi.Esercizio;
import com.example.palestratiium.classi.PersonalTrainer;
import com.example.palestratiium.classi.UserFactory;

import java.io.Serializable;

public class ModificaEsercizio extends AppCompatActivity implements Serializable {

    Esercizio esercizio = new Esercizio();
    PersonalTrainer personalTrainer;

    EditText nomeEsercizio, descrizioneEsercizio;
    Uri video;
    boolean isPt;
    Button confirmEdit;
    TextView gruppoMuscolare, difficoltaAttuale;


    public static final String EXTRA_PT = "package com.example.palestratiium";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_esercizio);

        isPt = false;

        Intent intent = getIntent();
        Serializable objT = intent.getSerializableExtra(Login.EXTRA_PT);

        String name = getIntent().getStringExtra("NAME");
        String videoUri = getIntent().getStringExtra("VIDEO");
        String descrizione = getIntent().getStringExtra("DESCRIPTION");
        String gruppo = getIntent().getStringExtra("GRUPPO_MUSCOLARE");
        String difficolta = getIntent().getStringExtra("DIFFICOLTA");

        nomeEsercizio = findViewById(R.id.attributeTitolo);
        descrizioneEsercizio = findViewById(R.id.attributeDescrizione);
        gruppoMuscolare = findViewById(R.id.attributeMuscoli);
        difficoltaAttuale = findViewById(R.id.difficolta_attuale);
        confirmEdit = findViewById(R.id.editEsercizioButton);

        nomeEsercizio.setHint(name);
        descrizioneEsercizio.setHint(descrizione);
        gruppoMuscolare.setText(gruppo);
        difficoltaAttuale.setText(difficolta);

        if(objT instanceof PersonalTrainer){
            personalTrainer = (PersonalTrainer) objT;
            isPt = true;
        }else{
            personalTrainer = new PersonalTrainer();
        }

        confirmEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                esercizio.setNome(nomeEsercizio.getText().toString());
                esercizio.setDescrizioene(descrizioneEsercizio.getText().toString());
                personalTrainer.addEsercizi(esercizio);
                UserFactory.getInstance().addEsercizio(personalTrainer, esercizio);
                Intent home = new Intent(ModificaEsercizio.this, HomePersonalTrainer.class);
                home.putExtra(EXTRA_PT, personalTrainer);
                startActivity(home);
            }
        });

    }
}
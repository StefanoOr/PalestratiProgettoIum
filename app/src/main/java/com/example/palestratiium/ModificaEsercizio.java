package com.example.palestratiium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.palestratiium.PersonalActivity.HomePersonalTrainer;
import com.example.palestratiium.UserActivity.Home;
import com.example.palestratiium.classi.Esercizio;
import com.example.palestratiium.classi.PersonalTrainer;
import com.example.palestratiium.classi.UserFactory;

import java.io.Serializable;

public class ModificaEsercizio extends AppCompatActivity implements Serializable, AdapterView.OnItemSelectedListener, View.OnClickListener{

    Esercizio esercizio = new Esercizio();
    PersonalTrainer personalTrainer;

    EditText nomeEsercizio, descrizioneEsercizio;
    Uri video;
    boolean isPt;
    Button confirmEdit;
    TextView gruppoM, difficoltaAttuale;
    private CheckBox petto, gambe, bicipiti, dorso, tricipiti, spalle;
    private Esercizio.GruppoMuscolare gruppoSelezionato;
    private Spinner seleziona_difficolta;


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

        if(objT instanceof PersonalTrainer){
            personalTrainer = (PersonalTrainer) objT;
            isPt = true;
        }else{
            personalTrainer = new PersonalTrainer();
        }

        nomeEsercizio = findViewById(R.id.attributeTitolo);
        descrizioneEsercizio = findViewById(R.id.attributeDescrizione);
        gruppoM = findViewById(R.id.attributeMuscoli);
        difficoltaAttuale = findViewById(R.id.difficolta_attuale);
        confirmEdit = findViewById(R.id.editEsercizioButton);
        petto = findViewById(R.id.checkBox);
        dorso = findViewById(R.id.checkBox5);
        gambe = findViewById(R.id.checkBox4);
        tricipiti = findViewById(R.id.checkBox3);
        bicipiti = findViewById(R.id.checkBox6);
        spalle = findViewById(R.id.checkBox2);
        seleziona_difficolta = findViewById(R.id.attributeDifficolta);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.difficolta, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seleziona_difficolta.setAdapter(adapter);
        seleziona_difficolta.setOnItemSelectedListener(this);

        nomeEsercizio.setHint(name);
        descrizioneEsercizio.setHint(descrizione);
        gruppoM.setText(gruppo);
        difficoltaAttuale.setText(difficolta);
        gruppoSelezionato = Esercizio.GruppoMuscolare.valueOf(gruppo);

        petto.setOnClickListener(this);
        spalle.setOnClickListener(this);
        gambe.setOnClickListener(this);
        bicipiti.setOnClickListener(this);
        tricipiti.setOnClickListener(this);
        dorso.setOnClickListener(this);

        confirmEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                esercizio.setNome(nomeEsercizio.getText().toString());
                esercizio.setDescrizioene(descrizioneEsercizio.getText().toString());
                esercizio.setDifficolta(seleziona_difficolta.getSelectedItem().toString());
                esercizio.setGruppoMuscolare(gruppoSelezionato);
                personalTrainer.addEsercizi(esercizio);
                UserFactory.getInstance().addEsercizio(personalTrainer, esercizio);
                Intent home = new Intent(ModificaEsercizio.this, HomePersonalTrainer.class);
                home.putExtra(EXTRA_PT, personalTrainer);
                startActivity(home);
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.checkBox:
                if (petto.isChecked())
                    Toast.makeText(getApplicationContext(), "Petto", Toast.LENGTH_LONG).show();
                gruppoSelezionato = Esercizio.GruppoMuscolare.PETTO;
                break;
            case R.id.checkBox2:
                if (spalle.isChecked())
                    Toast.makeText(getApplicationContext(), "Spalle", Toast.LENGTH_LONG).show();
                gruppoSelezionato = Esercizio.GruppoMuscolare.SPALLE;
                break;
            case R.id.checkBox3:
                if (tricipiti.isChecked())
                    Toast.makeText(getApplicationContext(), "Tricipiti", Toast.LENGTH_LONG).show();
                gruppoSelezionato = Esercizio.GruppoMuscolare.TRICIPITI;
                break;
            case R.id.checkBox4:
                if (gambe.isChecked())
                    Toast.makeText(getApplicationContext(), "Gambe", Toast.LENGTH_LONG).show();
                gruppoSelezionato = Esercizio.GruppoMuscolare.GAMBE;
                break;
            case R.id.checkBox5:
                if (dorso.isChecked())
                    Toast.makeText(getApplicationContext(), "Dorso", Toast.LENGTH_LONG).show();
                gruppoSelezionato = Esercizio.GruppoMuscolare.DORSO;
                break;
            case R.id.checkBox6:
                if (bicipiti.isChecked())
                    Toast.makeText(getApplicationContext(), "Bicipiti", Toast.LENGTH_LONG).show();
                gruppoSelezionato = Esercizio.GruppoMuscolare.BICIPITI;
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
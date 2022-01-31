package com.example.palestratiium.PersonalActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.palestratiium.Login;
import com.example.palestratiium.R;
import com.example.palestratiium.adapter.Adapter_ListaEserciziHomePersonalT;
import com.example.palestratiium.classi.PersonalTrainer;
import com.example.palestratiium.classi.UserFactory;

import java.io.Serializable;

public class CreazioneSchedaAllenamento extends AppCompatActivity {

    Button indietro,add;
    PersonalTrainer personal;
    public static final String EXTRA_PT = "package com.example.palestratiium";
    public RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creazione_scheda_allenamento);

        indietro = findViewById(R.id.indietroCreazioneAllenamento);


        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_PT);

        if(obj instanceof PersonalTrainer){
            personal = (PersonalTrainer) obj;

        }else{
            personal = new PersonalTrainer();
        }


        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showResult = new Intent(CreazioneSchedaAllenamento.this, PanelloDiUpload.class);
                showResult.putExtra(EXTRA_PT, personal);
                startActivity(showResult);
            }
        });

    }

    /*public void init(){


        listaEserciziPt = UserFactory.getInstance().getEserciziPt(personal);



        if(listaEserciziPt.size()>0){
            mRecyclerView = findViewById(R.id.listRecyclerViewEserciziPt);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);

            adapter = new Adapter_ListaEserciziHomePersonalT(listaEserciziPt,this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(adapter);
        }

    }*/

}
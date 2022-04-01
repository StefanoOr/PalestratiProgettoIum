package com.example.palestratiium.PersonalActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.palestratiium.Login;
import com.example.palestratiium.R;
import com.example.palestratiium.adapter.AdapterListaCreazioneAllenamento;
import com.example.palestratiium.adapter.RecycleViewInterface;
import com.example.palestratiium.classi.Esercizio;
import com.example.palestratiium.classi.EsercizioSchedaAllenamento;
import com.example.palestratiium.classi.PersonalTrainer;
import com.example.palestratiium.classi.SchedeAllenamento;
import com.example.palestratiium.classi.User;
import com.example.palestratiium.classi.UserFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CreazioneSchedaAllenamento extends AppCompatActivity implements RecycleViewInterface {

    Button indietro,aggiungiEsercizioAllenamento,confermaAllenamento;
    EditText titolo,descrizione;

    public static final String EXTRA_PT = "package com.example.palestratiium";
    public RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;


    PersonalTrainer personal;
    List<EsercizioSchedaAllenamento> EsercizioAllenameto;
    SchedeAllenamento allenamento;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creazione_scheda_allenamento);

        indietro = findViewById(R.id.indietroCreazioneAllenamento);
        confermaAllenamento=findViewById(R.id.addSchedaAllnamento);
        aggiungiEsercizioAllenamento=findViewById(R.id.addEsercizioSchedaAllenamento);
        titolo=findViewById(R.id.nomeSchedaAllenamento);

        allenamento= new SchedeAllenamento();
        EsercizioAllenameto=new ArrayList<>();

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

        EsercizioSchedaAllenamento init = new EsercizioSchedaAllenamento();
        EsercizioAllenameto.add(init);

        mRecyclerView = findViewById(R.id.item_Lista_allenamento);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL,false);

        adapter = new AdapterListaCreazioneAllenamento(EsercizioAllenameto,this,this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);


        confermaAllenamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkInput()) {
                    allenamento.setNomeScheda(titolo.getText().toString());
                    allenamento.addListEsercizi(EsercizioAllenameto);
                    UserFactory.getInstance().addAllenamento(allenamento);


                    Context context = getApplicationContext();
                    CharSequence text = allenamento.getNomeScheda() + " Scheda aggiunta con successo";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Intent showResult = new Intent(CreazioneSchedaAllenamento.this, PanelloDiUpload.class);
                    showResult.putExtra(EXTRA_PT, personal);
                    startActivity(showResult);
                }
            }
        });

        aggiungiEsercizioAllenamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EsercizioAllenameto.add(new EsercizioSchedaAllenamento(new Esercizio(null),1,1,null));
                adapter.notifyDataSetChanged();

            }
        });
    }


    //todo esempio
    private void init() {

        EsercizioSchedaAllenamento prova3 = new EsercizioSchedaAllenamento(new Esercizio("4"),1,1,"4");

        EsercizioAllenameto.add(prova3);

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void OnItemClickModify(int position) {

    }



    public boolean checkInput() {

        //how many error occurred? We need to save the number
        int errors = 0;

        if(titolo.getText().toString() == null || titolo.getText().length() == 0){
            Context context = getApplicationContext();
            CharSequence text =  " Inserisci un titolo alla scheda Allenamento";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            titolo.setError("Perfavore inserisci un titolo all'allenamento");
            errors++;
        }else{
            titolo.setError(null);
        }


        return (errors == 0);
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
package com.example.palestratiium.UserActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.palestratiium.EserciziActivity;
import com.example.palestratiium.Login;
import com.example.palestratiium.R;
import com.example.palestratiium.adapter.AdapterListaCreazioneAllenamento;
import com.example.palestratiium.adapter.AdapterListaEserciziAllenamento;
import com.example.palestratiium.adapter.RecycleViewInterface;
import com.example.palestratiium.classi.Esercizio;
import com.example.palestratiium.classi.EsercizioSchedaAllenamento;
import com.example.palestratiium.classi.MyEnum;
import com.example.palestratiium.classi.User;
import com.example.palestratiium.classi.UserFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityEserciziLDellaListaAllenamento extends AppCompatActivity implements RecycleViewInterface {

    User user;
    String name;
    List<EsercizioSchedaAllenamento> esercizi;
    public static final String EXTRA_USER = "package com.example.palestratiium";


    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Esercizio> esercizio = new ArrayList<>();
    private ImageView back;
    private TextView toolbar_title;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esercizi_l_della_lista_allenamento);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);

        if(obj instanceof User){
            user = (User) obj;

        }else{
            user = new User();
        }

        toolbar_title = findViewById(R.id.toolbartag);
        back = findViewById(R.id.back_toolbar);
        name = getIntent().getStringExtra("NAME");

        esercizi= UserFactory.getInstance().getEserciziAllenamento(name);

        for (int i = 0; i< esercizi.size() ; i++){
            esercizio.add(esercizi.get(i).getEsercizio());
        }


        mRecyclerView = findViewById(R.id.reclycleViewListaEserciziDellAllenamento);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL,false);

        adapter = new AdapterListaEserciziAllenamento(esercizi,this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);

        toolbar_title.setText("Scheda Allenamento");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showResult = new Intent(ActivityEserciziLDellaListaAllenamento.this, ActivityListaSchedaAllenamentoUser.class);
                showResult.putExtra(EXTRA_USER, user);
                startActivity(showResult);
            }
        });







    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(ActivityEserciziLDellaListaAllenamento.this, EserciziActivity.class);

        intent.putExtra("NAME",esercizio.get(position).getNome());
        intent.putExtra("DESCRIPTION",esercizio.get(position).getDescrizioene());
        intent.putExtra("GRUPPOMUSCOLARE",esercizio.get(position).getGruppoMuscolare());
        intent.putExtra("RATING",(float)esercizio.get(position).getRating());
        intent.putExtra("DIFFICOLTA",esercizio.get(position).getDifficolta());
        intent.putExtra("ISALLENAMENTO",true);
        intent.putExtra("NOMEALLENAMENTO",name);
        //intent.putExtra("ISALLENAMENTO",false);
        if(esercizio.get(position).getImage()==null){
            intent.putExtra("IMAGEDAFAULT",esercizio.get(position).getImageDefault());
        }else {
            intent.putExtra("IMAGE", esercizio.get(position).getImage());
        }

        if(esercizio.get(position).getVideo()==null){
            intent.putExtra("VIDEODEFAULT",esercizio.get(position).getVideoDefault());
        }
        intent.putExtra(EXTRA_USER, user);

        startActivity(intent);
    }

    @Override
    public void OnItemClickModify(int position) {

    }
}
package com.example.palestratiium.UserActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.palestratiium.Login;
import com.example.palestratiium.R;
import com.example.palestratiium.adapter.AdapterListaCreazioneAllenamento;
import com.example.palestratiium.adapter.AdapterListaEserciziAllenamento;
import com.example.palestratiium.adapter.RecycleViewInterface;
import com.example.palestratiium.classi.EsercizioSchedaAllenamento;
import com.example.palestratiium.classi.MyEnum;
import com.example.palestratiium.classi.User;
import com.example.palestratiium.classi.UserFactory;

import java.io.Serializable;
import java.util.List;

public class ActivityEserciziLDellaListaAllenamento extends AppCompatActivity implements RecycleViewInterface {

    User user;
    String name;
    List<EsercizioSchedaAllenamento> esercizi;
    public static final String EXTRA_USER = "package com.example.palestratiium";


    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Button indietro;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esercizi_l_della_lista_allenamento);


        indietro=findViewById(R.id.buttonIndietroListaEserciziAllenamento);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);

        if(obj instanceof User){
            user = (User) obj;

        }else{
            user = new User();
        }

        name = getIntent().getStringExtra("NAME");

        esercizi= UserFactory.getInstance().getEserciziAllenamento(name);



        mRecyclerView = findViewById(R.id.reclycleViewListaEserciziDellAllenamento);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL,false);

        adapter = new AdapterListaEserciziAllenamento(esercizi,this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);



        indietro.setOnClickListener(new View.OnClickListener() {
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

    }

    @Override
    public void OnItemClickModify(int position) {

    }
}
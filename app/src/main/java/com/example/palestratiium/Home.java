/*
    Corso IUM - AA 2019 - 2020
    65577 - Daniele Stochino
    Esercitazione Bonus
 */

package com.example.palestratiium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import adapter.RecycleViewInterface;
import classi.Esercizio;
import classi.PersonalTrainer;
import classi.User;
import adapter.Adapter_ListaEserciziHome;
import classi.UserFactory;

public class Home extends AppCompatActivity implements RecycleViewInterface {


    User user;
    PersonalTrainer pt;
    Button profilo;
    TextView welcome, username, password, city, datetext, modifyPassword;
    Button logout;


    //TODO test esercizi da elimiare
    List<Esercizio> listaEserciziCard;

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    public static final String EXTRA_USER = "package com.example.palestratiium";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profilo = findViewById(R.id.profilo);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);

        if(obj instanceof User){
            user = (User) obj;

        }else{
            user = new User();
        }



        profilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showResult = new Intent(Home.this, Profilo.class);
                showResult.putExtra(EXTRA_USER, user);
                startActivity(showResult);
            }
        });



        listaEserciziCard = UserFactory.getInstance().getAllEsercizi();



        if(listaEserciziCard.size()>0){
            mRecyclerView = findViewById(R.id.listRecyclerView_esercizi);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new GridLayoutManager(this,2);
            adapter = new Adapter_ListaEserciziHome(listaEserciziCard,this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(adapter);
        }




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent showResult;
                switch (item.getItemId()) {
                    case R.id.profilo:

                        showResult = new Intent(Home.this, Profilo.class);
                        showResult.putExtra(EXTRA_USER, user);
                        startActivity(showResult);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.menuHome:
                        return true;

                }
                return false;
            }
        });




    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(Home.this, Esercizi.class);

        intent.putExtra("NAME",listaEserciziCard.get(position).getNome());
        intent.putExtra("DESCRIPTION",listaEserciziCard.get(position).getDescrizioene());
        intent.putExtra(EXTRA_USER, user);

        startActivity(intent);
    }
}

/*
    Corso IUM - AA 2019 - 2020
    65577 - Daniele Stochino
    Esercitazione Bonus
 */

package com.example.palestratiium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import adapter.RecycleViewInterface;
import classi.Esercizio;
import classi.User;
import adapter.Adapter_ListaEserciziHome;

public class Home extends AppCompatActivity implements RecycleViewInterface {

    User user;
    Button profilo;
    TextView welcome, username, password, city, datetext, modifyPassword;
    Button logout;


    //TODO test esercizi da elimiare
    private List<Esercizio> esercizi = new ArrayList<>();

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

      /*  welcome.setText("Welcome " + user.getUsername().toString() + "!");
        username.setText(user.getUsername());
        password.setText(user.getPassword());
        city.setText(user.getCity());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        datetext.setText(format.format(user.getDate().getTime()));

        modifyPassword.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                Intent modifyPage = new Intent(Home.this, ModifyPassword.class);
                modifyPage.putExtra(EXTRA_USER, user);
                startActivity(modifyPage);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = null;
                Intent login = new Intent(Home.this, Login.class);
                login.putExtra(EXTRA_USER, user);
                startActivity(login);
            }
        });
*/
        Esercizio uno = new Esercizio("a","aa","aaa",1);
        Esercizio due = new Esercizio("b","bb","bbb",2);
        Esercizio tre = new Esercizio("c","aa","aaa",1);
        Esercizio quattro = new Esercizio("d","bb","bbb",2);
        esercizi.add(uno);
        esercizi.add(due);
        esercizi.add(tre);
        esercizi.add(quattro);


        if(esercizi.size()>0){
            mRecyclerView = findViewById(R.id.listRecyclerView_esercizi);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new GridLayoutManager(this,3);
            adapter = new Adapter_ListaEserciziHome(esercizi,this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(adapter);
        }






    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(Home.this, Esercizi.class);
        intent.putExtra(EXTRA_USER, user);
        
        startActivity(intent);
    }
}

/*
    Corso IUM - AA 2019 - 2020
    65577 - Daniele Stochino
    Esercitazione Bonus
 */

package com.example.palestratiium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import adapter.Adapter_ListaEserciziHome;

public class Home extends AppCompatActivity {

    User user;
    TextView welcome, username, password, city, datetext, modifyPassword;
    Button logout;
//TODO test esercizi da elimiare
    private List<Esercizio> esercizi = new ArrayList<>();

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    public static final String EXTRA_USER = "package com.example.BonusLogin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);






        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);

        if(obj instanceof User){
            user = (User) obj;
        }else{
            user = new User();
        }

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
        esercizi.add(uno);
        esercizi.add(due);


        if(esercizi.size()>0){
            mRecyclerView = findViewById(R.id.listRecyclerView_esercizi);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new GridLayoutManager(this,3);
            adapter = new Adapter_ListaEserciziHome(esercizi);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(adapter);
        }






    }
}

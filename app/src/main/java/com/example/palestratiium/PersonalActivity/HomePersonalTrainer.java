package com.example.palestratiium.PersonalActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.palestratiium.EserciziActivity;
import com.example.palestratiium.Login;
import com.example.palestratiium.R;
import com.example.palestratiium.adapter.Adapter_ListaEserciziHomePersonalT;
import com.example.palestratiium.adapter.RecycleViewInterface;
import com.example.palestratiium.classi.Esercizio;
import com.example.palestratiium.classi.PersonalTrainer;
import com.example.palestratiium.classi.UserFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.List;

public class HomePersonalTrainer extends AppCompatActivity implements RecycleViewInterface {
    public static final String EXTRA_PT = "package com.example.palestratiium";
    PersonalTrainer personal;
    EditText ricerca;
    public RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    List<Esercizio> listaEserciziPt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_personal_trainer);




        ricerca = findViewById(R.id.ricercaPtEsercizi);


        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);

        if(obj instanceof PersonalTrainer){
            personal = (PersonalTrainer) obj;

        }else{
            personal = new PersonalTrainer();
        }

        ricerca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //filter(s.toString());


            }
        });

        init();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent showResult;
                switch (item.getItemId()) {
                    case R.id.profilo:

                        showResult = new Intent(HomePersonalTrainer.this, ProfiloPersonalTrainer.class);
                        showResult.putExtra(EXTRA_PT, personal);
                        startActivity(showResult);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.aggiungiVideo:

                        showResult = new Intent(HomePersonalTrainer.this, PanelloDiUpload.class);
                        showResult.putExtra(EXTRA_PT, personal);
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

    public void init(){


        listaEserciziPt = UserFactory.getInstance().getEserciziPt(personal);



        if(listaEserciziPt.size()>0){
            mRecyclerView = findViewById(R.id.listRecyclerViewEserciziPt);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);

            adapter = new Adapter_ListaEserciziHomePersonalT(listaEserciziPt,this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(adapter);
        }

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(HomePersonalTrainer.this, EserciziActivity.class);



        intent.putExtra("NAME",listaEserciziPt.get(position).getNome());
        intent.putExtra("DESCRIPTION",listaEserciziPt.get(position).getDescrizioene());
        intent.putExtra("GRUPPOMUSCOLARE",listaEserciziPt.get(position).getGruppoMuscolare());
        intent.putExtra(EXTRA_PT, personal);

        startActivity(intent);
    }

   /* private void filter(String text){
        ArrayList<User> filterdList = new ArrayList<>();
        for( User item : UserFactory.getInstance().getUsers()){
            if(item.getUsername().toLowerCase().contains(text.toLowerCase())){
                filterdList.add(item);
            }
        }
        UserFactory.getInstance().filterList(filterdList);

    }

    */
}
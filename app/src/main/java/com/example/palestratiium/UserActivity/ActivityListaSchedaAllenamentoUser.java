package com.example.palestratiium.UserActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.palestratiium.EserciziActivity;
import com.example.palestratiium.Login;
import com.example.palestratiium.PersonalActivity.HomePersonalTrainer;
import com.example.palestratiium.R;
import com.example.palestratiium.adapter.AdapterListaAllenamento;
import com.example.palestratiium.adapter.RecycleViewInterface;
import com.example.palestratiium.classi.SchedeAllenamento;
import com.example.palestratiium.classi.User;
import com.example.palestratiium.classi.UserFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.List;

public class ActivityListaSchedaAllenamentoUser extends AppCompatActivity implements RecycleViewInterface {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static final String EXTRA_USER = "package com.example.palestratiium";
    List<SchedeAllenamento> listaSchedeAllenamento;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheda_allenamento_user);

        listaSchedeAllenamento= UserFactory.getInstance().getListAllenamento();

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);

        if(obj instanceof User){
            user = (User) obj;

        }else{
            user = new User();
        }



        mRecyclerView = findViewById(R.id.item_Lista_allenamento_user);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        adapter = new AdapterListaAllenamento(listaSchedeAllenamento,this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.aggiungiVideo);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent showResult;
                switch (item.getItemId()) {
                    case R.id.profilo:

                        showResult = new Intent(ActivityListaSchedaAllenamentoUser.this, ProfiloUser.class);
                        showResult.putExtra(EXTRA_USER, user);
                        startActivity(showResult);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.aggiungiVideo:

                        showResult = new Intent(ActivityListaSchedaAllenamentoUser.this, WorkOut.class);
                        showResult.putExtra(EXTRA_USER, user);
                        startActivity(showResult);
                        overridePendingTransition(0, 0);

                        return true;

                    case R.id.menuHome:

                        showResult = new Intent(ActivityListaSchedaAllenamentoUser.this, Home.class);
                        showResult.putExtra(EXTRA_USER, user);
                        startActivity(showResult);
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });



    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(ActivityListaSchedaAllenamentoUser.this, ActivityEserciziLDellaListaAllenamento.class);


        intent.putExtra("NAME",listaSchedeAllenamento.get(position).getNomeScheda());
       // intent.putExtra("DESCRIPTION",listaSchedeAllenamento.get(position).getDescrizioene());

       // intent.putExtra("ESERCIZI", (Serializable) listaSchedeAllenamento.get(position).getEsercizi());




        intent.putExtra(EXTRA_USER, user);
        startActivity(intent);
    }

    @Override
    public void OnItemClickModify(int position) {

    }
}
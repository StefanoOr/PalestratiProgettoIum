

package com.example.palestratiium.UserActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.palestratiium.EserciziActivity;
import com.example.palestratiium.Login;
import com.example.palestratiium.R;
import com.example.palestratiium.adapter.CoachAdapter;
import com.example.palestratiium.classi.MyEnum;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.palestratiium.adapter.RecycleViewInterface;
import com.example.palestratiium.classi.Esercizio;
import com.example.palestratiium.classi.PersonalTrainer;
import com.example.palestratiium.classi.User;
import com.example.palestratiium.adapter.Adapter_ListaEserciziHome;
import com.example.palestratiium.classi.UserFactory;

public class Home extends AppCompatActivity implements RecycleViewInterface {


    User user;
    PersonalTrainer coach;
    private CoachAdapter coachAdapter;
    private TextView nomeA;

    Spinner spinnerCoach,spinnerGruppoMuscolare;

    String clickedCountryName;




    List<Esercizio> listaEserciziCard;


    //todo recycle view per le card esercizi
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;

    MyEnum gruppoMuscolare=MyEnum.TUTTI;
    public static final String EXTRA_USER = "package com.example.palestratiium";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        coachAdapter = new CoachAdapter(this, (ArrayList<PersonalTrainer>) UserFactory.getInstance().getPersonal());

        spinnerCoach = findViewById(R.id.spinner_coachUser1);
        spinnerGruppoMuscolare = findViewById(R.id.spinner_gruppo_muscolare);
        nomeA = findViewById(R.id.toolbartag);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);

        if(obj instanceof User){
            user = (User) obj;

        }else{
            user = new User();
        }

        spinnerCoach.setAdapter(coachAdapter);
        spinnerCoach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                coach = (PersonalTrainer) parent.getItemAtPosition(position);

                System.out.println(clickedCountryName);
                adapterCard();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        nomeA.setText("Home");


        spinnerGruppoMuscolare.setAdapter(new ArrayAdapter<MyEnum>(this, android.R.layout.simple_spinner_item, MyEnum.values()));
        spinnerGruppoMuscolare.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gruppoMuscolare = (MyEnum) parent.getItemAtPosition(position);

                adapterCard();
                System.out.println(clickedCountryName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent showResult;
                switch (item.getItemId()) {
                    case R.id.profilo:

                        showResult = new Intent(Home.this, ProfiloUser.class);
                        showResult.putExtra(EXTRA_USER, user);
                        startActivity(showResult);
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.aggiungiVideo:

                        showResult = new Intent(Home.this, WorkOut.class);
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
        Intent intent = new Intent(Home.this, EserciziActivity.class);

        intent.putExtra("NAME",listaEserciziCard.get(position).getNome());
        intent.putExtra("DESCRIPTION",listaEserciziCard.get(position).getDescrizioene());
        intent.putExtra("GRUPPOMUSCOLARE",listaEserciziCard.get(position).getGruppoMuscolare());
        intent.putExtra("RATING",(float)listaEserciziCard.get(position).getRating());
        intent.putExtra("DIFFICOLTA",listaEserciziCard.get(position).getDifficolta());
        //intent.putExtra("ISALLENAMENTO",false);
        if(listaEserciziCard.get(position).getImage()==null){
            intent.putExtra("IMAGEDAFAULT",listaEserciziCard.get(position).getImageDefault());
        }else {
            intent.putExtra("IMAGE", listaEserciziCard.get(position).getImage());
        }

        if(listaEserciziCard.get(position).getVideo()==null){
            intent.putExtra("VIDEODEFAULT",listaEserciziCard.get(position).getVideoDefault());
        }
        intent.putExtra(EXTRA_USER, user);

        startActivity(intent);
    }

    @Override
    public void OnItemClickModify(int position) {

    }


    public void adapterCard (){


        listaEserciziCard = UserFactory.getInstance().lisTest(coach,gruppoMuscolare);

            mRecyclerView = findViewById(R.id.listRecyclerView_esercizi);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new GridLayoutManager(Home.this,2);

            adapter = new Adapter_ListaEserciziHome(listaEserciziCard,Home.this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(adapter);
       // }
    }
}

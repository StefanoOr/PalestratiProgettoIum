package com.example.palestratiium.UserActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.palestratiium.Login;
import com.example.palestratiium.R;
import com.example.palestratiium.adapter.AdapterListCoach;
import com.example.palestratiium.adapter.AdapterListaAllenamento;
import com.example.palestratiium.adapter.RecycleViewInterface;
import com.example.palestratiium.classi.PersonalTrainer;
import com.example.palestratiium.classi.User;
import com.example.palestratiium.classi.UserFactory;

import java.io.Serializable;
import java.util.List;

public class ActivityListaPt extends AppCompatActivity implements RecycleViewInterface {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    User user;


    public static final String EXTRA_USER = "package com.example.palestratiium";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pt);


        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);

        if(obj instanceof User){
            user = (User) obj;

        }else{
            user = new User();
        }


        List<PersonalTrainer> listaCoach = UserFactory.getInstance().getCoach();


        mRecyclerView = findViewById(R.id.RecycleViewListaCoach);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        adapter = new AdapterListCoach(listaCoach,this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void OnItemClickModify(int position) {

    }
}
package com.example.palestratiium.UserActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.palestratiium.Login;
import com.example.palestratiium.R;
import com.example.palestratiium.adapter.AdapterListaAllenamento;
import com.example.palestratiium.adapter.RecycleViewInterface;
import com.example.palestratiium.classi.SchedeAllenamento;
import com.example.palestratiium.classi.User;
import com.example.palestratiium.classi.UserFactory;

import java.io.Serializable;
import java.util.List;

public class ActivitySchedaAllenamentoUser extends AppCompatActivity implements RecycleViewInterface {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

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



        mRecyclerView = findViewById(R.id.item_Lista_allenamento);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        adapter = new AdapterListaAllenamento(listaSchedeAllenamento,this);
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
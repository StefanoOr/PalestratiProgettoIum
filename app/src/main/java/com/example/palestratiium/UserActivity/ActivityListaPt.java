package com.example.palestratiium.UserActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.palestratiium.Login;
import com.example.palestratiium.ModifyPassword;
import com.example.palestratiium.PersonalActivity.ProfiloPersonalTrainer;
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
    private ImageView back;
    private TextView toolbar_title;

    User user;


    public static final String EXTRA_USER = "package com.example.palestratiium";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pt);


        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);

        toolbar_title=findViewById(R.id.toolbartag);
        back=findViewById(R.id.back_toolbar);

        if(obj instanceof User){
            user = (User) obj;

        }else{
            user = new User();
        }


        List<PersonalTrainer> listaCoach = UserFactory.getInstance().getCoach();
        listaCoach.remove(0);

        mRecyclerView = findViewById(R.id.RecycleViewListaCoach);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        adapter = new AdapterListCoach(listaCoach,this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);

        toolbar_title.setText("Lista Personal Trainer");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent home = new Intent(ActivityListaPt.this, WorkOut.class);
                home.putExtra(EXTRA_USER, user);
                startActivity(home);
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
package com.example.palestratiium.UserActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.palestratiium.Login;
import com.example.palestratiium.ModifyPassword;
import com.example.palestratiium.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

import com.example.palestratiium.classi.User;

public class ProfiloUser extends AppCompatActivity {

    User user;
    TextView modify_password, username, nome, peso_attuale, altezza_attuale,eta;
    Button home;
    ImageView edit_peso, edit_altezza;
    int tmp_peso, tmp_altezza;
    String tmp_progress_peso, tmp_progress_altezza;
    TextView peso_attuale_tmp, altezza_attuale_tmp;
    SeekBar seek_peso, seek_altezza;
    Button conferma_peso, nega_peso, conferma_altezza, nega_altezza,logout;
    AlertDialog dialog;


    public static final String EXTRA_USER = "package com.example.palestratiium";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        modify_password = findViewById(R.id.modify_password);
        username = findViewById(R.id.textView9);
        nome = findViewById(R.id.textView11);
        home = findViewById(R.id.back_home);
        edit_peso = findViewById(R.id.edit_peso);
        edit_altezza = findViewById(R.id.edit_altezza);
        peso_attuale = findViewById(R.id.peso_attuale);
        altezza_attuale = findViewById(R.id.altezza_attuale);
        eta=findViewById(R.id.eta_user);
        logout=findViewById(R.id.logoutUser);


        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);

        if(obj instanceof User){
            user = (User) obj;
        }else{
            user = new User();
        }

        username.setText(user.getUsername());
        nome.setText(user.getUsername());
        eta.setText(user.getAge());

        altezza_attuale.setText( user.getAltezza());
        peso_attuale.setText(user.getPeso());

        edit_peso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(ProfiloUser.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_modifica_peso, null);

                peso_attuale_tmp = mView.findViewById(R.id.current_peso);
                seek_peso = mView.findViewById(R.id.seekbar_peso);
                conferma_peso = mView.findViewById(R.id.conferma_peso);
                nega_peso =  mView.findViewById(R.id.indietro_peso);

                alert.setView(mView);
                dialog = alert.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                seek_peso.setProgress(80);
                seek_peso.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        tmp_peso = progress;
                        tmp_progress_peso =  String.valueOf(tmp_peso);
                        peso_attuale_tmp.setText(tmp_progress_peso);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                nega_peso.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                conferma_peso.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user.setPeso(peso_attuale_tmp.getText().toString());
                        peso_attuale.setText(user.getPeso() + " Kg");
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = null;
                Intent login = new Intent(ProfiloUser.this, Login.class);
                login.putExtra(EXTRA_USER, user);
                startActivity(login);
            }
        });


        edit_altezza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(ProfiloUser.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_modifica_altezza, null);

                altezza_attuale_tmp = mView.findViewById(R.id.current_altezza);
                seek_altezza = mView.findViewById(R.id.seekbar_altezza);
                conferma_altezza = mView.findViewById(R.id.conferma_altezza);
                nega_altezza =  mView.findViewById(R.id.indietro_altezza);

                alert.setView(mView);
                dialog = alert.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                seek_altezza.setProgress(80);
                seek_altezza.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        tmp_altezza = progress;
                        tmp_progress_altezza =  String.valueOf(tmp_altezza);
                        altezza_attuale_tmp.setText(tmp_progress_altezza);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                nega_altezza.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                conferma_altezza.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user.setAltezza(altezza_attuale_tmp.getText().toString());

                        altezza_attuale.setText(user.getAltezza() + " cm");
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        modify_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showResult = new Intent(ProfiloUser.this, ModifyPassword.class);
                showResult.putExtra(EXTRA_USER, user);
                startActivity(showResult);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(ProfiloUser.this, Home.class);
                home.putExtra(EXTRA_USER, user);
                startActivity(home);
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.profilo);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent showResult;
                switch (item.getItemId()) {
                    case R.id.menuHome:

                        showResult = new Intent(ProfiloUser.this, Home.class);
                        showResult.putExtra(EXTRA_USER, user);
                        startActivity(showResult);
                        overridePendingTransition(0, 0);
                        //startActivity(new Intent(Home.this,Calendario.class));

                        return true;

                    case R.id.profilo:

                        return true;

                }
                return false;
            }
        });
    }



}
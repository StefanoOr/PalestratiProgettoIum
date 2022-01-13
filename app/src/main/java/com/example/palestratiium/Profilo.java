package com.example.palestratiium;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.Serializable;

import classi.User;

public class Profilo extends AppCompatActivity {

    User user;
    TextView modify_password, username, nome, peso_attuale;
    Button home;
    ImageView edit_peso, edit_altezza;
    int tmp_peso;
    String tmp_progress;
    TextView peso_attuale_tmp;
    SeekBar seek_peso;
    Button conferma_peso, nega_peso;
    AlertDialog dialog;

    public static final String EXTRA_USER = "package com.example.BonusLogin";


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

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);

        if(obj instanceof User){
            user = (User) obj;
        }else{
            user = new User();
        }

        username.setText(user.getUsername());
        nome.setText(user.getUsername());

        edit_peso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(Profilo.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_modifica_peso, null);

                peso_attuale_tmp = mView.findViewById(R.id.peso_attuale);
                seek_peso = mView.findViewById(R.id.seekbar_peso);
                conferma_peso = mView.findViewById(R.id.conferma_peso);
                nega_peso =  mView.findViewById(R.id.indietro_peso);

                alert.setView(mView);
                dialog = alert.create();
                dialog.setCanceledOnTouchOutside(false);

                seek_peso.setMax(200);
                seek_peso.setProgress(80);
                seek_peso.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        tmp_peso = progress;
                        tmp_progress =  String.valueOf(tmp_peso);
                        peso_attuale_tmp.setText(tmp_progress);
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
                        peso_attuale.setText(peso_attuale_tmp.getText().toString());
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        modify_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showResult = new Intent(Profilo.this, ModifyPassword.class);
                showResult.putExtra(EXTRA_USER, user);
                startActivity(showResult);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(Profilo.this, Home.class);
                home.putExtra(EXTRA_USER, user);
                startActivity(home);
            }
        });
    }
}
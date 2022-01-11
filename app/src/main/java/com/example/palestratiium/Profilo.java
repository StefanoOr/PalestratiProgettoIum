package com.example.palestratiium;

import androidx.appcompat.app.AppCompatActivity;

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
    Button home, indietro_peso, conferma_peso, conferma_altezza;
    ImageView edit_peso, edit_altezza;
    Dialog dialog;
    Context context;
    SeekBar seekBar_peso, seekBar_altezza;

    public static final String EXTRA_USER = "package com.example.BonusLogin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        context = this;
        dialog = new Dialog(context);

        modify_password = findViewById(R.id.modify_password);
        username = findViewById(R.id.textView9);
        nome = findViewById(R.id.textView11);
        home = findViewById(R.id.back_home);
        edit_peso = findViewById(R.id.edit_peso);
        edit_altezza = findViewById(R.id.edit_altezza);
        indietro_peso = findViewById(R.id.indietro_peso);
        conferma_peso = findViewById(R.id.conferma_peso);
        peso_attuale = findViewById(R.id.peso_attuale);
        seekBar_peso = findViewById(R.id.seekbar_peso);

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
                dialog.show();

                dialog.setContentView(R.layout.activity_modifica_peso);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    dialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.custom_dialog));
                }

                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

                conferma_peso.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int peso;
                        peso = seekBar_peso.getProgress();
                        peso_attuale.setText(peso);
                    }
                });

                dialog.setCancelable(false);
            }
        });

        edit_altezza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();

                dialog.setContentView(R.layout.activity_modifica_peso);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    dialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.custom_dialog));
                }

                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
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
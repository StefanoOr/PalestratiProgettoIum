package com.example.palestratiium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.Serializable;

import com.example.palestratiium.PersonalActivity.HomePersonalTrainer;
import com.example.palestratiium.UserActivity.Home;
import com.example.palestratiium.classi.Esercizio;
import com.example.palestratiium.classi.MyEnum;
import com.example.palestratiium.classi.PersonalTrainer;
import com.example.palestratiium.classi.User;
import com.example.palestratiium.classi.UserFactory;

public class EserciziActivity extends AppCompatActivity implements Serializable{

    User user;
    PersonalTrainer personalTrainer;
    Button back;
    RatingBar ratingBar;
    String name;
    float voteInt;

    private  Boolean vote=false;

    VideoView videoView;
    TextView nomeEsercizio,descrizioneEsercizio,gruppoMuscolare,difficoltaEsercizio;
    boolean isPt,isUser;
    public static final String EXTRA_USER = "package com.example.palestratiium";
    public static final String EXTRA_PT = "package com.example.palestratiium";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esercizi);


        isPt=false;
        isUser=false;

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.EXTRA_USER);
        Serializable objT = intent.getSerializableExtra(Login.EXTRA_PT);

        name = getIntent().getStringExtra("NAME");
        String descrizione = getIntent().getStringExtra("DESCRIPTION");
        String difficolta = getIntent().getStringExtra("DIFFICOLTA");
        MyEnum gruppo = (MyEnum) intent.getSerializableExtra("GRUPPOMUSCOLARE");
        String video = getIntent().getStringExtra("VIDEO");
        String image = getIntent().getStringExtra("IMAGE");
        float rate = getIntent().getExtras().getFloat("RATING");


        nomeEsercizio= findViewById(R.id.esercizio_title);
        descrizioneEsercizio = findViewById(R.id.text_corpo_descrizione);
        back = findViewById(R.id.back_button);
        gruppoMuscolare = findViewById(R.id.text_corpo_gruppo_muscolare);
        difficoltaEsercizio = findViewById(R.id.text_corpo_difficolta);
        videoView = findViewById(R.id.videoView);
        ratingBar = findViewById(R.id.ratingBar);


        if(video != null) {
            setVideoToVideoView(video);
        }

        nomeEsercizio.setText(name);
        descrizioneEsercizio.setText(descrizione);
        gruppoMuscolare.setText(gruppo.name());
        difficoltaEsercizio.setText(difficolta);
        ratingBar.setRating(rate);

        if(obj instanceof User){
            user = (User) obj;
            isUser=true;
        }else{
            user = new User();
        }

        if(objT instanceof PersonalTrainer){
            personalTrainer = (PersonalTrainer) objT;
            isPt=true;

        }else{
            personalTrainer = new PersonalTrainer();
        }



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vote){
                    Esercizio esercizio=UserFactory.getInstance().getEsercizioNome(name);
                    esercizio.setRating(voteInt);
                }

                if(isUser) {
                    Intent home = new Intent(EserciziActivity.this, Home.class);
                    home.putExtra(EXTRA_USER, user);
                    startActivity(home);
                }else if(isPt) {
                    Intent home = new Intent(EserciziActivity.this, HomePersonalTrainer.class);
                    home.putExtra(EXTRA_PT, personalTrainer);
                    startActivity(home);
                }
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                vote=true;

                voteInt= rating;
            }
        });


    }

    private void setVideoToVideoView(String v){
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        Uri vUri = Uri.parse(v);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(vUri);
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.pause();
            }
        });
    }
}
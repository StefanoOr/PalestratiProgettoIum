package com.example.palestratiium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.Serializable;

import com.example.palestratiium.PersonalActivity.HomePersonalTrainer;
import com.example.palestratiium.UserActivity.ActivityEserciziLDellaListaAllenamento;
import com.example.palestratiium.UserActivity.Home;
import com.example.palestratiium.classi.Esercizio;
import com.example.palestratiium.classi.MyEnum;
import com.example.palestratiium.classi.PersonalTrainer;
import com.example.palestratiium.classi.User;
import com.example.palestratiium.classi.UserFactory;

public class EserciziActivity extends AppCompatActivity implements Serializable {

    User user;
    PersonalTrainer personalTrainer;
    Button back;
    RatingBar ratingBar;
    String name;
    float voteInt;

    private  Boolean vote=false;

    VideoView videoView;
    ImageView imageView;
    TextView nomeEsercizio,descrizioneEsercizio,gruppoMuscolare,difficoltaEsercizio,text_vota;
    boolean isPt,isUser;
    public static final String EXTRA_USER = "package com.example.palestratiium";
    public static final String EXTRA_PT = "package com.example.palestratiium";

    String nomeallenamento;

    @RequiresApi(api = Build.VERSION_CODES.Q)
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
        int videoDefault=intent.getExtras().getInt("VIDEODEFAULT");
        int imageDefault = intent.getExtras().getInt("IMAGEDAFAULT");
        float rate = getIntent().getExtras().getFloat("RATING");



        final boolean isAllenamento= getIntent().getBooleanExtra("ISALLENAMENTO",false);

        if (isAllenamento){
            nomeallenamento = getIntent().getStringExtra("NOMEALLENAMENTO");
        }


        nomeEsercizio= findViewById(R.id.esercizio_title);
        descrizioneEsercizio = findViewById(R.id.text_corpo_descrizione);
        back = findViewById(R.id.back_button);
        gruppoMuscolare = findViewById(R.id.text_corpo_gruppo_muscolare);
        difficoltaEsercizio = findViewById(R.id.text_corpo_difficolta);
        videoView = findViewById(R.id.videoView);
        imageView = findViewById(R.id.imageView);
        ratingBar = findViewById(R.id.ratingBar);
        text_vota= findViewById(R.id.text_vota);


        if(video != null) {
            setVideoToVideoView(video);
        }

        if(image!=null) {
            setImageToImageView(image);
        }

        if(imageDefault>0){
            imageView.setImageResource(imageDefault);
        }

        if(videoDefault>0) {
            setVideoToVideoViewDefault(videoDefault);

        }





        nomeEsercizio.setText(name);
        descrizioneEsercizio.setText(descrizione);
        descrizioneEsercizio.setMovementMethod(new ScrollingMovementMethod());
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
            CharSequence a ="Media voti";
            text_vota.setText(a);
            ratingBar.setActivated(false);

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

                    if(isAllenamento){
                        Intent allenamento = new Intent(EserciziActivity.this, ActivityEserciziLDellaListaAllenamento.class);
                        allenamento.putExtra("NAME",nomeallenamento);
                        allenamento.putExtra(EXTRA_USER, user);
                        startActivity(allenamento);
                    }else {

                        Intent home = new Intent(EserciziActivity.this, Home.class);
                        home.putExtra(EXTRA_USER, user);
                        startActivity(home);
                    }
                }else if(isPt) {
                    Intent home = new Intent(EserciziActivity.this, HomePersonalTrainer.class);
                    home.putExtra(EXTRA_PT, personalTrainer);
                    startActivity(home);
                }
            }
        });


        if(isUser) {

            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    vote = true;

                    voteInt = rating;
                }
            });
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void setVideoToVideoViewDefault(int videoDefault) {
        MediaController mediaController = new MediaController(this);


String uriPath="android.resource://"+ getPackageName()+"/"+  videoDefault ;
Uri uri=Uri.parse(uriPath);


        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        mediaController.setAnchorView(videoView);
        videoView.requestFocus();
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.pause();
            }
        });
    }

    private void setImageToImageView(String image) {
        Uri iUri = Uri.parse(image);
        imageView.setImageURI(iUri);
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
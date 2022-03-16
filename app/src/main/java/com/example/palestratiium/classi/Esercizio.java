package com.example.palestratiium.classi;

import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Esercizio implements Serializable {

    private String nome;
    private String descrizioene;
    private List<Float> rating= new ArrayList<>();
    private ImageView imagineMiniatura;


    public  enum GruppoMuscolare {
        PETTO,
        DORSO,
        GAMBE,
        TRICIPITI,
        BICIPITI,
        SPALLE
    }

    private MyEnum gruppoMuscolare;
    private String difficolta;
    private String video;

    public Esercizio(String nome, String descrizioene, MyEnum gruppoMuscolare, String difficolta,String video,ImageView imageView){
        this.nome=nome;
        this.descrizioene= descrizioene;
        this.gruppoMuscolare = gruppoMuscolare;
        this.difficolta=difficolta;
        this.video=video;
        this.imagineMiniatura=imageView;
    }


    public float getRating(){
        float rate=0;
        for (int i=0 ; i<rating.size(); i++){
            rate+=rating.get(i);
        }
        if(rate!=0){
            return rate/rating.size();

        }
        return rate;
    }

    public void setRating(float rate){
        rating.add(rate);
    }


    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Esercizio(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizioene() {
        return descrizioene;
    }

    public void setDescrizioene(String descrizioene) {
        this.descrizioene = descrizioene;
    }



    public MyEnum getGruppoMuscolare() {
        return gruppoMuscolare;
    }

    public void setGruppoMuscolare(MyEnum gruppoMuscolare) {
        this.gruppoMuscolare = gruppoMuscolare;
    }

    public String getDifficolta() {
        return difficolta;
    }

    public void setDifficolta(String difficolta) {
        this.difficolta = difficolta;
    }
}

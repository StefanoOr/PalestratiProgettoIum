package com.example.palestratiium.classi;

import android.net.Uri;

import java.io.Serializable;
import java.net.URI;

public class Esercizio implements Serializable {

    private  String nome;
    private  String descrizioene;

    private  String gruppoMuscolare;
    private int difficolta;



    private Uri video;

    public Esercizio(String nome, String descrizioene, String gruppoMuscolare, int difficolta,Uri video){
        this.nome=nome;
        this.descrizioene=descrizioene;
        this.gruppoMuscolare=gruppoMuscolare;
        this.difficolta=difficolta;
        this.video=video;
    }


    public Uri getVideo() {
        return video;
    }

    public void setVideo(Uri video) {
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

    public String getGruppoMuscolare() {
        return gruppoMuscolare;
    }

    public void setGruppoMuscolare(String gruppoMuscolare) {
        this.gruppoMuscolare = gruppoMuscolare;
    }

    public int getDifficolta() {
        return difficolta;
    }

    public void setDifficolta(int difficolta) {
        this.difficolta = difficolta;
    }
}

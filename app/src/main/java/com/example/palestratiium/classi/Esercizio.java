package com.example.palestratiium.classi;

import java.io.Serializable;

public class Esercizio implements Serializable {

    private String nome;
    private String descrizioene;

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

    public Esercizio(String nome, String descrizioene, MyEnum gruppoMuscolare, String difficolta,String video){
        this.nome=nome;
        this.descrizioene= descrizioene;
        this.gruppoMuscolare = gruppoMuscolare;
        this.difficolta=difficolta;
        this.video=video;
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

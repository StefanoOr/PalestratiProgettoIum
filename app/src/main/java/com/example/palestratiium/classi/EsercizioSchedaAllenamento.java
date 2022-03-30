package com.example.palestratiium.classi;

import java.io.Serializable;

public class EsercizioSchedaAllenamento implements Serializable {

    private Esercizio esercizio;
    private int ripetizioni;
    private  int serie;
    private  String tempoRecupero;

    public EsercizioSchedaAllenamento(Esercizio esercizio, int ripetizioni, int serie, String tempoRecupero) {
        this.esercizio = esercizio;
        this.ripetizioni = ripetizioni;
        this.serie = serie;
        this.tempoRecupero = tempoRecupero;
    }


    public Esercizio getEsercizio() {
        return esercizio;
    }

    public void setEsercizio(Esercizio esercizio) {
        this.esercizio = esercizio;
    }

    public int getRipetizioni() {
        return ripetizioni;
    }

    public void setRipetizioni(int ripetizioni) {
        this.ripetizioni = ripetizioni;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public String getTempoRecupero() {
        return tempoRecupero;
    }

    public void setTempoRecupero(String tempoRecupero) {
        this.tempoRecupero = tempoRecupero;
    }

    public String getNome() {
        return esercizio.getNome();
    }
}

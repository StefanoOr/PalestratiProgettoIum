package com.example.palestratiium.classi;

import java.util.List;

public class SchedeAllenamento {

    private  String nomeScheda;
    private  String descrizione;
    private List<Esercizio> listaEsercizi;



    public SchedeAllenamento(String nomeScheda,String descrizione,List<Esercizio> listaEsercizi){

        this.nomeScheda=nomeScheda;
        this.descrizione=descrizione;
        this.listaEsercizi=listaEsercizi;

    }

    public String getNomeScheda() {
        return nomeScheda;
    }

    public void setNomeScheda(String nomeScheda) {
        this.nomeScheda = nomeScheda;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Esercizio> getEsercizi() {
        return listaEsercizi;
    }

    public void setEsercizi(List<Esercizio> ListaEsercizi) {
        this.listaEsercizi = ListaEsercizi;
    }


    public void addEsercizio(Esercizio esercizio){
        this.listaEsercizi.add(esercizio);
    }

    public void removeEsericizio(int position){
        this.listaEsercizi.remove(position);
    }






}

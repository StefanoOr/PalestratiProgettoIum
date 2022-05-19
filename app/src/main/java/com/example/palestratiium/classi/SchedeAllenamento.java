package com.example.palestratiium.classi;

import java.util.List;

public class SchedeAllenamento {

    private  String nomeScheda;
    private  String descrizione;
    private List<EsercizioSchedaAllenamento> listaEsercizi;



    public SchedeAllenamento(String nomeScheda,String descrizione,List<EsercizioSchedaAllenamento> listaEsercizi){

        this.nomeScheda=nomeScheda;
        this.descrizione=descrizione;
        this.listaEsercizi=listaEsercizi;

    }

    public SchedeAllenamento(){

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

    public List<EsercizioSchedaAllenamento> getEsercizi() {
        return listaEsercizi;
    }


    public EsercizioSchedaAllenamento getEsercizio(int position){
        return this.listaEsercizi.get(position);
    }

    public void setEsercizi(List<EsercizioSchedaAllenamento> ListaEsercizi) {
        this.listaEsercizi = ListaEsercizi;
    }


    public void addEsercizio(EsercizioSchedaAllenamento esercizio){
        this.listaEsercizi.add(esercizio);
    }

    public void removeEsericizio(int position){
        this.listaEsercizi.remove(position);
    }


    public void addListEsercizi(List<EsercizioSchedaAllenamento> lista) {
        this.listaEsercizi=lista;
    }
}

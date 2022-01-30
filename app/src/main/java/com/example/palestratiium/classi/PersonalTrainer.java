package com.example.palestratiium.classi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PersonalTrainer implements Serializable {

    private String username;
    private String password;
    private String city;
    private Calendar date;

    private String team;
    private boolean coach;

    private List<Esercizio> listaEsercizi= new ArrayList<>();




    public PersonalTrainer(){
        this.coach=true;
    }

    public PersonalTrainer(String username, String password, String city, Calendar date, String team){
        this.username=username;
        this.password=password;
        this.city=city;
        this.date=date;
        this.team=team;
        this.coach=true;

    }


    public void initEsercizi(List<Esercizio> ese){

            listaEsercizi.addAll(ese);

    }
    public void addEsercizi(Esercizio esercizio){
        listaEsercizi.add(esercizio);

    }

    public List<Esercizio> getAllListaEsercizi(){
        return listaEsercizi;
    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }




}

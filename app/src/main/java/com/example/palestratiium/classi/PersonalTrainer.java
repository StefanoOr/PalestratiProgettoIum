package com.example.palestratiium.classi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PersonalTrainer implements Serializable {

    private String username;
    private String password;
    private String imageProfile;
    private String city;
    private Calendar date;

    private String team;
    private boolean coach;


    String instagram;

    private List<Esercizio> listaEsercizi= new ArrayList<>();

    public PersonalTrainer(){
        this.coach=true;
    }

    public PersonalTrainer(String username, String password, String imageProfile, String city, Calendar date, String team, String instagram){
        this.username=username;
        this.password=password;
        this.imageProfile = imageProfile;
        this.city=city;
        this.date=date;
        this.team=team;
        this.coach=true;
        this.instagram=instagram;

    }



    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
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

    public Esercizio getEsercizioString (String ex){
        for(int i=0;i<listaEsercizi.size();i++){
            if(listaEsercizi.get(i).getNome().equals(ex)){
                return listaEsercizi.get(i);
            }
        }
        return null;
    }

    public Esercizio getEsercizio(int index) {
        return listaEsercizi.get(index);

    }


    public String getAge(){
        Calendar today = Calendar.getInstance();



        int age = today.get(Calendar.YEAR) - date.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < date.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        return new Integer(age).toString();

    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }
}

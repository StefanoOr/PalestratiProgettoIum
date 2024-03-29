

package com.example.palestratiium.classi;

import java.io.Serializable;
import java.util.Calendar;

public class User implements Serializable {

    private String username;
    private String password;
    private String city;
    private Calendar date;
    private String peso;
    private String altezza;
    private String genere;

    public User(){
        this.setUsername("");
        this.setPassword("");
    }


    public User(String username, String password){
        this.setUsername(username);
        this.setPassword(password);
    }

    public void setGenere(String genere){
        this.genere=genere;
    }

    public String getGenere(){
        return this.genere;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltezza() {
        return altezza;
    }

    public void setAltezza(String altezza) {
        this.altezza = altezza;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
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


    public String getAge(){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();



        int age = today.get(Calendar.YEAR) - date.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < date.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

       return new Integer(age).toString();



    }

}

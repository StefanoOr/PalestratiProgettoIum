/*
    Corso IUM - AA 2019 - 2020
    65577 - Daniele Stochino
    Esercitazione Bonus
 */

package Classi;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable {

    private String username;
    private String password;
    private String city;
    private Calendar date;

    public User(){
        this.setUsername("");
        this.setPassword("");
    }


    public User(String username, String password){
        this.setUsername(username);
        this.setPassword(password);
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

}

/*
    Corso IUM - AA 2019 - 2020
    65577 - Daniele Stochino
    Esercitazione Bonus
 */

package com.example.palestratiium;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserFactory {

    private static UserFactory singleton;
    private List<User> users = new ArrayList<>();
    private List<PersonalTrainer> coach = new ArrayList<>();
    DatePickerFragment datePickerFragment = new DatePickerFragment();

    private UserFactory() {
    }


    public static UserFactory getInstance() {

        if (singleton == null) {
            singleton = new UserFactory();
        }
        return singleton;
    }

    public List<PersonalTrainer> getPersonal(){
        PersonalTrainer atzeni = new PersonalTrainer();
        atzeni.setUsername("Atezenis");
        atzeni.setPassword("atz");
        atzeni.setCity("Cagliari");

        atzeni.getDate().set(Calendar.YEAR,1992);
        atzeni.getDate().set(Calendar.MONTH, Calendar.MARCH);
        atzeni.getDate().set(Calendar.DAY_OF_MONTH,16);
        atzeni.setTeam("Atzenis Team");


        coach.add(atzeni);
        return  coach;
    }


    public List<User> getUsers() {




        User user = new User();

        user.setUsername("ste");
        user.setPassword("a");
        user.setCity("Girasole");
        user.setDate(Calendar.getInstance());
        user.getDate().set(Calendar.YEAR,1992);
        user.getDate().set(Calendar.MONTH, Calendar.MARCH);
        user.getDate().set(Calendar.DAY_OF_MONTH,16);
        users.add(user);



        return users;
    }

    public void addUsers(User u) {
        users.add(u);
    }





    public void addPersonal(PersonalTrainer u){coach.add(u);}

    public void modifyPassPersonal(PersonalTrainer personal){
        for(PersonalTrainer u : coach){
            if (u.getUsername().equals(personal.getUsername()) && u.getCity().equals(personal.getCity())){
                coach.remove(u);
                coach.add(personal);
            }
        }
    }

    public void modifyPass(User user) {
        for (User u: users) {
            if (u.getUsername().equals(user.getUsername()) && u.getCity().equals(user.getCity())){
                users.remove(u);
                users.add(user);
            }
        }
    }

}

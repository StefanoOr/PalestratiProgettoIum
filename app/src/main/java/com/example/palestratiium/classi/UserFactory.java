/*
    Corso IUM - AA 2019 - 2020
    65577 - Daniele Stochino
    Esercitazione Bonus
 */

package com.example.palestratiium.classi;

import com.example.palestratiium.DatePickerFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserFactory {

    private static UserFactory singleton;
    private List<User> users = new ArrayList<>();
    private List<PersonalTrainer> coach = new ArrayList<>();
    private List<Esercizio> esercizio = new ArrayList<>();
    DatePickerFragment datePickerFragment = new DatePickerFragment();
    public static boolean fine_user = true , fine_pt=true;
    private UserFactory() {
    }


    public static UserFactory getInstance() {

        if (singleton == null) {
            singleton = new UserFactory();
        }
        return singleton;
    }



    /*public void setListaEsercizio(){
        for(int i=0;i<coach.size();i++){
            for(int j=0;j<coach.get(i).getEsercizio(j);j++){

            }
        }
    }*/


    public void addEsercizio(PersonalTrainer pt,Esercizio esercizio){

        for (int i=0; i<coach.size();i++){
            //TODO da verificare se funziona
            if(coach.get(i).equals(pt)){
                coach.get(i).addEsercizi(esercizio);

            }
        }
    }


    public List<Esercizio> getEserciziPt(PersonalTrainer pt) {

        for (int i = 0; i < coach.size(); i++) {
            //TODO da verificare se funziona
            if (coach.get(i).equals(pt)) {
                return coach.get(i).getAllListaEsercizi();
            }
        }
        System.out.println("errore get Esercizi pt , nessun pt trovato");
        return null;
    }

    public List<Esercizio> getAllEsercizi(){
            return esercizio;
    }

    public List<PersonalTrainer> getPersonal(){

        if(fine_pt) {
            fine_pt = false;
            PersonalTrainer atzeni = new PersonalTrainer();
            atzeni.setUsername("Atezenis");
            atzeni.setPassword("atz");
            atzeni.setCity("Cagliari");
            atzeni.setDate(Calendar.getInstance());
            atzeni.getDate().set(Calendar.YEAR, 1992);
            atzeni.getDate().set(Calendar.MONTH, Calendar.MARCH);
            atzeni.getDate().set(Calendar.DAY_OF_MONTH, 16);
            atzeni.setTeam("Atzenis Team");

            atzeni.initEsercizi(esercizio);
            coach.add(atzeni);
        }
        return  coach;
    }


    public List<User> getUsers() {
        if(fine_user) {
            fine_user = false;
            User user = new User();

            user.setUsername("ste");
            user.setPassword("a");
            user.setCity("Girasole");
            user.setAltezza("170");
            user.setPeso("70");
            user.setDate(Calendar.getInstance());
            user.getDate().set(Calendar.YEAR, 1992);
            user.getDate().set(Calendar.MONTH, Calendar.MARCH);
            user.getDate().set(Calendar.DAY_OF_MONTH, 16);

            initEsercizi();



            users.add(user);

        }

        return users;
    }

    public void initEsercizi(){

        esercizio.add(new Esercizio("Dip.Distensioni di braccia","aa","aaa",1));
        esercizio.add(new Esercizio("Barca","bb","bbb",2));
        esercizio.add(new Esercizio("Crunch","aa","aaa",1));
        esercizio.add(new Esercizio("Front squat","bb","bbb",2));
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

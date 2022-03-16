/*
    Corso IUM - AA 2019 - 2020
    65577 - Daniele Stochino
    Esercitazione Bonus
 */

package com.example.palestratiium.classi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.palestratiium.classi.MyEnum.BICIPITI;
import static com.example.palestratiium.classi.MyEnum.DORSO;
import static com.example.palestratiium.classi.MyEnum.GAMBE;
import static com.example.palestratiium.classi.MyEnum.PETTO;
import static com.example.palestratiium.classi.MyEnum.TRICIPITI;
import static com.example.palestratiium.classi.MyEnum.TUTTI;

public class UserFactory implements Serializable {

    private static UserFactory singleton;
    private List<User> users = new ArrayList<>();
    private List<PersonalTrainer> coach = new ArrayList<>();
    private List<Esercizio> eserciziAtzeni = new ArrayList<>();
    private List<Esercizio> eserciziRoberto = new ArrayList<>();
    private List<Esercizio> allEsercizi = new ArrayList<>();
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


    public void addEsercizio(PersonalTrainer pt,Esercizio esercizio){

        for (int i=0; i<coach.size();i++){
            //TODO da verificare se funziona
            if(coach.get(i).getUsername().equals(pt.getUsername())){
                coach.get(i).addEsercizi(esercizio);
                allEsercizi.add(esercizio);

            }
        }
    }


    public List<Esercizio> getEserciziPt(PersonalTrainer pt) {

        for (int i = 0; i < coach.size(); i++) {
            //TODO da verificare se funziona
            if (coach.get(i).getUsername().equals(pt.getUsername())) {
                return coach.get(i).getAllListaEsercizi();
            }
        }
        System.out.println("errore get Esercizi pt , nessun pt trovato");
        return null;
    }

    public List<Esercizio> getAllEsercizi(){
            return allEsercizi;
    }


    public List<PersonalTrainer> getPersonal(){

        if(fine_pt) {
            fine_pt = false;

            PersonalTrainer all =new PersonalTrainer();
            all.setUsername("all");
            coach.add(all);

            PersonalTrainer atzeni = new PersonalTrainer();
            atzeni.setUsername("At");
            atzeni.setPassword("at");
            atzeni.setCity("Cagliari");
            atzeni.setDate(Calendar.getInstance());
            atzeni.getDate().set(Calendar.YEAR, 1992);
            atzeni.getDate().set(Calendar.MONTH, Calendar.MARCH);
            atzeni.getDate().set(Calendar.DAY_OF_MONTH, 16);
            atzeni.setTeam("Atzenis Team");

            atzeni.initEsercizi(eserciziAtzeni);
            coach.add(atzeni);

            PersonalTrainer roberto = new PersonalTrainer();
            roberto.setUsername("Ro");
            roberto.setPassword("ro");
            roberto.setCity("Cagliari");
            roberto.setDate(Calendar.getInstance());
            roberto.getDate().set(Calendar.YEAR, 1993);
            roberto.getDate().set(Calendar.MONTH, Calendar.MARCH);
            roberto.getDate().set(Calendar.DAY_OF_MONTH, 12);
            roberto.setTeam("RobertosFitness");
            roberto.initEsercizi(eserciziRoberto);
            

            coach.add(roberto);

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

        eserciziAtzeni.add(new Esercizio("Dip.Distensioni di braccia","aa", BICIPITI,"1",null,null));
        eserciziAtzeni.add(new Esercizio("Barca","bb", DORSO,"2",null,null));
        eserciziAtzeni.add(new Esercizio("Crunch","aa", PETTO,"3",null,null));
        eserciziAtzeni.add(new Esercizio("Front squat","bb", DORSO,"2",null,null));
        eserciziRoberto.add(new Esercizio("Panca piana","vv", TRICIPITI,"3",null,null));
        eserciziRoberto.add(new Esercizio("Panca verticale","vv", GAMBE,"3",null,null));

        allEsercizi.addAll(eserciziAtzeni);
        allEsercizi.addAll(eserciziRoberto);
        System.out.println(allEsercizi.size());
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

    public List<Esercizio> lisTest(PersonalTrainer pt, MyEnum gruppoMuscolare){

        List<Esercizio> exe ;
        List<Esercizio> esercizi = new ArrayList<>();

        if(pt.getUsername().equals("all") && gruppoMuscolare.equals(TUTTI) ){
            return getAllEsercizi();
        }

        if(pt.getUsername().equals("all")){
            exe=getAllEsercizi();
        }else{
            exe=(getEserciziPt(pt));
        }

        if(gruppoMuscolare.equals(TUTTI)){
            return exe;
        }

       for (int j=0; j<exe.size(); j++){
           if(exe.get(j).getGruppoMuscolare()==gruppoMuscolare){
               esercizi.add(exe.get(j));
           }
       }


        return  esercizi;
    }


    public Esercizio getEsercizioNome(String nomeEsercizio){
        for (int i=0 ; i<allEsercizi.size() ; i++){
            if(allEsercizi.get(i).getNome().equals(nomeEsercizio)){
                return allEsercizi.get(i);
            }
        }
        return null;
    }

}

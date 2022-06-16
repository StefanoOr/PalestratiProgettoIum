/*
    Corso IUM - AA 2019 - 2020
    65577 - Daniele Stochino
    Esercitazione Bonus
 */

package com.example.palestratiium.classi;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.example.palestratiium.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
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

    private List<PersonalTrainer> coach = new ArrayList<>();

    //LISTE
    private List<User> users = new ArrayList<>();
    private List<Esercizio> eserciziAtzeni = new ArrayList<>();
    private List<Esercizio> eserciziRoberto = new ArrayList<>();
    private List<Esercizio> allEsercizi = new ArrayList<>();
    private List<SchedeAllenamento> schedeAllenamento = new ArrayList<>();

    DatePickerFragment datePickerFragment = new DatePickerFragment();

    public static boolean fine_user = true, fine_pt = true;

    private Integer[] imgDefault;
    private Integer[] videoDefault;

    private UserFactory() {
    }


    public static UserFactory getInstance() {

        if (singleton == null) {
            singleton = new UserFactory();
        }
        return singleton;
    }

    public void addAllenamento(SchedeAllenamento allenamento) {
        schedeAllenamento.add(allenamento);
    }


    public void addEsercizio(PersonalTrainer pt, Esercizio esercizio) {

        for (int i = 0; i < coach.size(); i++) {
            //TODO da verificare se funziona
            if (coach.get(i).getUsername().equals(pt.getUsername())) {
                coach.get(i).addEsercizi(esercizio);
                allEsercizi.add(esercizio);

            }
        }
    }


    public List<PersonalTrainer> getCoach() {

        return coach;

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

    public List<Esercizio> getAllEsercizi() {
        return allEsercizi;
    }

    public Esercizio getEsercizioByPosition(int position) {
        return allEsercizi.get(position);
    }


    public List<EsercizioSchedaAllenamento> getEserciziAllenamento(String nomeAllenamento) {

        for (int i = 0; i < schedeAllenamento.size(); i++) {
            if (schedeAllenamento.get(i).getNomeScheda().equals(nomeAllenamento)) {
                return schedeAllenamento.get(i).getEsercizi();
            }
        }

        return null;
    }


    public List<PersonalTrainer> getPersonal() {

        if (fine_pt) {
            fine_pt = false;

            PersonalTrainer all = new PersonalTrainer();
            all.setUsername("Tutti");
            coach.add(all);

            PersonalTrainer atzeni = new PersonalTrainer();
            atzeni.setUsername("Atzeni");
            atzeni.setNome("Michele");
            atzeni.setPassword("at");
            atzeni.setCity("Cagliari");
            atzeni.setDate(Calendar.getInstance());
            atzeni.getDate().set(Calendar.YEAR, 1992);
            atzeni.getDate().set(Calendar.MONTH, Calendar.MARCH);
            atzeni.getDate().set(Calendar.DAY_OF_MONTH, 16);
            atzeni.setTeam("Atzenis Team");
            atzeni.setInstagram("francesco_atzenii");

            atzeni.initEsercizi(eserciziAtzeni);
            coach.add(atzeni);

            PersonalTrainer roberto = new PersonalTrainer();
            roberto.setUsername("Roby");
            roberto.setNome("Roberto");
            roberto.setPassword("ro");
            roberto.setCity("Cagliari");
            roberto.setDate(Calendar.getInstance());
            roberto.getDate().set(Calendar.YEAR, 1993);
            roberto.getDate().set(Calendar.MONTH, Calendar.MARCH);
            roberto.getDate().set(Calendar.DAY_OF_MONTH, 12);
            roberto.setTeam("RobertosFitness");
            roberto.setInstagram("roberos_Fit");
            roberto.initEsercizi(eserciziRoberto);


            coach.add(roberto);

        }
        return coach;
    }


    public List<User> getUsers() {
        if (fine_user) {
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

    public void initEsercizi() {

        eserciziAtzeni.add(new Esercizio("Dip", "Le dips o i dips rappresentano un'ampia categoria di esercizi calistenici (a corpo libero) utilizzata per stimolare molti muscoli della parte superiore del corpo, e in particolar modo il grande pettorale, il deltoide anteriore e il tricipite brachiale.", TRICIPITI, "1", videoDefault[0], imgDefault[0]));
        eserciziAtzeni.add(new Esercizio("Barca", "Solleva i piedi e intreccia le mani davanti le ginocchia. Porta ginocchia e torace vicini. Scendi lentamente, distendi le gambe e apri le braccia a croce, senza poggiare testa e spalle. Stabilizza il tuo corpo e i muscoli interessati.", DORSO, "2", videoDefault[1], imgDefault[1]));
        eserciziAtzeni.add(new Esercizio("Crunch", "Il crunch viene eseguito stendendosi in posizione supina, con le gambe divaricate all'altezza dei fianchi e in posizione flessa, sollevando il busto in direzione del bacino che invece deve restare in saldo appoggio.", PETTO, "3", videoDefault[2], imgDefault[2]));
       eserciziAtzeni.add(new Esercizio("Front squat", "il bilanciere viene appoggiato sul dorso del soggetto, mentre le gambe vengono divaricate e le punte dei piedi extraruotate proprio come nella variante dello stacco sumo.", GAMBE, "2", videoDefault[2], imgDefault[3]));
       eserciziRoberto.add(new Esercizio("Panca piana", "La panca piana è un esercizio multiarticolare full body, dato che vengono utilizzati muscoli di tutto il corpo per eseguirla: petto, braccia, spalle, ma anche il core e le gambe hanno il loro ruolo nell'esecuzione.", TRICIPITI, "3", videoDefault[2], imgDefault[4]));
       eserciziRoberto.add(new Esercizio("Panca verticale", "La panca ad inversione non è altro che una panca su cui ti “stendi”. La sua funzione è quella di decomprimere la colonna vertebrale e alleviare i dolori muscolo-articolari alla schiena e apportare sollievo e far respirare la tua colonna vertebrale", GAMBE, "3", videoDefault[2], imgDefault[5]));

        allEsercizi.addAll(eserciziAtzeni);
        allEsercizi.addAll(eserciziRoberto);
        System.out.println(allEsercizi.size());
    }

    public void addUsers(User u) {
        users.add(u);
    }

    public void addPersonal(PersonalTrainer u) {
        coach.add(u);
    }

    public void modifyPassPersonal(PersonalTrainer personal) {
        for (PersonalTrainer u : coach) {
            if (u.getUsername().equals(personal.getUsername()) && u.getCity().equals(personal.getCity())) {
                coach.remove(u);
                coach.add(personal);
                return;
            }
        }
    }

    public void modifyPass(User user) {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername()) && u.getCity().equals(user.getCity())) {
                users.remove(u);
                users.add(user);
                return;
            }
        }
    }


    public List<Esercizio> lisTest(PersonalTrainer pt, MyEnum gruppoMuscolare) {

        List<Esercizio> exe;
        List<Esercizio> esercizi = new ArrayList<>();

        if (pt.getUsername().equals("Tutti") && gruppoMuscolare.equals(TUTTI)) {
            return getAllEsercizi();
        }

        if (pt.getUsername().equals("Tutti")) {
            exe = getAllEsercizi();
        } else {
            exe = (getEserciziPt(pt));
        }

        if (gruppoMuscolare.equals(TUTTI)) {
            return exe;
        }

        for (int j = 0; j < exe.size(); j++) {
            if (exe.get(j).getGruppoMuscolare() == gruppoMuscolare) {
                esercizi.add(exe.get(j));
            }
        }


        return esercizi;
    }


    public Esercizio getEsercizioNome(String nomeEsercizio) {
        for (int i = 0; i < allEsercizi.size(); i++) {
            if (allEsercizi.get(i).getNome().equals(nomeEsercizio)) {
                return allEsercizi.get(i);
            }
        }
        return null;
    }

    public void setImgDafault(Integer[] imgDefault) {
        this.imgDefault = imgDefault;
    }

    public void setVideoDefault(Integer[] videoDefault) {
        this.videoDefault = videoDefault;
    }


    public void setAllenamentoDefault() {

        List<EsercizioSchedaAllenamento> eserciziAllenamento = new ArrayList<>();

        eserciziAllenamento.add(new EsercizioSchedaAllenamento(allEsercizi.get(1), 8, 3, "1.30"));
        eserciziAllenamento.add(new EsercizioSchedaAllenamento(allEsercizi.get(2), 8, 3, "1.30"));
        eserciziAllenamento.add(new EsercizioSchedaAllenamento(allEsercizi.get(3), 8, 3, "1.30"));


        schedeAllenamento.add(new SchedeAllenamento("Scheda Standard", "Piccola descrizione dell'allenamento", eserciziAllenamento));


    }

    public void modifyEsercizio(String nomeOriginale, PersonalTrainer pt, Esercizio esercizio) {

        for (int i = 0; i < coach.size(); i++) {
            //TODO da verificare se funziona
            if (coach.get(i).getUsername().equals(pt.getUsername())) {
                for (int j = 0; j < coach.get(i).getAllListaEsercizi().size(); j++) {

                    if (coach.get(i).getEsercizio(j).getNome().equals(nomeOriginale)) {
                        allEsercizi.remove(coach.get(i).getEsercizio(j));

                        coach.get(i).getAllListaEsercizi().remove(j);

                        allEsercizi.add(esercizio);

                        coach.get(i).addEsercizi(esercizio);

                    }
                }

            }

        }
    }

    public void changeSchedeAllenamentoAutomati(Esercizio esercizio) {
        for (int i = 0; i < schedeAllenamento.size(); i++) {
            for (int j = 0; j < schedeAllenamento.get(i).getEsercizi().size(); j++) {
                if (schedeAllenamento.get(i).getEsercizio(j).getEsercizio().equals(esercizio)) {
                    schedeAllenamento.get(i).removeEsericizio(j);
                }

            }
        }

    }

    public List<SchedeAllenamento> getListAllenamento() {
        return schedeAllenamento;
    }

    public void removeEsercizio(Esercizio esercizio) {

        for (int i = 0; i < coach.size(); i++) {
            //TODO da verificare se funziona
            for (int j = 0; j < coach.get(i).getAllListaEsercizi().size(); j++) {

                if (coach.get(i).getEsercizio(j).equals(esercizio)) {
                    changeSchedeAllenamentoAutomati(esercizio);
                    allEsercizi.remove(coach.get(i).getEsercizio(j));
                    coach.get(i).getAllListaEsercizi().remove(j);

                }
            }


        }
    }
}

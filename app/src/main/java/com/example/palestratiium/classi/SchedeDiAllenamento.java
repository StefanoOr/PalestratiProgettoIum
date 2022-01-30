package com.example.palestratiium.classi;

import java.io.Serializable;
import java.util.List;

public class SchedeDiAllenamento implements Serializable {

    private PersonalTrainer personalTrainer;
    private List<Esercizio> schedaDiAllenamento;




    public  SchedeDiAllenamento(PersonalTrainer personalTrainer, List<Esercizio> schedaDiAllenamento){
        this.personalTrainer=personalTrainer;
        this.schedaDiAllenamento=schedaDiAllenamento;

    }

    


}

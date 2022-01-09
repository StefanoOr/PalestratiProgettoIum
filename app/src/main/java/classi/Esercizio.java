package classi;

public class Esercizio {

    private  String nome;
    private  String descrizioene;

    private  String gruppoMuscolare;
    private int difficolta;


    public Esercizio(String nome, String descrizioene, String gruppoMuscolare, int difficolta){
        this.nome=nome;
        this.descrizioene=descrizioene;
        this.gruppoMuscolare=gruppoMuscolare;
        this.difficolta=difficolta;
    }

    public Esercizio(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizioene() {
        return descrizioene;
    }

    public void setDescrizioene(String descrizioene) {
        this.descrizioene = descrizioene;
    }

    public String getGruppoMuscolare() {
        return gruppoMuscolare;
    }

    public void setGruppoMuscolare(String gruppoMuscolare) {
        this.gruppoMuscolare = gruppoMuscolare;
    }

    public int getDifficolta() {
        return difficolta;
    }

    public void setDifficolta(int difficolta) {
        this.difficolta = difficolta;
    }
}

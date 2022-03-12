package Model;

public class QuizAperto extends Quiz{
    private int lunghezzaMax;

    public QuizAperto(String domanda){
        super(domanda);
    }

    public void setLunghezzaMax(int lm){ this.lunghezzaMax = lm; };

    public int getLunghezzaMax(){ return lunghezzaMax; };
}

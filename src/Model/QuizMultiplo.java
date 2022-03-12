package Model;

import java.util.ArrayList;

public class QuizMultiplo extends Quiz{
    ArrayList<String> listaRisposte = new ArrayList<String>();
    private String rispostaCorretta;

    public QuizMultiplo(String d){
        super(d);
    }

    public String getRispostaCorretta() { return rispostaCorretta; };
    @Override
    public ArrayList<String> getRisposte(){
        return listaRisposte;
    }

    public void setRisposte(String r1, String r2, String r3, String r4){
        listaRisposte.add(r1);
        listaRisposte.add(r2);
        listaRisposte.add(r3);
        listaRisposte.add(r4);
    }
    public void setRispostaCorretta(String risposta){
        this.rispostaCorretta = risposta;
    }
}

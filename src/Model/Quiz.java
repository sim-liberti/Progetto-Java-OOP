package Model;

import java.util.ArrayList;

public class Quiz {
    private ArrayList<String> risposte = null;
    private String domanda;
    private char tipoQuiz;
    private String rispostaStudente;
    private float votoStudente;

    public Quiz(String d){
        this.domanda = d;
    }
    
    public char getTipoQuiz() { return tipoQuiz; };
    public String getDomanda(){ return domanda;  };
    public String getRispostaStudente(){ return rispostaStudente; };
    public float getVotoStudente(){ return votoStudente; };
    public ArrayList<String> getRisposte(){
        return risposte;
    }

    public void setRispostaStudente(String risposta){
        this.rispostaStudente = risposta;
    }
    public void setTipoQuiz(char t){ this.tipoQuiz = t; };
    public void setVotoStudente(float v){ this.votoStudente = v; };

}

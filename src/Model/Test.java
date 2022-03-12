package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Test {
    private String nome;
    private int numeroDomande;
    private ArrayList<Quiz> listaQuiz = new ArrayList<Quiz>();
    private String dataCreazione;
    private String dataConsegna;
    private String dataCorrezione;
    public boolean isTaken;
    public boolean isGraded;
    public boolean toBeGraded;

    private int votoMax = 30;

    public Test(String nomeTest){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        
        this.nome = nomeTest;
        this.dataCreazione = String.valueOf(dtf.format(now));
        this.isTaken = false;
        this.isGraded = false;
        this.toBeGraded = false;
    }

    public void aggiungiQuiz(Quiz q){ 
        listaQuiz.add(q); 
    };
    
    public void setNumeroDomande(int nm){ numeroDomande = nm; };
    public void setDataConsegna(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        this.dataConsegna = String.valueOf(dtf.format(now));
    }
    public void setDataCorrezione(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        this.dataCorrezione = String.valueOf(dtf.format(now));
    }

    public String getNome()          { return nome;               };
    public String getDataCreazione() { return dataCreazione;      };
    public String getDataConsegna()  { return dataConsegna;       };
    public String getDataCorrezione(){ return dataCorrezione;     };
    public int getVotoMax()          { return votoMax; };
    public int getNumeroDomande()    { return numeroDomande;      };
    public Quiz getQuiz(int pos)     { return listaQuiz.get(pos); };
}
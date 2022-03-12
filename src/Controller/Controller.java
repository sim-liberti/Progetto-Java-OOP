package Controller;

import java.util.ArrayList;

import Model.*;

public class Controller {
    Test t;
    QuizAperto qa;
    QuizMultiplo qm;
    Docente d;
    Studente s;
    public ArrayList<Test> listaTest = new ArrayList<Test>();

    public String controlloCredenziali(String nome, String cognome, String login, String password){
        String errore = "OK";
        if (nome.contentEquals("")||cognome.contentEquals("")||login.contentEquals("")||password.contentEquals("")){
            errore = new String("Compila tutti i campi");
        }
        else if (!(login.contains("@studenti.unina.it"))&&!(login.contains("@unina.it"))){
            System.out.println(login);
            System.out.println(!(login.contains("@studenti.unina.it")));
            errore = new String("Username non valido");
        }
        return errore;
    }

    public String nuovoUtente(String nome, String cognome, String login, String password){
        String ruolo = null;
        if (login.contains("@studenti.unina.it")){
            s = new Studente(nome, cognome, login, password);
            s.setRuolo("STUDENTE");
            ruolo = new String("STUDENTE");
        }
        else if (login.contains("@unina.it")){
            d = new Docente(nome, cognome, login, password);
            d.setRuolo("DOCENTE");
            ruolo = new String("DOCENTE");
        }
        return ruolo;
    }

    public String scegliNomeTest(String nomeTest){
		String errore="OK";
        if (nomeTest.contentEquals(""))
            errore = new String("Inserisci un nome valido");
        else
            for (int i = 0; i < listaTest.size(); i++)
                if (listaTest.get(i).getNome().contentEquals(nomeTest))
                    errore = new String("Nome già utilizzato");
		return errore;
    }

    public void nuovoTest(String nomeTest){
        t = new Test(nomeTest);
        listaTest.add(t);
    }

    public String scegliNumeroDomande(String numeroDomande){
        String errore = "OK";
        int nm;
        try {
            nm = Integer.parseInt(numeroDomande);
            t.setNumeroDomande(nm);
        } catch (NumberFormatException e){
            errore = new String("Numero inserito non valido");
        }
        return errore;
    }

    public String scegliDomanda(String domanda){
        String errore = "OK";
        if (domanda.contentEquals("")){
            errore = new String("Inserisci una domanda valida");
        }
        return errore;
    }
    public String scegliLunghezzaMax(String lunghezzaMax){
        String errore = "OK";
        try {
            Integer.parseInt(lunghezzaMax);
        } catch (NumberFormatException e){
            errore = new String("Inserisci un numero valido");
        }
        return errore;
    }

    public void creaQuizAperto(String domanda, String lunghezzaMax){
        qa = new QuizAperto(domanda);
        qa.setLunghezzaMax(Integer.parseInt(lunghezzaMax));
        qa.setTipoQuiz('a');
        t.aggiungiQuiz(qa);
    }

    public String scegliRispostePossibili(String r1, String r2, String r3, String r4){
        String errore = "OK";
        if (r1.contentEquals("")||r2.contentEquals("")||r3.contentEquals("")||r4.contentEquals("")){
            errore = new String("Inserisci tutte le risposte");
        }
        return errore;
    }

    public void creaQuizMultiplo(String d, String r1, String r2, String r3, String r4){
        qm = new QuizMultiplo(d);
        qm.setRisposte(r1, r2, r3, r4);
        qm.setTipoQuiz('m');
        t.aggiungiQuiz(qm);
    }

    public void selezionaRispostaCorretta(String risposta){
        qm.setRispostaCorretta(risposta);
    }

    public boolean controllaNomeTest(String nomeDaControllare){
        for (int i = 0; i < listaTest.size(); i++){
            if (listaTest.get(i).getNome().contentEquals(nomeDaControllare))
                return true;
        }
        return false;
    }

    public String controllaRispostaInserita(String risposta){
        String errore = "OK";
        int np = risposta.split("\\s+").length;
        if (np > qa.getLunghezzaMax())
            errore = new String("Troppe parole utilizzate. Il massimo è "+ qa.getLunghezzaMax());
        return errore;
    }

    public void assegnaRispostaStudente(String risposta, int index){
        t.getQuiz(index).setRispostaStudente(risposta);
    }

    public Test selezionaTest(String nomeTest){
        Test testDaControllare = null;
        for (int i = 0; i < listaTest.size(); i++)
            if (nomeTest.contentEquals(listaTest.get(i).getNome()))
                testDaControllare = listaTest.get(i);
        return testDaControllare;
    }

    public void assegnaRispostaGiusta(){
        String rispostaStudente = qm.getRispostaStudente();
        String rispostaGiusta = qm.getRispostaCorretta();
        if (rispostaStudente.contentEquals(rispostaGiusta)){
            float voto = t.getVotoMax()/t.getNumeroDomande();
            qm.setVotoStudente(voto);
        }
    }

    public String controllaInserimentoVoto(String voto){
        String errore = "OK";
        if (voto.contentEquals(""))
            errore = new String("Inserisci un voto");
        else{
            try {
                Float.parseFloat(voto);
            } catch (NumberFormatException e){
                errore = new String("Inserisci un numero valido");
            }
        }
        return errore;
    }
}

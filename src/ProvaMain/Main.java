package ProvaMain;

import Controller.Controller;

public class Main {
    public static void main(String[] args) {
        Controller c = new Controller();
        String nomeTest = "Prova test";
        if (c.scegliNomeTest(nomeTest).contentEquals("OK")){
            c.nuovoTest(nomeTest);
        }
        else {
            System.out.println(c.scegliNomeTest(nomeTest));
        }
        String numeroDomande = "2";
        if (c.scegliNumeroDomande(numeroDomande) != "OK")
            System.out.println(c.scegliNumeroDomande(numeroDomande));
        
        for (int i = 0; i < Integer.parseInt(numeroDomande); i++){
            String domanda = "Domanda "+i;
            if (c.scegliDomanda(domanda).contentEquals("OK")){
                if (i == 0){
                    String lm = "10";
                    if (c.scegliLunghezzaMax(lm).contentEquals("OK"))
                        c.creaQuizAperto(domanda, lm);
                    else
                        System.out.println(c.scegliLunghezzaMax(lm));
                }
                if (i == 1){
                    String r1 = "Risposta 1";
                    String r2 = "Risposta 2";
                    String r3 = "Risposta 3";
                    String r4 = "Risposta 4";
                    if (c.scegliRispostePossibili(r1, r2, r3, r4).contentEquals("OK"))
                        c.creaQuizMultiplo(domanda, r1, r2, r3, r4);
                    else 
                        System.out.println(c.scegliRispostePossibili(r1, r2, r3, r4));
                }
            }
            else {
                System.out.println(c.scegliDomanda(domanda));
            }
            System.out.println(domanda + " inserita.");
        }
        System.out.println("Test inseriti: ");
        for (int i = 0; i < c.listaTest.size(); i++){
            System.out.println("Nome: "+c.listaTest.get(i).getNome());
            System.out.println("Domande: "+c.listaTest.get(i).getNumeroDomande());
        }
        System.out.println("Fine test.");
        // END MAIN //
    }

    // END CLASS //
}


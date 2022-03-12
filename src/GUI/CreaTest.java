package GUI;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.Controller;

public class CreaTest {
    private String nomeTest;
    private String numeroDomande;
    private String domanda;
    private String validazione;
    private String lunghezzaMax;

    public CreaTest(Controller controller){
        Controller c = controller;

        do {
            nomeTest = JOptionPane.showInputDialog("Inserisci il nome del test:");
            validazione = c.scegliNomeTest(nomeTest);
            if (!(validazione.contentEquals("OK")))
                JOptionPane.showMessageDialog(null, validazione);
        } while(!(validazione.contentEquals("OK")));
        c.nuovoTest(nomeTest);
        do {
            numeroDomande = JOptionPane.showInputDialog(null, "Quante domande ci saranno?");
            validazione = c.scegliNumeroDomande(numeroDomande);
            if (!(validazione.contentEquals("OK")))
                JOptionPane.showMessageDialog(null, validazione);
        } while(!(validazione.contentEquals("OK")));

        for (int i = Integer.parseInt(numeroDomande); i > 0; i--){
            if (i > 0){
                Object[] options = { "Aperta", "Multipla" };
                int scelta = JOptionPane.showOptionDialog(
                    null, 
                    "(domande rimanenti: " + i + ")\nScegli il tipo di risposta: ", 
                    "Scelta",
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.INFORMATION_MESSAGE,
                    null, 
                    options, 
                    options[0]);
    
                do {
                    domanda = JOptionPane.showInputDialog(null, "Inserisci la domanda");
                    validazione = c.scegliDomanda(domanda);
                    if (!(validazione.contentEquals("OK")))
                        JOptionPane.showMessageDialog(null, validazione);
                } while(!(validazione.contentEquals("OK")));

                if (scelta == 0)
                    inserisciDomandaAperta(c);
                else if (scelta == 1)
                    inserisciDomandaMultipla(c);
            }
            if (i <= 0){
                JOptionPane.showMessageDialog(null, "Domande finite");
            }
        }
        new HomeDocente(c);
        // END CONSTRUCTOR //
    }

    public void inserisciDomandaAperta(Controller c){
        do {
            lunghezzaMax = JOptionPane.showInputDialog(null, "Inserisci la lunghezza \nmassima per la risposta");
            validazione = c.scegliLunghezzaMax(lunghezzaMax);
            if (!(validazione.contentEquals("OK")))
                JOptionPane.showMessageDialog(null, validazione);
        } while(!(validazione.contentEquals("OK")));
        c.creaQuizAperto(domanda, lunghezzaMax);
    }

    public void inserisciDomandaMultipla(Controller c){
        int option;
        String r1, r2, r3, r4;

        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        JTextField field3 = new JTextField();
        JTextField field4 = new JTextField();
        Object[] message = {
            "Risposta 1:", field1,
            "Risposta 2:", field2,
            "Risposta 3:", field3,
            "Risposta 4:", field4,
        };
        
        do {
            option = JOptionPane.showConfirmDialog(null, message, "Inserisci le risposte: ", JOptionPane.OK_CANCEL_OPTION);
            r1 = field1.getText();
            r2 = field2.getText();
            r3 = field3.getText();
            r4 = field4.getText();
            validazione = c.scegliRispostePossibili(r1, r2, r3, r4);
            if (!(validazione.contentEquals("OK")))
                JOptionPane.showMessageDialog(null, validazione);
        } while(!(validazione.contentEquals("OK")));

        if (option == JOptionPane.OK_OPTION){
            String[] buttons = { "r1", "r2", "r3", "r4" };    
            int returnValue = JOptionPane.showOptionDialog(
                null, 
                "Scegli la risposta giusta", 
                "Scelta",
                JOptionPane.DEFAULT_OPTION, 
                1, 
                null, 
                buttons, 
                buttons[0]);
            
            c.creaQuizMultiplo(domanda, r1, r2, r3, r4);
            
            if (returnValue == 0)
                c.selezionaRispostaCorretta(r1);
            if (returnValue == 1)
                c.selezionaRispostaCorretta(r2);
            if (returnValue == 2)
                c.selezionaRispostaCorretta(r3);
            if (returnValue == 3)
                c.selezionaRispostaCorretta(r4);
        }
    }
    // END CLASS //
}

package GUI;

import Controller.Controller;
import Model.Test;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class VisualizzaTest {
    Controller c;

    JFrame frame = new JFrame();
    JPanel[] panels;

    private Test testDaControllare;
    private int numeroDomande;
    private float votoTotale = 0;

    public VisualizzaTest(Controller controller, String nomeTest){
        c = controller;
        if (c.selezionaTest(nomeTest) != null)
            testDaControllare = c.selezionaTest(nomeTest);
        
        frame.setSize(500, 500);
        
        numeroDomande = testDaControllare.getNumeroDomande();
        frame.setLayout(new GridLayout(numeroDomande, 1));
        panels = new JPanel[numeroDomande];
        
        for (int i = 0; i < numeroDomande; i++){
            votoTotale += testDaControllare.getQuiz(i).getVotoStudente();
            panels[i] = new JPanel();
            panels[i].setLayout(new GridLayout(3, 1));
            panels[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            panels[i].add(new JLabel("Domanda: "+testDaControllare.getQuiz(i).getDomanda()));
            panels[i].add(new JLabel("Risposta data: "+testDaControllare.getQuiz(i).getRispostaStudente()));
            panels[i].add(new JLabel("Voto: "+String.valueOf(testDaControllare.getQuiz(i).getVotoStudente())));
            frame.add(panels[i]);
        }
        
        frame.add(new JLabel("Voto complessivo: "+votoTotale+"/"+testDaControllare.getVotoMax()));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    

}

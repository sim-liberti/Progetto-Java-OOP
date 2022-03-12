package GUI;

import Controller.Controller;
import Model.Test;

import java.awt.event.*;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

public class CorreggiTest implements ActionListener{
    Controller c;
    Test testDaCorregere;

    JFrame frame = new JFrame();
    JLabel labelDomanda = new JLabel();
    JLabel labelRisposta = new JLabel();
    JLabel errore = new JLabel();
    JLabel labelMaxScore = new JLabel();
    JButton btnNext = new JButton("Avanti");
    JTextField textFieldVoto = new JTextField();

    private int index = 0;

    public CorreggiTest(Controller controller, String nomeTest){
        c = controller;
        if (c.selezionaTest(nomeTest) != null)
            testDaCorregere = c.selezionaTest(nomeTest);

        frame.setSize(600, 350);
        frame.setResizable(false);
        frame.setLayout(null);

        btnNext.addActionListener(this);
        errore.setForeground(Color.RED);

        textFieldVoto.setBounds(120, 195, 20, 20);
        labelMaxScore.setBounds(140, 195, 60, 20);
        labelDomanda.setBounds(50, 80, 450, 20);
        labelRisposta.setBounds(50, 110, 450, 50);
        btnNext.setBounds(250, 190, 100, 30);
        errore.setBounds(250, 220, 200, 20);
        errore.setVisible(false);

        frame.add(labelDomanda);
        frame.add(labelRisposta);
        frame.add(errore);
        frame.add(textFieldVoto);
        frame.add(labelMaxScore);
        frame.add(btnNext);

        controllaDomanda();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void controllaDomanda(){
        System.out.println(testDaCorregere.getQuiz(index).getTipoQuiz());
        if (testDaCorregere.getQuiz(index).getTipoQuiz() == 'a'){
            visualizzaRisposta();
        }
        else if (testDaCorregere.getQuiz(index).getTipoQuiz() == 'm'){
            assegnaVotoMultipla();
        }
    }

    public void visualizzaRisposta(){
        System.out.println(index+"-");
        String domanda = testDaCorregere.getQuiz(index).getDomanda();
        String risposta = testDaCorregere.getQuiz(index).getRispostaStudente();
        float maxScore = testDaCorregere.getVotoMax()/testDaCorregere.getNumeroDomande();
        labelDomanda.setText(domanda);
        labelRisposta.setText(risposta);
        labelMaxScore.setText(String.valueOf(maxScore));
        textFieldVoto.setText(null);
    }

    public void assegnaVotoMultipla(){
        c.assegnaRispostaGiusta();
        if (index >= testDaCorregere.getNumeroDomande()-1){
            JOptionPane.showMessageDialog(null, "Test corretto.");
            testDaCorregere.toBeGraded = false;
            testDaCorregere.isGraded = true;
            testDaCorregere.setDataCorrezione();
            frame.dispose();
            new HomeDocente(c);
        }
        else {
            index++;
            controllaDomanda();
        }
    }

    public void assegnaVotoAperta(){
        String validazione = c.controllaInserimentoVoto(textFieldVoto.getText());
        if (!(validazione.contentEquals("OK"))){
            errore.setText(validazione);
            errore.setVisible(true);
        }
        else {
            Float voto = Float.parseFloat(textFieldVoto.getText());
            testDaCorregere.getQuiz(index).setVotoStudente(voto);
            if (index < testDaCorregere.getNumeroDomande()-1)
                index++;
            controllaDomanda();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (index >= testDaCorregere.getNumeroDomande()-1){
            assegnaVotoAperta();
            JOptionPane.showMessageDialog(null, "Test corretto.");
            testDaCorregere.toBeGraded = false;
            testDaCorregere.isGraded = true;
            testDaCorregere.setDataCorrezione();
            frame.dispose();
            new HomeDocente(c);
        }
        else {
            if (e.getSource() == btnNext){
                assegnaVotoAperta();
            }
        }
    }
}

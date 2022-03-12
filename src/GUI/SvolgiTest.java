package GUI;

import Controller.Controller;
import Model.Test;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

public class SvolgiTest implements ActionListener{
    Controller c;
    Test testDaSvolgere;
    
    JFrame frame = new JFrame();
    
    JLabel labelDomanda = new JLabel();
    JLabel errore = new JLabel();
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup optionsGroup = new ButtonGroup();
    JTextArea areaRispostaAperta = new JTextArea();
    JButton btnNext = new JButton("Avanti");
    JButton btnResult = new JButton("Consegna");

    private int index = 0;

    public SvolgiTest(Controller controller, String nomeTest){
        c = controller;
        if (c.selezionaTest(nomeTest) != null)
            testDaSvolgere = c.selezionaTest(nomeTest);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 350);
        frame.setLayout(null);
        frame.setResizable(false);

        for (int i = 0; i < 4; i++){
            options[i] = new JRadioButton();
            optionsGroup.add(options[i]);
            frame.add(options[i]);
            options[i].setVisible(false);
        }
        areaRispostaAperta.setVisible(false);
        errore.setVisible(false);
        errore.setForeground(Color.RED);

        labelDomanda.setBounds(50, 80, 450, 20);
        areaRispostaAperta.setBounds(50, 110, 450, 120);
        options[0].setBounds(50, 110, 450, 20);
        options[1].setBounds(50, 140, 450, 20);
        options[2].setBounds(50, 170, 450, 20);
        options[3].setBounds(50, 200, 450, 20); 
        btnNext.setBounds(100, 240, 100, 30);
        btnResult.setBounds(270, 240, 100, 30);
        errore.setBounds(100, 280, 400, 20);
        
        btnNext.addActionListener(this);
        btnResult.setVisible(false);
        btnResult.addActionListener(this);

        if (testDaSvolgere.getNumeroDomande() == 1){
            btnNext.setVisible(false);
            btnResult.setVisible(true);
        }

        frame.add(errore);
        frame.add(areaRispostaAperta);
        frame.add(btnNext);
        frame.add(btnResult);
        frame.add(labelDomanda);

        if (testDaSvolgere.getQuiz(index).getTipoQuiz() == 'a'){
            displayQuizAperto();
        }
        else if (testDaSvolgere.getQuiz(index).getTipoQuiz() == 'm'){
            displayQuizMultiplo();
        }

        frame.setVisible(true);
    }

    public void displayQuizAperto(){
        String domanda = testDaSvolgere.getQuiz(index).getDomanda();
        optionsGroup.clearSelection();
        for (int i = 0; i < 4; i++)
            options[i].setVisible(false);
        labelDomanda.setText(index+": "+domanda);
        areaRispostaAperta.setVisible(true);
    }
    public void displayQuizMultiplo(){
        String domanda = testDaSvolgere.getQuiz(index).getDomanda();
        String risposta;
        areaRispostaAperta.setVisible(false);
        for (int i = 0; i < 4; i++){
            risposta = testDaSvolgere.getQuiz(index).getRisposte().get(i);
            System.out.println("- "+risposta);
            options[i].setVisible(true);
            options[i].setText(risposta);
        }

        labelDomanda.setText(index+": "+domanda);
    }

    public boolean assegnaRisposta(){
        String risposta = areaRispostaAperta.getText();
        char tipoQuiz = testDaSvolgere.getQuiz(index).getTipoQuiz();

        if (tipoQuiz == 'a'){
            String validazione = c.controllaRispostaInserita(risposta);
            if (!(validazione.contentEquals("OK"))){
                errore.setText(validazione);
                errore.setVisible(true);
                System.out.println(index);
                return false;
            }
            else {
                c.assegnaRispostaStudente(risposta, index);
                return true;
            }
        }
        else if (tipoQuiz == 'm'){
            risposta = "";
            for (int i = 0; i < 4; i++)
                if (options[i].isSelected())
                    risposta = options[i].getText();

            c.assegnaRispostaStudente(risposta, index);
            return true;
        }

        return false;
    }

    private void prossimaDomanda(){
        char tipoQuiz = testDaSvolgere.getQuiz(index).getTipoQuiz();

        if (index >= testDaSvolgere.getNumeroDomande()-1){
            btnNext.setVisible(false);
            btnResult.setVisible(true);
        }
        
        areaRispostaAperta.setText(null);
        errore.setVisible(false);
        
        if (tipoQuiz == 'a'){
            displayQuizAperto();
        }
        else if (tipoQuiz == 'm'){
            displayQuizMultiplo();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNext){
            if (assegnaRisposta() == true){
                System.out.println(index);
                index++;
                prossimaDomanda();
            }
        }
        if (e.getSource() == btnResult){
            if (assegnaRisposta() == true){
                JOptionPane.showMessageDialog(null, "Test consegnato");
                testDaSvolgere.isTaken = true;
                testDaSvolgere.toBeGraded = true;
                testDaSvolgere.setDataConsegna();
                frame.dispose();
                new HomeStudente(c);
            }
        }
    }
}

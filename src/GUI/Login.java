package GUI;

import Controller.Controller;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Login {
    Controller c;

    JTextField inputFirstName = new JTextField();
    JTextField inputLastName = new JTextField();
    JTextField inputUsername = new JTextField();
    JPasswordField inputPassword = new JPasswordField();
    
    public Login(Controller controller){
        c = controller;
        Object[] options = { "Login", "Esci" };
        Object[] message = {
            "Inserisci le tue credenziali.",
            "Nome:",      inputFirstName,
            "Cognome: ",  inputLastName,
            "Username: ", inputUsername,
            "Password: ", inputPassword,
        };

        int result = JOptionPane.showOptionDialog(null, 
            message, 
            "Login",
            JOptionPane.YES_NO_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE,
            null, 
            options, 
            null);

        if (result == JOptionPane.YES_OPTION){
            String nome = inputFirstName.getText();
            String cognome = inputLastName.getText();
            String login = inputUsername.getText();
            String password = inputPassword.getPassword().toString();
            String validazione = c.controlloCredenziali(nome, cognome, login, password);
            if (validazione.contentEquals("OK")){
                if (c.nuovoUtente(nome, cognome, login, password).contentEquals("DOCENTE")){
                    new HomeDocente(c);
                    System.out.println("Loggato come docente");
                }
                else if (c.nuovoUtente(nome, cognome, login, password).contentEquals("STUDENTE")){
                    new HomeStudente(c);
                    System.out.println("Loggato come studente");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, validazione);
                new Login(c);
            }
        }
        else {
            System.exit(0);
        }
    }
}


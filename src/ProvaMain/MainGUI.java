package ProvaMain;

import GUI.*;
import Controller.Controller;

public class MainGUI {
    public static void main(String[] args) { 
        Controller c = new Controller();
        new Login(c);        
    }
}

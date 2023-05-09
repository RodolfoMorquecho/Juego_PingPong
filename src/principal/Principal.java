package principal;

import clases.Ventana;

import javax.swing.*;

public class Principal {
    public static void main(String[] args) {
        Ventana ventana = new Ventana();  //Se crea una instancia de la clase "Ventana"
        ventana.setVisible(true);  //Se hace visible la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Cuando se cierra la ventana, termina la ejecucion del programa
    }
}

package clases;

import javax.swing.*;

public class Ventana extends JFrame {
    private final int ANCHO = 800,ALTO = 500;  //Dimensiones de la ventana

    //Constructor donde se incluiran toda las caracteristicas de la ventana
    public Ventana(){
        setTitle("Pong");  //Titulo de la ventana
        setSize(800,500);  //Se establece el tamaño de la ventana
        setLocationRelativeTo(null);  //Centrar la ventana cada que se inicia el juego
        setResizable(false);  //No se podra modificar el tamaño de la ventana por el usuario(jugador)
    }
}

package clases;

import javax.swing.*;

public class Ventana extends JFrame {
    private final int ANCHO = 800,ALTO = 500;  //Dimensiones de la ventana

    private MesaJuego lamina;  //Se declara un objeto de la clase MesaJuego
    private Hilo hilo;  //Se declara un objeto de la clase Hilo


    //Constructor donde se incluiran toda las caracteristicas de la ventana
    public Ventana(){
        setTitle("Pong");  //Titulo de la ventana
        setSize(ANCHO, ALTO);  //Se establece el tamaño de la ventana
        setLocationRelativeTo(null);  //Centrar la ventana cada que se inicia el juego
        setResizable(false);  //No se podra modificar el tamaño de la ventana por el usuario(jugador)

        lamina = new MesaJuego();  //Se crea el objeto de la clase que contiene el panel y su configuracion
        add(lamina);  //Se agrega la lamina a la ventana

        //Para que la clase EventoTeclado entre en funcionamiento se debe de instanciar
        addKeyListener(new EventoTeclado());


        hilo = new Hilo(lamina);  //Se crea la instancia de Hilo y se pasa como parametro el panel(lamina) sobre la que trabajara
        hilo.start();  //Se inicia el proceso del hilo

    }
}

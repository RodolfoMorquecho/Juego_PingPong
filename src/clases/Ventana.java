package clases;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private final int ANCHO = 800,ALTO = 500;  //Dimensiones de la ventana

    public static MesaJuego lamina;  //Se declara un objeto de la clase MesaJuego
    private Hilo hilo;  //Se declara un objeto de la clase Hilo


    //Constructor donde se incluiran toda las caracteristicas de la ventana
    public Ventana(){
        setTitle("Pong");  //Titulo de la ventana
        setSize(ANCHO, ALTO);  //Se establece el tamaño de la ventana
        setLocationRelativeTo(null);  //Centrar la ventana cada que se inicia el juego
        setResizable(false);  //No se podra modificar el tamaño de la ventana por el usuario(jugador)

        lamina = new MesaJuego();  //Se crea el objeto de la clase que contiene el panel y su configuracion
        add(lamina);  //Se agrega la lamina a la ventana

        //-----------------------------------Etiqueta de pausa-----------------------------------------------------
        lamina.setLayout(null);  //Indicar al programa que se hara uso de coordenadas para la interfaz con el metodo Layout(JFrame),
                                 // null para que no se posicione la interfaz hasta que reciba instrucciones
        EventoTeclado.etiqueta.setBounds(350,190,80,60);  //Se le indican las coordenadas donde se posicionara la etiqueta
        EventoTeclado.etiqueta.setForeground(Color.YELLOW);  //Se asigna un color a la fuente de la etiqueta

        EventoTeclado.etiqueta.setFont(new Font("Arial",3,28));
        lamina.add(EventoTeclado.etiqueta);  //Se agrega la etiqueta en la lamina(panel)



        //Para que la clase EventoTeclado entre en funcionamiento se debe de instanciar
        addKeyListener(new EventoTeclado());

        //lamina.add(EventoTeclado.etiqueta);

        hilo = new Hilo(lamina);  //Se crea la instancia de Hilo y se pasa como parametro el panel(lamina) sobre la que trabajara
        hilo.start();  //Se inicia el proceso del hilo

    }

}

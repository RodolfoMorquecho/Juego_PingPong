package clases;

//En esta clase se dibujara la mesa, raquetas y pelota

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

//Para comenzar a dibujar los elementos, primero se debe crear un panel(lamina) que ira sobre la ventana previamente configurada
//Ya colocado el panel, se podran dibujar cada componente sobre el.
public class MesaJuego extends JPanel {

    Pelota pelota = new Pelota(0,0);  //Crear objeto de la clase Pelota con las coordenadas x=0,y=0 como parametro
    public MesaJuego(){  //Constructor
        setBackground(Color.BLACK);
    }

    //Método para pintar sobre el panel
    //Ya que esta clase hereda de JPanel(donde se puede pintar), se crearan las raquetas y pelota
    @Override
    public void paintComponent(Graphics g){  //Tiene como parametro una variable de tipo Graphics
        super.paintComponent(g);  //Se ejecuta el método paintComponent de la clase padre, se indica con el 'super'

        //Se crea un objeto de tipo Graphics2D(Subclase de Graphics) ya que tiene mas herramientas para pintar
        Graphics2D g2 = (Graphics2D) g;  //Se hara casting para covertir el objeto Graphics en Graphics2D

        //El objeto g2 esta listo para acceder a metodos de la clase Graphics2D y dibujar
        //Dibujo de pelota
        g2.setColor(Color.WHITE);

        //g2.fill(new Rectangle2D.Double(0,0,10,10));  dibujo de pelota sin usar una clase especifica

        //Se llama al metodo dibujar para ejecutar/plasmar el dibujo en el panel
        dibujar(g2);  //El parametro g2 que previamente se casteo tambien se establecio con el color blanco

        //Ya que se dibujo el primer frame con los componentes en una posición, se debe renovar el movimiento con el método actualizar
        actualizar();
    }

    //Método destinado para configurar cada elemento que se pintara en el panel
    public void dibujar(Graphics2D g){  //El parametro que va a recibir es el que se casteo de Graphics a Graphics2D
        //Se accedera al metodo fill para establecer que se hara uso de un cubo(pelota) y para dar las dimendiones
        //se pasa como parametro el método getPelota(), al cual se accede mediante el objeto perteneciente a la clase Pelota
        g.fill(pelota.getPelota());
    }

    //Método para renovar cada movimiento de los elementos que se dibujaran dentro del panel
    public void actualizar(){
        pelota.mover(getBounds());  //Se pasa como parametro las dimensiones del panel
    }

}

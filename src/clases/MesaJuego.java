package clases;

//En esta clase se dibujara la mesa, raquetas y pelota

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MesaJuego extends JPanel {
    //Para comenzar a dibujar los elementos, primero se debe crear un panel(lamina) que ira sobre la ventana previamente configurada
    //Ya colocado el panel, se podran dibujar cada componente sobre el.

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
        g2.fill(new Rectangle2D.Double(0,0,10,10));
    }

}

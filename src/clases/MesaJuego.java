package clases;

//En esta clase se dibujara la mesa, raquetas y pelota

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

//Para comenzar a dibujar los elementos, primero se debe crear un panel(lamina) que ira sobre la ventana previamente configurada
//Ya colocado el panel, se podran dibujar cada componente sobre el.
public class MesaJuego extends JPanel {

    Pelota pelota = new Pelota(393,231);  //Crear objeto de la clase Pelota con las coordenadas en el centro de la ventana, asi sera la primera aparicion


    //Se hara la instancia de la clase Raqueta para crear 2 objetos de ella, con distintas posiciones(coordenadas)
    //Aunque a un inicio se dieron las medidas de ANCHO:800 y ALTO:500 para la ventana(panel), el area donde se trabajara
    //se reduce debido a los bordes en ambos ejes, para saber la dimension del área de trabajo se debe acceder
    //al metodo getMaxX/Y() mediante getBounds y mostrara en la terminal las dimensiones.  x:786  y:463
    Raqueta raqueta1 = new Raqueta(10,200);
    Raqueta raqueta2 = new Raqueta(786 -10 - Raqueta.ANCHO,200);  //Se restara 10 de espacio y el ancho de la raqueta

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

        //Para saber la dimension final del panel, se imprime el valor maximo de los ejes X, Y.
        //System.out.println("x: "+ getBounds().getMaxX());    R:786
        //System.out.println("y: "+ getBounds().getMaxY());    R:463

        //Se pintan las 2 raquetas
        g.fill(raqueta1.getRaqueta());
        g.fill(raqueta2.getRaqueta());

        //Trazado de linea central
        g.fill(pelota.lineaCentral(getBounds()));

        //Se configura el tipo de fuente,borde y tamaño
        g.setFont(new Font("Serif",Font.BOLD,34));  //Se le dara formato y tamaño a la fuente
        g.drawString(pelota.jugador1+"",200,35);  //Se escribe el mensaje a mostrar y las coordenadas en donde se dibujara
        g.drawString( pelota.jugador2+"",590,35);  //El primer parametro es un contador, solo se concatena a una cadena vacia para ser mostrado
    }

    //Método para renovar cada movimiento de los elementos que se dibujaran dentro del panel
    public void actualizar(){
        //Se pasa como primer parametro las dimensiones del panel
        //El segundo parametro se manda con que otro elemento hara colision la pelota, en este caso, la raqueta1
        //El tercer parametro al igual que el segundo, se manda las dimensiones(objeto) con el que hara colision la pelota
        pelota.mover(getBounds(), colision(raqueta1.getRaqueta()), colision(raqueta2.getRaqueta()));

        //Se agrega el movimiento de las 2 raquetas, mediante las 2 instancias que ya tenemos de la clase Raqueta
        raqueta1.moverR1(getBounds());
        raqueta2.moverR2(getBounds());


        //pelota.lineaCentral(getBounds());
    }

    //Se creara el método 'colision' de tipo boolean ya que el return sera 'true' o 'false' en caso de que la pelota colisione con las raquetas
    //El método 'intersects' perteneciente de la clase 'Rectangle' determina o detecta si dos rectangulos se cruzan o no, y dependiendo
    //de eso retorna true o false, en este caso, se tienen involucradas las raquetas y la pelota
    private boolean colision(Rectangle2D r){
        return pelota.getPelota().intersects(r);
    }

}

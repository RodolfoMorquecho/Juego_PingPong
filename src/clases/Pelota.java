package clases;

//Clase especial para configurar dimensiones y posicion de la pelota

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Pelota {
    private int x;
    private int y;
    private final int ANCHO = 15;
    private final int LARGO = 15;

    public Pelota(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Rectangle2D getPelota(Graphics g){  //Método que retorna posicionamiento y tamño de la pelota
        return new Rectangle2D.Double(x, y, ANCHO, LARGO);
    }
}

package clases;

import java.awt.geom.Rectangle2D;

//Clase que configura las variables/método para pintar las raquetas
public class Raqueta {
    //Coordenadas de las posiciones iniciales de la raqueta
    private int x;
    private int y;

    //Dimensiones de la raqueta
    static final int ANCHO = 10;  //Se declaran estaticas para poder ser invocadas desde otras clases
    static final int ALTO = 40;

    public Raqueta(int x, int y){  //Se asignaran mediante parametro las ccordenadas
        this.x = x;
        this.y = y;
    }

    public Rectangle2D getRaqueta(){  //Método que retorna posicionamiento y tamño de la raqueta
        return new Rectangle2D.Double(x, y, ANCHO, ALTO);
    }
}

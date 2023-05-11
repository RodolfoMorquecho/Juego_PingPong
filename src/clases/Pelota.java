package clases;

//Clase especial para configurar dimensiones y posicion de la pelota

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Pelota {
    private int x;
    private int y;

    //Variables para incrementar/decrementar las coordenadas del posicionamiento de la pelota
    private int dx = 1;
    private int dy = 1;

    //Contantes de las dimensiones que siempre tendra la pelota
    private final int ANCHO = 15;
    private final int LARGO = 15;

    public Pelota(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Rectangle2D getPelota(){  //Método que retorna posicionamiento y tamño de la pelota
        return new Rectangle2D.Double(x, y, ANCHO, LARGO);
    }

    //Método que contiene la interacción de la pelota dentro de la mesa de juego
    //Se pasara como parametro un objeto de tipo Rectangle llamado limites que nos dara el tamaño maximo en los ejes x,y.
    public void mover(Rectangle limites){
        //x: posición actual    dx: incremento/decremento de 1
        x += dx;  // x = x+1  ó  x = x-1   || posición actual = posición actual + 1
        y += dy;

        if(x > limites.getMaxX()){  //Si la pelota es mayor al limite del panel en el eje x:
            dx = -dx;  //Ahora dx ya no valdra 1, si no que valdra "-1" hasta que la pelota se encuentre con el limite contrario
        }

        if (y > limites.getMaxY()){  //Si la pelota es mayor al limite del panel en el eje y:
            dy = -dy;  ////Ahora dy ya no valdra 1, si no que valdra "-1" hasta que la pelota se encuentre con el limite contrario
        }

        if (x < 0){  //Si la pelota es menor al limite del panel en el eje x:
            //En este punto dx = -1 ya que la pelota estaba en movimiento hacia la pared izquiera del panel, por lo que es
            //necesario que se comienze a mover a la derecha(que incrementen las coordenadas en el plano positivo)
            dx = -dx; // dx = -dx  ---->  dx = -(-1)  ---->  dx = 1
        }

        if (y < 0){
            dy = -dy;
        }
    }
}

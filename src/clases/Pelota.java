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
    private final int ANCHO = 10;
    private final int LARGO = 10;

    public Pelota(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Rectangle2D getPelota(){  //Método que retorna posicionamiento y tamño de la pelota
        return new Rectangle2D.Double(x, y, ANCHO, LARGO);
    }

    //Método que contiene la interacción de la pelota dentro de la mesa de juego
    //Se pasara como parametro un objeto de tipo Rectangle llamado limites que nos dara el tamaño maximo en los ejes x,y.
    //Se pasaran 2 parametros mas de tipo boolean donde se transmite si han hecho contacto con alguna de las raquetas
    public void mover(Rectangle limites, boolean colisionR1, boolean colisionR2){
        //x: posición actual    dx: incremento/decremento de 1
        x += dx;  // x = x+1  ó  x = x-1   || posición actual = posición actual + 1
        y += dy;

        if (colisionR1){  //Si la colision en la raqueta1 es true:
            dx = -dx;  //Cambia la dirección de la pelota

            //Existe un bug al correr el juego, cuando la pelota colisiona con la raqueta no rebota hacia la dirección contraria
            //debido a que se tiene en la condición de movimiento de la pelota que se va a desplazar hasta los limites de la
            //ventana, por lo que aunque toque la raqueta se trata de escurrir hacia los lados para llegar al borde de la ventana.

            //Una alternativa para que se elimine ese bug es posicionar la pelota en el borde de la raqueta y ejecute el cambio de dirección
            x = 10+ANCHO;
        }
        if (colisionR2){  //Si la colision en la raqueta2 es true:
            dx = -dx;  //Cambia la dirección de la pelota

            //Se situa la pelota en la orilla de la raqueta2:
            //Se hace casting debido a que "limites.getMaxX()" es de tipo double.
            //para posicionar correctamente la pelota, a la medida total en X se le resta 10 del espacio entre raqueta y el borde,
            //el ancho de la raqueta y el ancho de la pelota(para que no se sobreposicione a la altura de la raqueta2)
            x = (int) (limites.getMaxX()-10-ANCHO-Raqueta.ANCHO);
        }

        if(x >= limites.getMaxX()-10){  //Si la pelota es mayor al limite del panel en el eje x:
            dx = -dx;  //Ahora dx ya no valdra 1, si no que valdra "-1" hasta que la pelota se encuentre con el limite contrario
        }

        //El "-10" es devido a que el tamaño de la pelota tiene esas dimensiones, asi que al llgar al borde pareciera que lo
        //atraviesa ya que la condicion detecta la orilla izquierda de la pelota como la posicion con la que trabaja

        if (y >= limites.getMaxY()-10){  //Si la pelota es mayor al limite del panel en el eje y:
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

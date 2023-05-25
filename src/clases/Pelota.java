package clases;

//Clase especial para configurar dimensiones y posicion de la pelota

import obtenerRecursos.Audio;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Pelota {
    private int x;
    private int y;

    //Variables correspondientes a los marcadores de cada jugador
    int jugador1 = 0;  //Se incrementara, cada que el jugador marque una anotacion
    int jugador2 = 0;

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

    //-------------------------SECCION DE INSTANCIACION Y DECLARACION DE ARCHIVOS DE AUDIO------------------------------
    Audio audio = new Audio();  //Crear objeto de la clase audio para tener acceso al metodo getAudio y asignar las direcciones de los sonidos
    AudioClip rebote1 = audio.getAudio("/recursos/rebote_pelota1.wav");  //Se asigna el sonido correspondiente a cada var declarada
    AudioClip rebote2 = audio.getAudio("/recursos/rebote_pelota2.wav");
    AudioClip falta = audio.getAudio("/recursos/falta.wav");  //Sonido para cuando haya un punto de cualquiera de los 2 jugadores
    //-------------------------------------------------------------------------------------------------------------------

    //Método que retorna el rectangulo(pelota) con todas sus dimensiones especificadas
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


        //------------------------------SECCION DE COLISION ENTRE PELOTA Y RAQUETAS-------------------------------
        if (colisionR1){  //Si la colision en la raqueta1 es true:
            dx = -dx;  //Cambia la dirección de la pelota

            //Existe un bug al correr el juego, cuando la pelota colisiona con la raqueta no rebota hacia la dirección contraria
            //debido a que se tiene en la condición de movimiento de la pelota que se va a desplazar hasta los limites de la
            //ventana, por lo que aunque toque la raqueta se trata de escurrir hacia los lados para llegar al borde de la ventana.

            //Una alternativa para que se elimine ese bug es posicionar la pelota en el borde de la raqueta y ejecute el cambio de dirección
            x = 10+ANCHO;

            rebote1.play();  //Cada que colisione con la raqueta1 se reproducira el sonido de "rebote1"
        }
        if (colisionR2){  //Si la colision en la raqueta2 es true:
            dx = -dx;  //Cambia la dirección de la pelota

            //Se situa la pelota en la orilla de la raqueta2:
            //Se hace casting debido a que "limites.getMaxX()" es de tipo double.
            //para posicionar correctamente la pelota, a la medida total en X se le resta 10 del espacio entre raqueta y el borde,
            //el ancho de la raqueta y el ancho de la pelota(para que no se sobreposicione a la altura de la raqueta2)
            x = (int) (limites.getMaxX()-10-ANCHO-Raqueta.ANCHO);

            rebote2.play();  //Cada que colisione con la raqueta2 se reproducira el sonido de "rebote2"
        }


        //---------------------SECCION DE LIMITES DESPLAZAMIENTO DE LA PELOTA Y COMPORTAMIENTO-------------------------
        if(x >= limites.getMaxX()-10){  //Si la pelota es mayor al limite del panel en el eje x:
            dx = -dx;  //Ahora dx ya no valdra 1, si no que valdra "-1" hasta que la pelota se encuentre con el limite contrario
        }

        //El "-10" es devido a que el tamaño de la pelota tiene esas dimensiones, asi que al llgar al borde pareciera que lo
        //atraviesa ya que la condicion detecta la orilla izquierda de la pelota como la posicion con la que trabaja

        if (y >= limites.getMaxY()-10){  //Si la pelota es mayor al limite del panel en el eje y(Se resta 10 por el ancho de pelota):
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

        //-----------------------------SECCION DE MARCADOR Y FRONTERAS DE ANOTACION------------------------------------
        //Se crean 2 banderas las cuales seran las lineas que indicaran que el equipo contrario marco 1 punto en cancha contraria
        //boolean lineaMeta1 = false;
        //boolean lineaMeta2 = false;

        int lineaMeta1 = (int) (limites.getMinX()+5+Raqueta.ANCHO);  //Limite de mete del primer jugador
        int lineaMeta2 = (int) (limites.getMaxX()-5-Raqueta.ANCHO);  //Limite de meta del segundo jugador

        int centroX = (int) (limites.getMaxX()/2);  //Obtener el centro en eje X para que la pelota siempre aparezca desde ese punto

        if(x < lineaMeta1){  //Si la posicion en X de la pelota es menor que la linea de meta del jugador1:
            //jugador1 y jugador2 son variables globales
            jugador2++;  //El marcador del jugador 2 aumenta su puntuacion
            //String player1 = String.valueOf(jugador2);  //Se pasa de enteo a String para poder pasarlo como parametro en el el metodo "drawString"
            x = centroX+1;  //Al hacer anotación la pelota hace respawn en el centro de la mesa
            dx = -dx;  //Se cambia la dirección hacia el jugador que marco un punto

            falta.play();  //Cada que haya una anotacion del jugador2 se reproducira el sonido "falta"
        }
        if (x > lineaMeta2){  //Si la posicion en X de la pelota es menor que la linea de meta del jugador2:
            jugador1++;  //El marcador del jugador 1 aumenta su puntuacion
            x = centroX-1;
            dx = -dx;

            falta.play();  //Cada que haya una anotacion del jugador1 se reproducira el sonido "falta"
        }
    }

    //Trazo de la linea central de la mesa
    public Rectangle2D lineaCentral(Rectangle limites){  //Se pasa como parametro las medidas de la mesa de juego
        int centroX = (int) ((limites.getMaxX() / 2) - 1);  //Se obtiene la posicion central en 'x' de la mesa y e le resta 1
        int LARGO = (int) limites.getMaxY();  //Hacemos casting para obtener el tamaño de 'y' de la mesa
        int ANCHO = 3;  //Tamaño de la linea central
        return new Rectangle2D.Double(centroX, limites.getMinY(), ANCHO, LARGO);  //Retornar el objeto con todas las medidas y posicion para ser dibujada
    }
}

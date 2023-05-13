package clases;

import java.awt.*;
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

    //Se crearan los metodos para darle las mecanicas(movimiento) a las raquetas

    //Rauqeta 1
    public void moverR1(Rectangle limites){  //Se pasa como parametro la dimension donde se desplazara la raqueta
        if (EventoTeclado.w){  //Cuando w es verdadero, significa que esta siendo presionada
            y--;  //se aumentara de 1 en 1, como sabemos la raqueta esta en medio para que visualmente suba, se deben disminuir posicione en y
        }
        if (EventoTeclado.s){
            y++;
        }
    }

    //Raqueta 2
    public void moverR2(Rectangle limites){
        if (EventoTeclado.up){  //Se puede acceder a las variables sin instanciar la clase, debido a que son de tipo static
            y--;
        }
        if (EventoTeclado.down){
            y++;
        }
    }
}

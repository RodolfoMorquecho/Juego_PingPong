package clases;

import javax.swing.*;
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
        //En el condicional tambien se debe de poner un limite para que el cuerpo de la raqueta no sobrepase los limites de la ventana
        if (EventoTeclado.w  &&  y > limites.getMinY()){  //Cuando w es verdadero, significa que esta siendo presionada
            y--;  //se aumentara de 1 en 1, como sabemos la raqueta esta en medio para que visualmente suba, se deben disminuir posicione en y
        }
        //Si 's' es true y la posición 'y' de la raqueta restando el ALTO de la misma es menor al limite maximo, se podra mover la raquete
        //Se resta el ALTO para el limite inferior, ya que toma la esquina izq. superior de la raqueta como el punto final de la misma,
        //visualmente hasta que ese punto no toque el limite seguira desplazandose hacia abajo.
        if (EventoTeclado.s  &&  y < limites.getMaxY()-ALTO){
            y++;
        }
    }

    //Raqueta 2
    public void moverR2(Rectangle limites){
        if (EventoTeclado.up  &&  y > limites.getMinY()){  //Se puede acceder a las variables sin instanciar la clase, debido a que son de tipo static
            y--;
        }
        if (EventoTeclado.down  &&  y < limites.getMaxY()-ALTO){
            y++;
        }
    }
}

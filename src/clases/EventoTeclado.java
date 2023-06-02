package clases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Clase en la que se configurara el movimiento de las raquetas desde teclado

//Se heredara el contenido de la clase KeyAdapter, está clase permite trabajar con eventos de teclado, es decír, nos permite
//detectar si una tecla ha sido presionada o no.
public class EventoTeclado extends KeyAdapter {

    //Se crearan 4 variables de tipo boolean que se utilizaran para asignar al control de las raquetas, es necesario sean static para usarlas dentro del método
    // 'w' y 's' seran asociadas con la primer raqueta, con w la raqueta subira y con s bajara
    // 'up' y 'down' seran asociadas con la segunda raqueta, con up la raqueta subira y con down bajara
    static boolean w, s, up, down, pause;
    int i = 0;  //Contador para la tecla de espacio, cada que sea impar se pausara y cuando sea par quitara la pausa

    static JLabel etiqueta = new JLabel();  //Se crea objeto de tipo label para poner una etiqueta de indicacion cuando el juego esta pausado

    //Al heredar de la clase KeyAdapter se puede hacer uso del método KeyPressed y el método KeyReleased, en ambos se pasa como parametro un 'KeyEvent'
    //El objeto KeyEvent contiene información acerca de que tecla es presionada o deja de serlo
    //KeyPressed: se invocara cada que se presiona una tecla
    //KeyReleased: se invocara cada que se suelte una tecla

    @Override
    public void keyPressed(KeyEvent e) {
        //Se declara una variable de tipo entero llamada 'id', el cual almacenara el código de la tecla que se presione
        int id = e.getKeyCode();

        //En la api de java se puede verificar la forma de usar cada tecla y cual es su valor en código ASCII que sera el
        //que se guardara en la variable 'id'

        //A continuación se hara la validación de las teclas que usaremos para mover las raquetas
        if(id == KeyEvent.VK_W){  //Si el evento registrado en teclado coincide con valor establecido de 'VK_W' perteneciente a KeyEvent
            w = true;  //EL valor de 'w' pasa a estado true
        }
        if (id == KeyEvent.VK_S){  //Raquete1 -> mover abajo
            s = true;
        }
        if (id == KeyEvent.VK_UP){  //Raquete2 -> mover arriba
            up = true;
        }
        if (id == KeyEvent.VK_DOWN){  //Raquete2 -> mover abajo
            down = true;
        }

        //Al presionar la barra espaciadora se pone y quita pausa
        if (id == KeyEvent.VK_SPACE){
            i++;  //Aumenta el contador a 1 y el juego esta en pausa

            if (i%2 != 0){  //Ejecutara las acciones solo si el valor del contador 'i' es impar
                //Al estar en pausa el juego se agregara una etiqueta que se pintara en la mitad de la mesa, indicando al jugador la pausa
                //Debido a que a la mitad tambien se encuentre la linea central que indica el respawn de pelota, se pierde un poco
                //Para poner un fondo del mismo color de mesa(negro) a la etiqueta se necesita activar el metodo "setOpaque"
                etiqueta.setOpaque(true);  //Permitir que se tenga acceso a pintar el fondo de la etiqueta
                EventoTeclado.etiqueta.setBackground(Color.BLACK);  //Se pinta de negro el fondo de etiqueta

                pause = true;  //Al presionar por primera vez se le asigna true a la variable pause, teniendo en cuenta que el contador vale 0 en este punto

                etiqueta.setText("Pausa");  //Cuando entre en este condicional, la etiqueta mostrara este mensaje
            }
            if (i%2 == 0){  //Se verifica el condicional 1%2 == 0 y no se cumple ya que el residuo es diferente de 0, el juego sigue en pausa
                etiqueta.setText("");  //Cuando entre en este condicional, la etiqueta se limpiara y el juego esta en movimiento
                etiqueta.setOpaque(false);  //Cuando se quita le pausa necesitamos desactivar el color de fondo de la etiqueta para que no tape la linea central de respawn

                pause = false;  //Se cambiara el estado de la variable, cuando se vuelva a presionar la barra espaciadora, aumente el contador a 2
                                //y se pueda acceder al if ya que 2%2 si da como residuo 0.
                //En la clase Hilo se tiene un if con condicional "(!EventoTeclado.pause)":
                //Al presionar la barra se le asigna "true" a "pause" pero con el signo '!' lo pasa a false por lo que se deja de
                //cuplir el condicional y pausa el funcionamiento del hilo hasta que se presione la barra de nuevo para cambiar el estado
                //etiqueta.setVisible(pause);

            }

        }
    }


    //Validación para cuando alguna de las teclas involucradas deje de ser presionada
    @Override
    public void keyReleased(KeyEvent e) {
        //Se declara una variable de tipo entero llamada 'id', el cual almacenara el código de la tecla que se dejo de presionar
        int id = e.getKeyCode();

        if (id == KeyEvent.VK_W){
            w = false;
        }
        if (id == KeyEvent.VK_S){
            s = false;
        }
        if (id == KeyEvent.VK_UP){
            up = false;
        }
        if (id == KeyEvent.VK_DOWN){
            down = false;
        }
        //Para la pausa no son necesarias las 3 lineas sig. ya que al oltar la barra de espacio se terminaria la pausa,
        //y usuamlente la pausa es removida volviendo a presionar la tecla sin tiempo indefinido

        //if (id == KeyEvent.VK_SPACE){
        //    pause = false;
        //}
    }
}


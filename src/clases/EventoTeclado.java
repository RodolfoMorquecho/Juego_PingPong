package clases;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Clase en la que se configurara el movimiento de las raquetas desde teclado

//Se heredara el contenido de la clase KeyAdapter, está clase permite trabajar con eventos de teclado, es decír, nos permite
//detectar si una tecla ha sido presionada o no.
public class EventoTeclado extends KeyAdapter {

    //Se crearan 4 variables de tipo boolean que se utilizaran para asignar al control de las raquetas, es necesario sean static para usarlas dentro del método
    // 'w' y 's' seran asociadas con la primer raqueta, con w la raqueta subira y con s bajara
    // 'up' y 'down' seran asociadas con la segunda raqueta, con up la raqueta subira y con down bajara
    static boolean w, s, up, down;

    //Al heredar de la clase KeyAdapter se puede hacer uso del método KeyPressed y el método KeyReleased, en ambos se pasa como parametro un 'KeyEvent'
    //El objeto KeyEvent contiene información acerca de que tecla es presionada o deja de serlo
    //KeyPressed: se invocara cada que se presiona una tecla
    //KeyReleased: se invocara cada que se suelte una tecla
    public void KeyPressed(KeyEvent e){

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
    }

    //Validación para cuando alguna de las teclas involucradas deje de ser presionada
    public void KeyReleased(KeyEvent e){

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
    }
}

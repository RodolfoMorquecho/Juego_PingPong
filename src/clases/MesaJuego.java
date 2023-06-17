package clases;

//En esta clase se dibujara la mesa, raquetas y pelota

import principal.Principal;

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

    static boolean op1 = false;  //Variable que al cambiar su estado a true, significa que la tecla de "arriba" esta siendo oprimida
    static boolean op2 = false;  //Variable que al cambiar su estado a true, significa que la tecla de "abajo" esta siendo oprimida"

    static boolean bandera1 = false;  //Variable que al ser true, indica que el ganador1 ha llegado primero a los 5 puntos
    static boolean bandera2 = false;  //Variable que al ser true, indica que el ganador2 ha llegado primero a los 5 puntos

    public MesaJuego(){  //Constructor
        setBackground(Color.BLACK);  //El fondo sera negro
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


        //Se llama al metodo dibujar para ejecutar/plasmar el dibujo en el panel
        dibujar(g2);  //El parametro g2 que previamente se casteo tambien se establecio con el color blanco

        //Se llama al siguiente metodo para pintar el puntaje de ambos jugadores y mostrar al ganador
        //Se pinta despues este metedo ya que la pelota y linea central se pintan de negro primero y asi se evita que se sobrepongan sobre el marcador

        //Si los 2 puntajes son menores a 5, el marcador sera visible. En cuanto alguien gane desaparecera
        if (pelota.getPuntaje1() < 5 && pelota.getPuntaje2() < 5){
            dibujarMarcador(g2);  //Se dibuja el marcador
        }

        //Funcion que mostrara al ganador despues de que el marcador desaparezca
        anunciarGanador(g2);

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
        g.fill(pelota.lineaCentral(getBounds()));  //Se pinta de color blanco

        //Condicional para pintar la linea central de negro en caso de que termine el juego
        //pelota.finDeJuego
        if (pelota.getPuntaje1() >= 5 || pelota.getPuntaje2() >= 5){ //Si la variable finDeJuego es true significa que ha terminado el juego
            g.setColor(Color.BLACK);  //Se cambia el color de la fuente
            g.fill(pelota.lineaCentral(getBounds()));  //Se rellena de ese color la linea central
            g.fill(pelota.getPelota());  //Se pinta de negro la pelota al terminar el juego para que no tape el mensaje del ganadors
            g.fill(raqueta1.getRaqueta());  //Se pintan las 2 raquetas de negro, para que visualmente desaparezcan para los jugadores
            g.fill(raqueta2.getRaqueta());
            pelota.ganador.stop();  //Se detenienen todos los sonidos que hay durante la partida de juego
            pelota.rebote1.stop();
            pelota.rebote2.stop();
            pelota.falta.stop();

            //Despues de que la pantalla esta limpia, se pinta el menu con 2 opciones:
            //Comenzar una nueva partida ó Finalizar y cerrar la ventana
            opcionesDeMenu(g);
        }

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
    }

    //Se creara el método 'colision' de tipo boolean ya que el return sera 'true' o 'false' en caso de que la pelota colisione con las raquetas
    //El método 'intersects' perteneciente de la clase 'Rectangle' determina o detecta si dos rectangulos se cruzan o no, y dependiendo
    //de eso retorna true o false, en este caso, se tienen involucradas las raquetas y la pelota
    private boolean colision(Rectangle2D r){
        return pelota.getPelota().intersects(r);
    }

    public void dibujarMarcador(Graphics2D g){

        //Se crean 2 variables de tipo GRaphics2D
        Graphics2D g1 = g;
        Graphics2D g2 = g;

        //Se configura el tipo de fuente,borde y tamaño
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Serif",Font.BOLD,32));  //Se le dara formato y tamaño a la fuente

        //Se pinta la puntuacion de los 2 jugadores con las especificaciones previas de la fuente y coordenadas
        g1.drawString(pelota.jugador1+"",200,35);  //Se escribe el mensaje a mostrar y las coordenadas en donde se dibujara
        g2.drawString( pelota.jugador2+"",590,35);  //El primer parametro es un contador, solo se concatena a una cadena vacia para ser mostrado
    }


    public void anunciarGanador(Graphics2D g){
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Serif",Font.BOLD,32));  //Se le dara formato y tamaño a la fuente

        if (pelota.getPuntaje1() >= 5){  //Si el puntaje del jugador 1 es igual a 5:
            bandera1 = true;

            if (bandera1 && !bandera2){  //Si el puntaje1 es true y el puntaje2 es false, se pintara que el ganador sera el jugador1
                g.drawString("El jugador 1 ha ganado!",235,180);  //Se muestra en pantalla un mensaje del ganador
                pelota.ganador.play();
            }


        }
        if (pelota.getPuntaje2() >= 5){  //Si el puntaje del jugador 2 es igual a 5:
            bandera2 = true;

            if (!bandera1 && bandera2){  //Si el puntaje2 es true y el puntaje1 es false, se pintara que el ganador sera el jugador2
                g.drawString("El jugador 2 ha ganado!",235,180);  //Se muestra en pantalla un mensaje del ganador
                pelota.ganador.play();  //Se agrega el sonido del ganador
            }
        }
    }

    public void opcionesDeMenu(Graphics2D g){
        //Coordenadas x=250   y=220

        //Se crean los 2 objetos de tipo Graphics2D
        Graphics2D g1 = g;
        Graphics2D g2 = g;

        //Se crean las variables que contienen las coordenadas de las 2 opciones del menu final
        int posX1 = 350, posX2 = posX1+35;
        int posY1 = 260, posY2 = posY1+30;

        String cadena1 = "Juego Nuevo";
        String cadena2 = "Salir";

        g.setFont(new Font("Serif",Font.BOLD,18));

        if(EventoTeclado.down){  //Si se presiona la flecha hacia abajo:
            op1 = false;
            op2 = true;
        }
        if (EventoTeclado.up){  //Si se presiona la flecha hacia arriba:
            op1 = true;
            op2 = false;
        }

        if (!op1 && !op2){  //Si las dos variables op son false, se pinta como default la primer opcion seleccionada(en amarillo)
            g.setColor(Color.YELLOW);
            g1.drawString(cadena1, posX1, posY1);

            g.setColor(Color.WHITE);
            g2.drawString(cadena2, posX2, posY2);
        }

        if (!op1 && op2){  //Si op1 es false y op2 es verdadero significa que se presiono abajo, por lo que se esta posicionado en la segunda op()
            g.setColor(Color.WHITE);
            g1.drawString(cadena1, posX1, posY1);
            g.setColor(Color.YELLOW);
            g2.drawString(cadena2, posX2, posY2);
            if (EventoTeclado.enter){  //Si se presiona enter en esta opcion, se cierra el juego
                System.exit(0);
            }
        }

        if (op1 && !op2){  //Si op1 es true y op2 es falso significa que se presiono arriba, por lo que se esta posicionado en la primera op()
            g.setColor(Color.YELLOW);
            g1.drawString(cadena1, posX1, posY1);
            g.setColor(Color.WHITE);
            g2.drawString(cadena2, posX2, posY2);
            if (EventoTeclado.enter){  //Si se presiona enter en esta opcion, se inicia una nueva partida
                pelota.jugador1 = 0;  //Se tiene que resetear el contador del puntaje de los 2 jugadores, para salir del menu final
                pelota.jugador2 = 0;
                EventoTeclado.enter = false;  //Se retorna el valor del enter para que al finalizar el juego nuevo no seleccione la primera opcion que aparezca
                bandera1 = false;  //Se resetan las banderas que indican que jugador llego primero a los 5 puntos para que muestre el marcador
                bandera2 = false;
                op1 = false;  //Se resetean las variables de la seleccion de opciones a false
                op2 = false;
            }
        }

    }

}


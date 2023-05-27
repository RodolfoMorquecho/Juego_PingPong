package clases;

public class Hilo extends Thread{
    //Declaración de objeto de tipo MesaJuego que contiene el método painComponent el cual dibuja todos los componentes y su progresión
    MesaJuego lienzo;

    //A la hora de crear un objeto Hilo, se tendra la opción de pasar como parametro el panel sobre el que e haran las multitareas
    public Hilo(MesaJuego lienzo){
        this.lienzo = lienzo;
    }

    public void run(){
        //Mientras sea true, el panel/lienzo/lamina se vuelve a dibujar

        //finDeJuego esta inicializada como false, así que, el condicional dentro del while "!finDeJuego" quiere decir:
        //Mientras finDeJuego = true, ejecutara las acciones dentr del while

        //Si se cambia el estado del "finDejuego" a true, al llegar al punto del while lo convertira en false con el "!" por lo que no ejecutara las acciones
        while (!Pelota.finDeJuego){
            try {
                Thread.sleep(4);  //El método sleep(t) de la clase Thread permite detener la ejecución del thread durante t milisegundos.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lienzo.repaint();  //El método repaint, vuelve a dibujar los componentes en la nueva posición y tapa el mismo componente en la pos anterior
        }
    }
}

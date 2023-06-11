package clases;

//Se crea un segundo Hilo para trabajar con el menu, cuando el primer hilo esta pausado
public class HiloMenu {
    MesaJuego lienzo;

    public HiloMenu(MesaJuego lienzo){
        this.lienzo = lienzo;
    }
}

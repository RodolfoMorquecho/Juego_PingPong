package obtenerRecursos;


import java.applet.AudioClip;

public class Audio {

    //Se crea un método getter que retorna un elemendo de tipo "AudioClip"
     public AudioClip getAudio(String direccion){  //Se recibe por parametro la direccion(dentro de los paquetes) del archivo de audio
         return java.applet.Applet.newAudioClip(getClass().getResource(direccion));  //Retorna el audio, segun la direccion argumentada
     }
}

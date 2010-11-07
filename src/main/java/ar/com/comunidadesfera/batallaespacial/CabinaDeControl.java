package ar.com.comunidadesfera.batallaespacial;

import java.util.Queue;

public interface CabinaDeControl {

    /**
     * @pre ninguna.
     * @post devuelve el Radar de la Cabina de ControlAplicacion utilizado
     *       para detectar las Entidades en el contexto de la Nave. 
     * 
     * @return Radar de la Nave correspondiente.
     */
    Radar getRadar();
    
    /**
     * @pre ninguna.
     * @post devuelve el Monitor de la Cabina de ControlAplicacion utilizado
     *       para conocer el estado en que se encuentra la Nave.
     * 
     * @return Monitor de la Nave correspondiente.
     */
    Monitor getMonitor();
    
    /**
     * @pre ninguna.
     * @post devuelve el ControlAplicacion de la Cabina utilizado para crear
     *       los Comandos de control de la Nave.
     * 
     * @return ControlAplicacion de la Nave correspondiente.
     */
    Control getControl();
    
    
    /**
     * @pre ninguna.
     * @post devuelve la cola de Notificaciones que se han informado
     *       a la Nave entre dos invocaciones sucesivas de 
     *       Piloto#proximoComando().
     *       La Notificaciones son los eventos referidos a la Nave
     *       que ocurrieron entre uno y otro Comando del Piloto.
     * 
     * @return Notificaciones.
     */
    Queue<Notificacion> getNotificaciones();
}

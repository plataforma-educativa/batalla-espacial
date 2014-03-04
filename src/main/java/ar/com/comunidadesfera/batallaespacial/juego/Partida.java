package ar.com.comunidadesfera.batallaespacial.juego;

import java.util.List;
import java.util.Set;

import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.DefinicionDeBases;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.DefinicionDeNaves;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.Reglamentacion;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;

public interface Partida {

    void comenzar();

    Tablero getTablero();
    
    List<PilotoControlado> getPilotos();

    Configuracion getConfiguracion();
    
    ControlDeRadar getControlDeRadar();

    List<BaseEspacial> getBases();

    List<Nave> getNaves();
    
    /**
     * 
     * @return Motor que ejecuta la Partida, null si la Partida no comenzó. 
     */
    Motor getMotor();
    
    /**
     * 
     * @return Thread que esta ejecutando el Motoir de la Partida, null si aún no se
     *         comenzó la ejecución.
     */
    Thread getThreadMotor();

    Set<Pieza> getPiezasDestruidas();

    /**
     * @return Reglamentacion a utilizar por la Partida.
     */
    Reglamentacion getReglamentacion();

    /**
     * @return Estrategia utilizada para asignar Bases Espaciales a los
     *         Participantes.
     */
    DefinicionDeBases getEstrategiaDeDefinicionDeBases();

    /**
     * @return Estrategia utilizada para asignar Naves a los Pilotos de los
     *         Participantes.
     */
    DefinicionDeNaves getEstrategiaDeDefinicionDeNaves();
}

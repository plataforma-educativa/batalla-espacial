package ar.com.comunidadesfera.batallaespacial.juego;

import java.util.List;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.DefinicionDeBases;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.DefinicionDeNaves;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.Reglamentacion;

public interface Configuracion {

    /**
     * @return lista de Participantes de una Partida.
     */
    List<Participante> getParticipantes();
    
    /**
     * @param civilizacion
     * @return Participante que pertenece a la Civilizacion dada.
     */
    Participante getParticipante(Civilizacion civilizacion);
    
    /**
     * @return Tablero que se usará en una Partida.
     */
    Tablero getTablero();
    
    /**
     * @return cantidad de rondas que se van a jugar en una Partida.
     */
    int getRondas();
    
    /**
     * @return milisegundos a esperar entre cada ronda.
     */
    long getTimeout();
    
    /**
     * @return estrategia a utilizar para definir las Bases en la Partida.
     */
    DefinicionDeBases getDefinicionDeBases();
    
    /**
     * @return estrategia a utilizar para definir las Naves en la Partida.
     */
    DefinicionDeNaves getDefinicionDeNaves();
    
    /**
     * @return Reglamentación a usar durane la Partida.
     */
    Reglamentacion getReglamentacion();
}

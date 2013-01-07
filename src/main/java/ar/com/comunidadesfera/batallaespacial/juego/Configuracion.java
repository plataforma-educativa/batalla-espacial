package ar.com.comunidadesfera.batallaespacial.juego;

import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;

public class Configuracion<T extends Participante> {

    /**
     * Tablero utilizado en la Partida
     */
    private Tablero tablero;
    
    /**
     * Cantidad de rondas de una Partida
     */
    private int rondas;
    
    /**
     * Timeout entre Rondas
     */
    private long timeout;
    
    /**
     * Ruta a partir de la cual se relativiza la configuración
     */
    private Path ruta;
    
    /**
     * Participantes de la Partida
     */
    private List<T> participantes = new LinkedList<T>();
    
    public List<T> getParticipantes() {
        
        return this.participantes;
    }

    /**
     * 
     * @param civilizacion
     * @return Participante que pertenece a la Civilizacion dada.
     */
    public T getParticipante(Civilizacion civilizacion) {
        
        T participante = null;
        
        Iterator<T>  it = this.getParticipantes().iterator();
        while (it.hasNext() && (participante == null)) {

            participante = it.next();
            
            if (! participante.getCivilizacion().equals(civilizacion)) {
                
                participante = null;
            }
        }

        return participante;
    }
    
    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    public String toString() {
        
        return new StringBuffer()
                    .append("Configuracion [ tablero: ")
                    .append(this.getTablero())
                    .append(", participantes: ")
                    .append(this.getParticipantes())
                    .append(" ]")
                    .toString();
    }
    
    public int getRondas() {
        return rondas;
    }

    public void setRondas(int rondas) {
        this.rondas = rondas;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public Path getRuta() {
        
        return this.ruta;
    }
    
    public void setRuta(Path ruta) {
        
        this.ruta = ruta;
    }
}

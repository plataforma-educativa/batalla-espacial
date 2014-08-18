package ar.com.comunidadesfera.batallaespacial.config;

import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.BaseUnicaPosicionadaAleatoriamente;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.DefinicionDeBases;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.DefinicionDeNaves;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.NavesDeCombatePosicionadasEnBase;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.Reglamentacion;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias.ReglamentacionBasica;

public class ConfiguracionBasica implements Configuracion {

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
    private List<Participante> participantes = new LinkedList<Participante>();

    private DefinicionDeNaves definicionDeNaves;

    private DefinicionDeBases definicionDeBases;

    private Reglamentacion reglamentacion;
    
    @Override
    public List<Participante> getParticipantes() {
        
        return this.participantes;
    }

    /**
     * 
     * @param civilizacion
     * @return Participante que pertenece a la Civilizacion dada.
     */
    @Override
    public Participante getParticipante(Civilizacion civilizacion) {
        
        boolean encontrado = false;
        Participante participante = null;
        
        Iterator<Participante>  it = this.getParticipantes().iterator();
        while (it.hasNext() && (! encontrado)) {

            participante = it.next();
            encontrado = participante.getCivilizacion().equals(civilizacion);
        }

        return encontrado ? participante : null;
    }
    
    @Override
    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    @Override
    public int getRondas() {
        return rondas;
    }

    public void setRondas(int rondas) {
        this.rondas = rondas;
    }

    @Override
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

    @Override
    public DefinicionDeBases getDefinicionDeBases() {
        
        if (this.definicionDeBases == null) {
            
            this.definicionDeBases = new BaseUnicaPosicionadaAleatoriamente();
        }
        return this.definicionDeBases;
    }

    @Override
    public DefinicionDeNaves getDefinicionDeNaves() {

        if (this.definicionDeNaves == null) {
            
            this.definicionDeNaves = new NavesDeCombatePosicionadasEnBase();
        }
        
        return this.definicionDeNaves;
    }

    @Override
    public Reglamentacion getReglamentacion() {

        if (this.reglamentacion == null) {
            
            this.reglamentacion = new ReglamentacionBasica();
        }
        
        return this.reglamentacion;
    }

    public void setDefinicionDeNaves(DefinicionDeNaves estrategia) {
        
        this.definicionDeNaves = estrategia;
    }

    public void setDefinicionDeBases(DefinicionDeBases estrategia) {

        this.definicionDeBases = estrategia;
    }

    public void setReglamentacion(Reglamentacion estrategia) {

        this.reglamentacion = estrategia;
    }
    
    @Override
    public String toString() {
        
        return new StringBuffer()
                    .append("Configuracion [ tablero: ")
                    .append(this.getTablero())
                    .append(", participantes: ")
                    .append(this.getParticipantes())
                    .append(" ]")
                    .toString();
    }
}

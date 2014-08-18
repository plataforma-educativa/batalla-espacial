package ar.com.comunidadesfera.batallaespacial.galaxias;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;
import ar.com.comunidadesfera.batallaespacial.juego.partidas.PartidaSimple;

/**
 * Implementación básica de Batalla Espacial. Es una implementación completa
 * y un punto de extensión para Batallas personalizadas y/o diferenciadas.
 * 
 * @author Mariano Tugnarelli
 *
 */
public abstract class BatallaEspacialBasica implements BatallaEspacial {

    private static Logger LOG = LoggerFactory.getLogger(BatallaEspacial.class);
    
    private List<Observador> observadores;
    
    public BatallaEspacialBasica() {
        
        LOG.info("Batalla Espacial creada: {}", this.getClass().getName());

        this.observadores = new LinkedList<Observador>();
    }

    @Override
    public void iniciar() {
        
        LOG.debug("Iniciar");
    }

    @Override
    public void agregarObservador(Observador observador) {

        this.observadores.add(observador);
    }
    
    @Override
    public Partida jugar(Configuracion configuracion) {
        
        Partida partida = new PartidaSimple(configuracion);
        
        for (Observador observador : this.observadores) {
            
            observador.jugando(this, partida);
        }
        
        LOG.debug("Jugando: {}", partida);
        
        return partida;
    }
    
    protected InputStream leerTablero(String ruta, String rutaPorDefecto) {
        
        InputStream tablero = this.getClass().getResourceAsStream(ruta);
        
        if (tablero == null) {
            
            tablero = this.getClass().getResourceAsStream(rutaPorDefecto);
        }
        
        return tablero;
    }
}

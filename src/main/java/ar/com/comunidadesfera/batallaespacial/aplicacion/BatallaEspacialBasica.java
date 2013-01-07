package ar.com.comunidadesfera.batallaespacial.aplicacion;

import java.util.LinkedList;
import java.util.List;

import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;
import ar.com.comunidadesfera.batallaespacial.calificadores.Basica;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;
import ar.com.comunidadesfera.batallaespacial.ui.FrameAplicacion;
import ar.com.comunidadesfera.batallaespacial.ui.VistaAplicacion;
import ar.com.comunidadesfera.batallaespacial.ui.VistaAplicacionThreadSafe;

/**
 * Implementación básica de Batalla Espacial. Es una implementación completa
 * y un punto de extensión para Batallas personalizadas y/o diferenciadas.
 * 
 * @author Mariano Tugnarelli
 *
 */
@Basica
public class BatallaEspacialBasica implements BatallaEspacial {

    private List<Observador> observadores;
    
    private ControlAplicacion control;
    private VistaAplicacion vista;
    
    public BatallaEspacialBasica() {

        this.observadores = new LinkedList<Observador>();
    }

    /**
     * @pre se encuentra inicializada la Vista de la Aplicación.
     * @post instancia y devuelve el ControlAplicación.
     */
    protected ControlAplicacion crearControlAplicacion() {
        
        return new ControlAplicacion(this.getVista());
    }
    
    /**
     * @post instancia y devuelve la VistaAplicación.
     */
    protected VistaAplicacion crearVistaAplicacion() {
        
        return new VistaAplicacionThreadSafe(new FrameAplicacion());
    }

    protected ControlAplicacion getControl() {
        
        return this.control;
    }
    
    public VistaAplicacion getVista() {
        
        return this.vista;
    }

    @Override
    public void iniciar() {
        
        this.vista = this.crearVistaAplicacion();
        
        this.control = this.crearControlAplicacion();
        
        /* hace visible el frame */
        this.getVista().setVisible(true);
        
        for (Observador observador : this.observadores) {
            
            observador.iniciada(this);
        }
        
    }

    @Override
    public void agregarObservador(Observador observador) {

        this.observadores.add(observador);
    }
    
    @Override
    public <P extends Participante> Partida<P> jugar(Configuracion<P> configuracion) {
        
        Partida<P> partida = new Partida<P>(configuracion);
        
        for (Observador observador : this.observadores) {
            
            observador.jugando(this, partida);
        }
        
        return partida;
    }
}

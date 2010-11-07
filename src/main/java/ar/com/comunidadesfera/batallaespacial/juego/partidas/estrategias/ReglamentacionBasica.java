package ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias;

import java.util.HashMap;
import java.util.Map;

import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.Reglamento;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.NaveDeCombate;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.ReglamentoNaveDeCombate;

/**
 * Implementación elemental de Reglamentación. 
 * 
 * @author Mariano Tugnarelli
 *
 */
public class ReglamentacionBasica extends EstrategiaAbstracta 
                                  implements Reglamentacion {

    private Map<Class<? extends Pieza>, Reglamento> reglamentos; 

    public ReglamentacionBasica() {
     
        /* crea el mapa para todos los posibles reglamentos */
        this.reglamentos = new HashMap<Class<? extends Pieza>, Reglamento>();
        
    }

    /**
     * @post configura los reglamentos.
     */
    protected void configurar() {

        /* agrega los reglamentos por default */
        this.reglamentos.clear();
        
        this.reglamentos.put(Pieza.class, 
                             new Reglamento(this.getPartida()));
        this.reglamentos.put(NaveDeCombate.class, 
                             new ReglamentoNaveDeCombate(this.getPartida()));
    }

    public Reglamento getReglamento(Pieza pieza) {

        Reglamento reglamento = this.reglamentos.get(pieza.getClass());
        
        /* si no existe utiliza el reglamento por default */
        if (reglamento == null) {
            
            reglamento = this.reglamentos.get(Pieza.class);
        }
        
        return reglamento;
    }

}

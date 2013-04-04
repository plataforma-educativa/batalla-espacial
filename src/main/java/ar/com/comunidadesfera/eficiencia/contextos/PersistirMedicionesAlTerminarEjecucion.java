package ar.com.comunidadesfera.eficiencia.contextos;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.InstrumentoDeMedicion;
import ar.com.comunidadesfera.eficiencia.persistencia.AdministradorDeMediciones;
import ar.com.comunidadesfera.eficiencia.persistencia.PersistenciaException;

/**
 * Implementación del Observador de Ejecuciones que persiste las Mediciones
 * realizadas por todos los Instrumentos de Medición al terminar la Ejecución.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class PersistirMedicionesAlTerminarEjecucion implements Ejecucion.Observador {

    private final static Logger logger = LoggerFactory.getLogger(PersistirMedicionesAlTerminarEjecucion.class);
    
    private Ejecucion ejecucion;
    
    private AdministradorDeMediciones administradorDeMediciones;
    
    private List<InstrumentoDeMedicion> instrumentos;
    
    /**
     * @post deja asociado a la instancia como Observador de
     *       <code>ejecucion</code>.
     *       
     * @param ejecucion Ejecución que observa.
     */
    public PersistirMedicionesAlTerminarEjecucion(Ejecucion ejecucion) {
     
        this.ejecucion = ejecucion;
        this.instrumentos = new LinkedList<InstrumentoDeMedicion>();
        this.ejecucion.agregarObservador(this);
    }
    
    @Override
    public void instrumentoDeMedicionCreado(Ejecucion ejecucion,
                                            InstrumentoDeMedicion instrumento) {

        this.verificarEjecucion(ejecucion);
        
        this.instrumentos.add(instrumento);
    }

    @Override
    public void ejecucionTerminada(Ejecucion ejecucion) {

        this.verificarEjecucion(ejecucion);
        
            
        for (InstrumentoDeMedicion instrumento : this.instrumentos) {
            
            try {
                
                this.administradorDeMediciones.guardar(instrumento.getMediciones());

            } catch (PersistenciaException pe) {
                
                // TODO : mecanismo de notificación de errores.
                logger.error("No se pudo persistir las Mediciones al terminar la Ejecución.",
                             pe);
            }
        }
            
    }
    
    /**
     * @pre uno de los métodos de la interfaz Ejecucion.Observador ha sido 
     *      invocado indicando como parámetro <code>ejecucion</code>.
     * @post verifica que la Ejecución indicada corresponde con la
     *       ejecución a la que pertenece esta instancia.
     */
    private void verificarEjecucion(Ejecucion ejecucion) {
        
        if (! this.ejecucion.equals(ejecucion)) {
            
            throw new IllegalArgumentException("Este Ejecucion.Observador está asociado a otra Ejecucion." +
                                               "Se esperaba " + this.ejecucion + 
                                               " y se invocó con " + ejecucion);
        }
    }

    public void setAdministradorDeMediciones(AdministradorDeMediciones administrador) {
        
        this.administradorDeMediciones = administrador;
    }
}

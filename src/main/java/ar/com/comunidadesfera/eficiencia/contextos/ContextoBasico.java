package ar.com.comunidadesfera.eficiencia.contextos;

import javax.inject.Inject;

import ar.com.comunidadesfera.eficiencia.Contexto;
import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.ejecuciones.EjecucionBasica;
import ar.com.comunidadesfera.eficiencia.persistencia.AdministradorDeMediciones;

/**
 * Implementación básica de un Contexto de Eficiencia.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class ContextoBasico implements Contexto {

    private boolean persistente = false;
    
    private AdministradorDeMediciones administradorDeMediciones;
    
    @Override
    public void setPersistente(boolean esPersistente) {

        this.persistente = esPersistente;
    }

    @Override
    public boolean isPersistente() {

        return this.persistente;
    }

    @Override
    public Ejecucion iniciarEjecucion(String nombreAlgoritmo, long[] tamaño) {
        
        Ejecucion ejecucion = new EjecucionBasica(nombreAlgoritmo, tamaño);
        
        if (this.isPersistente()) {
            
            new PersistirMedicionesAlTerminarEjecucion(ejecucion)
                    .setAdministradorDeMediciones(this.administradorDeMediciones);
        }
        
        return ejecucion;
    }

    @Inject
    public void setAdministradorDeMediciones(AdministradorDeMediciones administrador) {
        
        this.administradorDeMediciones = administrador;
    }
}

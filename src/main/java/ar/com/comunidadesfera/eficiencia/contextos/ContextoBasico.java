package ar.com.comunidadesfera.eficiencia.contextos;

import javax.persistence.EntityManagerFactory;

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

    private EntityManagerFactory emFactory;
    
    private boolean persistente = false;
    
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
            
            new PersistirMedicionesAlTerminarEjecucion(ejecucion);
        }
        
        return ejecucion;
    }

    public void setEntityManagerFactory(EntityManagerFactory emFactory) {

        this.emFactory = emFactory;
        
        AdministradorDeMediciones.instancia().setEntityManagerFactory(this.emFactory);
    }

}

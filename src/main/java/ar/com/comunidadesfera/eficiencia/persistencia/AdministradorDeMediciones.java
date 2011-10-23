package ar.com.comunidadesfera.eficiencia.persistencia;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import ar.com.comunidadesfera.eficiencia.registros.Discriminante;
import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.persistencia.EstrategiaTransaccional;
import ar.com.comunidadesfera.persistencia.Transaccional;

public class AdministradorDeMediciones {
    
    private EntityManager em;

    public AdministradorDeMediciones() {

    }

    @Inject
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    /**
     * @post persiste las Mediciones, los Problemas y los Módulos asociados.
     * 
     * @param mediciones
     * @throws PersistenciaException
     */
    @Transaccional(EstrategiaTransaccional.REQUERIDA)
    public void guardar(List<Medicion> mediciones) throws PersistenciaException {

        for (Medicion medicion : mediciones) {

            this.guardarMedicion(medicion);
        }
    }

    /**
     * @post persiste la Medición.
     * 
     * @param em
     * @param medicion
     */
    private void guardarMedicion(Medicion medicion) {

        this.guardarModulo(medicion);
        this.guardarProblema(medicion);
        this.guardarDiscriminante(medicion);
        
        em.persist(medicion);
    }

    /**
     * @post persiste el Problema asociado a la Medición.
     * 
     * @param em
     * @param medicion
     */
    private void guardarProblema(Medicion medicion) {

        if (!em.contains(medicion.getProblema())) {

            em.persist(medicion.getProblema());
        }
    }

    /**
     * @post para el Módulo asociado a la Medición, si no ha sido persistido
     *       lo persiste. 
     *  
     * @param em
     * @param medicion
     */
    private void guardarModulo(Medicion medicion) {

        Modulo modulo = medicion.getModulo();

        if (modulo.getId() == null) {

            try {
                
                Long id = (Long) em.createNamedQuery("buscarIdModulo")
                                    .setParameter("identificacion", modulo.getIdentificacion())
                                    .setParameter("version", modulo.getVersion())
                                    .getSingleResult();
                
                modulo.setId(id);
                
            } catch (NoResultException nre) {
                
                /* si no encontró el módulo lo persiste */
                em.persist(medicion.getModulo());
            }
        }
    }
    
    /**
     * @post para el Discriminante de la Medición, si no ha sido persistido
     *       lo persiste. 
     * 
     * @param em
     * @param medicion
     */
    private void guardarDiscriminante(Medicion medicion) {
        
        Discriminante discriminante = medicion.getDiscriminante();

        if (discriminante.getId() == null) {

            try {
                
                Long id = (Long) em.createNamedQuery("buscarIdDiscriminante")
                                    .setParameter("nombre", discriminante.getNombre())
                                    .setParameter("entorno", discriminante.getEntorno())
                                    .getSingleResult();
                
                discriminante.setId(id);
                
            } catch (NoResultException nre) {
                
                /* si no encontró el discriminante lo persiste */
                em.persist(medicion.getDiscriminante());
            }
        }
    }

}

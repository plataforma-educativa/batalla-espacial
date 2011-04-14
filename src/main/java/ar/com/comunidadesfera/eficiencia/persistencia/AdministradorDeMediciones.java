package ar.com.comunidadesfera.eficiencia.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import ar.com.comunidadesfera.eficiencia.registros.Discriminante;
import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;

public class AdministradorDeMediciones {

    private static final AdministradorDeMediciones instancia = new AdministradorDeMediciones();

    private volatile EntityManagerFactory emf;

    private AdministradorDeMediciones() {

    }

    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    /**
     * @post persiste las Mediciones, los Problemas y los Módulos asociados.
     * 
     * @param mediciones
     * @throws PersistenciaException
     */
    public void guardar(List<Medicion> mediciones) throws PersistenciaException {

        EntityManager em = this.emf.createEntityManager();

        try {

            em.getTransaction().begin();

            for (Medicion medicion : mediciones) {

                this.guardarMedicion(em, medicion);
            }

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {

                em.getTransaction().rollback();
            }
            
            throw new PersistenciaException("Guardando Mediciones", e);

        } finally {

            em.close();
        }

    }

    /**
     * @post persiste la Medición.
     * 
     * @param em
     * @param medicion
     */
    private void guardarMedicion(EntityManager em, Medicion medicion) {

        this.guardarModulo(em, medicion);
        this.guardarProblema(em, medicion);
        this.guardarDiscriminante(em, medicion);
        
        em.persist(medicion);
    }

    /**
     * @post persiste el Problema asociado a la Medición.
     * 
     * @param em
     * @param medicion
     */
    private void guardarProblema(EntityManager em, Medicion medicion) {

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
    private void guardarModulo(EntityManager em, Medicion medicion) {

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
    private void guardarDiscriminante(EntityManager em, Medicion medicion) {
        
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
    
    public static AdministradorDeMediciones instancia() {
        
        return instancia;
    }

}

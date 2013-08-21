package ar.com.comunidadesfera.eficiencia.persistencia;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import ar.com.comunidadesfera.eficiencia.registros.Discriminante;
import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Problema;
import ar.com.comunidadesfera.eficiencia.registros.Unidad;
import ar.com.comunidadesfera.eficiencia.reportes.ItemReporte;
import ar.com.comunidadesfera.eficiencia.reportes.Reporte;
import ar.com.comunidadesfera.eficiencia.reportes.ReporteAgrupadoPorClasificacion;
import ar.com.comunidadesfera.eficiencia.reportes.ReporteSimple;
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

        this.guardarRegistroDeEjecucion(medicion);
        this.guardarDiscriminante(medicion);
        
        em.persist(medicion);
    }

    /**
     * @post persiste el RegistroDeEjecución asociado a la Medición.
     * 
     * @param medidion
     */
    private void guardarRegistroDeEjecucion(Medicion medicion) {
        
        if (!em.contains(medicion.getEjecucion())) {

            this.guardarModulo(medicion);
            this.guardarProblema(medicion);

            em.persist(medicion.getEjecucion());
        }
    }
    
    /**
     * @post persiste el Problema asociado a la Medición.
     * 
     * @param em
     * @param medicion
     */
    private void guardarProblema(Medicion medicion) {

        Problema problema = medicion.getProblema();

        if (problema.getId() == null) {

            try {

                Long id = (Long) em.createNamedQuery("buscarIdProblema")
                                   .setParameter("nombre", problema.getNombre())
                                   .getSingleResult();
                
                problema.setId(id);
                
            } catch (NoResultException nre) {
                
                /* si no encontró el problema lo persiste */
                em.persist(medicion.getProblema());
            }
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
    
    @SuppressWarnings("unchecked")
    @Transaccional(EstrategiaTransaccional.REQUERIDA)
    public List<Modulo> listarModulos() {

        return this.em.createNamedQuery("listarModulos")
                      .getResultList();
    }

    @SuppressWarnings("unchecked")
    @Transaccional(EstrategiaTransaccional.REQUERIDA)
    public List<Modulo> buscarModulos(String filtro) {

        return this.em.createNamedQuery("buscarModulos")
                      .setParameter("nombre", "%" + filtro + "%")
                      .getResultList();
    }
    
    @SuppressWarnings("unchecked")
    @Transaccional(EstrategiaTransaccional.REQUERIDA)
    public Reporte<Medicion> buscarMediciones(Modulo modulo) {

        return new ReporteSimple<>(this.em.createNamedQuery("buscarMedicionesDeUnModulo")
                                          .setParameter("modulo", modulo)
                                          .getResultList());
    }

    @SuppressWarnings("unchecked")
    @Transaccional(EstrategiaTransaccional.REQUERIDA)
    public Reporte<Discriminante> sumarizarMedicionesPorDiscriminante(Discriminante discriminante) {
        
        List<ItemReporte<Discriminante>> subitems;
        
        subitems = this.em.createNamedQuery("sumarizarMedicionesPorDiscriminanteEnUnEntorno")
                          .setParameter("entorno", discriminante)
                          .setParameter("unidadMedida", Unidad.INSTRUCCIONES)
                          .getResultList();
        
        return new ReporteSimple<>(subitems);
    }

    @SuppressWarnings("unchecked")
    @Transaccional(EstrategiaTransaccional.REQUERIDA)
    public Reporte<Discriminante> sumarizarMedicionesPorDiscriminanteDimension(Discriminante discriminante) {
        
        List<ItemReporte<Discriminante>> subitems;
        
        subitems = this.em.createNamedQuery("promediarMedicionesPorDiscriminanteDimensionEnUnEntorno")
                          .setParameter("entorno", discriminante)
                          .setParameter("unidadMedida", Unidad.INSTRUCCIONES)
                          .getResultList();
        
        return new ReporteAgrupadoPorClasificacion<>(subitems);
    }

}

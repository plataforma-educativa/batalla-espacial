package ar.com.comunidadesfera.persistencia;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.comunidadesfera.persistencia.Configuracion.Tipo;

/**
 * Implementación de Contexto de Persistencia con soporte para múltiples hilos
 * de ejecución.
 * 
 * @see http://www.laliluna.de/articles/2011/01/12/jboss-weld-jpa-hibernate.html
 * @see http://seamframework.org/Documentation/WeldAndJPARunningInTomcat
 * @author Mariano Tugnarelli
 *
 */
@ApplicationScoped
public class ContextoDePersistenciaMultiThread implements ContextoDePersistencia {

    private static final Logger logger = LoggerFactory.getLogger(ContextoDePersistenciaMultiThread.class);
    
    private EntityManagerFactory emf;

    private ThreadLocal<Deque<EntityManager>> emStackThreadLocal = new ThreadLocal<Deque<EntityManager>>();
    
    @Override
    public EntityManager buscarEntityManager() {

        logger.debug("Buscando el Entity Manager del Contexto");
        
        final Deque<EntityManager> entityManagerStack = this.emStackThreadLocal.get();
        
        return (entityManagerStack != null) && (! entityManagerStack.isEmpty()) ?
                entityManagerStack.peek() : null;            
    }
    
    @Override
    public EntityManager getEntityManager() {
        
        EntityManager entityManager = this.buscarEntityManager();

        if (entityManager == null) {
            
            logger.error("No se encontró un Entity Manager en el Contexto.");
            
            throw new NoExisteEntityManagerException("No se encontró un Entity Manager en el Contexto de Persistencia." +
                                                     "Asegúrese que el método esté anotado como @Transaccional y que " +
                                                     "estén activados los interceptores.");
        }
        
        return entityManager;
    }

    @Override
    public EntityManager agregarEntityManager() {
        
        logger.debug("Agregando y registrando un EntityManager al Contexto");
        
        Deque<EntityManager> entityManagerStack = this.emStackThreadLocal.get();
        
        if (entityManagerStack == null) {
            
            entityManagerStack = new LinkedList<EntityManager>();
            this.emStackThreadLocal.set(entityManagerStack);
        }

        final EntityManager entityManager = this.getEntityManagerFactory()
                                            .createEntityManager();
        
        entityManagerStack.push(entityManager);

        return entityManager;
    }

    @Override
    public void removerEntityManager(EntityManager entityManager) {

        logger.debug("Removiendo y liberando un Entity Manager del Contexto");
        
        final Deque<EntityManager> entityManagerStack = this.emStackThreadLocal.get();
        
        if ((entityManagerStack == null) || (entityManagerStack.isEmpty())) {
            
            throw new NoExisteEntityManagerException("No existe Entity Manager en el Contexto");
        }
        if (entityManagerStack.peek() != entityManager) {
         
            throw new NoExisteEntityManagerException("El Entity Manager no pertenece al Contexto");
        }
        
        entityManagerStack.pop();   
    }
    
    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        
        return this.emf;
    }
    
    @Inject
    public void inicializar(@Configuracion(Tipo.PERSISTENCE_UNIT_NAME) String persistenceUnitName,
                            @Configuracion(Tipo.PERSISTENCE_UNIT_PROPERTIES) Map<Object, Object> persistenceUnitProperties) {
        
        logger.info("Inicializando Contexto de Persistencia con la Persistence Unit: " + persistenceUnitName);

        this.emf = Persistence.createEntityManagerFactory(persistenceUnitName, persistenceUnitProperties);
    }

}

package ar.com.comunidadesfera.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Interfaz del servicio que manipula el Contexto de Persistencia utilizado para
 * gestionar el acceso y propagación del EntityManager, así como también
 * manipulando las transacciones a través de una mecanismo de demarcación
 * similar al ofrecido por un EJB Container pero en un ambiente SE.
 * 
 * @see http://www.laliluna.de/articles/2011/01/12/jboss-weld-jpa-hibernate.html
 * @see http://seamframework.org/Documentation/WeldAndJPARunningInTomcat
 * @author Mariano Tugnarelli
 *
 */
public interface ContextoDePersistencia {

    /**
     * @pre  ha sido registrado un Entity Manager en el Contexto.
     * @post busca el Entity Manager en el Contexto y lo devuelve.
     * @throws NoExisteEntityManagerException si no existe un Entity Manager 
     *         registrado en el Contexto.
     * @return Entity Manager asociado al Contexto.
     */
    EntityManager getEntityManager();

    /**
     * @post crea y registra un nuevo EntityManager en el Contexto.
     * @return Entity Manager asociado al Contexto.
     */
    EntityManager agregarEntityManager();

    /**
     * @pre  <code>entityManager</code> ha sido registrado en el Contexto. 
     * @post remueve el Entity Manager del Conteto.
     * @param entityManager  Entity Manager a remover
     * @throws NoExisteEntityManagerException si el Entity Manager no fue registrado.
     */
    void removerEntityManager(EntityManager entityManager);
    
    /**
     * @return devuelve la fábrica de Entity Manager utilizada.
     */
    EntityManagerFactory getEntityManagerFactory();
    
}

package ar.com.comunidadesfera.persistencia;

import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;

/**
 * Implthis.contexto.getEntityManager()entación de Entity Manager expuesto para ser inyectado en todos
 * los componentes que lo requieran.
 * Hace las veces de proxy del Contexto de Persistencia.
 * 
 * @see http://www.laliluna.de/articles/2011/01/12/jboss-weld-jpa-hibernate.html
 * @see http://seamframework.org/Documentation/WeldAndJPARunningInTomcat
 * @author Mariano Tugnarelli
 *
 */
@ApplicationScoped
public class EntityManagerDelegado implements EntityManager {

    private ContextoDePersistencia contexto;
    
    @Inject
    public void setContexto(ContextoDePersistencia contexto) {
        this.contexto = contexto;
    }
    
    public void persist(Object entity) {
        this.contexto.getEntityManager().persist(entity);
    }

    public <T> T merge(T entity) {
        return this.contexto.getEntityManager().merge(entity);
    }

    public void remove(Object entity) {
        this.contexto.getEntityManager().remove(entity);
    }

    public <T> T find(Class<T> entityClass, Object primaryKey) {
        return this.contexto.getEntityManager().find(entityClass, primaryKey);
    }

    public <T> T find(Class<T> entityClass, Object primaryKey,
            Map<String, Object> properties) {
        return this.contexto.getEntityManager().find(entityClass, primaryKey, properties);
    }

    public <T> T find(Class<T> entityClass, Object primaryKey,
            LockModeType lockMode) {
        return this.contexto.getEntityManager().find(entityClass, primaryKey, lockMode);
    }

    public <T> T find(Class<T> entityClass, Object primaryKey,
            LockModeType lockMode, Map<String, Object> properties) {
        return this.contexto.getEntityManager().find(entityClass, primaryKey, lockMode, properties);
    }

    public <T> T getReference(Class<T> entityClass, Object primaryKey) {
        return this.contexto.getEntityManager().getReference(entityClass, primaryKey);
    }

    public void flush() {
        this.contexto.getEntityManager().flush();
    }

    public void setFlushMode(FlushModeType flushMode) {
        this.contexto.getEntityManager().setFlushMode(flushMode);
    }

    public FlushModeType getFlushMode() {
        return this.contexto.getEntityManager().getFlushMode();
    }

    public void lock(Object entity, LockModeType lockMode) {
        this.contexto.getEntityManager().lock(entity, lockMode);
    }

    public void lock(Object entity, LockModeType lockMode,
            Map<String, Object> properties) {
        this.contexto.getEntityManager().lock(entity, lockMode, properties);
    }

    public void refresh(Object entity) {
        this.contexto.getEntityManager().refresh(entity);
    }

    public void refresh(Object entity, Map<String, Object> properties) {
        this.contexto.getEntityManager().refresh(entity, properties);
    }

    public void refresh(Object entity, LockModeType lockMode) {
        this.contexto.getEntityManager().refresh(entity, lockMode);
    }

    public void refresh(Object entity, LockModeType lockMode,
            Map<String, Object> properties) {
        this.contexto.getEntityManager().refresh(entity, lockMode, properties);
    }

    public void clear() {
        this.contexto.getEntityManager().clear();
    }

    public void detach(Object entity) {
        this.contexto.getEntityManager().detach(entity);
    }

    public boolean contains(Object entity) {
        return this.contexto.getEntityManager().contains(entity);
    }

    public LockModeType getLockMode(Object entity) {
        return this.contexto.getEntityManager().getLockMode(entity);
    }

    public void setProperty(String propertyName, Object value) {
        this.contexto.getEntityManager().setProperty(propertyName, value);
    }

    public Map<String, Object> getProperties() {
        return this.contexto.getEntityManager().getProperties();
    }

    public Query createQuery(String qlString) {
        return this.contexto.getEntityManager().createQuery(qlString);
    }

    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
        return this.contexto.getEntityManager().createQuery(criteriaQuery);
    }

    public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
        return this.contexto.getEntityManager().createQuery(qlString, resultClass);
    }

    public Query createNamedQuery(String name) {
        return this.contexto.getEntityManager().createNamedQuery(name);
    }

    public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
        return this.contexto.getEntityManager().createNamedQuery(name, resultClass);
    }

    public Query createNativeQuery(String sqlString) {
        return this.contexto.getEntityManager().createNativeQuery(sqlString);
    }

    @SuppressWarnings("rawtypes")
    public Query createNativeQuery(String sqlString, Class resultClass) {
        return this.contexto.getEntityManager().createNativeQuery(sqlString, resultClass);
    }

    public Query createNativeQuery(String sqlString, String resultSetMapping) {
        return this.contexto.getEntityManager().createNativeQuery(sqlString, resultSetMapping);
    }

    public void joinTransaction() {
        this.contexto.getEntityManager().joinTransaction();
    }

    public <T> T unwrap(Class<T> cls) {
        return this.contexto.getEntityManager().unwrap(cls);
    }

    public Object getDelegate() {
        return this.contexto.getEntityManager().getDelegate();
    }

    public void close() {
        this.contexto.getEntityManager().close();
    }

    public boolean isOpen() {
        return this.contexto.getEntityManager().isOpen();
    }

    public EntityTransaction getTransaction() {
        return this.contexto.getEntityManager().getTransaction();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return this.contexto.getEntityManager().getEntityManagerFactory();
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return this.contexto.getEntityManager().getCriteriaBuilder();
    }

    public Metamodel getMetamodel() {
        return this.contexto.getEntityManager().getMetamodel();
    }

}

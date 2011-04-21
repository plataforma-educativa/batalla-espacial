package ar.com.comunidadesfera.persistencia.test.transacciones;

import java.util.concurrent.CountDownLatch;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ar.com.comunidadesfera.persistencia.ContextoDePersistenciaMultiThread;
import ar.com.comunidadesfera.persistencia.NoExisteEntityManagerException;

@RunWith(JMock.class)
public class ContextoDePersistenciaMultiThreadTest {

    private static final int MAX_ENTITY_MANAGER = 5;
    
    private Mockery simulador = new JUnit4Mockery();
    
    private EntityManagerFactory entityManagerFactory;
    
    private EntityManager[] entityManagers;
    
    private ContextoDePersistenciaMultiThread contextoDePersistencia;
    
    @Before
    public void inicializar() {
        
        this.entityManagerFactory = this.simulador.mock(EntityManagerFactory.class);
        
        this.entityManagers = new EntityManager[MAX_ENTITY_MANAGER];
        for (int i = 0; i < entityManagers.length; i++) {
            
            this.entityManagers[i] = this.simulador.mock(EntityManager.class, 
                                                         "EntityManager[" + i + "]");
        }
        
        this.simulador.checking(this.expectativasComunes());
        this.contextoDePersistencia = new ContextoDePersistenciaMultiThread();
        this.contextoDePersistencia.setEntityManagerFactory(this.entityManagerFactory);
    }
    
    /**
     * @return Expectativas comunes a todos los tests.
     */
    private Expectations expectativasComunes() {

        return new Expectations() {{

            for (int i = 0; i < entityManagers.length; i++) {
                
                atMost(1).of(entityManagerFactory).createEntityManager();
                will(returnValue(entityManagers[i]));
            }
        }};
    }

    @Test
    public void gestionarMultiplesSesiones() {
        
        EntityManager em = this.contextoDePersistencia.buscarEntityManager();
        Assert.assertThat(em, Matchers.nullValue());
        
        EntityManager em1 = this.agregarLuegoObtener(this.entityManagers[0]);
        
        EntityManager em2 = this.agregarLuegoObtener(this.entityManagers[1],
                                                     em1);
        EntityManager em3 = this.agregarLuegoObtener(this.entityManagers[2], 
                                                     em1, em2);
        EntityManager em4 = this.agregarLuegoObtener(this.entityManagers[3], 
                                                     em1, em2, em3);

        this.removerLuegoObtener(em4, em3);
        this.removerLuegoObtener(em3, em2);
        this.removerLuegoObtener(em2, em1);
        this.removerLuegoObtener(em1, null);
                
    }
    
    /**
     * @post remueve el EntityManager removido y luego obtiene del contexto
     *       otro, asegurándose que se el esperado.
     * 
     * @param removido
     * @param esperado
     */
    private void removerLuegoObtener(EntityManager removido, EntityManager esperado) {
        
        this.contextoDePersistencia.removerEntityManager(removido);
        
        if (esperado != null) {
            
            EntityManager obtenido = this.contextoDePersistencia.getEntityManager();
            this.assertEntityManager(obtenido, esperado, removido);
        
        } else {
            
            Assert.assertThat(this.contextoDePersistencia.buscarEntityManager(),
                              Matchers.nullValue());
        }
        
    }

    /**
     * @post asegura que: 
     *          * obtenido no es nulo 
     *          * obtenido es la misma instancia que esperado
     *          * obtenido no es igual a otros
     * 
     * @param obtenido
     * @param esperado
     * @param otros
     */
    private void assertEntityManager(EntityManager obtenido, EntityManager esperado, 
                                     EntityManager... otros) {
        
        Assert.assertThat(obtenido, Matchers.notNullValue());
        Assert.assertThat(obtenido, Matchers.sameInstance(esperado));
        
        for (int i = 0; i < otros.length; i++) {
            
            Assert.assertThat(obtenido, Matchers.not(Matchers.sameInstance(otros[i])));
        }
    }
    
    /**
     * @post agrega un EntityManager, comprueba que sea el esperado y no los otros,
     *       luego lo recupera y se asegura que sea el mismo.
     * 
     * @param esperado
     * @param otros
     * @return
     */
    private EntityManager agregarLuegoObtener(EntityManager esperado, EntityManager... otros) {
        
        EntityManager emAgregado;
        EntityManager emObtenido;
        
        emAgregado = this.contextoDePersistencia.agregarEntityManager();
        this.assertEntityManager(emAgregado, esperado, otros);
        
        emObtenido = this.contextoDePersistencia.buscarEntityManager();
        this.assertEntityManager(emObtenido, emAgregado, otros);
        
        emObtenido = this.contextoDePersistencia.getEntityManager();
        this.assertEntityManager(emObtenido, emAgregado, otros);
        
        return emAgregado;
    }
    
    @Test(expected = NoExisteEntityManagerException.class)
    public void getEntityManagerNoExistente() {
        
        this.contextoDePersistencia.getEntityManager();
    }
    
    @Test(expected = NoExisteEntityManagerException.class)
    public void getEntityManagerRetirado() {
        
        EntityManager em = this.agregarLuegoObtener(this.entityManagers[0]);
        this.removerLuegoObtener(em, null);
    
        this.contextoDePersistencia.getEntityManager();
    }
    
    @Test(expected = NoExisteEntityManagerException.class)
    public void retirarEntityManagerNoAgregado() {
        
        EntityManager em1 = this.agregarLuegoObtener(this.entityManagers[0]);
        this.agregarLuegoObtener(this.entityManagers[1], em1);
        
        this.contextoDePersistencia.removerEntityManager(this.entityManagers[3]);
    }
    
    @Test(expected = NoExisteEntityManagerException.class)
    public void retirarEntityManagerAgregadoPreviamente() {
        
        EntityManager em1 = this.agregarLuegoObtener(this.entityManagers[0]);
        this.agregarLuegoObtener(this.entityManagers[1], em1);
        
        this.contextoDePersistencia.removerEntityManager(em1);
    }
    
    @Test(expected = NoExisteEntityManagerException.class)
    public void retirarEntityManagerSinAgregar() {
        
        this.contextoDePersistencia.removerEntityManager(this.entityManagers[2]);
    }
    
    @Test
    public void agregarEnMultiplesThreads() throws Exception {
    
        CountDownLatch agregado = new CountDownLatch(1);
        CountDownLatch retirar = new CountDownLatch(1);
        
        this.agregarLuegoObtener(this.entityManagers[0]);

        /* utiliza el contexto de persistencia en otro thread */
        new Thread(this.tareaConcurrente(agregado, retirar)).start();

        /* espera hasta que el thread adicional agrega sus entity managers en 
         * el contexto */
        agregado.await();
        
        EntityManager em = this.contextoDePersistencia.getEntityManager();
        Assert.assertThat(em, Matchers.sameInstance(entityManagers[0]));
        
        /* indica al thread adicional que puede terminar */
        retirar.countDown();
        
        removerLuegoObtener(em, null);
    }
    
    /**
     * @post construye un Runnable que agrega y luego remueve EntityManagers
     *       del Contexto de Persistencia, esperando las señales.
     *       
     * @param agregado
     * @param retirar
     * @return
     */
    private Runnable tareaConcurrente(final CountDownLatch agregado,
                                      final CountDownLatch retirar) {
        
        return new Runnable() {

            @Override
            public void run() {
 
                try {
                    
                    agregarLuegoObtener(entityManagers[1]);
                    agregarLuegoObtener(entityManagers[2]);
                    agregado.countDown();
                
                    /* espera hasta que el thread original le indica terminar */
                    retirar.await();
        
                    removerLuegoObtener(entityManagers[2], entityManagers[1]);
                    removerLuegoObtener(entityManagers[1], null);
                    
                } catch (InterruptedException e) {

                    throw new RuntimeException("Interrupción inesperada", e);
                }
            }
            
        };
    }
}

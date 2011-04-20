package ar.com.comunidadesfera.persistencia.test.transacciones;

import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ar.com.comunidadesfera.persistencia.ContextoDePersistencia;
import ar.com.comunidadesfera.persistencia.InterceptorTransaccional;

@RunWith(JMock.class)
public abstract class InterceptorTransaccionalTest<T extends InterceptorTransaccional> {
    
    private Mockery simulador = new JUnit4Mockery();
    
    private T interceptor;

    protected InvocationContext contextoDeInvocacion;

    protected ContextoDePersistencia contextoDePersistencia;
    
    /**
     * Sesión (EntityManager) existente en el contexto, previo a la invocación
     * del método interceptado.
     */
    protected EntityManager sesionPrevia;
    
    /**
     * Sesión (EntityManager) abierto/propagado en la invocación
     * del método interceptado.
     */
    protected EntityManager sesion;
    
    /**
     * Transacción (EntityTransaaction) activada en el contexto, previo a la
     * invocación del método interceptado.
     */
    protected EntityTransaction transaccionPrevia;
    
    /**
     * Transacción (EntityTransaction) activada en la invocación del método
     * interceptado. 
     */
    protected EntityTransaction transaccion;
    
    protected Sequence secuencia;
    
    @Before
    public void inicializarInterceptor() throws Exception {
        
        this.interceptor = this.crearInterceptor();
        
        this.contextoDeInvocacion = this.simulador.mock(InvocationContext.class);
        this.contextoDePersistencia = this.simulador.mock(ContextoDePersistencia.class);
        this.sesion = this.simulador.mock(EntityManager.class, "sesion");
        this.transaccion = this.simulador.mock(EntityTransaction.class, "transaccion");
        this.sesionPrevia = this.simulador.mock(EntityManager.class, "sesionPrevia");
        this.transaccionPrevia = this.simulador.mock(EntityTransaction.class, "transaccionPrevia");
        
        this.secuencia = this.simulador.sequence("invocacion");
        
        this.interceptor.setContextoDePersistencia(this.contextoDePersistencia);

        this.simulador.checking(this.expectativasGenerales());
    }
    
    /**
     * @post crea las expectativas generales; permite la ejecución de múltiples
     *       métodos y define su retorno. 
     *  
     * @return Expectivas generales
     */
    protected Expectations expectativasGenerales() {
        
        return new Expectations() {{

            allowing(sesion).getTransaction();
            will(returnValue(transaccion));
            
            allowing(sesionPrevia).getTransaction();
            will(returnValue(transaccionPrevia));
            
            allowing(transaccion).isActive();
            will(returnValue(true));
            
            allowing(transaccionPrevia).isActive();
            will(returnValue(true));
            
        }};
    }
    
    protected abstract T crearInterceptor();  
    
    /**
     * @post delimita la transacción para el contexto de invocación dado,
     *       verificando las expectativas proporcionadas.
     *       
     * @param expectativas
     * @throws Exception
     */
    private void delimitarTransaccion(Expectations... expectativas) 
        throws Exception {
        
        for (Expectations e : expectativas) {
            
            this.simulador.checking(e);
        }
        
        this.interceptor.deliminarTransaccion(this.contextoDeInvocacion);
    }
    
    /**
     * @post delimita la transacción en una ejecución en cuyo contexto
     *       NO existe un Entity Manager (Sesión de persistencia) creado.
     */
    @Test
    public final void delimitarTransaccionEnEjecucionSinSesion() 
        throws Exception{
        
        this.delimitarTransaccion(this.expectativasSinSesion());
    }
    
    /**
     * @post delimita la transacción en una ejecución en cuyo contexto
     *       NO existe un Entity Manager (Sesión de persistencia) creado
     *       y la invocación arroja una excepción.
     */
    @Test
    public final void delimitarTransaccionEnEjecucionSinSesionArrojandoExcepcion() 
        throws Exception {

        try {
            
            this.delimitarTransaccion(this.expectativasSinSesionArrojandoException());
            
            /* se espera una excepción */
            Assert.fail();
            
            // No se configura en la anotación @Test(exception = Exception.class) 
            // porque ante un fail se distociona el error
            
        } catch (Exception e) {
            
            /* excepcion esperada */
        }
    }
    
    /**
     * @post delimita la transacción en una ejecución en cuyo contexto
     *       existe un Entity Manager (Sesión de persistencia) creado
     *       que NO tiene una transacción activa. 
     *       
     * @throws Exception
     */
    @Test
    public final void delimitarTransaccionEnEjecucionConSesionSinTransaccionActiva() 
        throws Exception {
        
        this.delimitarTransaccion(this.expectativasConSesionSinTransaccionActiva());
    }
    
    /**
     * @post delimita la transacción en una ejecución en cuyo contexto
     *       existe un Entity Manager (Sesión de persistencia) creado
     *       que NO tiene una transacción activa y la invocación
     *       arroja una excepción. 
     *       
     * @throws Exception
     */
    @Test
    public final void delimitarTransaccionEnEjecucionConSesionSinTransaccionActivaArrojandoException() 
        throws Exception {

        try {
            
            this.delimitarTransaccion(this.expectativasConSesionSinTransaccionActivaArrojandoException());
            
            /* se espera una excepción */
            Assert.fail();
            
            // No se configura en la anotación @Test(exception = Exception.class) 
            // porque ante un fail se distociona el error
            
        } catch (Exception e) {
            
            /* excepcion esperada */
        }

    }
    
    /**
     * @post delimita la transacción en una ejecución en cuyo contexto
     *       existe un Entity Manager (Sesión de persistencia) creado
     *       que tiene una transacción activa. 
     *       
     * @throws Exception
     */
    @Test
    public final void delimitarTransaccionEnEjecucionConTransaccionActiva() 
        throws Exception {
        
        this.delimitarTransaccion(this.expectativasConTransaccionActiva());
    }
    
    /**
     * @post delimita la transacción en una ejecución en cuyo contexto
     *       existe un Entity Manager (Sesión de persistencia) creado
     *       que tiene una transacción activa y la invocación
     *       arroja una excepción. 
     *       
     * @throws Exception
     */
    @Test
    public final void delimitarTransaccionEnEjecucionConTransaccionActivaArrojandoException() 
        throws Exception {
        
        try {
            
            this.delimitarTransaccion(this.expectativasConTransaccionActivaArrojandoException());
            
            /* se espera una excepción */
            Assert.fail();
            
            // No se configura en la anotación @Test(exception = Exception.class) 
            // porque ante un fail se distociona el error
            
        } catch (Exception e) {
            
            /* excepcion esperada */
        }
    }
    
    protected abstract Expectations expectativasSinSesion()
        throws Exception;

    protected abstract Expectations expectativasSinSesionArrojandoException()
        throws Exception;
    
    protected abstract Expectations expectativasConSesionSinTransaccionActiva()
        throws Exception;

    protected abstract Expectations expectativasConSesionSinTransaccionActivaArrojandoException()
        throws Exception;

    protected abstract Expectations expectativasConTransaccionActiva()
        throws Exception;

    protected abstract Expectations expectativasConTransaccionActivaArrojandoException()
        throws Exception;

}



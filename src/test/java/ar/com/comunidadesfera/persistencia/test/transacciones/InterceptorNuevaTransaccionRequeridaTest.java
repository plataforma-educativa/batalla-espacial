package ar.com.comunidadesfera.persistencia.test.transacciones;

import javax.persistence.EntityManager;

import org.jmock.Expectations;

import ar.com.comunidadesfera.persistencia.InterceptorNuevaTransaccionRequerida;

public class InterceptorNuevaTransaccionRequeridaTest 
    extends InterceptorTransaccionalTest<InterceptorNuevaTransaccionRequerida> {

    @Override
    protected InterceptorNuevaTransaccionRequerida crearInterceptor() {

        return new InterceptorNuevaTransaccionRequerida();
    }

    @Override
    protected Expectations expectativasSinSesion() 
            throws Exception {
        
        return new Expectations(){{
            
            oneOf(contextoDePersistencia).agregarEntityManager();
            will(returnValue(sesion));
            inSequence(secuencia);
            
            oneOf(transaccion).begin();
            inSequence(secuencia);
            
            oneOf(contextoDeInvocacion).proceed();
            inSequence(secuencia);
            
            oneOf(transaccion).commit();
            inSequence(secuencia);
            
            oneOf(contextoDePersistencia).removerEntityManager(with(any(EntityManager.class)));
            inSequence(secuencia);
            
            oneOf(sesion).close();
            inSequence(secuencia);
        }};
        
    }

    @Override
    protected Expectations expectativasSinSesionArrojandoException()
            throws Exception {

        return new Expectations(){{
            
            oneOf(contextoDePersistencia).agregarEntityManager();
            will(returnValue(sesion));
            inSequence(secuencia);

            oneOf(transaccion).begin();
            inSequence(secuencia);

            oneOf(contextoDeInvocacion).proceed();
            will(throwException(with(any(Exception.class))));
            inSequence(secuencia);
            
            oneOf(transaccion).rollback();
            inSequence(secuencia);
            
            oneOf(contextoDePersistencia).removerEntityManager(with(any(EntityManager.class)));
            inSequence(secuencia);
            
            oneOf(sesion).close();
            inSequence(secuencia);
        }};
    }

    @Override
    protected Expectations expectativasConSesionSinTransaccionActiva()
            throws Exception {

        /* son las mismas expectativas */
        return this.expectativasSinSesion();
    }

    @Override
    protected Expectations expectativasConSesionSinTransaccionActivaArrojandoException()
            throws Exception {

        /* son las mismas expectativas */
        return this.expectativasSinSesionArrojandoException();
    }

    @Override
    protected Expectations expectativasConTransaccionActiva() 
            throws Exception {

        /* son las mismas expectativas */
        return this.expectativasSinSesion();
    }

    @Override
    protected Expectations expectativasConTransaccionActivaArrojandoException()
            throws Exception {

        /* son las mismas expectativas */
        return this.expectativasSinSesionArrojandoException();
    }

}

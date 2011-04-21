package ar.com.comunidadesfera.persistencia.test.transacciones;

import javax.persistence.EntityManager;

import org.jmock.Expectations;

import ar.com.comunidadesfera.persistencia.InterceptorTransaccionRequerida;

public class InterceptorTransaccionRequeridaTest 
    extends InterceptorTransaccionalTest<InterceptorTransaccionRequerida> {

    @Override
    protected InterceptorTransaccionRequerida crearInterceptor() {

        return new InterceptorTransaccionRequerida();
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
            then(estadoTransaccion.is(ACTIVA));
            
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
            then(estadoTransaccion.is(ACTIVA));

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

        return new Expectations(){{

            oneOf(transaccion).begin();
            inSequence(secuencia);
            then(estadoTransaccion.is(ACTIVA));
            
            oneOf(contextoDeInvocacion).proceed();
            inSequence(secuencia);
            
            oneOf(transaccion).commit();
            inSequence(secuencia);
        }};
    }

    @Override
    protected Expectations expectativasConSesionSinTransaccionActivaArrojandoException()
            throws Exception {

        return new Expectations(){{

            oneOf(transaccion).begin();
            inSequence(secuencia);
            then(estadoTransaccion.is(ACTIVA));
            
            oneOf(contextoDeInvocacion).proceed();
            will(throwException(with(any(Exception.class))));
            inSequence(secuencia);
            
            oneOf(transaccion).rollback();
            inSequence(secuencia);
        }};
    }

    @Override
    protected Expectations expectativasConTransaccionActiva() 
            throws Exception {

        return new Expectations(){{

            oneOf(contextoDeInvocacion).proceed();
            inSequence(secuencia);
        }};
    }

    @Override
    protected Expectations expectativasConTransaccionActivaArrojandoException()
            throws Exception {

        return new Expectations(){{

            oneOf(contextoDeInvocacion).proceed();
            will(throwException(with(any(Exception.class))));
            inSequence(secuencia);
            
            oneOf(transaccion).setRollbackOnly();
            inSequence(secuencia);
        }};
    }

}

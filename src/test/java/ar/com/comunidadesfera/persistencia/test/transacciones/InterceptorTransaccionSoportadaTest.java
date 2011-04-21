package ar.com.comunidadesfera.persistencia.test.transacciones;

import org.jmock.Expectations;

import ar.com.comunidadesfera.persistencia.InterceptorTransaccionSoportada;

public class InterceptorTransaccionSoportadaTest 
    extends InterceptorTransaccionalTest<InterceptorTransaccionSoportada> {

    @Override
    protected InterceptorTransaccionSoportada crearInterceptor() {

        return new InterceptorTransaccionSoportada();
    }

    @Override
    protected Expectations expectativasSinSesion() throws Exception {

        return new Expectations() {{

            oneOf(contextoDePersistencia).agregarEntityManager();
            will(returnValue(sesion));
            inSequence(secuencia);
            
            oneOf(contextoDeInvocacion).proceed();
            inSequence(secuencia);
            
            oneOf(contextoDePersistencia).removerEntityManager(sesion);
            inSequence(secuencia);
            
            oneOf(sesion).close();
            inSequence(secuencia);
        }};
    }

    @Override
    protected Expectations expectativasSinSesionArrojandoException()
            throws Exception {

        return new Expectations() {{

            oneOf(contextoDePersistencia).agregarEntityManager();
            will(returnValue(sesion));
            inSequence(secuencia);
            
            oneOf(contextoDeInvocacion).proceed();
            will(throwException(new Exception()));
            inSequence(secuencia);
            
            oneOf(contextoDePersistencia).removerEntityManager(sesion);
            inSequence(secuencia);
            
            oneOf(sesion).close();
            inSequence(secuencia);
        }};
    }

    @Override
    protected Expectations expectativasConSesionSinTransaccionActiva()
            throws Exception {

        return new Expectations() {{

            oneOf(contextoDeInvocacion).proceed();
            inSequence(secuencia);

        }};
    }

    @Override
    protected Expectations expectativasConSesionSinTransaccionActivaArrojandoException()
            throws Exception {

        return new Expectations() {{

            oneOf(contextoDeInvocacion).proceed();
            will(throwException(new Exception()));
            inSequence(secuencia);
            
        }};
    }

    @Override
    protected Expectations expectativasConTransaccionActiva() throws Exception {

        /* las expectativas son iguales, propaga la transacción */
        return this.expectativasConSesionSinTransaccionActiva();
    }

    @Override
    protected Expectations expectativasConTransaccionActivaArrojandoException()
            throws Exception {

        return new Expectations() {{

            oneOf(contextoDeInvocacion).proceed();
            will(throwException(new Exception()));
            inSequence(secuencia);
            
            oneOf(transaccion).setRollbackOnly();
            inSequence(secuencia);
        }};
    }

}

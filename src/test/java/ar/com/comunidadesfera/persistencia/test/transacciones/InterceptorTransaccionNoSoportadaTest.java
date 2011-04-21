package ar.com.comunidadesfera.persistencia.test.transacciones;

import org.jmock.Expectations;

import ar.com.comunidadesfera.persistencia.InterceptorTransaccionNoSoportada;

public class InterceptorTransaccionNoSoportadaTest 
    extends InterceptorTransaccionalTest<InterceptorTransaccionNoSoportada> {

    @Override
    protected InterceptorTransaccionNoSoportada crearInterceptor() {

        return new InterceptorTransaccionNoSoportada();
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

        /* son las mismas expectativas, porque necesita aislarse de la
         * transacción activa */
        return this.expectativasSinSesion();
    }

    @Override
    protected Expectations expectativasConTransaccionActivaArrojandoException()
            throws Exception {

        /* son las mismas expectativas, porque necesita aislarse de la
         * transacción activa */
        return this.expectativasSinSesionArrojandoException();
    }

}

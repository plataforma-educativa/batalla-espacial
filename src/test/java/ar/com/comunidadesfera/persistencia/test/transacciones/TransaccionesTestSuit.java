package ar.com.comunidadesfera.persistencia.test.transacciones;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith( Suite.class )
@SuiteClasses( {
    InterceptorTransaccionRequeridaTest.class,
    InterceptorNuevaTransaccionRequeridaTest.class,
    InterceptorTransaccionSoportadaTest.class,
    InterceptorTransaccionNoSoportadaTest.class
})
public class TransaccionesTestSuit {

}

package ar.com.comunidadesfera.eficiencia.test.persistencia;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith( Suite.class )
@SuiteClasses( {
    ContextoPersistenteTest.class,
    AdministradorDeMedicionesTest.class
})
public final class PersistenciaTestSuit {

}

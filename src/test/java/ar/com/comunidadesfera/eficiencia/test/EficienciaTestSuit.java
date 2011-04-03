package ar.com.comunidadesfera.eficiencia.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith( Suite.class )
@SuiteClasses( {
    EficienciaTest.class,
    EjecucionTest.class,
    MedicionTest.class,
    ContextoPersistenteTest.class,
})
public class EficienciaTestSuit {

}

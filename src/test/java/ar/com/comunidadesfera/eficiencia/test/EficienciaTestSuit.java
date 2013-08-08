package ar.com.comunidadesfera.eficiencia.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ar.com.comunidadesfera.eficiencia.test.persistencia.ContextoPersistenteTest;

@RunWith( Suite.class )
@SuiteClasses( {
    EficienciaTest.class,
    EjecucionTest.class,
    MedicionTest.class,
    ContextoPersistenteTest.class,
    EjecucionObservadaTest.class,
    ModuloTest.class
})
public abstract class EficienciaTestSuit {

}

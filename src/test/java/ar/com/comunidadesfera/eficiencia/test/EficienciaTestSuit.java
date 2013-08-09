package ar.com.comunidadesfera.eficiencia.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ar.com.comunidadesfera.eficiencia.test.persistencia.PersistenciaTestSuit;

@RunWith( Suite.class )
@SuiteClasses( {
    EficienciaTest.class,
    EjecucionTest.class,
    MedicionTest.class,
    EjecucionObservadaTest.class,
    ModuloTest.class,
    PersistenciaTestSuit.class,
})
public final class EficienciaTestSuit {

}

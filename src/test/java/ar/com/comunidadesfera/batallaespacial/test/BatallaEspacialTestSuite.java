package ar.com.comunidadesfera.batallaespacial.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ar.com.comunidadesfera.batallaespacial.test.juego.TableroTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.PiezaTestSuite;


@RunWith( Suite.class )
@SuiteClasses( {
    PiezaTestSuite.class,
    TableroTest.class 
})
public class BatallaEspacialTestSuite {

}

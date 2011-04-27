package ar.com.comunidadesfera.batallaespacial.test.pieza;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ar.com.comunidadesfera.batallaespacial.test.pieza.asteroide.AsteroideTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.base.BaseEspacialTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.base.PistaDeAterrizajeTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.contenedor.ContenedorTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.nave.ControlNaveDeCombateTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.nave.MonitorNaveDeCombateTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.nave.NaveDeCombateTest;

@RunWith( Suite.class )
@SuiteClasses({ 
    NaveDeCombateTest.class, 
    ContenedorTest.class,
    AsteroideTest.class,
    BaseEspacialTest.class,
    AgujeroNegroTest.class,
    TransporteDeSustanciasTest.class,
    PistaDeAterrizajeTest.class,
    ControlNaveDeCombateTest.class,
    MonitorNaveDeCombateTest.class
})
public class PiezaTestSuite {

}

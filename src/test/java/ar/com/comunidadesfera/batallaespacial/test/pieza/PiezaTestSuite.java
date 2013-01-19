package ar.com.comunidadesfera.batallaespacial.test.pieza;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ar.com.comunidadesfera.batallaespacial.test.pieza.asteroide.AsteroideDetectableTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.asteroide.AsteroideTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.base.BaseEspacialDetectableTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.base.BaseEspacialTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.base.PistaDeAterrizajeTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.contenedor.ContenedorDetectableTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.contenedor.ContenedorTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.nave.ControlNaveDeCombateTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.nave.MonitorNaveDeCombateTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.nave.NaveDeCombateDetectableTest;
import ar.com.comunidadesfera.batallaespacial.test.pieza.nave.NaveDeCombateTest;

@RunWith( Suite.class )
@SuiteClasses({ 

    /* Pieza */
    AgujeroNegroTest.class,
    AsteroideTest.class,
    ContenedorTest.class,
    BaseEspacialTest.class,
    NaveDeCombateTest.class, 

    /* Nave de Combate */
    ControlNaveDeCombateTest.class,
    MonitorNaveDeCombateTest.class,

    /* Interfaces */
    TransporteDeSustanciasTest.class,
    PistaDeAterrizajeTest.class,
    
    /* Detectables */
    AgujeroNegroDetectableTest.class,
    AsteroideDetectableTest.class,
    ContenedorDetectableTest.class,
    BaseEspacialDetectableTest.class,
    NaveDeCombateDetectableTest.class,
    
    /* Servicios de una Pieza */
    PiezaVisitableTest.class
    
})
public class PiezaTestSuite {

}

package ar.com.comunidadesfera.eficiencia.test.persistencia;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ar.com.comunidadesfera.eficiencia.test.persistencia.consultas.BuscarIdProblemaTest;
import ar.com.comunidadesfera.eficiencia.test.persistencia.consultas.BuscarMedicionesDeUnModuloTest;
import ar.com.comunidadesfera.eficiencia.test.persistencia.consultas.PromediarMedicionesPorDiscriminanteDimensionEnUnEntornoTest;
import ar.com.comunidadesfera.eficiencia.test.persistencia.consultas.SumarizarMedicionesPorDiscriminanteEnUnEntornoTest;

@RunWith( Suite.class )
@SuiteClasses( {
    ContextoPersistenteTest.class,
    AdministradorDeMedicionesTest.class,
    BuscarIdProblemaTest.class,
    BuscarMedicionesDeUnModuloTest.class,
    SumarizarMedicionesPorDiscriminanteEnUnEntornoTest.class,
    PromediarMedicionesPorDiscriminanteDimensionEnUnEntornoTest.class
})
public final class PersistenciaTestSuit {

}

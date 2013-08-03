package ar.com.comunidadesfera.batallaespacial.test.config;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ar.com.comunidadesfera.batallaespacial.aplicacion.FabricaDeTableros;
import ar.com.comunidadesfera.batallaespacial.aplicacion.ParticipanteExtendido;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.CivilizacionHumana;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.numenor.Numenor;
import ar.com.comunidadesfera.batallaespacial.config.CargadorDeConfiguraciones;
import ar.com.comunidadesfera.batallaespacial.config.CargadorDeConfiguracionesDesdeProperties;
import ar.com.comunidadesfera.batallaespacial.config.ConfiguracionInvalidaException;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;
import ar.com.comunidadesfera.batallaespacial.test.civilizaciones.simulada.CivilizacionSimulada;

public class CargadorDeConfiguracionesDesdePropertiesTest {
    
    private CargadorDeConfiguraciones cargador;

    private FabricaDeTableros fabricaDeTableros;

    @SuppressWarnings("unchecked")
    @Before
    public void iniciar() {
        
        fabricaDeTableros = mock(FabricaDeTableros.class);
        
        when(this.fabricaDeTableros.crearTablero(Mockito.any(Configuracion.class), Mockito.notNull(InputStream.class)))
            .thenReturn(mock(Tablero.class));
        
        cargador = new CargadorDeConfiguracionesDesdeProperties();
        cargador.setFabricaDeTableros(fabricaDeTableros);
        
    }
    
    @Test
    public void cargarDesdeTestConfig1Properties() throws ConfiguracionInvalidaException {
        
        Path ruta = Paths.get("src/test/content/config/test-config-1.properties");
        
        Configuracion<ParticipanteExtendido> configuracion = cargador.cargar(ruta);
        
        assertThat(configuracion, notNullValue());
        
        /* Partida */
        assertThat("rondas", configuracion.getRondas(), is(0));
        assertThat("timeout", configuracion.getTimeout(), is(0L));
        
        /* Tablero */
        assertThat("tablero", configuracion.getTablero(), notNullValue());
        
        /* Participantes */
        assertThat("participantes", configuracion.getParticipantes(), notNullValue());
        assertThat("cantidad de participantes", configuracion.getParticipantes().size(), is(1));
        
        ParticipanteExtendido participante;
        
        participante = configuracion.getParticipantes().get(0);
        assertThat("civilizacion del participante 1", participante.getCivilizacion(), instanceOf(Numenor.class));
        assertThat("cantida de naves del participante 1", participante.getCantidadNaves(), is(3));
        assertThat("color del participante 1", participante.getPintura(), is((Paint) Color.web("0x00AA22")));
        
        /* Interacciones */
        verify(fabricaDeTableros).crearTablero(Mockito.eq(configuracion), Mockito.<InputStream>any());
        verifyNoMoreInteractions(fabricaDeTableros);
    }
    
    @Test
    public void cargarDesdeTestConfig2Propeties() throws ConfiguracionInvalidaException {
        
        Path ruta = Paths.get("src/test/content/config/test-config-2.properties");
        
        Configuracion<ParticipanteExtendido> configuracion = cargador.cargar(ruta);
        
        assertThat(configuracion, notNullValue());
        
        /* Partida */
        assertThat("rondas", configuracion.getRondas(), is(1000));
        assertThat("timeout", configuracion.getTimeout(), is(500L));
        
        /* Tablero */
        assertThat("tablero", configuracion.getTablero(), notNullValue());

        /* Participantes */
        assertThat("participantes", configuracion.getParticipantes(), notNullValue());
        assertThat("cantidad de participantes", configuracion.getParticipantes().size(), is(3));
        
        ParticipanteExtendido participante;
        
        participante = configuracion.getParticipantes().get(0); 
        assertThat("civilizacion del participante 1", participante.getCivilizacion(), instanceOf(Numenor.class));
        assertThat("cantida de naves del participante 1", participante.getCantidadNaves(), is(3));
        assertThat("color del participante 1", participante.getPintura(), is((Paint) Color.web("0x00AA22")));

        participante = configuracion.getParticipantes().get(1); 
        assertThat("civilizacion del participante 2", participante.getCivilizacion(), instanceOf(CivilizacionSimulada.class));
        assertThat("cantida de naves del participante 2", participante.getCantidadNaves(), is(8));
        assertThat("color del participante 2", participante.getPintura(), notNullValue());
        
        participante = configuracion.getParticipantes().get(2); 
        assertThat("civilizacion del participante 3", participante.getCivilizacion(), instanceOf(CivilizacionHumana.class));
        assertThat("cantida de naves del participante 3", participante.getCantidadNaves(), greaterThan(0));
        assertThat("color del participante 3", participante.getPintura(), is((Paint) Color.web("0xFF00FF")));
        
        /* Interacciones */
        verify(fabricaDeTableros).crearTablero(Mockito.eq(configuracion), Mockito.<InputStream>any());
        verifyNoMoreInteractions(fabricaDeTableros);
    }
    
    @Test(expected = ConfiguracionInvalidaException.class)
    public void cargarDesdeArchivoInexistente() throws ConfiguracionInvalidaException {
        
        Path ruta = Paths.get("src/test/content/config/inexistente.properties");
        
        cargador.cargar(ruta);
    }

    @Test(expected = ConfiguracionInvalidaException.class)
    public void cargarDesdeArchivoConErrores1() throws ConfiguracionInvalidaException {
        
        Path ruta = Paths.get("src/test/content/config/error-config-1.properties");
        
        cargador.cargar(ruta);
    }
    
    @Test(expected = ConfiguracionInvalidaException.class)
    public void cargarDesdeArchivoConErrores2() throws ConfiguracionInvalidaException {
        
        Path ruta = Paths.get("src/test/content/config/error-config-2.properties");
        
        cargador.cargar(ruta);
    }
}

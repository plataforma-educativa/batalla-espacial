package ar.com.comunidadesfera.batallaespacial.galaxias.andromeda;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import ar.com.comunidadesfera.batallaespacial.config.ConfiguracionBasica;
import ar.com.comunidadesfera.batallaespacial.ui.TipoControl;
import ar.com.comunidadesfera.batallaespacial.ui.VistaAplicacion;

/**
 * Control de Aplicación para la Galaxia Andrómeda.
 * 
 * @author Mariano Tugnarelli
 * 
 */
public class ControlAplicacion extends 
    ar.com.comunidadesfera.batallaespacial.aplicacion.ControlAplicacion {

    public static final String CONFIGURACION = "/ar/com/comunidadesfera/batallaespacial/galaxias/andromeda/config/partida.andromeda.properties";

    public ControlAplicacion(VistaAplicacion vista) {
        super(vista);
    }

    @Override
    protected void inicializar() {

        InputStream origenConfiguracion = this.getClass().getResourceAsStream(
                CONFIGURACION);

        if (origenConfiguracion != null) {

            this.setPartida(this.crearPartida(origenConfiguracion));
            
            this.controlActivado(TipoControl.JUGAR);

        } else {

            super.inicializar();
        }
    }

    /**
     * @post redefinido para recuperar el Tablero desde el classpath.
     */
    @Override
    protected void configurarTablero(ConfiguracionBasica configuracion,
                                     Properties properties) throws IOException {

        String pathTablero = properties.getProperty(PARTIDA_TABLERO);

        InputStream is = this.getClass().getResourceAsStream(pathTablero);

        if (is != null) {

            configuracion.setTablero(this.crearTablero(new InputStreamReader(is)));
            
        } else {
            
            super.configurarTablero(configuracion, properties);
        }
    }
}

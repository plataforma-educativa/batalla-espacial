package ar.com.comunidadesfera.batallaespacial.config;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.exception.ExceptionUtils;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.aplicacion.FabricaDeTableros;
import ar.com.comunidadesfera.batallaespacial.aplicacion.ParticipanteExtendido;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.FabricaDePiezas;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;

public class CargadorDeConfiguracionesDesdeProperties implements CargadorDeConfiguraciones {
    
    private static final List<String> FILTROS = Arrays.asList("*.properties");
    
    private static final String PARTIDA      = "partida";
    private static final String RONDAS       =      "rondas";
    private static final String TIMEOUT      =          "timeout";
    private static final String CANTIDAD     =          "cantidad";
    private static final String PARTICIPANTE =      "participante";
    private static final String TABLERO      =      "tablero";
    
    private static final int NAVES_POR_DEFECTO = 1;
    private static final String COLOR_POR_DEFECTO = "#FF0000";
    private static final String TABLERO_POR_DEFECTO = "partida.tablero";
    
    private FabricaDeTableros fabricaDeTableros;
    
    private FabricaDePiezas fabricaDePiezas;
    
    @Override
    public Configuracion<ParticipanteExtendido> cargar(Path ruta) throws ConfiguracionInvalidaException {

        this.validarOrigen(ruta);
        
        Configuracion<ParticipanteExtendido> configuracion = new Configuracion<ParticipanteExtendido>();
        configuracion.setRuta(ruta.getParent());

        try {
        
            Configuration config = new PropertiesConfiguration(ruta.toFile());
            
            this.cargarPartida(configuracion, config.subset(PARTIDA));
            
        } catch (ConfiguracionInvalidaException cie) {
            
            throw cie;
            
        } catch (Exception e) {
            
            throw new ConfiguracionInvalidaException("Se provoco un error intentando leer la configuracion desde '" +
                                                     ruta.toAbsolutePath() + "'. " + 
                                                     ExceptionUtils.getMessage(e), e);
        }

        return configuracion;
    }

    private void validarOrigen(Path ruta) throws ConfiguracionInvalidaException {
        
        if (! Files.exists(ruta)) {
            
            throw new ConfiguracionInvalidaException("El archivo de configuración no existe: " + 
                                                     ruta.toAbsolutePath());
        }
    }

    private void cargarPartida(Configuracion<ParticipanteExtendido> configuracion, Configuration config) 
            throws Exception {
        
        this.cargarRondas(configuracion, config.subset(RONDAS));
        
        this.cargarParticipantes(configuracion, config.subset(PARTICIPANTE));
        this.cargarTablero(configuracion, config.subset(TABLERO));
    }
    
    private void cargarRondas(Configuracion<ParticipanteExtendido> configuracion, Configuration config) 
            throws Exception {

        configuracion.setRondas(config.getInt(CANTIDAD, 0));
        configuracion.setTimeout(config.getLong(TIMEOUT, 0L));
    }

    private void cargarParticipantes(Configuracion<ParticipanteExtendido> configuracion, Configuration config) 
            throws Exception {

        Configuration configParticipante;
        
        int indice = 0;
        
        do {
            
            /* busca las posibles configuraciones de participantes por número */
            
            configParticipante = config.subset(String.valueOf(++indice));
            
            if (! configParticipante.isEmpty()) {
                
                this.cargarParticipante(configuracion, configParticipante);
            }
            
        } while (! configParticipante.isEmpty()); 
    }

    private void cargarParticipante(Configuracion<ParticipanteExtendido> configuracion, Configuration config) 
            throws Exception {

        /* Instancia la civilización */
        Class<?> civilizacionClass = Class.forName(config.getString("civilizacion"));
        
        /* verifica que sea una Civilización */
        if (! Civilizacion.class.isAssignableFrom(civilizacionClass)) {
        
            throw new IllegalArgumentException(civilizacionClass + 
                                               " no es una Civilizacion");
        }
        
        Civilizacion civilizacion = (Civilizacion) civilizacionClass.newInstance();
        
        /* naves a crear para la Civilización */
        int naves = config.getInt("naves", NAVES_POR_DEFECTO);
        String color = config.getString("color", COLOR_POR_DEFECTO); 
        
        ParticipanteExtendido participante = new ParticipanteExtendido(civilizacion, 
                                                                       naves, 
                                                                       this.fabricaDePiezas, 
                                                                       color);
        
        /* agrega el Participante a la Configuración */
        configuracion.getParticipantes().add(participante);
    }

    private void cargarTablero(Configuracion<ParticipanteExtendido> configuracion, Configuration config) 
            throws Exception {

        Path rutaOrigenTablero = configuracion.getRuta()
                                    .resolve(Paths.get(config.getString("", TABLERO_POR_DEFECTO)));
        
        if (! Files.exists(rutaOrigenTablero)) {
            
            throw new ConfiguracionInvalidaException("El archivo de configuración de tablero no existe: " +
                                                     rutaOrigenTablero.toAbsolutePath());
        }
        
        try (InputStream origenTablero = Files.newInputStream(rutaOrigenTablero)) {
            
            Tablero tablero = this.fabricaDeTableros.crearTablero(configuracion, origenTablero);
            
            configuracion.setTablero(tablero);
        }
    }

    @Override
    @Inject
    public void setFabricaDeTableros(FabricaDeTableros fabrica) {
        
        this.fabricaDeTableros = fabrica;
    }
    
    @Override
    @Inject
    public void setFabricaDePiezas(FabricaDePiezas fabrica) {
        
        this.fabricaDePiezas = fabrica;
    }
    
    @Override
    public String getDescripcion() {

        return "Partida (*.properties)";
    }
    
    @Override
    public List<String> getFiltros() {

        return FILTROS;
    }
}

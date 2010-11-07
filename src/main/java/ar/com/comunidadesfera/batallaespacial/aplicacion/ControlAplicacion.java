package ar.com.comunidadesfera.batallaespacial.aplicacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.Piloto;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.FabricaDePiezas;
import ar.com.comunidadesfera.batallaespacial.juego.Motor;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;
import ar.com.comunidadesfera.batallaespacial.ui.FabricaDePanelesTablero;
import ar.com.comunidadesfera.batallaespacial.ui.PanelTablero;
import ar.com.comunidadesfera.batallaespacial.ui.TipoControl;
import ar.com.comunidadesfera.batallaespacial.ui.VistaAplicacion;

public class ControlAplicacion implements VistaAplicacion.Observador,
                                          Motor.Observador {

    protected static final String PARTIDA_PARTICIPANTE = "partida.participante.";
    protected static final String PARTICIPANTE_COLOR = ".color";
    protected static final String PARTICIPANTE_NAVES = ".naves";
    protected static final String PARTICIPANTE_CIVILIZACION = ".civilizacion";
    protected static final String PARTIDA_RONDAS_CANTIDAD = "partida.rondas.cantidad";
    protected static final String PARTIDA_RONDAS_TIMEOUT = "partida.rondas.timeout";
    protected static final String PARTIDA_CONFIG_PATH = "partida.config.path";
    protected static final String PARTIDA_TABLERO = "partida.tablero";
    
    public static final String COLOR_DEFAULT = "FF0000";
    public static final String NAVES_DEFAULT  = "1";
    public static final String TABLERO_PRESENTACION = "/ar/uba/fi/algo3/batallaespacial/config/presentacion.tablero";
    public static final String[] INFORMES_BASE = {
        "puntos",
        "carga"
    };
    public static final String[] INFORMES_NAVE = {
        "puntos",
        "nivelDeEscudos",
        "cantidadDeTorpedos",
        "carga"
    };
    public static final String[] INFORMES_CONTENEDOR = {
        "puntos",
        "contenido"
    };
    
    private VistaAplicacion vista;
    
    private Partida<ParticipanteExtendido> partida;

    private Integer rondas;
    
    private FabricaDePiezas fabricaDePiezas;
    
    private FabricaDePanelesTablero fabricaDePanelesTablero;
    
    private PanelTablero presentacion;
    
    public ControlAplicacion(VistaAplicacion vista) {
        
        super();
        this.vista = vista;
        this.vista.registrarObservador(this);

        /* recursos para la creación de Tableros */
        this.fabricaDePiezas = new FabricaPrototipicaDePiezas();
        this.fabricaDePanelesTablero = new FabricaDePanelesTablero();

        
        this.inicializar();

    }
    
    protected void inicializar() {
        
        /* panel de presentación */
        this.crearPresentacion();
        
        /* no existe partida actualmente */
        this.setPartida(null);
    }

    protected void crearPresentacion() {
        
        InputStream origen = this.getClass().getResourceAsStream(TABLERO_PRESENTACION);
        
        Tablero tablero = this.crearTablero(new InputStreamReader(origen));
        
        Configuracion<ParticipanteExtendido> config = new Configuracion<ParticipanteExtendido>();
        config.setTablero(tablero);
        this.presentacion = this.fabricaDePanelesTablero.crearTablero(config);

    }
    
    public Tablero crearTablero(Reader origen) {
        
        Tablero tablero = null;
        FabricaDeTableros fabrica = new ParserDeTableros(new BufferedReader(origen),"");
        fabrica.setFabricaDePiezas(this.fabricaDePiezas);
        tablero = fabrica.crearTablero();
        return tablero;
    }


    public void controlActivado(TipoControl tipoControl) {

        if (this.partida != null) {
            
            switch (tipoControl) {
            
                case JUGAR:
                    
                    if (this.rondas == null) {
                        
                        this.partida.comenzar();
                        
                    } else {
                    
                        /* lanza el motor */
                        this.partida.getMotor().getSemaforo().release(this.rondas);
                    }
                    this.rondas = 0;
                    
                    break;
                    
                case PROXIMA_MOVIDA :
                    
                    /* ejecuta una movida si está suspendido a la espera */
                    if ((this.rondas == null) || (! this.rondas.equals(0))) {
                    
                        /* toma todas rondas y luego comienza la partida */
                        if ( this.rondas == null ) {
                        
                            this.rondas = this.partida.getMotor().getSemaforo().drainPermits();
                            this.partida.comenzar();
                        }
                        
                        if (this.rondas > 0) {
                            
                            this.rondas--;
                        }
                        
                        /* ejecuta una sola ronda */
                        this.partida.getMotor().getSemaforo().release();
                    }

                    break;
                    
                case DETENER :

                    /* solamente detiene el motor si se está ejecutando */
                    if ((this.rondas != null) && (this.rondas.equals(0))) {
                        
                        /* detiene el motor */
                        this.rondas = this.partida.getMotor().getSemaforo().drainPermits(); 
                    }
                    break;
            }
        }
    }
    
    public void partidaCargada(InputStream origenConfiguracion) {

        /* crea la partida actual */
        Partida<ParticipanteExtendido> partida = this.crearPartida(origenConfiguracion); 
        this.setPartida(partida);
    }

    /**
     * Crea una partida a partir de su configuración.
     */ 
    protected Partida<ParticipanteExtendido> crearPartida(InputStream origenConfiguracion) {

        Partida<ParticipanteExtendido> partida = null;
     
        /* toma la configuración */
        Configuracion<ParticipanteExtendido> configuracion =  this.crearConfiguracion(origenConfiguracion);
        
        if (configuracion != null) {
            
            /* si la configuración fue cargada correctamente crea la nueva partida */
            partida = new Partida<ParticipanteExtendido>(configuracion);
            
        }
        
        return partida;
    }
    
    
    protected Configuracion<ParticipanteExtendido> crearConfiguracion(InputStream origenConfiguracion) {

        Configuracion<ParticipanteExtendido> configuracion;
        
        try {

            Properties properties = this.newConfigurationProperties(origenConfiguracion);
            
            configuracion = this.newConfiguracion(properties);
            
            this.configurarTablero(configuracion, properties);
            this.configurarRondas(configuracion, properties);
            this.configurarTimeout(configuracion, properties);
            this.configurarParticipantes(configuracion, properties);

        } catch (Exception error) {

            error.printStackTrace();
            
            configuracion = null;
        }
        
        return configuracion;
        
    }
    
    /**
     * @post instancia y carga las properties de la configuración de la partida.
     */
    protected Properties newConfigurationProperties(InputStream origenConfiguracion) 
        throws IOException {
        
        /* carga la configuración de la Partida */
        Properties properties = new Properties();
        properties.load(origenConfiguracion);
        
        return properties;
    }
    
    /**
     * @post instancia la configuración de la partida.
     * 
     */
    protected Configuracion<ParticipanteExtendido> newConfiguracion(Properties properties) {
        
        return new Configuracion<ParticipanteExtendido>();
    }

    /**
     * @post carga la configuración del tablero para la partida. 
     */
    protected void configurarTablero(Configuracion<ParticipanteExtendido> configuracion, 
                                     Properties properties)
        throws IOException {
        
        File archivoTablero = new File(properties.getProperty(PARTIDA_TABLERO));
        
        if(!archivoTablero.isAbsolute()) {

            /* si el path no absoluto lo hace relativo al archivo
             * de properties de configuración */
            archivoTablero = new File(System.getProperty(PARTIDA_CONFIG_PATH), 
                                      properties.getProperty(PARTIDA_TABLERO));
        }
        
        configuracion.setTablero(crearTablero(new FileReader(archivoTablero)));
    }
    
    /**
     * @post configura el timeout entre rondas de la partida.
     */
    protected void configurarTimeout(Configuracion<ParticipanteExtendido> configuracion, 
                                     Properties properties) {
        
        long timeout = Long.parseLong(properties.getProperty(PARTIDA_RONDAS_TIMEOUT));
        configuracion.setTimeout(timeout);
    }

    /**
     * @post configura la cantidad de rondas de la partida.
     */
    protected void configurarRondas(Configuracion<ParticipanteExtendido> configuracion, 
                                    Properties properties) {
        
        int rondas = Integer.parseInt(properties.getProperty(PARTIDA_RONDAS_CANTIDAD));
        configuracion.setRondas(rondas);
    }

    
    /**
     * Configura los Participantes.
     * 
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    protected void configurarParticipantes(Configuracion<ParticipanteExtendido> configuracion,
                                           Properties properties) 
        throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        /* busca los posibles participantes */
        int indice = 1;
        String className;

        do {
       
            /* Civilización del Participante */
            className = properties.getProperty(PARTIDA_PARTICIPANTE + 
                                               indice + 
                                               PARTICIPANTE_CIVILIZACION);
            if (className != null) {

                /* Instancia la civilización */
                Class<?> civilizacionClass = Class.forName(className);
                
                /* verifica que sea una Civilización */
                if (! Civilizacion.class.isAssignableFrom(civilizacionClass)) {
                
                    throw new IllegalArgumentException(civilizacionClass + 
                                                       " no es una Civilizacion");
                }
                
                Civilizacion civilizacion = (Civilizacion) civilizacionClass.newInstance();
                
                /* naves a crear para la Civilización */
                int naves = Integer.parseInt(properties.getProperty(PARTIDA_PARTICIPANTE +
                                                                    indice +
                                                                    PARTICIPANTE_NAVES,NAVES_DEFAULT));
                
                String color = properties.getProperty(PARTIDA_PARTICIPANTE +
                                                      indice +
                                                      PARTICIPANTE_COLOR, COLOR_DEFAULT); 
                
                /* agrega el Participante a la Configuración */
                configuracion.getParticipantes()
                             .add(new ParticipanteExtendido(civilizacion, naves, 
                                                   this.getFabricaDePiezas(), color));
            }
            
            /* busca el siguiente participante */
            indice++;
            
        } while (className != null);

    }
    
    /**
     * Asigna la Partida actual deteniendo otra anterior.
     *  
     * @param partida
     */
    protected void setPartida(Partida<ParticipanteExtendido> partida) {
        
        if (this.partida != null) {

            /* termina la ejecución del thread de la Partida anterior */
            Thread thread  = this.partida.getThreadMotor();
            if (thread != null) {
                
                thread.interrupt();
            }
        }
        
        this.partida = partida;
        this.rondas = null;

        /* se agrega como observador del Motor de la Partida */
        if (this.partida != null) {
            
            this.partida.getMotor().agregarObservador(this);
        }
        
        this.regenerarVista();
    }
    
    protected void setFabricaDePiezas(FabricaDePiezas fabrica) {
        
        this.fabricaDePiezas = fabrica;
    }
    
    protected FabricaDePiezas getFabricaDePiezas() {
        
        return this.fabricaDePiezas;
    }
    
    protected void regenerarVista() {
        
        /* crea la interfaz para la partida */
        if (this.partida != null) {

            Configuracion<ParticipanteExtendido> config = this.partida.getConfiguracion(); 
            
            /* Tablero y Piezas en el Tablero */
            PanelTablero panel =  this.fabricaDePanelesTablero.crearTablero(config);

            this.vista.setPanelTablero(panel);
            
            /* Piezas que no están en el Tablero */
            List<BaseEspacial> bases = this.partida.getBases();

            for (BaseEspacial base : bases) {

                this.vista.addInforme(base, INFORMES_BASE);
                
                /* Naves que están en la Base */
                for (Nave nave : base.getNaves()) {
                    
                    this.fabricaDePanelesTablero.crearPieza(nave, panel, config);
                    
                    this.vista.addInforme(nave, INFORMES_NAVE);
                }
            }
            
            // TODO refactorizar
            for (Pieza pieza : this.partida.getTablero()) {

                if (pieza instanceof Contenedor) {
                    
                    this.vista.addInforme(pieza, INFORMES_CONTENEDOR);
                }
            }
            
        } else {
            
            this.vista.setPanelTablero(this.presentacion);
        }
        
        this.vista.revalidate();
    }

    public void rondaTerminada(Motor motor, long ronda) {

        this.vista.setRonda(ronda);
    }

    public void exception(Motor motor, long ronda, Piloto piloto, Comando comando, Exception exception) {

        // TODO
        exception.printStackTrace();
    }

    public void finalizacion(Motor motor) {

        // TODO
    }
}

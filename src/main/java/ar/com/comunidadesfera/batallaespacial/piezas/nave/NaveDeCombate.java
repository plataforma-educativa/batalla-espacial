package ar.com.comunidadesfera.batallaespacial.piezas.nave;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import ar.com.comunidadesfera.batallaespacial.CabinaDeControl;
import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.Monitor;
import ar.com.comunidadesfera.batallaespacial.Notificacion;
import ar.com.comunidadesfera.batallaespacial.Piloto;
import ar.com.comunidadesfera.batallaespacial.Radar;
import ar.com.comunidadesfera.batallaespacial.Reporte;
import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.juego.Detectable;
import ar.com.comunidadesfera.batallaespacial.juego.ReporteEstatico;
import ar.com.comunidadesfera.batallaespacial.juego.escenarios.Escenario;
import ar.com.comunidadesfera.batallaespacial.piezas.CabinaDeControlBasica;
import ar.com.comunidadesfera.batallaespacial.piezas.PiezaControlable;
import ar.com.comunidadesfera.batallaespacial.piezas.TransporteDeSustancias;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;

/**
 * Una NaveDeCombate es una Nave con capacidad para realizar ataques,
 * transportar carga.
 * Es controlada por un Piloto asignado. 
 *  
 * @author Mariano Tugnarelli
 *
 */
public class NaveDeCombate extends Nave 
    implements TransporteDeSustancias, PiezaControlable {

    /**
     * Características comunes a todas las Naves de Combate.
     */
    private static AtomicLong capacidadDeCarga = new AtomicLong(100);
    private static AtomicInteger  capacidadTorpedos = new AtomicInteger(20);
    
    /**
     * Cantidad de torpedos.
     */
    private int cantidadDeTorpedos;
    
    /**
     * Bodega de carga de la Nave
     */
    private Contenedor bodega;
    
    /**
     * Cabina de ControlAplicacion de la Nave.
     */
    private CabinaDeControl cabina;
    
    /**
     * ControlAplicacion de la Nave
     */
    private ControlNaveDeCombate control;
    
    /**
     * El Radar da información del Contexto al Piloto. 
     */
    private Radar radar;
    
    /**
     * Monitor de la Nave.
     */
    private Monitor monitor;
    
    /**
     * Cola de Notificaciones
     */
    private Queue<Notificacion> notificaciones;
    
    /**
     * Piloto al mando de la nave
     */
    private Piloto piloto;

    /**
     * Crea una NaveDeCombate con los puntos indicados.
     * 
     * @param puntos
     */
    public NaveDeCombate(int puntos) {
    
        super(puntos);
        
        this.setCantidadDeTorpedos(NaveDeCombate.getCapacidadTorpedos());
        this.bodega = new Contenedor(this.getPuntos(), NaveDeCombate.getCapacidadDeCarga());
        
        this.inicializar();
    }
    
    /**
     * @pre ninguna.
     * @post realiza la inicialización de la instancia 
     *
     */
    private void inicializar() {
        
        /* crea la bodega utilizando un Contenedor */
        this.cabina = new CabinaDeControlBasica(this);
        this.control = new ControlNaveDeCombate(this);
        this.monitor = new MonitorNaveDeCombate(this);
        this.notificaciones = new LinkedList<Notificacion>();
    }
    
    /**
     * @pre ninguna.
     * @post devuelve la Civilización a la que pertenece la Nave.
     *  
     */
    public Civilizacion getCivilizacion() {
        
        return this.getPiloto().getCivilizacion();
    }
    
    /**
     * 
     * @return cantidad de torpedos restantes en la Nave.
     */
    public synchronized int getCantidadDeTorpedos() {
        
        return cantidadDeTorpedos;
    }

    /**
     * @pre <code>cantidadDeTorperdos</code> debe ser mayor o igual 0.
     * @post cambia la cantidad de torpedos de la Nave. 
     * 
     * @param cantidadDeTorpedos
     */
    public void setCantidadDeTorpedos(int cantidadDeTorpedos) {
        
        if (cantidadDeTorpedos < 0) {
            
            throw new IllegalArgumentException("La cantidad de torpedos invalida");
        }
        
        synchronized (this) {
            
            this.cantidadDeTorpedos = cantidadDeTorpedos;
        }
        
        this.notificar();
    }


    /**
     * @return Monitor de la Nave de Combate
     */
    public Monitor getMonitor() {

        return this.monitor;
    }

    /**
     * @pre ninguna.
     * @post asigna a la NaveDeCombate el Piloto <code>piloto</code>. 
     * 
     * @param piloto 
     */
    public void setPiloto(Piloto piloto) {
        
        this.piloto = piloto;
        
        /* asigna al piloto el control de la nave */
        this.piloto.setCabinaDeControl(this.getCabinaDeControl());
    }
    
    /**
     * 
     * @return Piloto asignada a la NaveDeCombate.
     */
    public Piloto getPiloto() {
        
        return this.piloto;
    }

    /**
     * @see TransporteDeSustancias#agregarSustancia(Sustancia, long) 
     */
    public void agregarSustancia(Sustancia sustancia, long cantidad) 
        throws IllegalArgumentException {
        
        /* delega en la bodega */
        bodega.agregarSustancia(sustancia, cantidad);
        
        this.notificar();
    }

    /**
     * @see TransporteDeSustancias#extraerSustancia(Sustancia, long)
     */
    public void extraerSustancia(Sustancia sustancia, long cantidad) 
        throws IllegalArgumentException {

        /* delega en la bodega */
        bodega.extraerSustancia(sustancia, cantidad);
        
        this.notificar();
    }

    /**
     * @see TransporteDeSustancias#getCantidad(Sustancia)
     */
    public long getCantidad(Sustancia sustancia) {

        /* delega en la bodega */
        return bodega.getCantidad(sustancia);
    }

    /**
     * @see TransporteDeSustancias#getCapacidadDisponible(Sustancia) 
     */
    public long getCapacidadDisponible(Sustancia sustancia) {

        /* delega en la bodega */
        return bodega.getCapacidadDisponible(sustancia);
    }

    public void despegar() {
        
        super.despegar();
        
        /* notifica del desgue */
        this.notificaciones.offer(Notificacion.DESPEGUE);
    }
    
    /**
     * 
     * @return Bodega de la Nave de Combate. 
     */
    public Contenedor getBodega() {
        
        return this.bodega;
    }
    
    public Radar getRadar() {
        return radar;
    }

    public void setRadar(Radar radar) {
        this.radar = radar;
    }

    public CabinaDeControl getCabinaDeControl() {
        return cabina;
    }

    public ControlNaveDeCombate getControl() {
        
        return this.control;
    }

    /**
     * @see Detectable#reportar()
     */
    @Override
    public Reporte reportar() {

        return new ReporteEstatico(Espectro.NAVE, this.getCivilizacion(), null);
    }

    /**
     * 
     * @return Notificaciones de la Nave
     */
    public Queue<Notificacion> getNotificaciones() {
        
        return notificaciones;
    }
    
    /**
     * @return identificación de la Pieza : nombre del Piloto de la Nave.
     */
    public String getIdentificacion() {
        
        return this.getPiloto() != null ? this.getPiloto().getNombre() : "desconocido";
    }
    
    /**
     * @see Object#clone()
     */
    public NaveDeCombate clone() {
        
        NaveDeCombate clon = (NaveDeCombate) super.clone();
        
        /* inicializa los atributos no clonados */
        clon.inicializar();
        clon.setBodega( this.bodega.clone() );
        
        return clon;
    }

    /**
     * 
     * @param bodega
     */
    protected void setBodega(Contenedor bodega) {
        this.bodega = bodega;
    }
    
    public Map<Sustancia, Long> getCarga() {
        
        return this.getBodega().getContenido();
    }

    public void modificarPuntos(int delta, Escenario escenario) {
        
        /* agrega las notificaciones correspondientes */
        this.getNotificaciones().addAll(escenario.getNotificaciones());

        super.modificarPuntos(delta, escenario);
    }
    
    /**
     * Configuración de las Naves de Combate.
     * 
     */
    public static long getCapacidadDeCarga() {
        
        return capacidadDeCarga.get();
    }

    public static void setCapacidadDeCarga(long capacidadDeCarga) {
        
        NaveDeCombate.capacidadDeCarga.set(capacidadDeCarga);
    }

    public static int getCapacidadTorpedos() {
        
        return capacidadTorpedos.get();
    }

    public static void setCapacidadTorpedos(int capacidadTorpedos) {
        
        NaveDeCombate.capacidadTorpedos.set(capacidadTorpedos);
    }
}

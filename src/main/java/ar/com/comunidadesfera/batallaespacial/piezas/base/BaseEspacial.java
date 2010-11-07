package ar.com.comunidadesfera.batallaespacial.piezas.base;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.Reporte;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.ReporteEstatico;
import ar.com.comunidadesfera.batallaespacial.piezas.TransporteDeSustancias;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;

/**
 * La BaseEspacial es una Pieza que puede contener en su interior
 * una conjunto de Naves.
 * 
 * @author Mariano Tugnarelli.
 *
 */
public class BaseEspacial extends Pieza  implements TransporteDeSustancias, 
                                                    PistaDeAterrizaje {

    /**
     * Naves que aún no despegaron de la Base.
     */
    private List<Nave> naves;
    
    /**
     * Civilización a la que pertenece la Base.
     */
    private Civilizacion civilizacion;
    

    private Contenedor bodega;
    
    private String nombre;
    
    /**
     * 
     * @param puntos
     * @param civilizacion
     */
    public BaseEspacial(int puntos) {
        
        super(puntos);
        this.setBodega(new Contenedor(puntos, Long.MAX_VALUE));
        this.inicializar();
    }
    
    private void inicializar() {
        
        this.naves = new LinkedList<Nave>();
    }

    /**
     * 
     * @param nave
     */
    public void agregarNave(Nave nave) {
        
        this.getNaves().add(nave);
        
        this.notificar();
    }

    public void retirarNave(Nave nave) {
        
        this.getNaves().remove(nave);
        
        this.notificar();
    }
    
    public List<Nave> getNaves() {
        return this.naves;
    }

    
    /**
     * @see Pieza#reportar()
     */
    public Reporte reportar() {
        
        return new ReporteEstatico(Espectro.BASE, this.getCivilizacion(), null);
    }

    /**
     * 
     * @return Civilizacion a la que pertenece la Base.
     */
    public Civilizacion getCivilizacion() {
        return civilizacion;
    }

    /**
     * 
     * @param civilizacion
     */
    public void setCivilizacion(Civilizacion civilizacion) {
        this.civilizacion = civilizacion;
    }

    
    /**
     * @see TransporteDeSustancias#agregarSustancia(Sustancia, long)
     */
    public void agregarSustancia(Sustancia sustancia, long cantidad) throws IllegalArgumentException {
        bodega.agregarSustancia(sustancia, cantidad);
        
        this.notificar();
    }

    /**
     * @see TransporteDeSustancias#extraerSustancia(Sustancia, long)
     */
    public void extraerSustancia(Sustancia sustancia, long cantidad) throws IllegalArgumentException {
        bodega.extraerSustancia(sustancia, cantidad);
        
        this.notificar();
    }

    /**
     * @see TransporteDeSustancias#getCantidad(Sustancia)
     */
    public long getCantidad(Sustancia sustancia) {
        return bodega.getCantidad(sustancia);
    }

    /**
     * @see TransporteDeSustancias#getCapacidadDisponible(Sustancia)
     */
    public long getCapacidadDisponible(Sustancia sustancia) {
        return bodega.getCapacidadDisponible(sustancia);
    }

    public Contenedor getBodega() {
        return bodega;
    }

    protected void setBodega(Contenedor bodega) {
        this.bodega = bodega;
    }

    public Map<Sustancia, Long> getCarga() {
        
        return this.getBodega().getContenido();
    }
    
    /**
     * @see Object#clone()
     */
    public BaseEspacial clone() {
        
        BaseEspacial clon = (BaseEspacial) super.clone();
        
        /* inicializa los atributos que no se clonaron */
        clon.inicializar();
        clon.setBodega(this.bodega.clone());

        return clon;
    }
    
    /**
     * @see Pieza#getIdentificacion()
     */
    public String getIdentificacion() {
        
        return "Base " + this.getCivilizacion().getNombre() + " " + this.getNombre(); 
    }

	/**
	 * @return Returns the nombre.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre The nombre to set.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

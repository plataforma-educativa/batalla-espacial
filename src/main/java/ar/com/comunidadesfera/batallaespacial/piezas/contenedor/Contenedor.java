package ar.com.comunidadesfera.batallaespacial.piezas.contenedor;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.Reporte;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.ReporteEstatico;
import ar.com.comunidadesfera.batallaespacial.piezas.TransporteDeSustancias;

/**
 * Pieza que implementa un Transporte de Sustancias.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class Contenedor extends Pieza implements TransporteDeSustancias {

    /**
     * Sustancias que contiene el Contenedor
     */
    private Map<Sustancia, Long> contenido;
    
    /**
     * Capacidad de transporte.
     */
    private long capacidad;
    
    /**
     * Crea el Contenedor.
     * 
     * @param puntos
     */
    public Contenedor(int puntos, long capacidad) {
        
        super(puntos);
        this.setCapacidad(capacidad);
        this.contenido =  new EnumMap<Sustancia, Long>(Sustancia.class); 
    }

    /**
     * @see ar.com.comunidadesfera.batallaespacial.piezas.TransporteDeSustancias#getCantidad(Sustancia)
     */
    public long getCantidad(Sustancia sustancia) {

        Long cantidad = this.getContenido().get(sustancia); 

        return cantidad != null ? cantidad.longValue() : 0;
    }

    /**
     * @see ar.com.comunidadesfera.batallaespacial.piezas.TransporteDeSustancias#getCapacidadDisponible(Sustancia)
     */
    public long getCapacidadDisponible(Sustancia sustancia) {

        /* esta implementación trata de manera homogÃ©a a todas las sustancias */
        
        return this.getCapacidad() - this.getCarga();
    }

    /**
     * @return total de la carga del Contenedor. 
     */
    protected long getCarga() {
        
        long carga = 0;
        
        /* suma las carga de todas las sustancias */
        for (Long cantidad : this.getContenido().values()) {
            
            carga += cantidad;
        }
        
        return carga;
    }
    
    /**
     * @see ar.com.comunidadesfera.batallaespacial.piezas.TransporteDeSustancias#agregarSustancia(Sustancia, long)
     */
    public void agregarSustancia(Sustancia sustancia, long cantidad)
            throws IllegalArgumentException {

        /* verifica la cantidad */
        if (cantidad < 0) {
            
            throw new IllegalArgumentException("Cantidad a agregar no puede ser negativa");
        }
        
        /* verifica la capacidad disponible */
        if (this.getCapacidadDisponible(sustancia) < cantidad) {
         
            throw new IllegalArgumentException("Capacidad insuficiente");
        }

        /* actualiza la carga de la sustancia */
        Long carga = this.getContenido().get(sustancia);
        carga = carga != null ? carga + cantidad : cantidad;

        this.modificarCarga(sustancia, carga);
    }

    /**
     * @see ar.com.comunidadesfera.batallaespacial.piezas.TransporteDeSustancias#extraerSustancia(Sustancia, long)
     */
    public void extraerSustancia(Sustancia sustancia, long cantidad)
            throws IllegalArgumentException {

        if (cantidad < 0) {
            
            throw new IllegalArgumentException("Cantidad a extraer no puede ser negativa");
        }
            
        if (cantidad > 0) {
            
            /* actualiza la carga de la sustancia */
            Long carga = this.getContenido().get(sustancia);
    
            /* verifica la cantidad */
            if ((carga == null) || (carga < cantidad)) {
                
                throw new IllegalArgumentException("Cantidad insuficiente");
            }
    
            /* actualiza la carga */
            carga = carga - cantidad;
            
            this.modificarCarga(sustancia, carga);
        }
    }

    protected void modificarCarga(Sustancia sustancia, long carga) {
        
        if (carga > 0) {
        
            this.getContenido().put(sustancia, carga);
            
        } else {
            
            this.getContenido().remove(sustancia);
        }
        
        this.notificar();
    }
    
    /**
     * 
     * @return mapa con el contenido del Contenedor.
     */
    public Map<Sustancia, Long> getContenido() {
        
        return this.contenido;
    }
    
    protected long getCapacidad() {
        return this.capacidad;
    }
    
    protected void setCapacidad(long capacidad) {
        
        this.capacidad = capacidad;
    }
    
    
    /**
     * @pre ninguna.
     * @post devuelve un valor entre 0 y 100 que representa
     *       el porcentaje de la ocupado.
     * 
     * @return porcentaje ocupado por la carga.
     */
    public byte getNivelDeCarga() {
        
        return (byte) ((float)this.getCarga() / (float) this.getCapacidad() * 100.0);
    }

    /**
     * 
     */
    public Civilizacion getCivilizacion() {

        /* no pertenece a ninguna Civilización */
        return null;
    }

    /**
     * @see Detectable#reportar()
     */
    @Override
    public Reporte reportar() {

        return new ReporteEstatico(Espectro.CONTENEDOR, null, 
                                   Collections.unmodifiableMap(this.getContenido()));
    }

    /**
     * @see Object#clone()
     */
    public Contenedor clone() {
        
        Contenedor clon = (Contenedor) super.clone();
        clon.contenido = ((EnumMap<Sustancia, Long>) this.contenido).clone();
        return clon;
    }
    
    public String getIdentificacion() {
        
        return "Contenedor C" + this.getNumero(); 
    }
    
    @Override
    public void distorsionar() {
     
        super.distorsionar();
        
        for (Sustancia sustancia : Sustancia.values()) {
            
            long carga = (long) (Pieza.azar.nextFloat() * this.getCantidad(sustancia));

            this.modificarCarga(sustancia, carga);
        }
    }
}


package ar.com.comunidadesfera.batallaespacial.juego.escenarios;

import java.util.EnumSet;
import java.util.Set;

import ar.com.comunidadesfera.batallaespacial.Notificacion;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.piezas.TransporteDeSustancias;

/**
 *   
 *
 */
public class TransferenciaNormal implements Transferencia {

    /**
     * TransporteDeSustancias del que se extrae Sustancia.
     */
    private TransporteDeSustancias origen;
    
    /**
     * TransporteDeSustancias al que se agrega Sustancia.
     */
    private TransporteDeSustancias destino;
    
    /**
     * Sustancia a transferir.
     */
    private Sustancia sustancia;
    
    /**
     * Cantidad de Sustancia a transferir.
     */
    private long cantidad;
    
    /**
     * 
     */
    public TransferenciaNormal(TransporteDeSustancias origen, 
                               Sustancia sustancia, long cantidad,                   
                               TransporteDeSustancias destino) {
        
        super();
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
        this.sustancia = sustancia;
    }

    /**
     * @see ar.com.comunidadesfera.batallaespacial.juego.escenarios.Transferencia#getOrigen()
     */
    public TransporteDeSustancias getOrigen() {
        
        return this.origen;
    }

    /**
     * @see ar.com.comunidadesfera.batallaespacial.juego.escenarios.Transferencia#getDestino()
     */
    public TransporteDeSustancias getDestino() {

        return this.destino;
    }

    /**
     * @see ar.com.comunidadesfera.batallaespacial.juego.escenarios.Transferencia#getCantidad()
     */
    public long getCantidad() {

        return this.cantidad;
    }

    /**
     * @see ar.com.comunidadesfera.batallaespacial.juego.escenarios.Transferencia#getSustancia()
     */
    public Sustancia getSustancia() {

        return this.sustancia;
    }

    /**
     * Ejecuta la transferencia
     */
    public void ejecutar() {
        
        long cantidadTransferida = this.origen.getCantidad(this.sustancia);
        
        /* ajunta la cantidad a la disponible en el origen */
        if (this.cantidad > cantidadTransferida) {
            
            this.cantidad = cantidadTransferida;
        }
        
        /* ajusta la cantidad a la capacidad disponible en el destino */
        cantidadTransferida = this.destino.getCapacidadDisponible(this.sustancia);
        
        if (cantidadTransferida < this.cantidad) {
            
            this.cantidad = cantidadTransferida;
        }

        /* realiza la transferencia de acuerdo a los límites */
        this.origen.extraerSustancia(this.sustancia, this.cantidad);
        this.destino.agregarSustancia(this.sustancia, this.cantidad);
    }

    public Set<Notificacion> getNotificaciones() {

        return EnumSet.noneOf(Notificacion.class);
    }

}

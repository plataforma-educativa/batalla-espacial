package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes;

import ar.com.comunidadesfera.batallaespacial.Direccion;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.PilotoHumano;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;

/**
 * Orden para transferir una carga desde un origen hasta un destino.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class TransferirCarga implements Orden {

    /**
     * Cantidad de carga a transferir.
     */
    private long carga;
    
    /**
     * Sustancia a transferir.
     */
    private Sustancia sustancia;
    
    /**
     * Dirección desde donde transferir.
     */
    private Direccion destino;
    
    /**
     * Dirección hacia donde transferir.
     */
    private Direccion origen;
    
    public TransferirCarga(Direccion origen, Direccion destino, 
                           Sustancia sustancia, long carga) {
        
        this.origen = origen;
        this.destino = destino;
        this.sustancia = sustancia;
        this.carga = carga;
    }

    public Comando ejecutar(PilotoHumano piloto) {

        return piloto.getCabinaDeControl().getControl()
                        .transferirCarga(this.getOrigen(), 
                                         this.getDestino(),
                                         this.getSustancia(), 
                                         this.getCarga());
    }

    private long getCarga() {

        return this.carga;
    }

    private Sustancia getSustancia() {

        return this.sustancia;
    }

    private Direccion getDestino() {

        return this.destino;
    }

    private Direccion getOrigen() {
     
        return this.origen;
    }

}

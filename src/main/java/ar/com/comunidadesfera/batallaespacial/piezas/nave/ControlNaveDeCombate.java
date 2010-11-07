package ar.com.comunidadesfera.batallaespacial.piezas.nave;

import ar.com.comunidadesfera.batallaespacial.Control;
import ar.com.comunidadesfera.batallaespacial.Direccion;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.comandos.Atacar;
import ar.com.comunidadesfera.batallaespacial.comandos.Avanzar;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;
import ar.com.comunidadesfera.batallaespacial.comandos.TransferirCarga;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;
import ar.com.comunidadesfera.batallaespacial.piezas.Arma;
import ar.com.comunidadesfera.batallaespacial.piezas.ComandoAbstracto;

/**
 * Implementación de la Fábrica de Comandos para los Comandos de una 
 * Nave de Combate.
 *  
 * @author Mariano Tugnarelli
 *
 */
public class ControlNaveDeCombate implements Control {

    public static final int IMPULSO = 1;
    public static final int DISTANCIA_ATAQUE = 1;
    
    /**
     * Nave sobre la cual se ejecutan los Comandos creados.
     */
    private final NaveDeCombate nave;

    /**
     * Iterador utilizado para realizar operaciones.
     */
    private Tablero.Iterador iterador;
    
    /**
     * Crea el control para la Nave dada.
     * 
     * @param nave
     */
    public ControlNaveDeCombate(NaveDeCombate nave) {
        super();
        this.nave = nave;
    }

    /**
     * @see ControlAplicacion#avanzar(Direccion)
     */
    public Avanzar avanzar(Direccion direccion) {
        
        return new AvanzarNaveDeCombate(this, direccion, IMPULSO);
    }

    /**
     * @see ControlAplicacion#atacar(Direccion)
     */
    public Atacar atacar(Direccion direccion) {
        
        Arma  arma;
        
        int torpedos = this.getNave().getCantidadDeTorpedos();
        
        /* utiliza torpedos si dispone de los mismos, en caso contrario laser */
        if (torpedos > 0) {
            
            arma = Arma.TORPEDO_DE_FOTONES;
            
            torpedos--;
            
            /* actualiza la cuenta de torpedos */
            this.getNave().setCantidadDeTorpedos(torpedos);
            
        } else {
            
            arma = Arma.LASER;
        }
        
        return new AtacarNaveDeCombate(this, direccion, arma, DISTANCIA_ATAQUE);
    }

    /**
     * @see ControlAplicacion#transferirCarga(Direccion, Direccion, Sustancia, long)
     */
    public TransferirCarga transferirCarga(Direccion origen, Direccion destino,
                                           Sustancia sustancia, long carga) {

        return new TransferirCargaNaveDeCombate(this, origen, destino, sustancia, carga);
    }

    
    /**
     * @see ControlAplicacion#esperar()
     */
    public Comando esperar() {
        
        return ComandoAbstracto.ESPERAR;
    }

    /**
     * 
     * @return Nave sobre la cual se ejecutan los Comandos.
     */
    protected NaveDeCombate getNave() {
        return nave;
    }


    /**
     * 
     * @return Iterador sobre el Tablero utilizado por todas las operaciones.
     */
    protected Tablero.Iterador getIterador() {
        
        return this.iterador;
    }
    
    /**
     * 
     */
    protected void setIterador(Tablero.Iterador iterador) {
        
        this.iterador = iterador;
    }

}

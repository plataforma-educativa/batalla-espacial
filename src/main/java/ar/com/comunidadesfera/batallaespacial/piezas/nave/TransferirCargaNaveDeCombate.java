package ar.com.comunidadesfera.batallaespacial.piezas.nave;

import ar.com.comunidadesfera.batallaespacial.Direccion;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.comandos.TransferirCarga;
import ar.com.comunidadesfera.batallaespacial.juego.CasilleroInvalidoException;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero.Iterador;
import ar.com.comunidadesfera.batallaespacial.juego.escenarios.Escenario;
import ar.com.comunidadesfera.batallaespacial.juego.escenarios.TransferenciaNormal;
import ar.com.comunidadesfera.batallaespacial.piezas.TransporteDeSustancias;

public class TransferirCargaNaveDeCombate extends ComandoNaveDeCombate implements
        TransferirCarga {

    public static final int DISTANCIA = 1;
    
    /**
     * Localización de la Pieza de donde se extrae carga.
     */
    private Direccion origen;
    
    /**
     * Localización de la Pieza a donde se agrega carga.
     */
    private Direccion destino;
    
    /**
     * Sustancia a transferir.
     */
    private Sustancia sustancia;
    
    /**
     * Cantida de carga a transferir.
     */
    private long carga;
    
    protected TransferirCargaNaveDeCombate(ControlNaveDeCombate control, 
                              Direccion origen, Direccion destino,
                              Sustancia sustancia, long carga) {
        
        super(control);
        this.setDirecciones(origen, destino);
        this.setSustancia(sustancia);
        this.setCarga(carga);
    }

    /**
     * @return Sustancia a Transferir.
     */
    public Sustancia getSustancia() {

        return this.sustancia;
    }

    /**
     * @return cantidad de carga a Transferir.
     */
    public long getCarga() {

        return this.carga;
    }

    /**
     * @see TransferirCargaNaveDeCombate#getDireccionOrigen()
     */
    public Direccion getDireccionOrigen() {

        return this.origen;
    }

    /**
     * @see TransferirCargaNaveDeCombate#getDireccionDestino()
     */
    public Direccion getDireccionDestino() {

        return this.destino;
    }

    /**
     * @see Comando#ejecutar(Partida)
     */
    public void ejecutar(Partida partida) {

        super.ejecutar(partida);
        
        try {
            
            Iterador iterador = this.getIterador(partida);
            
            TransporteDeSustancias origen, destino;
            
            
            origen = (TransporteDeSustancias) iterador.move(this.getNave(), 
                                                            this.getDireccionOrigen(), 
                                                            DISTANCIA)
                                                      .get();
            
            destino = (TransporteDeSustancias) iterador.move(this.getNave(), 
                                                             this.getDireccionDestino(), 
                                                             DISTANCIA)
                                                       .get();
            
            /* verifica que ambas piezas sean de la misma Civilización */
            if (this.transferenciaValida(origen, destino)) {
            
                /* crea el escenario de transferencia */
                Escenario transferencia = new TransferenciaNormal(origen,
                                                                  this.getSustancia(),
                                                                  this.getCarga(),
                                                                  destino);
            
                /* ejecuta el escenario de transferencia */
                transferencia.ejecutar();
                
            } else {
                
                /* no se puede transferir entre diferentes Civilizaciones */
                // TODO reportar
            }
            
        } catch (ClassCastException error) {
            
            /* la otra Piezas no es un TransporteDeSustancia */
            // TODO reportar
            
        } catch (CasilleroInvalidoException error) {
            
            /* no se pudo realizar la transferencia */
            // TODO reportar
        }

    }

    protected void setCarga(long carga) {
        
        if (carga < 0) {
            
            throw new IllegalArgumentException("No se puede Transferir carga negativa");
        }
        
        this.carga = carga;
    }

    /**
     * @pre <code>orige</code> o <code>destino</code> debe ser ORIGEN.
     * @post asigna las Direcciones de origen y destino de la Transferencia. 
     * 
     * @param origen
     * @param destino
     */
    protected void setDirecciones(Direccion origen, Direccion destino) {
    
        /* una de las dos debe ser ORIGEN */
        if ( (! origen.equals(Direccion.ORIGEN)) &&
             (! destino.equals(Direccion.ORIGEN))) {
            
            throw new IllegalArgumentException("Se debe Transferir desde o hasta el ORIGEN");
        }
        
        this.origen = origen;
        this.destino = destino;
    }

    protected void setSustancia(Sustancia sustancia) {
        this.sustancia = sustancia;
    }

    
    protected boolean transferenciaValida(TransporteDeSustancias origen,
                                          TransporteDeSustancias destino) {
        
        boolean valida = false;
        
        /* se puede transferir si tienen la misma Civilización o 
         * es nula */
        if ((origen != null) && (destino != null)) {

            /* el destino o el origen debe ser la nave */
            if (origen.equals(this.getNave())) {
                
                valida = destino.getCivilizacion() == null ||
                         this.getNave().getCivilizacion().equals(destino.getCivilizacion());
                
            } else  if (destino.equals(this.getNave())){

                valida = origen.getCivilizacion() == null ||
                         this.getNave().getCivilizacion().equals(origen.getCivilizacion());
            } else {
                
                /* ninguno es la nave correspondiente */
            }
        }
        
        return valida;
    }
}

package ar.com.comunidadesfera.batallaespacial.piezas.nave;

import ar.com.comunidadesfera.batallaespacial.Direccion;
import ar.com.comunidadesfera.batallaespacial.comandos.Atacar;
import ar.com.comunidadesfera.batallaespacial.juego.CasilleroInvalidoException;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero.Iterador;
import ar.com.comunidadesfera.batallaespacial.juego.escenarios.AtaqueSimple;
import ar.com.comunidadesfera.batallaespacial.juego.escenarios.Escenario;
import ar.com.comunidadesfera.batallaespacial.piezas.Arma;

public class AtacarNaveDeCombate extends ComandoNaveDeCombate implements Atacar {

    /**
     * Dirección del Ataque.
     */   
    private Direccion direccion;
    
    /**
     * Arma utilizada.
     */
    private Arma arma;
    
    /**
     * Distancia de alcance del Ataque.
     */
    private int distancia;
    
    
    public AtacarNaveDeCombate(ControlNaveDeCombate control, Direccion direccion,
                               Arma arma, int distancia) {
    
        super(control);
        this.setDireccion(direccion);
        this.setArma(arma);
        this.setDistancia(distancia);
    }

    public Direccion getDireccion() {

        return this.direccion;
    }

    public void ejecutar(Partida partida) {

        super.ejecutar(partida);
        
        try {
            
            Iterador iterador = this.getIterador(partida);
            
            Pieza pieza = iterador.move(this.getNave(), 
                                        this.getDireccion(), 
                                        this.getDistancia())
                                  .get();
            
            
            Escenario ataque = new AtaqueSimple(this.getNave(), 
                                                this.getArma(),
                                                pieza);
            
            ataque.ejecutar();
            
        } catch (CasilleroInvalidoException error) {
            
            /* no se puede atacar fuera del Tablero */
        }

    }

    protected void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    protected Arma getArma() {
        return arma;
    }

    protected void setArma(Arma arma) {
        this.arma = arma;
    }

    protected int getDistancia() {
        return distancia;
    }

    protected void setDistancia(int distancia) {
        this.distancia = distancia;
    }

}

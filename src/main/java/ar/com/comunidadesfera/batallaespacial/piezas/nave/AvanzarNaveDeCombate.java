package ar.com.comunidadesfera.batallaespacial.piezas.nave;

import ar.com.comunidadesfera.batallaespacial.Direccion;
import ar.com.comunidadesfera.batallaespacial.comandos.Avanzar;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;
import ar.com.comunidadesfera.batallaespacial.juego.CasilleroInvalidoException;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;
import ar.com.comunidadesfera.batallaespacial.juego.escenarios.ChoqueDirecto;
import ar.com.comunidadesfera.batallaespacial.juego.escenarios.Escenario;

/**
 * 
 * 
 * @author Mariano Tugnarelli
 *
 */
public class AvanzarNaveDeCombate extends ComandoNaveDeCombate implements Avanzar, Cloneable {

    /**
     * Dirección en la que se pretende avanzar la Nave. 
     */
    private Direccion direccion;
 
    /**
     * Cantidad de casilleros que avanza.
     */
    private int impulso;
    
    /**
     * Crea el Avance en la dirección especificada.
     * @param control : ControlAplicacion de la Nave de Combate con la que está asociado. 
     * @param direccion
     * @param impulso
     */
    protected AvanzarNaveDeCombate(ControlNaveDeCombate control, 
                                   Direccion direccion, int impulso) {
        super(control);
        this.setDireccion(direccion);
        this.setImpulso(impulso);
    }

    /**
     * @see Avanzar#getDireccion()
     */
    public Direccion getDireccion() {
        return this.direccion;
    }

    /**
     * @see Avanzar#getImpulso()
     */
    public int getImpulso() {

        return this.impulso;
    }

    /**
     * Establece la dirección de Avance
     * @param direccion
     */
    private void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    /**
     * @see Comando#ejecutar(Partida)
     */
    public void ejecutar(Partida partida) {
        
        super.ejecutar(partida);
        
        try {
            
            /* obtiene un Iterador sobre el Tablero */
            Tablero.Iterador iterador = this.getIterador(partida);  
            
            iterador.move( this.getNave(), this.direccion, this.impulso );

            
            /* verifica si existe alguna Pieza en la posición a la que
             * se quiere avanzar */
            Pieza pieza = iterador.get();
            
            /* si no existe Pieza puede avanzar, en caso contrario
             * existe un choque */
            if (pieza == null) {
            
                partida.getTablero().colocarPieza(this.getNave(), iterador);

            } else {

                /* construye un escenario de Choque y lo ejecuta */
                Escenario choque = new ChoqueDirecto(this.getNave(), pieza);  
                choque.ejecutar();
            }
            
        } catch (CasilleroInvalidoException error) {
            
            /* choca contra los márgenes del tablero */
            Escenario choque = new ChoqueDirecto(this.getNave());
            choque.ejecutar();
        }
    }

    /**
     * @see Comando#invertir()
     */
    public AvanzarNaveDeCombate invertir() {
        
        /* copia el Comando e invierte su dirección */
        AvanzarNaveDeCombate avanzar = this.clone();
        avanzar.setDireccion(this.getDireccion().contraria());
        
        return avanzar;
    }
    
    /**
     * @see Object#clone() 
     */
    protected AvanzarNaveDeCombate clone() {
        
        AvanzarNaveDeCombate clon;
        
        try {
            clon = (AvanzarNaveDeCombate) super.clone();

        } catch ( CloneNotSupportedException error ) {
            
            clon = null;
        }
            
        return clon; 
    }

    private void setImpulso(int impulso) {
        this.impulso = impulso;
    }
}

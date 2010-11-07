package ar.com.comunidadesfera.batallaespacial.piezas.nave;

import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero.Casillero;
import ar.com.comunidadesfera.batallaespacial.piezas.base.PistaDeAterrizaje;

/**
 * Una Nave es un tipo particular de Pieza.
 *
 */
public abstract class Nave extends Pieza {

    public static final byte MAXIMO_NIVEL_ESCUDOS = 100;
    public static final byte MINIMO_NIVEL_ESCUDOS = 0;
    public static final int PUNTOS_MINIMO_ESCUDO = 1;
    
    /**
     * Cantidad de puntos inicialmente asignado a la Nave.
     * Determinará el nivel de los escudos.
     */
    private final int puntosIniciales; 

    /**
     * Pieza en la cual está la Nave, null en el caso de que 
     * haya despegado.
     */
    private PistaDeAterrizaje pista = null;
    
    /**
     * @pre ninguna.
     * @post crea la Nave con los puntos indicados.
     * 
     * @param puntos
     */
    public Nave(int puntos) {
        
        super(puntos);
        this.puntosIniciales = puntos;
    }

    /**
     * La Nave utiliza los puntos de la Pieza para 
     * mantener su escudo. 
     * 
     * @pre ninguna.
     * @post devuelve el nivel de los escudos de la Nave.
     * 
     * @return nivel de los escudos entre 0% y 100%. 
     */
    public byte getNivelDeEscudos() {
        
        return  this.getNivelDeEscudos(this.getPuntos(), this.getPuntosIniciales());
    }

    /**
     * @pre ninguna.
     * @post calcula el nivel de los escudos acotándolo entre
     *       los límites establecidos para los escudos.
     *       
     * @param puntos
     * @param puntosIniciales
     * @return
     */
    private byte getNivelDeEscudos(int puntos, int puntosIniciales) {

        byte nivel;
        
        if (puntos > puntosIniciales) {
            
            nivel = MAXIMO_NIVEL_ESCUDOS;
            
        } else if (puntos <= PUNTOS_MINIMO_ESCUDO) {
            
            nivel = MINIMO_NIVEL_ESCUDOS;
            
        } else {

            nivel = (byte) ((float) puntos / (float) puntosIniciales * 
            								 (float) ( MAXIMO_NIVEL_ESCUDOS -
            										   MINIMO_NIVEL_ESCUDOS )
            										 + MINIMO_NIVEL_ESCUDOS ); 
        }

        return nivel;
    }
    

    /**
     * @pre ninguna.
     * @post devuelve la cantidad de puntos asignados originalmente
     *       a la Nave.
     *       
     * @return cantidad de puntos.
     */
    protected int getPuntosIniciales() {
        return this.puntosIniciales;
    }

    public PistaDeAterrizaje getPista() {
        
        return pista;
    }

    public void setPista(PistaDeAterrizaje pista) {

        /* si antes estaba en una pista la retira */
        if (this.pista != null) {
            
            this.despegar();
        }

        this.pista = pista;
        
        /* si ahora está en una pista la agrega */
        if (this.pista != null) {
            
            this.aterizar();
        }
        
    }
    
    protected void despegar() {
        this.pista.retirarNave(this);
    }
    
    protected void aterizar() {
        this.pista.agregarNave(this);
    }
    
    /**
     * @see  Pieza#getCasillero()
     */
    public Casillero getCasillero() {
        
        return (this.pista != null) ? this.pista.getCasillero() : super.getCasillero();
        
    }
    
    /**
     * @see  Pieza#setCasillero(Casillero)
     */
    public void setCasillero(Casillero casillero) {
        
        /* la asignación de casilleros implica salir de la pista */
        if (this.pista != null) {

            this.setPista(null);
        }

        super.setCasillero(casillero);
    }
    
    
}

package ar.com.comunidadesfera.batallaespacial.juego;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ar.com.comunidadesfera.batallaespacial.Reporte;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero.Casillero;
import ar.com.comunidadesfera.batallaespacial.juego.escenarios.Escenario;

public abstract class Pieza implements Comparable<Pieza>, Cloneable {

    /**
     * Generador de factores aleatorios utilizado por las Piezas.
     */
    protected static final Random azar = new Random();
    
    /**
     * Número de Pieza.
     */
    private static int ultimoNumero = 0;
    
    /**
     * Número de la Pieza
     */
    private int numero;
    
    /**
     * Casillero que corresponde a la Pieza.
     */
    private Casillero casillero;

    /**
     * Cantidad de de puntos de vida de la Pieza.
     * 
     */
    private int puntos;
    
    /**
     * Observadores de la Pieza.
     */
    private List<Observador> observadores;

    /**
     * @pre ninguna.
     * @post crea la Pieza con los puntos indicados.
     */
    public Pieza(int puntos) {
        super();
        this.setPuntos(puntos);
        this.inicializar();
    }
    
    private void inicializar() {
        
        this.numero = ultimoNumero++;
    }
    
    /**
     * @pre ninguna.
     * @post asigna a la Pieza el Casillero correspondiente.  
     */
    protected void setCasillero(Casillero casillero) {
       
        this.casillero = casillero;

        /* notifica a los observadores */
        this.notificar();
    }
    
    /**
     * @pre ninguna.
     * @post devuelve la posición asignada al Casillero.
     * 
     * @return Casillero correspondiente a la Pieza.
     */
    public Casillero getCasillero() {
     
        return this.casillero;
    }
    
    
    /**
     * @pre ninguna.
     * @post agrega al Observador <code>observador</code> a la lista de objetos
     *       a ser notificados ante un cambio de la Pieza.
     *       El orden en que fueron agregados es el orden en que
     *       serán notificados. 
     * 
     * @param observador
     */
    public void agregarObservador(Observador observador) {

        if (this.observadores == null) {
            this.observadores = new LinkedList<Observador>();
        }
        
        if (! this.observadores.contains(observador)) {
            this.observadores.add(observador);
        }
    }
    
    /**
     * @pre ninguna.
     * @post retira al Observador <code>observador</code> de la lista
     *       de objetos a ser notificados ante un cambio de la Pieza.
     * 
     * @param observador
     */
    public void quitarObservador(Observador observador) {

        if (this.observadores != null) {
            
            this.observadores.remove(observador);
        }
    }

    /**
     * @pre ninguna.
     * @post devuelve la lista de Observadores de la Pieza.
     * 
     * @return lista de Observadores de la Pieza.
     */
    protected List<Observador> getObservadores() {

        return this.observadores;
    }
    
    /**
     * @pre la instancia ha cambiado su estado.
     * @post notifica a todos los Observadores.
     *
     */
    protected void notificar() {
        
        if (this.observadores != null) {
            
            /* notifica a todos los observadores en el orden en que
             * se encuentran en la lista, orden de inserción */
            for (Observador observador : this.observadores) {
                
                observador.cambio(this);
            }
        }
    }
    
    /**
     * @pre ninguna.
     * @post devuelve la cantidad de puntos que tiene la Pieza.
     * 
     * @return puntos de la Pieza.
     */
    public synchronized int getPuntos() {
        return puntos;
    }

    /**
     * @pre ninguna.
     * @post asigna <code>puntos</code> a la Pieza. 
     * 
     * @param puntos
     */
    protected void setPuntos(int puntos) {
     
        /* sincroniza sólo la asignación, porque la notificación
         * toma mucho tiempo */
        synchronized (this) {
            
            this.puntos = puntos;
        }
        
        this.notificar();
    }
    
 
    /**
     * Punto de extensión para las implementaciones de Pieza
     * que reaccionan de manera diferente a Escenarios distintos.
     * La implementación en Pieza ignora las características propias
     * del Escenario <code>escenario</code>. 
     *   
     * @pre ninguna.
     * @post los puntos de la Pieza son modificados en <code>delta</code>. 
     * 
     * @param delta : cambio en los puntos
     * @param escenario : Escenario en el que se provoca la modificación
     *                    de puntos.
     */
    public void modificarPuntos(int delta, Escenario escenario) {
        
        this.setPuntos(this.getPuntos() + delta);
    }
    
    
    /**
     * @pre ninguna
     * @post construye un Reporte de la Pieza que indica su situación.
     * 
     * @return Reporte de la Pieza.
     */
    public abstract Reporte reportar();

    /**
     * 
     * @return Número que identifica la Pieza.
     */
    public int getNumero() {
        return numero;
    }
    
    /**
     * 
     * @return cadena que identifica a la Pieza. 
     */
    public abstract String getIdentificacion();
    
    /**
     * @see Object#equals(java.lang.Object)
     */
    public boolean equals(Object otro) {
        
        boolean iguales = false;
        
        if ((otro != null) && (otro instanceof Pieza)) {
            
            iguales = this.numero == ((Pieza) otro).getNumero(); 
        }
        
        return iguales;
    }
    
    /**
     * @see Comparable#compareTo(T)
     */
    public int compareTo(Pieza pieza) {
        
        return this.numero == pieza.numero ? 0 :
               this.numero < pieza.numero ? -1 : 1;
    }
    
    /**
     * @see Object#clone() 
     */
    public Pieza clone() {

        Pieza clon = null;

        try {

            clon = (Pieza) super.clone();
            clon.inicializar();

        } catch(CloneNotSupportedException error) {

            error.printStackTrace();
        }

        return clon;
    }
    
    /**
     * @post modifica la Pieza de tal manera de que sus características
     *       cambian sutilmente, sin varia su escencia. 
     *       Utilizado para construir Partidas con un factor de azar.
     */
    public void distorsionar() {
        
    }

    /**
     * Pieza.Observador define la interfaz para los objetos que deben
     * ser notificados ante un cambio de una Pieza. 
     *
     */
    public interface Observador {
    
        /**
         * @pre se provocó un cambio en el estado de la Pieza 
         *       <code>pieza</code>
         * 
         * @param pieza : Pieza que ha cambiado de estado.
         */
        void cambio(Pieza pieza);
    }
}

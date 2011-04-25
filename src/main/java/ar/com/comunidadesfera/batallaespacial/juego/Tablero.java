package ar.com.comunidadesfera.batallaespacial.juego;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import ar.com.comunidadesfera.batallaespacial.Direccion;


/**
 * El Tablero mantiene el ordenamiento relativo de las Piezas en una Partida.
 * Cada Casillero está ocupado exclusivamente por una Pieza.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class Tablero implements Iterable<Pieza> {

    /**
     * Cantidad filas que posee el Tablero. 
     */
    private int filas;
    
    /**
     * Cantidad de columnas que posee el Tablero.
     */
    private int columnas;
    
    /**
     * Indica si las dimensiones del tablero se encuentran definidas o no.
     * Si no están definidas se pueden agregar Piezas en cualquir posición, 
     * en caso contrario no.
     */
    private boolean definido = false;

    /**
     * Asignaciones de Casilleros a Piezas.
     */
    private Map<Casillero,Pieza> casilleros; 

    
    /**
     * @pre <code>filas</code> y <code>columnas</code> son mayores a cero.
     * @post crea el Tablero con las dimensiones especificadas, dejándolo 
     *       definido. 
     * 
     * @param filas
     * @param columnas
     */
    public Tablero(int filas, int columnas) {
    
        this.casilleros = new TreeMap<Casillero,Pieza>();
        this.setFilas(filas);
        this.setColumnas(columnas);
        this.definir();
    }

    /**
     * @pre ninguna.
     * @post crea el Tablero sin dimensiones especificadas, no estando 
     *       definidas sus filas y columnas, pudiendo incorporar Piezas
     *       sin restricciones. 
     *
     */
    public Tablero() {
        
        this.filas = 1;
        this.columnas = 1;
        this.casilleros = new TreeMap<Casillero,Pieza>();        
    }

    /**
     * @pre ninguna.
     * @post cambia la condición del Tablero.
     * 
     * @param definido : indica si las dimensiones están fijas o no.
     */
    protected void setDefinido(boolean definido) {
        
        this.definido = definido;
    }
    
    /**
     * @pre ninguna.
     * @post devuelve true si las dimensiones del Tablero están 
     *       definidas, false en caso contrario.
     *       Si las dimensiones no están definidas, se pueden incorporar
     *       Piezas en cualquier Casillero. 
     *       
     * @return
     */
    public boolean estaDefinido() {
        return this.definido;
    }
    
    /**
     * @pre ninguna.
     * @post fija las dimensiones del tablero, impidiendo el posicionamiento
     *       de piezas fuera de las dimensiones definidas.
     *
     */
    public void definir() {
        this.setDefinido(true);
    }
    
    /**
     * @pre ninguna.
     * @post devuelve la cantidad de columnas que tiene el Tablero.
     *       
     * @return cantidad de columnas.
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * @pre ninguna.
     * @post cambia la cantidad de columnas que tiene el Tablero.
     * 
     * @param columnas
     */
    private void setColumnas(int columnas) {
        
        if (columnas < 0) {
            throw new IllegalArgumentException("Cantidad invalida de columnas");
        }
        
        /*
         * Esta operación es riegoza en el sentido que cambia el valor
         * de la clave que utiliza el TreeMap para manipular las entradas.
         * Funciona porque a todas las claves le aplica un desplazamiento
         * proporcional progresivo.
         * TODO Buscar otra alternativa de actualización. 
         *
         */
        for (Casillero casillero: this.casilleros.keySet()) {
            
            casillero.actualizarPosicionRelativa(this.columnas, columnas); 
        }
        
        this.columnas = columnas;
    }

    /**
     * @pre ninguna.
     * @post devuelve la cantidad de filas que tiene el Tablero.
     * 
     * @return cantidad de filas.
     */
    public int getFilas() {
        return filas;
    }

    /**
     * @pre ninguna.
     * @post cambia la cantidad de filas que tiene el Tablero.
     * @param filas
     */
    private void setFilas(int filas) {
        
        if (filas < 0) {
            throw new IllegalArgumentException("Cantidad invalida de filas");
        }
        
        this.filas = filas;
    }

    /**
     * @pre ninguna.
     * @post coloca la pieza en la posición del <code>iterador</code>. 
     * 
     * @throws CasilleroInvalidoException : si la posición del iterador 
     *         es inválida.
     */
    public void colocarPieza(Pieza pieza, Iterador iterador)  
        throws CasilleroInvalidoException {

        /* busca el casillero */
        Casillero casillero = this.liberarPieza(pieza);

        if (casillero != null) {

            /* si la pieza ya tiene un casillero definido lo remueve
             * y lo reacomoda */
            casillero.copiarPosicion(iterador.getCasillero());

        } else {

            /* crea un nuevo casillero para la Pieza */
            casillero = iterador.getCasillero().clone();
        }
        

        /* reacomoda el casillero */
        this.casilleros.put(casillero, pieza);

        /* en todos los casos setea el casillero para marcar el cambio */
        pieza.setCasillero(casillero);
    }

    /**
     * @pre ninguna.
     * @post retira la pieza del Tablero.
     * 
     * @param pieza
     */
    public void retirarPieza(Pieza pieza) {
        
        this.liberarPieza(pieza);
        
        pieza.setCasillero(null);
    }
    
    /**
     * @pre ninguna.
     * @post toma el Casillero en el que se encuentra localizada la 
     *       Pieza <code>pieza</code> y lo retira del conjunto de
     *       Casilleros del Tablero. 
     * nota: el Casillero puede ser cambiado de posición y reinsertado
     *       en el Tablero.
     *       
     * @param pieza
     * @return casillero en el que se encontraba la pieza.
     */
    private Casillero liberarPieza(Pieza pieza) {
        
        Casillero casillero = pieza.getCasillero();
        
        /* remueve la pieza si se encuentra en el Tablero asignado al casillero */
        if ((casillero != null) && 
            (this.casilleros.get(casillero).equals(pieza))) {
            
            this.casilleros.remove(casillero);
            
        } else {
            
            /* la pieza estaba compartiendo el casillero, y no estaba asignada
             * a la misma */
            casillero = null;
        }
        
        return casillero;
    }
    
    /**
     * @pre ninguna.
     * @post devuelve la cantidad de Casilleros que tiene el Tablero.
     * @return
     */
    public long getCantidadDeCasilleros() {
        
        return this.filas * this.columnas;
    }

    /**
     * 
     * @return Iterador sobre Casilleros
     */
    public Iterador iterator() {

        return new Tablero.Iterador();
    }
    
    
    /**
     * Un Casillero representa una posición en el Tablero asociado.
     *
     */
    public class Casillero implements Comparable<Casillero>, Cloneable {
        
        /**
         * Número de orden del Casillero en el Tablero.
         */
        private long numero;
        
        public Casillero() {
            this.numero = -1;
        }
        
        public Casillero(int fila, int columna) throws CasilleroInvalidoException {

            this.setPosicion(fila, columna);
        }
        
        /**
         * @return número de columna.
         */
        public int getColumna() {
            return (int) numero % Tablero.this.getColumnas();
        }

        /**
         * @return número de fila.
         */
        public int getFila() {
            return (int) numero / Tablero.this.getColumnas();
        }

        /**
         * @see java.util.Comparable#compareTo(Casillero)
         */
        public int compareTo(Casillero otro) {
            
            long thisNumero = this.numero;
            long otroNumero = otro.numero;
            return (thisNumero<otroNumero ? -1 : (thisNumero==otroNumero ? 0 : 1));
        }
        
        /**
         * 
         * @return Pieza que ocupa el Casillero
         */
        public Pieza getPieza() {
            
            return Tablero.this.casilleros.get(this);
        }

        /**
         * @pre ninguna.
         * @post cambia la posición de una Casilla, de acuerdo a las dimensiones
         *       del Tablero al que pertenece.
         * 
         * @param fila : número de fila.
         * @param columna : número de columna.
         * @throws CasilleroInvalidoException : si fila o columna es menor a 
         *         cero o, estando definidas las dimensiones del Tablero, Ã©stas
         *         las excedan.
         */
        private void setPosicion(int fila, int columna) 
            throws CasilleroInvalidoException {

            /* el número de dila y columna debe ser positivo */
            if ((fila < 0) || (columna < 0)) {

                throw new CasilleroInvalidoException(Tablero.this,
                                                     fila,columna);
                
            }
            
            /* si está definido el tablero verifica los rangos de filas
             * y columnas, en caso contrario los actuliza */
            if (Tablero.this.estaDefinido()) {

                if ((fila >= Tablero.this.getFilas()) || 
                    (columna >= Tablero.this.getColumnas())) {
                    
                    throw new CasilleroInvalidoException(Tablero.this,
                                                         fila,columna);
                }
                
            } else {
            
                this.actualizarDimensionesTablero(fila,columna);
            }

            this.numero = fila * Tablero.this.getColumnas() + columna; 
        }

        private void copiarPosicion(Casillero casillero) {
            
            this.numero = casillero.numero;
        }
        
        /**
         * @pre se a creado un Casillero para la posicion fila, columna.
         * @post actualiza las dimensiones  (filas y columnas) del Tablero
         *       asociado. 
         * 
         * @param fila
         * @param columna
         */
        private void actualizarDimensionesTablero(int fila, int columna) {

            /* ajusta los límites del Tablero dinámicamente */
            if (columna >= Tablero.this.getColumnas()) {
                Tablero.this.setColumnas(columna + 1);
            }

            if (fila >= Tablero.this.getFilas()) {
                Tablero.this.setFilas(fila + 1);
            }
        }
        

        /**
         * Ante un cambio en las dimensiones del tablero actualiza
         * la posición de la Pieza.
         * 
         * @param antes
         * @param despues
         */
        private void actualizarPosicionRelativa(int antes, int despues) {
            
                this.numero += ((despues - antes) * this.getFila());
        }
        
        public Casillero clone() {
            
            try {
                
                return (Casillero) super.clone();

            } catch (CloneNotSupportedException e) {
                
                throw new RuntimeException("Error no esperado al intentar clonar Casillero",
                                           e);
            }
        }
        
        @Override
        public boolean equals(Object otro) {
            
            boolean iguales = false;
            
            if ((otro != null) && (otro instanceof Casillero)) {
                
                iguales = this.compareTo((Casillero) otro) == 0;
            }
            return iguales;            
        }
        
        @Override
        public int hashCode() {
            return (int) this.numero;
        }
    }

 
    /**
     * Iterador sobre los Casilleros del Tablero.
     *
     */
    public class Iterador implements Iterator<Pieza> {

        /**
         * Casillero utilizado para iterar sobre los Casilleros del Tablero.
         */
        private Casillero casillero = new Casillero();
        
        private Iterador() {
            super();
        }
        
        public boolean hasNext() {

            return this.casillero.numero < ( Tablero.this.getCantidadDeCasilleros()  - 1);
        }

        /**
         * @return Pieza en el próximo casillero del Tablero.  
         * 
         */
        public Pieza next() {

            /* pasa al siguiente casillero */
            this.casillero.numero++;

            return this.get();
        }
        
        public void reset() {
            this.casillero.numero = 0;
        }
        
        public Pieza get() {
            return this.casillero.getPieza();
        }
        
        /**
         * @pre la nueva posición del Iterador se encuentra dentro del
         *       Tablero.
         * @post mueve el Iterador a la posición especificada por la <code>casilla</code>,
         *       desplazada <code>distancia</code> en la Dirección <code>direccion</code>.
         * 
         * @param casillero : posición de referencia. 
         * @param direccion : Dirección del movimiento.
         * @param distancia : distancia respecto del Casillero de referencia.
         * @throws CasilleroInvalidoException : si la nueva posición no existe
         *         en el Tablero.
         */
        public Iterador move(Pieza pieza, Direccion direccion, int distancia) 
            throws CasilleroInvalidoException {
            
            Casillero casillero = pieza.getCasillero();
            
            this.casillero.setPosicion(casillero.getFila() + direccion.getCoordenadaY() * distancia,
                                       casillero.getColumna() + direccion.getCoordenadaX() * distancia);
            
            return this;
        }
        
        /**
         * @pre la nueva posición del Iterador se encuentra dentro del
         *       Tablero.
         * @post mueve el Iterador a la posición especificada por <code>fila</code>,
         *       y <code>columna</code>.
         * 
         * @param fila 
         * @param columna
         * @throws CasilleroInvalidoException : si la nueva posición no existe
         *         en el Tablero.
         */
        public Iterador move(int fila, int columna) 
            throws CasilleroInvalidoException {
            
            this.casillero.setPosicion(fila, columna);
            
            return this;
        }
        
        /**
         * @throws UnsupportedOperationException no se pueden remover 
         *         Casilleros del Tablero
         */
        public void remove() {

            throw new UnsupportedOperationException("No se pueden remover Casilleros del Tablero");
            
        }
        
        /**
         * 
         * Coloca el iterador en una posición aleatoria dentro del Tablero.
         */
        public Iterador random() {
            
            try {
                
                this.casillero.setPosicion((int) (Math.random() * (Tablero.this.getFilas() - 1)),
                                           (int) (Math.random() * (Tablero.this.getColumnas() - 1)));
 
            } catch (CasilleroInvalidoException error) {
                
                error.printStackTrace();
            }
            
            return this;
        }
        
        private Casillero getCasillero() {
            return this.casillero;
        }
        
    }

}

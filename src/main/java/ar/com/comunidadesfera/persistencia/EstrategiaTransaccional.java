package ar.com.comunidadesfera.persistencia;


/**
 * Define las estrategias de manipulación del contexto transaccional en el que 
 * se ejecuta un método.
 *
 * @see http://download.oracle.com/javaee/5/api/javax/ejb/TransactionAttribute.html
 * @author Mariano Tugnarelli
 */
public enum EstrategiaTransaccional {

    /**
     * Transacción requerida. Si existe una transacción activa se une, sino
     * la inicia.
     */
    REQUERIDA,
    
    /**
     * Nueva transacción requerida. Inicia una nueva transacción.
     */
    NUEVA_REQUERIDA,
    
    /**
     * Transacción soportada. Si existe una transacción activa se une, 
     * sino continua sin transacción.
     */
    SOPORTADA,
    
    /**
     * Transacción no soportada. Si existe una transacción la suspende
     * y la reactiva al terminar el método.
     */
    NO_SOPORTADA
}

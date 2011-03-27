package ar.com.comunidadesfera.eficiencia;

public interface Contexto {

    /**
     * @post activa o desactiva la persistencia en los servicio de Eficiencia.
     *       
     * @param esPersistente
     */
    void setPersistente(boolean esPersistente);

    /**
     * @post indica si la persistencia en los servicios de Eficiencia está
     *       activada o desactivada.
     * 
     * @return
     */
    boolean isPersistente();

    /**
     * @pre <code>nombreModulo</code> identifica univocamente el módulo
     *       a medir.
     * @post inicia una nueva Ejecución para el módulo indicado sobre
     *       un problema del tamaño indicado.
     * 
     * @param nombreModulo identificador del módulo a medir.
     * @param tamaño tamaño del problema.
     * @return
     */
    Ejecucion iniciarEjecucion(String nombreModulo, long[] tamaño);

}

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
     * @pre  <code>modulo</code> identifica unívocamente el módulo a medir.
     *       <code>problema</code> identifica unívocamente el problema que el módulo resuelve.
     * @post inicia una nueva Ejecución para el módulo indicado sobre
     *       el problema del tamaño indicado.
     * 
     * @param modulo identificador del módulo a medir.
     * @param problema identificador del problema que se resuelve.
     * @param tamaño tamaño del problema.
     * @return
     */
    Ejecucion iniciarEjecucion(String modulo, String problema, long tamaño);
}

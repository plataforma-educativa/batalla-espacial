package ar.com.comunidadesfera.eficiencia;

import ar.com.comunidadesfera.eficiencia.contextos.ContextoBasico;

public class Eficiencia {

    private static Contexto contexto;
    
    /**
     * 
     * @return Contexto centralizado de Eficiencia. Permite configurar de manera
     *         centralizada la configuración de lo servicios de Eficiencia.
     */
    public synchronized static Contexto getContexto() {

        if (contexto == null) {
            
            contexto = new ContextoBasico();
        }
        
        return contexto;
    }

}

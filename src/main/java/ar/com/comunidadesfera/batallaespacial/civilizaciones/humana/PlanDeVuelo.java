package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana;

import ar.com.comunidadesfera.batallaespacial.comandos.Comando;

/**
 * Plan de Vuelo utilizado por los Pilotos Humanos para conducir su Nave.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface PlanDeVuelo {

    /**
     * @post devuelve el próximo comando que debe ejecutar el PilotoHumano
     *       <code>piloto</code>.
     */
    Comando proximoComando(PilotoHumano piloto);
    
    boolean estaAutorizado();
    
    void autorizar();
    
}

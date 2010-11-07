package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana;

import java.util.Queue;

import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes.Orden;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;

/**
 * Implementación de un Plan de Vuelo a partir de Ordenes impartidas por un
 * superior.
 * 
 * @author Mariano Tugnarelli
 * 
 */
public class PlanDeVueloComandado implements PlanDeVuelo {

    private boolean autorizado;

    private Queue<Orden> ordenes;

    public PlanDeVueloComandado(Queue<Orden> ordenes) {

        this.ordenes = ordenes;
        this.autorizado = false;
    }

    protected Queue<Orden> getOrdenes() {

        return this.ordenes;
    }

    public Comando proximoComando(PilotoHumano piloto) {

        Comando comando = null;

        if (this.estaAutorizado()) {

            Orden orden;
            
            do {
                
                orden = this.getOrdenes().poll();

                if (orden != null) {
                    
                    comando = orden.ejecutar(piloto);
                }
                
            } while ((orden != null) && (comando == null));

        }

        return comando != null ? 
                  comando : piloto.getCabinaDeControl().getControl().esperar();

    }

    public void autorizar() {

        this.autorizado = true;
    }

    public boolean estaAutorizado() {

        return this.autorizado;
    }
}

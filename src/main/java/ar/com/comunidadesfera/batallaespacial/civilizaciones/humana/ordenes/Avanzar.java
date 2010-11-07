package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes;

import ar.com.comunidadesfera.batallaespacial.Direccion;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.PilotoHumano;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;

/**
 * Orden para avanzar en la dirección indicada.
 *   
 * @author Mariano Tugnarelli
 * 
 */
public class Avanzar  implements Orden {

    /**
     * Dirección en la que debe avanzar.
     */
    private Direccion direccion;

    public Avanzar(Direccion direccion) {
        this.direccion = direccion;
    }
    
    private Direccion getDireccion() {
        
        return this.direccion;
    }
    
    public Comando ejecutar(PilotoHumano piloto) {

        return piloto.getCabinaDeControl().getControl()
                        .avanzar(this.getDireccion());
    }
}

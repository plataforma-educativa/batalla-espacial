package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes;

import ar.com.comunidadesfera.batallaespacial.Direccion;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.PilotoHumano;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;

/**
 * Orden para atacar en la dirección indicada.
 *  
 * @author Mariano Tugnarelli
 * 
 */
public class Atacar implements Orden {

    /**
     * Dirección en la que debe atacar.
     */
    private Direccion direccion;
    
    public Atacar(Direccion direccion) {
     
        this.direccion = direccion;
    }
    
    private Direccion getDireccion() {
        return this.direccion;
    }
    
    public Comando ejecutar(PilotoHumano piloto) {

        return piloto.getCabinaDeControl().getControl()
                        .atacar(this.getDireccion());
    }

}

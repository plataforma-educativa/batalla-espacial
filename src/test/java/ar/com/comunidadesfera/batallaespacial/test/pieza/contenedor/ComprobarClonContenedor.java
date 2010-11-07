package ar.com.comunidadesfera.batallaespacial.test.pieza.contenedor;

import org.hamcrest.Matchers;

import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.batallaespacial.test.pieza.ComprobarClonPieza;

public class ComprobarClonContenedor<T extends Contenedor> extends ComprobarClonPieza<T> {

    public ComprobarClonContenedor(String descripcion) {
        super(descripcion);
    }

    public void ejecutar(T pieza, T clon) {
        
        super.ejecutar(pieza, clon);
        
        this.procesar("nivel de carga", clon.getNivelDeCarga(), 
                      Matchers.equalTo(pieza.getNivelDeCarga()));
        
        for (Sustancia sustancia : Sustancia.values()) {
            
            this.procesar("cantidad de sustancia" + sustancia, 
                          clon.getCantidad(sustancia),
                          Matchers.equalTo(pieza.getCantidad(sustancia)));
            
            this.procesar("capacidad disponible" + sustancia, 
                          clon.getCapacidadDisponible(sustancia),
                          Matchers.equalTo(pieza.getCapacidadDisponible(sustancia)));
        }
    }
}

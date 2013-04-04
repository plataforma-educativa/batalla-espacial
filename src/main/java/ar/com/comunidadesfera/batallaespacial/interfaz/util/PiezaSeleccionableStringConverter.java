package ar.com.comunidadesfera.batallaespacial.interfaz.util;

import ar.com.comunidadesfera.batallaespacial.juego.Pieza;

public class PiezaSeleccionableStringConverter<T extends Pieza> 
    extends ValorSeleccionableStringConverter<T> {

    public String toStringValor(T pieza) {
        
        return pieza.getIdentificacion();
    }
    
}

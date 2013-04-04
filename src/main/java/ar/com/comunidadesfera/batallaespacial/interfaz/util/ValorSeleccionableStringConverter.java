package ar.com.comunidadesfera.batallaespacial.interfaz.util;

import javafx.util.StringConverter;

public abstract class ValorSeleccionableStringConverter<T> extends StringConverter<ValorSeleccionable<T>> {

    @Override
    public String toString(ValorSeleccionable<T> valorSeleccinable) {

        return this.toStringValor(valorSeleccinable.getValor());
    }

    public abstract String toStringValor(T valor);
    
    @Override
    public ValorSeleccionable<T> fromString(String valorSeleccinable) {
        
        return null;
    }

}

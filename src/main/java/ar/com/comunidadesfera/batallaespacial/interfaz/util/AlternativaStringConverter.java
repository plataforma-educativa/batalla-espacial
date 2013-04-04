package ar.com.comunidadesfera.batallaespacial.interfaz.util;

import javafx.util.StringConverter;
import ar.com.comunidadesfera.dependencias.Alternativa;

public class AlternativaStringConverter<T> extends StringConverter<Alternativa<T>> {
    
    @Override
    public String toString(Alternativa<T> valor) {

        return valor.getImplementacion().getSimpleName();
    }

    @Override
    public Alternativa<T> fromString(String valorString) {

        throw new UnsupportedOperationException("No se puede crear una Alternativa<T> a partir de un String");
    }

    public static <T> AlternativaStringConverter<T> crear() {
        
        return new AlternativaStringConverter<T>();
    }
}

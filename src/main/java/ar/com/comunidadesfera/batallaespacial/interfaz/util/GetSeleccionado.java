package ar.com.comunidadesfera.batallaespacial.interfaz.util;

import javafx.beans.value.ObservableValue;
import javafx.util.Callback;

public class GetSeleccionado<T> implements Callback<ValorSeleccionable<T>, ObservableValue<Boolean>> {

    @Override
    public ObservableValue<Boolean> call(ValorSeleccionable<T> parametro) {

        return parametro.seleccionadoProperty();
    }

}

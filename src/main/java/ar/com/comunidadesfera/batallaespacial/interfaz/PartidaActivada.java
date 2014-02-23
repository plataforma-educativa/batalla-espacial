package ar.com.comunidadesfera.batallaespacial.interfaz;

import javafx.beans.property.ReadOnlyBooleanPropertyBase;
import ar.com.comunidadesfera.batallaespacial.BatallaEspacial;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;

public class PartidaActivada extends ReadOnlyBooleanPropertyBase implements BatallaEspacial.Observador {

    
    private boolean valor = false;
    
    @Override
    public Object getBean() {

        return this;
    }

    @Override
    public String getName() {

        return "PartidaActivada";
    }

    @Override
    public boolean get() {
        return this.valor;
    }

    @Override
    public void iniciada(BatallaEspacial batallaEspacial) {
        
    }

    @Override
    public void jugando(BatallaEspacial batallaEspacial, Partida<? extends Participante> partida) {

        this.valor = true;
        this.fireValueChangedEvent();
    }
}
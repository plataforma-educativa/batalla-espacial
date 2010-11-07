package ar.com.comunidadesfera.batallaespacial.test.pieza;

import ar.com.comunidadesfera.batallaespacial.Reporte;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;

public class PiezaSimulada extends Pieza {

    public PiezaSimulada(int puntos) {
        super(puntos);
    }

    @Override
    public String getIdentificacion() {

        return "Pieza Simulada " + this.getNumero();
    }

    @Override
    public Reporte reportar() {

        return null;
    }

}

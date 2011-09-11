package ar.com.comunidadesfera.batallaespacial.civilizaciones.numenor;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.Comandante;


public class Numenor implements Civilizacion {

    public Numenor() {
        super();
    }

    public String getNombre() {
        return "Numenor";
    }

    public Comandante construirComandante() {

        return new CapitanNumenoreano(this);
    }

}

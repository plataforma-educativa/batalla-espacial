package ar.com.comunidadesfera.batallaespacial.civilizaciones.numenor;

import ar.com.comunidadesfera.batallaespacial.Comandante;
import ar.com.comunidadesfera.batallaespacial.Piloto;


public class CapitanNumenoreano implements Comandante {

    private Numenor civilizacion; 
    
    public CapitanNumenoreano(Numenor civilizacion) {
        super();

        this.civilizacion = civilizacion;
    }

    public Piloto[] construirEscuadron(int integrantes)
            throws IllegalArgumentException {

        PilotoNumenoreano[] pilotos = new PilotoNumenoreano[integrantes];
        
        for (int indice = 0; indice < pilotos.length; indice++) {
            
            pilotos[indice] = new PilotoNumenoreano(this.getCivilizacion());
        }
        
        return pilotos;
    }

    public Numenor getCivilizacion() {
        
        return this.civilizacion;
    }

    public String getNombre() {

        return "Capitar Numenoreano";
    }

}

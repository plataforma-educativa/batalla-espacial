package ar.com.comunidadesfera.batallaespacial.test.matchers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.Reporte;
import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;
import ar.com.comunidadesfera.batallaespacial.Sustancia;
import ar.com.comunidadesfera.batallaespacial.juego.Detectable;

public class ConfrontadorDeReporte<T extends Detectable> extends TypeSafeMatcher<Reporte> {

    private T detectable;
    
    private Matcher<? super Espectro> espectro;

    private Matcher<? super Civilizacion> civilizacion;
    
    private Map<Sustancia, Matcher<? super Long>> cantidadDeSustancia;
    
    public ConfrontadorDeReporte(T pieza) {

        this.detectable = pieza;
        this.cantidadDeSustancia = new HashMap<Sustancia, Matcher<? super Long>>();
    }

    @Override
    public void describeTo(Description description) {
        
        description.appendText("un reporte tal que");
        
        if (this.espectro != null) {
            
            description.appendText(" espectro ")
                       .appendDescriptionOf(this.espectro)
                       .appendText(",");
        }
        
        if (this.civilizacion != null) {
            
            description.appendText(" civilizacion ")
                       .appendDescriptionOf(this.civilizacion)
                       .appendText(",");
        }    

        Iterator<Sustancia> itSustancias = this.cantidadDeSustancia.keySet().iterator();
        while (itSustancias.hasNext()) {
            
            Sustancia sustancia = itSustancias.next();
            description.appendText(" cantidad de sustancia [" + sustancia + "] ")
                       .appendDescriptionOf(this.cantidadDeSustancia.get(sustancia))
                       .appendText(",");
        }
                    
    }

    @Override
    public boolean matchesSafely(Reporte reporte) {

        boolean matches = true;

        /* comprueba el espectro */
        if (matches && this.espectro != null) {

            matches = this.espectro.matches(reporte.getEspectro());
        }
        
        /* comprueba la civilizacion */
        if (matches  && this.civilizacion != null) {

            matches = this.civilizacion.matches(reporte.getCivilizacion());
        }

        /* comprueba las cantidades de sustancias */
        Iterator<Sustancia> itSustancias = this.cantidadDeSustancia.keySet().iterator();
        while (matches && itSustancias.hasNext()) {
            
            Sustancia sustancia = itSustancias.next();
            matches = this.cantidadDeSustancia.get(sustancia)
                            .matches(reporte.getCantidadDeSustancia(sustancia));
        }
        
        return matches;
    }
    
    public ConfrontadorDeReporte<T> setEspectro(Matcher<? super Espectro> espectro) {
        
        this.espectro = espectro;
        
        return this;
    }
    
    public ConfrontadorDeReporte<T> setCivilizacion(Matcher<? super Civilizacion> civilizacion) {
        
        this.civilizacion = civilizacion;
        
        return this;
    }
    
    public ConfrontadorDeReporte<T> setCantidadDeSustancia(Matcher<? super Long> cantidadDeSustancia,
                                                    Sustancia... sustancias) {

        for (Sustancia sustancia : sustancias) {

            this.cantidadDeSustancia.put(sustancia, cantidadDeSustancia);
        }

        return this;
    }
    
    public T getDetectable() {
        
        return detectable;
    }
}

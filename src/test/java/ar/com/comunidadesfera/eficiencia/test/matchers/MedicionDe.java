package ar.com.comunidadesfera.eficiencia.test.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Problema;

public class MedicionDe extends TypeSafeMatcher<Medicion> {

    private Matcher<Problema> problema;
    
    private Matcher<Modulo> modulo;
    
    public MedicionDe(Modulo modulo, Problema problema) {
    
        this.problema = Matchers.is(problema);
        this.modulo = Matchers.is(modulo);
    }
    
    @Override
    public void describeTo(Description description) {

        description.appendText("módulo ")
                   .appendDescriptionOf(this.modulo)
                   .appendText(", problema ")
                   .appendDescriptionOf(this.problema);
    }

    @Override
    public boolean matchesSafely(Medicion medicion) {
        
        return this.problema.matches(medicion.getProblema()) &&
               this.modulo.matches(medicion.getModulo());
    }

}

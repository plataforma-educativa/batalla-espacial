package ar.com.comunidadesfera.eficiencia.test.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class EstaEntre<T extends Comparable<T>> extends TypeSafeMatcher<T> {
    
    private T inferior;

    private T superior;

    public EstaEntre(T inferior, T superior) {
        super();
        if (inferior.compareTo(superior) > 0) {
            
            throw new IllegalArgumentException(inferior + " y " + superior + " no definen un intervalo");
        }
        
        this.inferior = inferior;
        this.superior = superior;
    }

    @Override
    public void describeTo(Description description) {
    
        description.appendText("esta entre")
                   .appendValue(this.inferior)
                   .appendText(" y ")
                   .appendValue(this.superior);
    }

    @Override
    public boolean matchesSafely(T valor) {
        return (this.inferior.compareTo(valor) <= 0) &&
               (this.superior.compareTo(valor) >= 0);
    }

}

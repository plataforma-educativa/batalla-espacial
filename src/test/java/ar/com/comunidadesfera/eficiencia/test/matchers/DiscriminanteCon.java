package ar.com.comunidadesfera.eficiencia.test.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

import ar.com.comunidadesfera.eficiencia.registros.Discriminante;

public class DiscriminanteCon extends TypeSafeMatcher<Discriminante>{

    private Matcher<String> nombre;
    
    private Matcher<Discriminante> entorno;
    
    public DiscriminanteCon(String nombre, Discriminante entorno) {
        super();
        this.nombre = Matchers.is(nombre);
        
        if (entorno == null) {
            this.entorno = Matchers.nullValue(Discriminante.class);
            
        } else {
            this.entorno = Matchers.is(entorno);
        }
    }

    @Override
    public void describeTo(Description description) {

        description.appendText("nombre ")
                   .appendDescriptionOf(this.nombre)
                   .appendText(", entorno ")
                   .appendDescriptionOf(this.entorno);
    }

    @Override
    public boolean matchesSafely(Discriminante discriminante) {

        return this.nombre.matches(discriminante.getNombre()) &&
               this.entorno.matches(discriminante.getEntorno());
    }

}

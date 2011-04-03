package ar.com.comunidadesfera.eficiencia.test.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

import ar.com.comunidadesfera.eficiencia.registros.Medida;
import ar.com.comunidadesfera.eficiencia.registros.Unidad;

public class MedidaCon extends TypeSafeMatcher<Medida> {

    private Matcher<Long> magnitud;
    private Matcher<Unidad> unidad;

    public MedidaCon(long magnitud, Unidad unidad) {
        
        this.magnitud = Matchers.is(magnitud);
        this.unidad = Matchers.is(unidad);
    }
    
    @Override
    public void describeTo(Description description) {

        description.appendText("magnitud ")
                   .appendDescriptionOf(this.magnitud)
                   .appendText(", unidad ")
                   .appendDescriptionOf(this.unidad);
    }

    @Override
    public boolean matchesSafely(Medida medida) {

        return this.magnitud.matches(medida.getMagnitud()) &&
               this.unidad.matches(medida.getUnidad());
    }

}

package ar.com.comunidadesfera.batallaespacial.test.pieza;

import org.hamcrest.Matcher;
import org.junit.Assert;

public abstract class ComprobarClonBasico<E> implements ComprobarClon<E>{

    private String descripcion;
    
    public ComprobarClonBasico(String descripcion) {
        this.descripcion = descripcion;
    }
    
    protected <T> void procesar(String atributo, T valor, 
                                Matcher<T> matcher) {

        Assert.assertThat(this.describir(atributo),
                          valor,
                          matcher);
    }

    protected <T> void procesar(ComprobarClon<T> comprobar, T entidad, T clon) {
        
        comprobar.ejecutar(entidad, clon);
    }
    
    
    protected String describir(String atributo) {
        
        return this.getDescripcion() + "." + atributo;
    }
    
    public String getDescripcion() {
     
        return descripcion;
    }
}

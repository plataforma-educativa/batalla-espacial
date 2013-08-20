package ar.com.comunidadesfera.eficiencia.test.matchers;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import ar.com.comunidadesfera.eficiencia.reportes.ItemReporte;

public class ItemReporteCon extends TypeSafeMatcher<ItemReporte<?>> {

    private Matcher<? extends Number> valor;

    private Matcher<?> objeto;
    
    private Matcher<?> clasificador;
    
    public ItemReporteCon() {

    }
    
    public ItemReporteCon valor(Number valor) {
        
        this.valor = is(equalTo(valor));
        
        return this;
    }
    
    public ItemReporteCon valor(Matcher<? extends Number> valor) {
        
        this.valor = valor;
        
        return this;
    }
    
    public ItemReporteCon objeto(Object objeto) {
        
        this.objeto = is(equalTo(objeto));
        
        return this;
    }
    
    public ItemReporteCon clasificador(Object clasificador) {
        
        this.clasificador = is(equalTo(clasificador));
        
        return this;
    }
    
    @Override
    public void describeTo(Description description) {

        description.appendText("item reporte");
        
        if (this.valor != null) {
            
            description.appendText(", valor ").appendDescriptionOf(this.valor);
        }
        
        if (this.objeto != null) {
            
            description.appendText(", objeto ").appendDescriptionOf(this.objeto);
        }
        
        if (this.clasificador != null) {
            
            description.appendText(", clasificador ").appendDescriptionOf(this.clasificador);
        }
    }
    

    @Override
    protected boolean matchesSafely(ItemReporte<?> item) {

        return (this.valor == null || this.valor.matches(item.getValor())) &&
               (this.objeto == null || this.objeto.matches(item.getObjeto())) &&
               (this.clasificador == null || this.clasificador.matches(item.getClasificador()));
    }

    @Override
    protected void describeMismatchSafely(ItemReporte<?> item, Description mismatchDescription) {

        mismatchDescription.appendText("item reporte");
        
        if (this.valor != null && ! this.valor.matches(item.getValor())) {
            
            mismatchDescription.appendText(", valor ");
            this.valor.describeMismatch(item.getValor(), mismatchDescription);
        }
        
        if (this.objeto != null && ! this.objeto.matches(item.getObjeto())) {
            
            mismatchDescription.appendText(", objeto ");
            this.objeto.describeMismatch(item.getObjeto(), mismatchDescription);
        }
        
        if (this.clasificador != null && ! this.clasificador.matches(item.getClasificador())) {
            
            mismatchDescription.appendText(", clasificador ");
            this.clasificador.describeMismatch(item.getClasificador(), mismatchDescription);
        }
    }
}


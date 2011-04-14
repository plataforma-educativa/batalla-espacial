package ar.com.comunidadesfera.eficiencia.test;

import org.hamcrest.Matcher;
import org.junit.Before;

import ar.com.comunidadesfera.eficiencia.Contexto;
import ar.com.comunidadesfera.eficiencia.Eficiencia;
import ar.com.comunidadesfera.eficiencia.registros.Discriminante;
import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Medida;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Problema;
import ar.com.comunidadesfera.eficiencia.registros.Unidad;
import ar.com.comunidadesfera.eficiencia.test.matchers.DiscriminanteCon;
import ar.com.comunidadesfera.eficiencia.test.matchers.EstaEntre;
import ar.com.comunidadesfera.eficiencia.test.matchers.MedicionDe;
import ar.com.comunidadesfera.eficiencia.test.matchers.MedidaCon;

public abstract class TestBasico {

    protected Contexto contexto;

    public TestBasico() {
        super();
    }

    @Before
    public void init() {
        
        this.contexto = Eficiencia.getContexto();
    }

    
    public Matcher<Medida> medidaCon(long magnitud, Unidad unidad) {
        return new MedidaCon(magnitud, unidad);
    }
    
    public Matcher<Medicion> medicionDe(Modulo modulo, Problema problema) {
        return new MedicionDe(modulo, problema);
    }
    
    public Matcher<Discriminante> discriminanteCon(String nombre, 
                                                   Discriminante entorno) {
     
        return new DiscriminanteCon(nombre, entorno);
    }
    
    public <T extends Comparable<T>> Matcher<T> estaEntre(T inferior, T superior) {
        
        return new EstaEntre<T>(inferior, superior);
    }
}
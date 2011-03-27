package ar.com.comunidadesfera.eficiencia.test;

import org.junit.Before;

import ar.com.comunidadesfera.eficiencia.Contexto;
import ar.com.comunidadesfera.eficiencia.Eficiencia;

public abstract class TestBasico {

    protected Contexto contexto;

    public TestBasico() {
        super();
    }

    @Before
    public void init() {
        
        this.contexto = Eficiencia.getContexto();
    }

}
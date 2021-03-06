package ar.com.comunidadesfera.eficiencia.test;

import java.util.Date;

import org.hamcrest.Matcher;
import org.junit.Before;

import ar.com.comunidadesfera.eficiencia.Contexto;
import ar.com.comunidadesfera.eficiencia.Eficiencia;
import ar.com.comunidadesfera.eficiencia.registros.Categoria;
import ar.com.comunidadesfera.eficiencia.registros.Discriminante;
import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Medida;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Problema;
import ar.com.comunidadesfera.eficiencia.registros.Unidad;
import ar.com.comunidadesfera.eficiencia.test.matchers.DiscriminanteCon;
import ar.com.comunidadesfera.eficiencia.test.matchers.EstaEntre;
import ar.com.comunidadesfera.eficiencia.test.matchers.ItemReporteCon;
import ar.com.comunidadesfera.eficiencia.test.matchers.MedicionDe;
import ar.com.comunidadesfera.eficiencia.test.matchers.MedidaCon;

public abstract class TestBasico {

    protected Contexto contexto;

    public TestBasico() {
        super();
    }
    
    @Before
    public void inicializarContexto() {
        
        this.contexto = Eficiencia.getContexto();
    }


    protected void esperar(int milisegundos) {

        try {
            
            Thread.sleep(milisegundos);
        
        } catch (InterruptedException ie) {

            throw new RuntimeException(ie);
        }
    }
    
    protected Date registrarTiempo() {
        
        Date marca;
        
        /* asegura que la marca temporal sea �nica, esperando al menos un milisegundo antes
         * y despu�s de obtener un timestamp */
        this.esperar(1);
        marca = new Date();
        this.esperar(1);
            
        return marca;
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
    
    public ItemReporteCon itemReporteCon() {
        
        return new ItemReporteCon();
    }

    protected Medicion medicionConId(Long id) {
    
        Medicion medicion = new Medicion();
        medicion.setId(id);
        
        return medicion;
    }

    protected Modulo moduloConId(Long id) {
        
        Modulo modulo = new Modulo();
        modulo.setId(id);
        
        return modulo;
    }

    protected Categoria categoriaConId(Long id) {
        
        Categoria categoria = new Categoria();
        categoria.setId(id);
        
        return categoria;
    }
}
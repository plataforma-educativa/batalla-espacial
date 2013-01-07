package ar.com.comunidadesfera.batallaespacial.test.config;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ar.com.comunidadesfera.batallaespacial.aplicacion.ParserDeTableros;
import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.FabricaDePiezas;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;
import ar.com.comunidadesfera.batallaespacial.piezas.asteroide.Asteroide;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.NaveDeCombate;

public class ParserDeTablerosTest {

    private ParserDeTableros parser;
    
    private FabricaDePiezas fabricaDePiezas;
    
    private ByteArrayOutputStream origenByteArray;
    private PrintStream origenPrinter;

    private Answer<Asteroide> crearPiezaAsteroide = new Answer<Asteroide>() {

        @Override
        public Asteroide answer(InvocationOnMock invocation) throws Throwable {

            return new Asteroide(100);
        }
    };
    
    private Answer<Contenedor> crearPiezaContenedor = new Answer<Contenedor>() {

        @Override
        public Contenedor answer(InvocationOnMock invocation) throws Throwable {

            return new Contenedor(100, 100);
        }
    };

    private Answer<BaseEspacial> crearBaseEspacial = new Answer<BaseEspacial>() {

        @Override
        public BaseEspacial answer(InvocationOnMock invocation) throws Throwable {

            return new BaseEspacial(100);
        }
    };
    
    private Answer<NaveDeCombate> crearNaveDeCombate = new Answer<NaveDeCombate>() {

        @Override
        public NaveDeCombate answer(InvocationOnMock invocation) throws Throwable {

            return new NaveDeCombate(100);
        }
    };
    
    
    @Before
    public void inicializar() {

        this.fabricaDePiezas = mock(FabricaDePiezas.class);
        when(this.fabricaDePiezas.crearBaseEspacial()).then(this.crearBaseEspacial);
        when(this.fabricaDePiezas.crearNaveDeCombate()).then(this.crearNaveDeCombate);
        when(this.fabricaDePiezas.crearPieza("#")).then(this.crearPiezaAsteroide);
        when(this.fabricaDePiezas.crearPieza("@")).then(this.crearPiezaContenedor);
        
        this.parser = new ParserDeTableros();
        this.parser.setFabricaDePiezas(this.fabricaDePiezas);
        
        this.origenByteArray = new ByteArrayOutputStream();
        this.origenPrinter = new PrintStream(this.origenByteArray);
    }
    
    @Test
    public void crearTablero() {
        
        Configuracion<Participante> configuracion = new Configuracion<Participante>();

        fila("####   ");
        fila("      #");
        fila("@  @ ##");
        
        Tablero tablero = this.parser.crearTablero(configuracion, origen());

        assertThat("tablero", tablero, notNullValue());
        assertThat("tablero esta definido", tablero.estaDefinido(), is(true));
        assertThat("filas del tablero", tablero.getFilas(), is(3));
        assertThat("columnas del tablero", tablero.getColumnas(), is(7));
        
        Tablero.Iterador iterador = tablero.iterator();
        
        assertPieza(iterador.next(), 0, 0, Asteroide.class);
        assertPieza(iterador.next(), 0, 1, Asteroide.class);
        assertPieza(iterador.next(), 0, 2, Asteroide.class);
        assertPieza(iterador.next(), 0, 3, Asteroide.class);
        assertPiezaNula(iterador.next());
        assertPiezaNula(iterador.next());
        assertPiezaNula(iterador.next());
        
        assertPiezaNula(iterador.next());
        assertPiezaNula(iterador.next());
        assertPiezaNula(iterador.next());
        assertPiezaNula(iterador.next());
        assertPiezaNula(iterador.next());
        assertPiezaNula(iterador.next());
        assertPieza(iterador.next(), 1, 6, Asteroide.class);
        
        assertPieza(iterador.next(), 2, 0, Contenedor.class);
        assertPiezaNula(iterador.next());
        assertPiezaNula(iterador.next());
        assertPieza(iterador.next(), 2, 3, Contenedor.class);
        assertPiezaNula(iterador.next());
        assertPieza(iterador.next(), 2, 5, Asteroide.class);
        assertPieza(iterador.next(), 2, 6, Asteroide.class);
    }

    @Test
    public void crearTableroVacio() {
        
        Configuracion<Participante> configuracion = new Configuracion<Participante>();

        Tablero tablero = this.parser.crearTablero(configuracion, origen());

        assertThat("tablero", tablero, notNullValue());
        assertThat("tablero esta definido", tablero.estaDefinido(), is(true));
        assertThat("filas del tablero", tablero.getFilas(), is(1));
        assertThat("columnas del tablero", tablero.getColumnas(), is(1));
        
        Tablero.Iterador iterador = tablero.iterator();
        assertPiezaNula(iterador.next());
    }
    
    private void assertPiezaNula(Pieza pieza) {
        
        assertThat("pieza", pieza, nullValue());
    }
    
    private void assertPieza(Pieza pieza, int fila, int columna, Class<? extends Pieza> clase) {

        assertThat("fila de la pieza", pieza.getCasillero().getFila(), is(fila));
        assertThat("columna de la pieza", pieza.getCasillero().getColumna(), is(columna));
        assertThat("tipo de pieza [" + fila + "," + columna + "]", pieza, is(clase));
    }

    private InputStream origen() {
        
        this.origenPrinter.flush();
        byte[] origen = this.origenByteArray.toByteArray();
        return new ByteArrayInputStream(origen);
    }
    
    private void fila(String fila) {
        
        this.origenPrinter.println(fila);
    }
}

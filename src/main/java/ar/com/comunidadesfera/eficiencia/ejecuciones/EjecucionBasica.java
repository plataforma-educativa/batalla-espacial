package ar.com.comunidadesfera.eficiencia.ejecuciones;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.InstrumentoDeMedicion;
import ar.com.comunidadesfera.eficiencia.excepciones.EjecucionTerminadaException;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;
import ar.com.comunidadesfera.eficiencia.instrumentos.ContadorBasico;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Problema;
import ar.com.comunidadesfera.eficiencia.registros.RegistroDeEjecucion;

public class EjecucionBasica implements Ejecucion {

    private Modulo modulo;
    
    private Problema problema;
    
    private Contador contador = null;
    
    private RegistroDeEjecucion registro = null;
    
    private boolean terminada = false;
    
    private List<Observador> observadores;

    public EjecucionBasica(String modulo, String problema, long tamaño) {

        this.modulo = new Modulo(modulo);
        this.problema = new Problema(problema);
        this.observadores = new LinkedList<Ejecucion.Observador>();
        this.registro = new RegistroDeEjecucion();
        this.registro.setInicio(new Date());
        this.registro.setDimension(tamaño);
        this.registro.setProblema(this.problema);
        this.registro.setModulo(this.modulo);
    }

    @Override
    public Contador contarInstrucciones() {

        if (this.terminada) {
            
            throw new EjecucionTerminadaException(this, "No es posible contar instrucciones");
        }
        
        if (this.contador == null) {
            
            this.contador = new ContadorBasico(this);
            this.notificarInstrumentoDeMedicionCreado(this.contador);
        }

        return this.contador;
    }
    
    @Override
    public Modulo getModulo() {
        
        return this.modulo;
    }

    @Override
    public Problema getProblema() {
        return this.problema;
    }
    
    @Override
    public long getDimension() {
     
        return this.getRegistro().getDimension();
    }

    @Override
    public RegistroDeEjecucion getRegistro() {

        return this.registro;
    }

    @Override
    public void terminar() {

        if (this.terminada) {
            
            throw new EjecucionTerminadaException(this);
        }
        
        this.terminada = true;
        this.getRegistro().setFin(new Date());
        
        this.notificarEjecucionTerminada();
    }

    @Override
    public void agregarObservador(Observador observador) {

        this.observadores.add(observador);
    }

    @Override
    public void removerObservador(Observador observador) {
        
        this.observadores.remove(observador);
    }
    
    private void notificarEjecucionTerminada() {
        
        for (Observador observador : this.observadores) {
            
            observador.ejecucionTerminada(this);
        }
    }
    
    private void notificarInstrumentoDeMedicionCreado(InstrumentoDeMedicion instrumento) {
        
        for (Observador observador : this.observadores) {
            
            observador.instrumentoDeMedicionCreado(this, instrumento);
        }
    }

}

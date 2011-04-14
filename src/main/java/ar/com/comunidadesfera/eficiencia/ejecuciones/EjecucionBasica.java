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

public class EjecucionBasica implements Ejecucion {

    private Modulo modulo;
    
    private Problema problema;
    
    private Contador contador = null;
    
    private boolean terminada = false;
    
    private List<Observador> observadores;

    public EjecucionBasica(String nombreModulo, long[] tamaño) {
        
        this.modulo = new Modulo(nombreModulo);
        this.problema = new Problema(tamaño);
        this.problema.setInicio(new Date());
        this.observadores = new LinkedList<Ejecucion.Observador>();
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
    public void terminar() {

        if (this.terminada) {
            
            throw new EjecucionTerminadaException(this);
        }
        
        this.terminada = true;
        this.getProblema().setFin(new Date());
        
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

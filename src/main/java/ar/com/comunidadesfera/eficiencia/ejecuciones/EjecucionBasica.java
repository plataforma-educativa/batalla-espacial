package ar.com.comunidadesfera.eficiencia.ejecuciones;

import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;
import ar.com.comunidadesfera.eficiencia.instrumentos.ContadorBasico;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Problema;

public class EjecucionBasica implements Ejecucion {

    private Modulo modulo;
    
    private Problema problema;
    
    private Contador contador = null;

    public EjecucionBasica(String nombreModulo, long[] tamaño) {
        
        this.modulo = new Modulo(nombreModulo);
        this.problema = new Problema(tamaño);
    }

    @Override
    public Contador contarInstrucciones() {

        if (this.contador == null) {
            
            this.contador = new ContadorBasico(this);
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

}

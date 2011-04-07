package ar.com.comunidadesfera.eficiencia.instrumentos;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.registros.Categoria;
import ar.com.comunidadesfera.eficiencia.registros.Discriminante;
import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Medida;
import ar.com.comunidadesfera.eficiencia.registros.Unidad;

public class ContadorBasico implements Contador {

    private Ejecucion ejecucion;
    
    private long cuenta;
    
    private Discriminante discriminante;

    private Map<String, ContadorBasico> parciales;
    
    /**
     * Inicializa el Contador para todo el Módulo de la Ejecución dada.
     * El Discriminante del Contador resulta ser todo el Módulo.
     * 
     * @param ejecucion
     */
    public ContadorBasico(Ejecucion ejecucion) {
        
        this(ejecucion, ejecucion.getModulo());
    }
    
    /**
     * Inicializa el Contador para el Discriminante indicado, perteneciente
     * al Módulo de la Ejecución dada.
     * 
     * @param ejecucion
     * @param discriminante
     */
    protected ContadorBasico(Ejecucion ejecucion, Discriminante discriminante) {
        
        this.ejecucion = ejecucion;
        this.discriminante = discriminante;
        this.parciales = new TreeMap<String, ContadorBasico>();
        this.cuenta = 0;
    }
    
    @Override
    public void incrementar() {
        
        this.incrementar(1);
    }

    @Override
    public long getValor() {

        long total = this.cuenta;
        
        for (Contador parcial : this.parciales.values()) {
            
            total += parcial.getValor();
        }
        
        return total;
    }

    @Override
    public Ejecucion getEjecucion() {

        return this.ejecucion;
    }
    
    @Override
    public void incrementar(long incremento) {

        this.cuenta += incremento;
    }

    @Override
    public Contador getParcial(String discriminante) {

        ContadorBasico contador = this.parciales.get(discriminante);
        
        if (contador == null) {
            
            Discriminante categoria = new Categoria(discriminante, 
                                                    this.getEjecucion().getModulo(),
                                                    this.getDiscriminante());
            
            contador = new ContadorBasico(this.getEjecucion(), 
                                          categoria);
            
            this.parciales.put(discriminante, contador);
        }
        
        return contador;
    }

    @Override
    public Discriminante getDiscriminante() {

        return this.discriminante;
    }

    public long getCuenta() {
        return cuenta;
    }

    @Override
    public Medicion getMedicion() {
        
        // TODO parametrizar las Unidades 
        return new Medicion(this.ejecucion.getProblema(), 
                            this.ejecucion.getModulo(),
                            this.getDiscriminante(),
                            new Medida(this.getValor(), Unidad.INSTRUCCIONES));
    }

    @Override
    public List<Medicion> getMediciones() {

        List<Medicion> mediciones = new LinkedList<Medicion>();
        
        this.agregarMediciones(mediciones);
        
        return mediciones;
    }
    
    protected void agregarMediciones(List<Medicion> mediciones) {
        
        mediciones.add(this.getMedicion());
        
        for (ContadorBasico contador : this.parciales.values()) {
            
            contador.agregarMediciones(mediciones);
        }
    }
}

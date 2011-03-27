package ar.com.comunidadesfera.eficiencia.instrumentos;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import ar.com.comunidadesfera.eficiencia.Ejecucion;

public class ContadorBasico implements Contador {

    private Ejecucion ejecucion;
    
    private long cuenta;
    
    private String discriminante;

    private Map<String, Contador> parciales;
    
    protected ContadorBasico(Ejecucion ejecucion, String discriminante) {
        
        this.ejecucion = ejecucion;
        this.cuenta = 0;
        this.discriminante = discriminante;
        this.parciales = new TreeMap<String, Contador>();
    }

    public ContadorBasico(Ejecucion ejecucion) {
        
        this(ejecucion, null);
    }
    
    protected ContadorBasico() {
        
        this(null, null);
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

    public void setEjecucion(Ejecucion ejecucion) {
        
        this.ejecucion = ejecucion;
    }
    
    @Override
    public void incrementar(long incremento) {

        this.cuenta += incremento;
    }

    @Override
    public Contador getParcial(String discriminante) {

        Contador contador = this.parciales.get(discriminante);
        
        if (contador == null) {
            
            contador = new ContadorBasico(this.getEjecucion(), discriminante);
            this.parciales.put(discriminante, contador);
        }
        
        return contador;
    }

    /**
     * @return mapa con los Contadores parciales asociados.
     */
    public Collection<Contador>  getParciales() {
        
        return this.parciales.values();
    }
    
    public void setParciales(Set<Contador> parciales) {
        
        if (parciales != null) {
            
            for (Contador parcial : parciales) {
                
                this.parciales.put(parcial.getDiscriminante(), parcial);
            }
        }
    }

    @Override
    public String getDiscriminante() {

        return this.discriminante;
    }
    
    public void setDiscriminante(String discriminante) {
        
        this.discriminante = discriminante;
    }

    public long getCuenta() {
        return cuenta;
    }

    public void setCuenta(long cuenta) {
        this.cuenta = cuenta;
    }
}

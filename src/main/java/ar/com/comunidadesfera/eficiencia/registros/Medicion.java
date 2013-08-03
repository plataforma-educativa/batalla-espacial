package ar.com.comunidadesfera.eficiencia.registros;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import ar.com.comunidadesfera.persistencia.Entidad;

@Entity
public class Medicion extends Entidad {

    private RegistroDeEjecucion ejecucion;

    private Discriminante discriminante;
    
    private Medida resultado;

    
    public Medicion() {
    }

    /**
     * Inicializa la Medición para la Ejecución indicada. 
     * El Discriminante de la Medición es todo Módulo.
     * 
     * @param problema
     * @param modulo
     * @param resultado
     */    
    public Medicion(RegistroDeEjecucion ejecucion, Discriminante discriminate, Medida resultado) {
        
        this.setEjecucion(ejecucion);
        this.setDiscriminante(discriminate);
        this.setResultado(resultado);
    }
    
    public void setEjecucion(RegistroDeEjecucion ejecucion) {
       
        this.ejecucion = ejecucion;
    }
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {})
    public RegistroDeEjecucion getEjecucion() {
        
        return this.ejecucion;
    }

    @Transient
    public Problema getProblema() {

        return this.ejecucion.getProblema();
    }

    @Transient
    public Modulo getModulo() {
        
        return this.ejecucion.getModulo();
    }
    
    @OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Medida getResultado() {
        
        return this.resultado;
    }

    public void setResultado(Medida resultado) {

        this.resultado = resultado;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {})
    public Discriminante getDiscriminante() {

        return this.discriminante;
    }
    
    public void setDiscriminante(Discriminante discriminante) {

        this.discriminante = discriminante;
    }
    
    @Override
    protected void describir(StringBuilder builder) {
        
        super.describir(builder);
        this.describirPropiedad(builder, "ejecucion", this.getEjecucion());
        this.describirPropiedad(builder, "modulo", this.getModulo());
        this.describirPropiedad(builder, "discriminante", this.getDiscriminante());
        this.describirPropiedad(builder, "resultado", this.getResultado());
    }

    @Transient
    public Date getInicio() {

        return this.ejecucion.getInicio();
    }

    @Transient
    public Date getFin() {

        return this.ejecucion.getFin();
    }

}

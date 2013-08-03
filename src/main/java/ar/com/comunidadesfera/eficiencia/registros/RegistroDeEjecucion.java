package ar.com.comunidadesfera.eficiencia.registros;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.com.comunidadesfera.persistencia.Entidad;

@Entity
public class RegistroDeEjecucion extends Entidad {

    private Problema problema;

    private Modulo modulo;
    
    private long dimension;

    private Date inicio;

    private Date fin;

    @Column(nullable = false)
    public long getDimension() {

        return this.dimension;
    } 

    public void setDimension(long dimension) {
        
        this.dimension = dimension;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {})
    public Problema getProblema() {
        
        return this.problema;
    }

    public void setProblema(Problema problema) {

        this.problema = problema;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {})
    public Modulo getModulo() {
    
        return this.modulo;
    }

    public void setModulo(Modulo modulo) {
    
        this.modulo = modulo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Date getInicio() {

        return this.inicio;
    }

    public void setInicio(Date marcaTemporal) {

        this.inicio = marcaTemporal;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Date getFin() {
 
        return this.fin;
    }

    public void setFin(Date marcaTemporal) {

        this.fin = marcaTemporal;
    }
 
    @Override
    protected void describir(StringBuilder builder) {

        super.describir(builder);
        this.describirPropiedad(builder, "problema", this.getProblema());
        this.describirPropiedad(builder, "modulo", this.getModulo());

    }
}

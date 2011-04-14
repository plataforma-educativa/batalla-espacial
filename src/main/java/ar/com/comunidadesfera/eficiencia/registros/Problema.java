package ar.com.comunidadesfera.eficiencia.registros;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ar.com.comunidadesfera.persistencia.Entidad;

@Entity
public class Problema extends Entidad {

    private Date inicio;

    private Date fin;
    
    @SuppressWarnings("unchecked")
    private List<Dimension> dimensiones = Collections.EMPTY_LIST;

    public Problema() {
    }
    
    public Problema(long[] tamaño) {
        
        this.setDimensiones(new ArrayList<Dimension>(tamaño.length));
        
        for (long valor : tamaño) {
            
            this.getDimensiones().add(new Dimension(valor));
        }
    }

    @OneToMany(targetEntity = Dimension.class,
               cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn
    public List<Dimension> getDimensiones() {

        return this.dimensiones;
    }

    public void setDimensiones(List<Dimension> dimensiones) {
        
        this.dimensiones = dimensiones;
    }
    
    @Override
    protected void describir(StringBuilder builder) {
        
        super.describir(builder);
        this.describirPropiedad(builder, "dimensiones", this.dimensiones);
    }

    @Transient
    public int dimensiones() {

        return this.dimensiones.size();
    }

    public Dimension getDimension(int i) {

        return this.getDimensiones().get(i);

    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Date getInicio() {
        
        return this.inicio;
    }
    
    public void setInicio(Date inicio) {
        
        this.inicio = inicio;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Date getFin() {
        
        return this.fin;
    }
    
    public void setFin(Date fin) {
        
        this.fin = fin;
    }
}

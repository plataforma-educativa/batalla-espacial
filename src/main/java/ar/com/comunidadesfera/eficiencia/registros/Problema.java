package ar.com.comunidadesfera.eficiencia.registros;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Transient;

import ar.com.comunidadesfera.persistencia.Entidad;

@Entity
public class Problema extends Entidad {

    @SuppressWarnings("unchecked")
    private List<Dimension> dimensiones = Collections.EMPTY_LIST;

    public Problema() {
    }
    
    public Problema(long[] tamaño) {
        
        this.dimensiones = new ArrayList<Dimension>(tamaño.length);
        
        for (long valor : tamaño) {
            
            this.dimensiones.add(new Dimension(valor));
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
}

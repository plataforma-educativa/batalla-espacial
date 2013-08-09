package ar.com.comunidadesfera.eficiencia.registros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ar.com.comunidadesfera.persistencia.Entidad;

@Entity
public class Medida extends Entidad {

    private long magnitud;
    
    private Unidad unidad;

    public Medida() {
    }

    public Medida(long magnitud, Unidad unidad) {
        super();
        this.setMagnitud(magnitud);
        this.setUnidad(unidad);
    }

    @Column(nullable = false)
    public long getMagnitud() {
        return this.magnitud;
    }
    
    public void setMagnitud(long magnitud) {
        
        this.magnitud = magnitud;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Unidad getUnidad() {

        return this.unidad;
    }

    public void setUnidad(Unidad unidad) {
        
        this.unidad = unidad;
    }
    
    @Override
    protected void describir(StringBuilder builder) {

        super.describir(builder);
        this.describirPropiedad(builder, "magnitud", this.getMagnitud());
        this.describirPropiedad(builder, "unidad", this.getUnidad());
    }
}

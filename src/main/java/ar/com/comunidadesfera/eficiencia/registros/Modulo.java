package ar.com.comunidadesfera.eficiencia.registros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"identificacion", "version"})) 
public class Modulo extends Discriminante {

    public static final int VERSION_MINIMA = 1;
    
    private String descripcion;
    
    private int version;

    private String identificacion;
    
    public Modulo() {
        this(null);
    }

    public Modulo(String nombre) {
    
        super(nombre);
        this.setVersion(VERSION_MINIMA);
    }
    
    @Override
    protected void describir(StringBuilder builder) {
        
        super.describir(builder);
        this.describirPropiedad(builder, "version", this.getVersion());
    }

    @Column(nullable = true, length = 2048)
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(nullable = false, updatable = false)
    public int getVersion() {
        return version;
    }

    @Column(nullable = false, updatable = false)
    public String getIdentificacion() {

        if (this.identificacion == null) {
            
            this.identificacion = this.getNombre();
        }
        
        return this.identificacion;
    } 
    
    public void setIdentificacion(String identificacion) {
        
        this.identificacion = identificacion;
    }
    
    @PrePersist
    public void verificarVersion() {
        
        if (this.getVersion() < VERSION_MINIMA) {
            
            this.setVersion(VERSION_MINIMA);
        }
    }

}

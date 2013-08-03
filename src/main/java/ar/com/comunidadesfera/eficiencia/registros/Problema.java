package ar.com.comunidadesfera.eficiencia.registros;

import javax.persistence.Column;
import javax.persistence.Entity;

import ar.com.comunidadesfera.persistencia.Entidad;

@Entity
public class Problema extends Entidad {

    private String nombre;

    public Problema() {
    }
    
    public Problema(String nombre) {
        
        
        this.setNombre(nombre);
    }
    
    @Override
    protected void describir(StringBuilder builder) {
        
        super.describir(builder);
        this.describirPropiedad(builder, "nombre", this.getNombre());
    }

    @Column(nullable = false, unique = true, updatable = false)
    public String getNombre() {

        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        
        this.nombre = nombre;
    }
}

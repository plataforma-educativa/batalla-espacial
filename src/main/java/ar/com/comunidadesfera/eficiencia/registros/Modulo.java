package ar.com.comunidadesfera.eficiencia.registros;

import javax.persistence.Column;
import javax.persistence.Entity;

import ar.com.comunidadesfera.persistencia.Entidad;

@Entity
public class Modulo extends Entidad {

    private String nombre;
    
    public Modulo() {
    }

    public Modulo(String nombre) {
        
        this.setNombre(nombre);
    }
    
    @Column(nullable = false, unique = true)
    public String getNombre() {
        
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        
        this.nombre = nombre;
    }

    @Override
    protected void describir(StringBuilder builder) {
        
        this.describirPropiedad(builder, "nombre", this.getNombre());
    }
}

package ar.com.comunidadesfera.eficiencia.registros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import ar.com.comunidadesfera.persistencia.Entidad;

/**
 * Un Discriminante define un criterio de Medición.
 * 
 * @author Mariano Tugnarelli
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Discriminante extends Entidad {

    private String nombre;
    
    private Discriminante entorno;
    
    public Discriminante() {
    }
    
    public Discriminante(String nombre) {
        this.setNombre(nombre);
    }

    public Discriminante(String nombre, Discriminante entorno) {
        this.setNombre(nombre);
        this.setEntorno(entorno);
    }

    @Column(nullable = false, length = 512, updatable = false)
    public String getNombre() {
        
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        
        this.nombre = nombre;
    }
    
    @Override
    protected void describir(StringBuilder builder) {
        super.describir(builder);
        this.describirPropiedad(builder, "nombre", this.getNombre());
    }

    public void setEntorno(Discriminante entorno) {
        this.entorno = entorno;
    }

    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {},
               targetEntity = Discriminante.class)
    public Discriminante getEntorno() {
        return entorno;
    }
}

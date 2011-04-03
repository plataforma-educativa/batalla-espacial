package ar.com.comunidadesfera.eficiencia.registros;

import javax.persistence.Column;
import javax.persistence.Entity;

import ar.com.comunidadesfera.persistencia.Entidad;

@Entity
public class Dimension extends Entidad {

    private long valor;
    
    private String nombre;

    public Dimension() {
    }

    public Dimension(long valor) {
        this.valor = valor;
    }
    
    @Column(nullable = true, length = 500)
    public String getNombre() {

        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        
        this.nombre = nombre;
    }

    @Column(nullable = false)
    public long getValor() {

        return this.valor;
    }
    
    public void setValor(long valor) {
        
        this.valor = valor;
    }
    
    @Override
    protected void describir(StringBuilder builder) {

        super.describir(builder);
        this.describirPropiedad(builder, "nombre", this.getNombre());
        this.describirPropiedad(builder, "valor", this.getValor());
    }

}

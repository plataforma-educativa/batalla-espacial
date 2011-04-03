package ar.com.comunidadesfera.eficiencia.registros;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Categoria extends Discriminante {

    private Modulo modulo;

    public Categoria() {
    }

    public Categoria(String nombre, Modulo modulo) {
        
        this(nombre, modulo, modulo);
    }
    
    public Categoria(String nombre, Modulo modulo, Discriminante entorno) {
        
        super(nombre, entorno);
        this.setModulo(modulo);
    }
    
    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = {})
    public Modulo getModulo() {
        return modulo;
    }    
    
    @Override
    protected void describir(StringBuilder builder) {

        super.describir(builder);
        this.describirPropiedad(builder, "módulo", this.getModulo());
    }
}

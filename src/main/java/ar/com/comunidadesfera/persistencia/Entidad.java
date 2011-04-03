package ar.com.comunidadesfera.persistencia;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entidad {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object other) {

        boolean equals = super.equals(other);
        
        if ((! equals) && (other != null) && (this.getClass().isInstance(other)))  {
            
            Entidad castOther = (Entidad) other;
            
            equals = (this.id != null) &&
                     (castOther.id != null) &&
                     (this.id.equals(castOther.id));
        }
        
        return equals;
    }
    
    @Override
    public int hashCode() {
        
        return this.id != null ? this.id.hashCode() : super.hashCode(); 
    }
    
    @Override
    public final String toString() {

        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName())
                                        .append("[");
        this.describir(builder);
        builder.setCharAt(builder.length() - 1, ']');
        return builder.toString();
    }
    
    protected void describir(StringBuilder builder) {
        
        this.describirPropiedad(builder, "id", this.id);
    }
    
    protected final void describirPropiedad(StringBuilder builder, 
                                            String nombre, Object valor) {
        builder.append(nombre)
               .append(":")
               .append(valor)
               .append(",");
    }    
}

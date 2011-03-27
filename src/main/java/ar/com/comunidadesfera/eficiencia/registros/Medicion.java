package ar.com.comunidadesfera.eficiencia.registros;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ar.com.comunidadesfera.persistencia.Entidad;

@Entity
public class Medicion extends Entidad {

    private Problema problema;
    
    private Modulo modulo;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, 
              optional = false, orphanRemoval = true)
    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {}, 
               optional = false)
    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }
    
    @Override
    protected void describir(StringBuilder builder) {

        this.describirPropiedad(builder, "problema", this.getProblema());
        this.describirPropiedad(builder, "modulo", this.getModulo());
    }
}

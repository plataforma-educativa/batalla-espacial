package ar.com.comunidadesfera.batallaespacial.piezas.asteroide;

import ar.com.comunidadesfera.batallaespacial.Reporte;
import ar.com.comunidadesfera.batallaespacial.Reporte.Espectro;
import ar.com.comunidadesfera.batallaespacial.juego.Detectable;
import ar.com.comunidadesfera.batallaespacial.juego.Pieza;
import ar.com.comunidadesfera.batallaespacial.juego.ReporteEstatico;
import ar.com.comunidadesfera.batallaespacial.piezas.VisitanteDePiezas;

public class Asteroide extends Pieza {

    public Asteroide(int puntos) {
        super(puntos);
    }

    /**
     * @see Detectable#reportar()
     */
    @Override
    public Reporte reportar() {

        return new ReporteEstatico(Espectro.ASTEROIDE, null, null);
    }

    /**
     * @see Pieza#getIdentificacion()
     */
    public String getIdentificacion() {
        
        return "Asteroide M" + (this.hashCode() % 1000);
    }
    
    @Override
    public void distorsionar() {

        super.distorsionar();
        
        this.setPuntos((int) (Pieza.azar.nextFloat() * this.getPuntos()));
    }
    
    @Override
    public void recibir(VisitanteDePiezas visitante) {
     
        visitante.visitar(this);
    }
}

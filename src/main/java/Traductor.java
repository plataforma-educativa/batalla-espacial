import ar.com.comunidadesfera.batallaespacial.Reporte;



public class Traductor {

    private static final Traductor instancia = new Traductor();
    
    public static Traductor instancia() {
        
        return instancia;
    }
    
    private Traductor() {
        
    }
    
    public Espectro traducir(Reporte.Espectro espectro) {
        
        return Espectro.values()[espectro.ordinal()]; 
    }
    
    public ar.com.comunidadesfera.batallaespacial.Sustancia traducir(Sustancia sustancia) {
        
        return ar.com.comunidadesfera.batallaespacial.Sustancia.values()[sustancia.ordinal()];
    }

}

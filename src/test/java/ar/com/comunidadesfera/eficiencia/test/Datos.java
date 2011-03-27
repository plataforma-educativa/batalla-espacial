package ar.com.comunidadesfera.eficiencia.test;

public class Datos {

    public enum Modulo {
      
        SIMPLE ("EficienciaTest.algoritmoSimple"),
        MULTIPLES_PASOS ("EficienciaTest.algoritmoMultiplesPasos"),
        VACIO ("");
        
        public final String nombre;
        
        private Modulo(String nombre) {
            
            this.nombre = nombre;
        }
    };
    
    public enum Ejecucion {
        
        SIMPLE_10 (Modulo.SIMPLE, 10),
        SIMPLE_20 (Modulo.SIMPLE, 20),
        MULTIPLES_PASOS (Modulo.MULTIPLES_PASOS, 5),
        VACIA(null, 0);
                   
        public final Modulo modulo;
        public final long[] tamaño;
        
        private Ejecucion(Modulo modulo, long... tamaño) {
            
            this.modulo = modulo;
            this.tamaño = tamaño;
        }
    }
}

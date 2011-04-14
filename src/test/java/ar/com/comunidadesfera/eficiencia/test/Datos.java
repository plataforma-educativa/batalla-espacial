package ar.com.comunidadesfera.eficiencia.test;

public class Datos {

    public enum Modulo {
      
        SIMPLE ("EficienciaTest.algoritmoSimple", 
                "Algoritmo simple.",
                2),
        MULTIPLES_PASOS ("EficienciaTest.algoritmoMultiplesPasos", 
                         "Algoritmo de múltiples pasos.", 
                         1),
        VACIO ("", "", 1);
        
        public final String nombre;
        public final String descripcion;
        public final int version;
        
        private Modulo(String nombre, String descripcion, int version) {
            
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.version = version;
            
        }
    };
    
    public enum Ejecucion {
        
        SIMPLE_10 (Modulo.SIMPLE, 10),
        SIMPLE_20 (Modulo.SIMPLE, 20),
        MULTIPLES_PASOS (Modulo.MULTIPLES_PASOS, 5, 4),
        VACIA(null, 0);
                   
        public final Modulo modulo;
        public final long[] tamaño;
        
        private Ejecucion(Modulo modulo, long... tamaño) {
            
            this.modulo = modulo;
            this.tamaño = tamaño;
        }
    }
}

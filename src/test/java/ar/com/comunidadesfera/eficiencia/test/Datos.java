package ar.com.comunidadesfera.eficiencia.test;

public class Datos {

    public enum Problema {
        
        PRIMERO ("primero", "primer problema");
        
        public final String nombre;
        public final String descripcion;
        
        private Problema(String nombre, String descripcion) {
            
            this.nombre = nombre;
            this.descripcion = descripcion;
        }
    }
    
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
    }
    
    public enum Ejecucion {
        
        SIMPLE_10 (Modulo.SIMPLE, Problema.PRIMERO, 10),
        SIMPLE_20 (Modulo.SIMPLE, Problema.PRIMERO, 20),
        MULTIPLES_PASOS (Modulo.MULTIPLES_PASOS, Problema.PRIMERO, 5, 4),
        VACIA(null, null, 0);
                   
        public final Modulo modulo;
        public final Problema problema;
        public final long dimension;
        @Deprecated
        public final long[] tamaño;
        
        
        private Ejecucion(Modulo modulo, Problema problema, long... tamaño) {
            
            this.modulo = modulo;
            this.problema = problema;
            this.dimension = tamaño[0];
            this.tamaño = tamaño;
        }
    }
}

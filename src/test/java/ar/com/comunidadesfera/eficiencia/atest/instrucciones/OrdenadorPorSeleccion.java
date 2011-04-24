package ar.com.comunidadesfera.eficiencia.atest.instrucciones;

import ar.com.comunidadesfera.eficiencia.Eficiencia;
import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;

/**
 * Test de Aceptación (Acceptance Test) para la medición de instrucciones.
 * 
 * 
 * @author Mariano Tugnarelli
 * 
 */
public class OrdenadorPorSeleccion {

    /**
     * @post ordena los elementos de vector de mayor a menor.
     * 
     * @param vector
     */
    public void ordenar(int[] vector) {

        Ejecucion ejecucion = Eficiencia.getContexto()
                                .iniciarEjecucion(this.getClass().getName(), 
                                                  new long[] { vector.length });
        
        Contador contador = ejecucion.contarInstrucciones();
        Contador comparaciones = contador.getParcial("comparaciones");
        Contador intercambios = contador.getParcial("intercambios");
        
        for (int longitud = vector.length; longitud > 1; longitud--) {

            int posicionMaximo = 0;

            /* busca el máximo para un vector con longitud */
            for (int j = 1; j < longitud; j++) {
            
                comparaciones.incrementar();
                if (vector[j] > vector[posicionMaximo]) {
                
                    posicionMaximo = j;
                }
            }
            
            intercambios.incrementar();
            this.intercambiar(vector, posicionMaximo, longitud - 1);
        }
        
        ejecucion.terminar();
    }

    /**
     * @pre i y j son índices válidos para vector.
     * @post intercambia los valores de la posición i-ésima por la j-ésima.
     * 
     * @param vector
     * @param i
     * @param j
     */
    private void intercambiar(int[] vector, int i, int j) {

        int auxiliar = vector[i];
        vector[i] = vector[j];
        vector[j] = auxiliar;
    }

}

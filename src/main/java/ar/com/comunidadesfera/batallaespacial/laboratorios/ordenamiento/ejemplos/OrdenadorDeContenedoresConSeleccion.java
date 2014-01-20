package ar.com.comunidadesfera.batallaespacial.laboratorios.ordenamiento.ejemplos;

import javax.inject.Inject;

import ar.com.comunidadesfera.batallaespacial.laboratorios.ordenamiento.OrdenadorDeContenedores;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.eficiencia.Contexto;
import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;

public class OrdenadorDeContenedoresConSeleccion implements OrdenadorDeContenedores {

    @Inject
    private Contexto contexto;
    
    @Override
    public void ordenar(Contenedor[] contenedores) {

        Ejecucion ejecucion = contexto.iniciarEjecucion("OrdenadorDeContenedoresConSeleccion", "Ordenamiento", contenedores.length);
        ejecucion.getModulo().setVersion(1);
        Contador contador = ejecucion.contarInstrucciones();

        for (int longitud = contenedores.length; longitud > 1; longitud--) {

            int posicionMaximo = 0;

            /* busca el máximo para un arreglo con 'longitud' elementos */
            for (int j = 1; j < longitud; j++) {

                contador.getParcial("comparaciones").incrementar();
                
                if (contenedores[j].getNivelDeCarga() > contenedores[posicionMaximo].getNivelDeCarga()) {

                    posicionMaximo = j;
                }
            }

            contador.getParcial("intercambios").incrementar();
            this.intercambiar(contenedores, posicionMaximo, longitud - 1);
        }
        
        ejecucion.terminar();
    }
    
    private void intercambiar(Contenedor[] contenedores, int i, int j) {

        Contenedor contenedorI = contenedores[i];
        contenedores[i] = contenedores[j];
        contenedores[j] = contenedorI;
    }
}

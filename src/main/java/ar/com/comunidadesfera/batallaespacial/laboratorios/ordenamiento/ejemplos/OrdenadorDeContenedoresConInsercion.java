package ar.com.comunidadesfera.batallaespacial.laboratorios.ordenamiento.ejemplos;

import javax.inject.Inject;

import ar.com.comunidadesfera.batallaespacial.laboratorios.ordenamiento.OrdenadorDeContenedores;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.eficiencia.Contexto;
import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;

public class OrdenadorDeContenedoresConInsercion implements
        OrdenadorDeContenedores {

    @Inject
    private Contexto contexto;
    
    @Override
    public void ordenar(Contenedor[] contenedores) {

        contexto.setPersistente(true);
        Ejecucion ejecucion = contexto.iniciarEjecucion("Inserción", "Ordenamiento", contenedores.length);
        ejecucion.getModulo().setVersion(1);
        Contador contador = ejecucion.contarInstrucciones();
        
        for (int i = 1; i < contenedores.length; i++) {
            
            int j = i;
            
            contador.getParcial("comparaciones").incrementar();
            
            while ((j > 0) && (contenedores[j].getNivelDeCarga() < contenedores[j-1].getNivelDeCarga())) {

                contador.getParcial("intercambios").incrementar();
                
                this.intercambiar(contenedores, j, j - 1);
                
                j--;
            }
        }
        
        ejecucion.terminar();
    }

    private void intercambiar(Contenedor[] contenedores, int i, int j) {

        Contenedor contenedorI = contenedores[i];
        contenedores[i] = contenedores[j];
        contenedores[j] = contenedorI;
    }
}

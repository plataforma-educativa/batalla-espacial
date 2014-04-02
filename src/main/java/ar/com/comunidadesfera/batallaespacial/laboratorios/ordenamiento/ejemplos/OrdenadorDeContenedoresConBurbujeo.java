package ar.com.comunidadesfera.batallaespacial.laboratorios.ordenamiento.ejemplos;

import javax.inject.Inject;

import ar.com.comunidadesfera.batallaespacial.laboratorios.ordenamiento.OrdenadorDeContenedores;
import ar.com.comunidadesfera.batallaespacial.piezas.contenedor.Contenedor;
import ar.com.comunidadesfera.eficiencia.Contexto;
import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;

public class OrdenadorDeContenedoresConBurbujeo implements
        OrdenadorDeContenedores {

    @Inject
    private Contexto contexto;
    
    @Override
    public void ordenar(Contenedor[] contenedores) {

        contexto.setPersistente(true);
        Ejecucion ejecucion = contexto.iniciarEjecucion("Burbujeo", "Ordenamiento", contenedores.length);
        ejecucion.getModulo().setVersion(3);
        Contador contador = ejecucion.contarInstrucciones();
        
        for (int i = 0; i < contenedores.length; i++) {
            
            for (int j = 0; j < contenedores.length - 1; j++) {
  
                contador.getParcial("comparaciones").incrementar();
               
                if (contenedores[j+1].getNivelDeCarga() < contenedores[j].getNivelDeCarga()) {

                    contador.getParcial("intercambios").incrementar();
                    
                    Contenedor contenedor = contenedores[j];
                    contenedores[j] = contenedores[j+1];
                    contenedores[j+1] = contenedor;
                }
            }
        }
        
        ejecucion.terminar();
    }


}

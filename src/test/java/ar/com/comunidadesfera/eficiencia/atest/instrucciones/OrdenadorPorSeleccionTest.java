package ar.com.comunidadesfera.eficiencia.atest.instrucciones;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrdenadorPorSeleccionTest {

    private OrdenadorPorSeleccion ordenador;
    
    @Before
    public void inicializar() {
        
        this.ordenador = new OrdenadorPorSeleccion();
    }
    
    @Test
    public void ordenar() {
       
        this.ejecutar(new int[] { 3, 4, 6, 9, 3, 0, 2 });
    }
    
    @Test 
    public void ordenarVectorVacio() {
        
        this.ejecutar(new int[] {} );
    }
    
    @Test
    public void ordenarVectorConUnElemento() {
        
        this.ejecutar(new int[] { 3 } );
    }
    
    @Test
    public void ordenarVectorConElementosOrdenados() {
        
        this.ejecutar(new int[] { 3, 5, 6, 8, 45, 46, 56, 89, 90} );
    }
    
    @Test
    public void ordenarVectorConElementosInvertidos() {
        
        this.ejecutar(new int[] { 67, 34, 23, 4, 3, 2, 1 } );
    }
    
    @Test
    public void ordenarVectorConElementosNegativos() {
        
        this.ejecutar(new int[] { 4, -8, 2, 56, -98, 58, 12, -9, 23, 2 } );
    }
    
    @Test
    public void ordenarVectorCon100Elementos() {
        
        int[] vector = new int[] {
                30,24,5,35,40,55,99,53,74,47,
                42,98,36,43,91,19,11,25,93,0,
                70,10,80,96,97,67,38,4,17,41,
                18,26,44,17,61,6,82,4,90,96,
                5,23,93,69,34,95,69,29,15,22,
                52,86,49,30,92,18,85,99,83,99,
                87,41,83,23,98,52,4,32,93,48,
                95,94,52,61,58,44,58,63,78,78,
                6,67,32,12,43,38,96,13,2,36,
                15,15,33,85,33,22,47,28,31,12
        };
          
        this.ejecutar(vector);
    }
    
    @Test
    public void ordenarVectorCon500Elementos() {
        
        int[] vector = new int[] {
                703,176,327,231,798,312,247,311,240,791,
                156,443,88,942,562,466,723,992,126,47,
                717,111,388,111,144,496,81,999,999,720,
                397,800,544,642,416,832,616,470,173,534,
                145,895,451,826,326,648,648,30,576,972,
                425,925,313,666,141,391,933,833,848,406,
                904,198,74,728,346,895,815,57,871,602,
                810,836,796,487,626,262,324,774,415,495,
                832,247,397,901,221,773,319,959,870,325,
                799,451,594,449,186,221,737,307,41,133,
                513,512,81,636,933,867,996,578,820,99,
                945,446,111,611,294,355,804,419,382,676,
                96,768,269,196,866,765,272,756,562,379,
                993,66,696,141,840,682,296,273,710,460,
                339,627,803,593,322,460,711,419,572,50,
                669,481,70,717,396,372,106,78,47,696,
                96,971,636,138,832,187,490,355,850,859,
                107,646,189,275,321,308,748,120,45,97,
                119,678,426,10,749,849,96,782,382,120,
                165,86,999,767,854,338,223,328,979,778,
                718,733,880,942,150,133,655,426,377,665,
                851,401,258,593,216,300,567,591,870,479,
                948,480,725,145,643,218,954,951,207,719,
                519,195,265,565,688,6,433,6,730,887,
                814,435,414,550,944,408,459,946,298,284,
                626,876,166,248,866,406,9,777,164,771,
                789,372,674,155,589,533,464,67,795,931,
                708,704,133,339,865,670,519,120,599,409,
                66,372,567,207,41,454,354,184,196,496,
                425,139,893,539,711,846,464,265,819,713,
                992,727,834,584,748,309,513,899,617,76,
                784,308,585,547,690,229,689,437,648,59,
                616,266,469,539,91,488,788,759,685,119,
                142,68,612,662,153,995,439,85,18,355,
                283,504,730,88,805,500,230,98,762,361,
                320,860,506,540,195,774,906,176,988,746,
                569,625,669,768,306,556,59,310,392,262,
                831,504,161,352,265,744,585,756,55,185,
                999,78,198,710,4,522,280,954,296,310,
                899,107,46,155,19,601,416,166,269,909,
                949,613,836,447,479,576,225,792,665,686,
                619,8,85,313,670,949,20,7,247,514,
                295,181,102,607,186,642,356,186,216,726,
                28,306,762,318,494,556,408,148,750,893,
                378,327,667,106,504,604,900,214,198,232,
                605,937,955,203,615,338,385,458,589,505,
                375,650,730,187,361,468,536,611,441,977,
                804,658,647,547,390,90,772,333,260,405,
                20,72,205,461,746,844,3,215,445,223,
                562,689,344,29,733,164,680,188,335,408,
        };
          
        this.ejecutar(vector);
    }
    
    
    /**
     * @post ejecuta la prueba, ordenando vector con el ordenador
     *       y luego asegurando su resultado.
     *       
     * @param vector
     */
    private void ejecutar(int[] vector) {
        
        this.ordenador.ordenar(vector);
        
        this.asegurarOrden(vector);
    }
    
    /**
     * @post asegura que los elementos de vector están ordenados de menor 
     *       a mayor.
     */
    private void asegurarOrden(int[] vector) {
        
        /* comienza a verificar desde el segundo elemento */
        for (int i = 1; i < vector.length; i++) {

            Assert.assertTrue("vector[" + i + "] >= vector[" + (i - 1)  + "]" ,
                              vector[i] >= vector[i-1]);
        }
    }
    
}

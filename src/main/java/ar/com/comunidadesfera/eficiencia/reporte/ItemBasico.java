package ar.com.comunidadesfera.eficiencia.reporte;

public abstract class ItemBasico<T> implements ItemReporte<T> {

    private static final long serialVersionUID = 3413888719128060515L;

    private ItemReporte<T> superItem;
    private T objeto;
    private String nombre;
    private String descripcion;

    public ItemBasico() {
        
        this(null, null, "", "");
    }

    public ItemBasico(ItemReporte<T> superItem, T objeto, String nombre,
                      String descripcion) {
        
        super();
        this.setObjeto(objeto);
        this.setSuperItem(superItem);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
    }

    public ItemBasico(T objeto) {
        
        this(objeto, String.valueOf(objeto));
    }

    public ItemBasico(String nombre) {
        
        this(null, nombre);
    }

    public ItemBasico(T objeto, String nombre) {

        this(null, objeto, nombre, "");
    }

    @Override
    public String getNombre() {

        return this.nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    @Override
    public String getDescripcion() {

        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {

        this.descripcion = descripcion;
    }

    @Override
    public ItemReporte<T> getSuperItem() {

        return this.superItem;
    }

    @Override
    public void setSuperItem(ItemReporte<T> item) {

        this.superItem = item;
    }

    @Override
    public Double getProporcion() {

        double proporcion = 1.0;

        if (this.getSuperItem() != null) {

            double total = this.getSuperItem().getValor().doubleValue();

            if (total > 0) {

                proporcion = this.getValor().doubleValue() / total;

            } else {

                proporcion = 0.0;
            }
        }
        return proporcion;
    }

    @Override
    public String toString() {
        
        return "{" + this.getNombre() + ": " + this.getValor() + " }";
    }

    @Override
    public T getObjeto() {
        
        return objeto;
    }

    public void setObjeto(T objeto) {
        
        this.objeto = objeto;
    }

}

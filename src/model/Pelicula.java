
package model;


import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Pelicula {
    
    private final StringProperty titulo;
    private final StringProperty descrpcion;
    private final IntegerProperty precio;
    private final IntegerProperty stock;
    private final IntegerProperty codigoBarras;
    private final ObjectProperty fechaAlta; 
    private final ObjectProperty fechaModificacion;
    private Image foto;
    
    public Pelicula(){
        this(null,null,0,0,0,null,null);
    }
    
    public Pelicula(String titulo){
            
        
        this.titulo = new SimpleStringProperty(titulo);
        this.descrpcion = new SimpleStringProperty("descripcion por defecto");
        
        this.precio = new SimpleIntegerProperty(15);
        this.stock = new SimpleIntegerProperty(29);
        this.codigoBarras = new SimpleIntegerProperty(28440);
        this.fechaAlta = new SimpleObjectProperty(LocalDate.of(1974, 6, 15));
        this.fechaModificacion = new SimpleObjectProperty(LocalDate.of(1974, 6,17)); 
        foto=new Image("/controller/defaultImage.png");
    }
    
    
    public Pelicula(String titulo,String descripcion,int precio,int stock,int codigoBarras,LocalDate fechaAlta, LocalDate fechaModificacion) {
        
       
        this.titulo = new SimpleStringProperty(titulo);
        this.descrpcion = new SimpleStringProperty(descripcion);
        this.precio = new SimpleIntegerProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
        this.codigoBarras = new SimpleIntegerProperty(codigoBarras);
        this.fechaAlta = new SimpleObjectProperty(fechaAlta);
        this.fechaModificacion = new SimpleObjectProperty(fechaModificacion);
        this.foto=new Image("/controller/defaultImage.png");
       
    }
    
   public Pelicula(String titulo,String descripcion,int precio,int stock,int codigoBarras,LocalDate fechaAlta, LocalDate fechaModificacion,Image foto) {
        
        this.titulo = new SimpleStringProperty(titulo);
        this.descrpcion = new SimpleStringProperty(descripcion);
        this.precio = new SimpleIntegerProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
        this.codigoBarras = new SimpleIntegerProperty(codigoBarras);
        this.fechaAlta = new SimpleObjectProperty(fechaAlta);
        this.fechaModificacion = new SimpleObjectProperty(fechaModificacion);
        this.foto=foto;
        
    }

    
    public String getTitulo() {
        return titulo.get();
    }

    public void setTitulo(String titulo) {
        this.titulo.set(titulo);
    }

    public StringProperty tituloProperty() {
        return titulo;
    }

    public String getDescripcion() {
        return descrpcion.get();
    }

    public void setDescripcion(String descrpcion) {
        this.descrpcion.set(descrpcion);
    }

    public StringProperty descrpcionProperty() {
        return descrpcion;
    }

    public int getPrecio() {
        return precio.get();
    }

    public void setPrecio(int precio) {
        this.precio.set(precio);
    }

    public IntegerProperty precioProperty() {
        return precio;
    }
    
    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public int getCodigoBarras() {
        return codigoBarras.get();
    }

    public void setCodigoBarras(int codigoBarras) {
        this.codigoBarras.set(codigoBarras);
    }

    public IntegerProperty codigoBarrasProperty() {
        return codigoBarras;
    }

    public LocalDate getFechaDeAlta() {
        return (LocalDate) fechaAlta.get();
    }

    public void setFechaDeAlta(LocalDate fechaAlta) {
        this.fechaAlta.set(fechaAlta);
    }

    public ObjectProperty FechaDeAltaProperty() {
        return fechaAlta;
    }
    
        public LocalDate fechaDeModificacion() {
        return (LocalDate) fechaModificacion.get();
    }

    public void setFechaDeModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion.set(fechaModificacion);
    }

    public ObjectProperty fechaDeModificacionProperty() {
        return fechaModificacion;
    }
    
    public Image getImage(){
        return foto;
        
    }
    
    public void setImage(Image foto){
        this.foto=foto;
    }

}


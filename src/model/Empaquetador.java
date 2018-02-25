
package model;



import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "peliculas") //Define el nombre del elemento raíz XML
public class Empaquetador {
    
    private List<Pelicula> peliculas;
    
    @XmlElement(name = "persona") //Opcional para el elemento especificado
    public List<Pelicula> getPeliculas(){
        return peliculas;
    }
    
    public void setPeliculas(List<Pelicula> peliculas){
        this.peliculas = peliculas;
    }
    
}

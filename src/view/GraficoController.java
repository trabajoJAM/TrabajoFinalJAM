package view;


import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import model.Pelicula;


public class GraficoController {
    
    @FXML
    private BarChart<String, Integer> graficoBarras;

  
    private String[] peliculasY = {"Coco","La llamada","Bataman","Thor","La momia","Star Wars","Up","It(eso)","Veronica","Brigth","Dunkerque",
                "Saw","Deadpool","Tadeo jones","Yo soy la juani","Tiburon","Dumbo","Indiana jones","Interstellar",
                "El exorcista","Wonder woman","Superman","Vaiana","Dracula","El renacido","300"};

    private ObservableList<String> nombrePeliculas = FXCollections.observableArrayList();
    
    private int stock;


//Se invoca justo después de que se ha cargado el archivo FXML
    @FXML
    private void initialize() {

        //Los convierto a lista obervable
        //nombrePeliculas.addAll(Arrays.asList(peliculasY));
       
    }
    
 //Set mes de cada persona para el eje Y
    public void setDatosPelicula(List<Pelicula> peliculas) {
        

        //Genero la serie
         XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        series.setName("Stock");
        for (Pelicula p1 : peliculas) {
            series.getData().add(new XYChart.Data<>(p1.getTitulo(), p1.getStock()));
        }
        
        //Añado la serie al gráfico
       graficoBarras.getData().add(series);
        
}
}
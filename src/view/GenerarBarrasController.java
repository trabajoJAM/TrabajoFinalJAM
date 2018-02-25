package view;



import com.itextpdf.text.DocumentException;
import controller.TrabajoJairo;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Pelicula;

public class GenerarBarrasController implements Initializable {
   @FXML private ChoiceBox choiceBox,choiceBoxCantidad;
   @FXML private Label finalLabel;
   @FXML private Button okButton;
   Pelicula p;
    Stage escenarioBarras;
    TrabajoJairo trabajo;
       
    public void setEscenarioBarras(Stage escenarioBarras) {
        this.escenarioBarras = escenarioBarras;
    }
    
      public void datosChoiceBox(java.util.List<Pelicula> peliculas){
         for(Pelicula p:peliculas){
             choiceBox.getItems().add(p.getTitulo());
         }
         
          for (int i = 0; i < 100; i++) {
              choiceBoxCantidad.getItems().add(i);
          }
     
      }
     @FXML
    public void initialize(URL url, ResourceBundle rb) {


    }   
    @FXML
    private void hechoBotonPulsado(){
       String titulo=(String) choiceBox.getSelectionModel().getSelectedItem();
       int cantidad=(int) choiceBoxCantidad.getSelectionModel().getSelectedItem();
               finalLabel.setText("Has elegido la pelicula "+titulo+" y una cantidad de "+cantidad+" codigo de barras");
               finalLabel.setVisible(true);
               }
    public void generarCodigoDeBarras() throws DocumentException, FileNotFoundException {
        
   String titulo=(String) choiceBox.getSelectionModel().getSelectedItem();
      int cantidad= (int) choiceBoxCantidad.getSelectionModel().getSelectedItem();
  
           
         trabajo=new TrabajoJairo();
         
         trabajo.generarCodigo(titulo,cantidad);
         escenarioBarras.close();
    }
}

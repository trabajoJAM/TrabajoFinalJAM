
package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class EditarPeliculaController implements Initializable {
    @FXML private TextField tituloTexfField;
    @FXML private TextField descripTextField;
    @FXML private TextField precioTextField;
    @FXML private TextField stockTextField;
    @FXML private TextField codBarrasTextField;
    @FXML private ImageView imageView;
    @FXML private DatePicker fechaAlta;
    Stage escenarioEditar;
  
    @FXML
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    public void setEscenarioEditar(Stage escenarioEditar) {
        this.escenarioEditar = escenarioEditar;
    }
 
}

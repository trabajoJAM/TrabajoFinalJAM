
package view;

import controller.TrabajoJairo;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Pelicula;


public class AñadirPeliculaController implements Initializable {

    private TrabajoJairo trabajo;
    private Pelicula p;
    @FXML
    private TextField tituloTextField, imagenTextField;
    @FXML
    private TextArea descripTextField;
    @FXML
    private TextField precio;
    @FXML
    private TextField stock;
    @FXML
    private DatePicker fechaAlta;
    @FXML
    private TextField codBarras;
    Stage escenarioAñadir;
    FileChooser fileChooser;
    File file;
    private boolean guardarClicked = false;

    public void setPelicula(Pelicula pelicula) {
        p = pelicula;
    }

    @FXML
    public void guardar(MouseEvent event) {

        p.setTitulo(tituloTextField.getText());
        p.setDescripcion(descripTextField.getText());
        p.setPrecio(Integer.parseInt(precio.getText()));
        p.setStock(Integer.parseInt(stock.getText()));
        p.setFechaDeAlta(fechaAlta.getValue());
        p.setFechaDeModificacion(LocalDate.now());
        p.setCodigoBarras(Integer.parseInt(codBarras.getText()));
        p.setImage(new Image("file:" + file.getPath()));

        guardarClicked = true; //Cambio valor booleano 
        escenarioAñadir.close();

    }

    public boolean isGuardarClicked() {
        return guardarClicked;
    }

    @FXML
    public void escogerImagen() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Elige una imagen de pelicula");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG ficheros", "*.png"));
        file = fileChooser.showOpenDialog(escenarioAñadir);
        imagenTextField.setText(file.getPath());

    }

    @FXML
    private void cancelar() {
        escenarioAñadir.close();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setEscenarioAñadir(Stage escenarioAñadir) {
        this.escenarioAñadir = escenarioAñadir;
    }

}

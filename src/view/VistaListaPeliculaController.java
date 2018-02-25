
package view;

import controller.TrabajoJairo;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Pelicula;


public class VistaListaPeliculaController {

    @FXML
    public TableView<Pelicula> tableView;
    @FXML
    private TableColumn<Pelicula, String> tituloColumn;
    @FXML
    private Button detallesButton;
    private Pelicula peliculaGuardada;
    private AnchorPane vistaPelicula, VistaAñadir;
    private TrabajoJairo trabajo;

    public VistaListaPeliculaController() {
    }

    public void mostrarDetallePelicula(MouseEvent event) {

        FXMLLoader loader = new FXMLLoader();
        URL location = TrabajoJairo.class.getResource("../view/DetallePelicula.fxml");
        loader.setLocation(location);
        try {
            vistaPelicula = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TrabajoJairo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Creo el escenario de edición (con modal) y establezco la escena
        Stage escenarioEdicion = new Stage();
        escenarioEdicion.initModality(Modality.WINDOW_MODAL);
        Scene escena = new Scene(vistaPelicula);
        escenarioEdicion.setScene(escena);

        //Asigno el escenario de edición y la pelicula seleccionada al controlador
        DetallePeliculaController controller = loader.getController();
        controller.setEscenarioEdicion(escenarioEdicion);
        controller.initData(tableView.getSelectionModel().getSelectedItem());

        //Muestro el diálogo ahjsta que el ussuario lo cierre
        escenarioEdicion.showAndWait();
    }

    public void peliculaPulsada() {
        this.detallesButton.setDisable(false);
    }

    @FXML
    private void borrarPelicula() {
        //Capturo el indice seleccionado y borro su item asociado de la tabla
        int indiceSeleccionado = tableView.getSelectionModel().getSelectedIndex();
        if (indiceSeleccionado >= 0) {
            //Borro item
            tableView.getItems().remove(indiceSeleccionado);

        } else {
            //Muestro alerta
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Atención");
            alerta.setHeaderText("Pelicula no borrada");
            alerta.setContentText("Por favor, selecciona una pelicula de la tabla");
            alerta.showAndWait();

        }

    }

    @FXML
    private void initialize() {
        tituloColumn.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());

    }

    //Es llamado por la apliación principal para tener una referencia de vuelta de si mismo
    public void setTrabajoJairo(TrabajoJairo trabajo) {

        this.trabajo = trabajo;

        tableView.setItems(trabajo.getDatosPelicula());
    }

    @FXML
    public void AñadirPelicula() {
        Pelicula temporal = new Pelicula();

        boolean guardarClicked = trabajo.mostrarAñadirPelicula(temporal);
        if (guardarClicked) {
            tableView.getItems().add(temporal);
            System.out.println("Se agregó " + temporal.getTitulo() + " correctamente");
        }

    }

    @FXML
    public void crearPDF() throws IOException {
        TrabajoJairo trabajojairo = new TrabajoJairo();
        trabajojairo.crearPDF();

    }
}

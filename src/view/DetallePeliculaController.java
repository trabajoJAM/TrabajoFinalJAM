package view;

import controller.TrabajoJairo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Pelicula;

public class DetallePeliculaController implements Initializable {

    @FXML
    Stage escenarioEdicion;

    private Pelicula peliculaSeleccionada;
    @FXML
    Label tituloLabel;
    @FXML
    TextArea descripTextArea;
    @FXML
    Label precioLabel;
    @FXML
    Label stockLabel;
    @FXML
    Label fechaDeAltaLabel;
    @FXML
    Label fechaDeModLabel;
    @FXML
    Label codigoBarrasLabel;
    @FXML
    private ImageView imageView;
    private AnchorPane vistaEditar;
    private TrabajoJairo trabajo;

    public void setEscenarioEdicion(Stage escenarioEdicion) {
        this.escenarioEdicion = escenarioEdicion;
    }

    public void initData(Pelicula pelicula) {
        peliculaSeleccionada = pelicula;
        tituloLabel.setText(peliculaSeleccionada.getTitulo());
        descripTextArea.setText(peliculaSeleccionada.getDescripcion());
        descripTextArea.setEditable(false);
        precioLabel.setText(String.valueOf(peliculaSeleccionada.getPrecio()));
        stockLabel.setText(String.valueOf(peliculaSeleccionada.getStock()));
        codigoBarrasLabel.setText(String.valueOf(peliculaSeleccionada.getCodigoBarras()));
        fechaDeAltaLabel.setText(peliculaSeleccionada.getFechaDeAlta().toString());
        imageView.setImage(peliculaSeleccionada.getImage());
    }

    public void editarPelicula(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        URL location = TrabajoJairo.class.getResource("/view/Editar.fxml");
        loader.setLocation(location);
        try {
            vistaEditar = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TrabajoJairo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Creo el escenario de edición (con modal) y establezco la escena
        Stage escenarioEditar = new Stage();
        escenarioEditar.initModality(Modality.WINDOW_MODAL);

        Scene escena = new Scene(vistaEditar);
        escenarioEditar.setScene(escena);

        //Asigno el escenario de edición y la PELICULA seleccionada al controlador
        EditarPeliculaController controller = loader.getController();
        controller.setEscenarioEditar(escenarioEditar);
        //   controller.peliculaEditada(peliculaSeleccionada);

        //Muestro el diálogo ahjsta que el ussuario lo cierre
        escenarioEditar.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}

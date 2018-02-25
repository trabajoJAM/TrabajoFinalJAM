
package view;

import controller.TrabajoJairo;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import model.Empaquetador;


public class VistaPrincipalController {

    private AnchorPane vistaGrafico, VistaBarras, VistaManual;
    //Referencia a la clase principal
    private TrabajoJairo trabajo;

    //Es llamada por la clase Principal para tener una referencia de vuelta de si misma
    public void setTrabajoJairo(TrabajoJairo trabajo) {
        this.trabajo = trabajo;
    }

    //Acerca de
    @FXML
    private void acercaDe() {
        //Muestro alerta
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Acerca de");
        alerta.setContentText("Trabajo final DAM:Stock de peliculas.Realizado por:Manuel Ros,Alvaro Martinez y Jorge López");
        alerta.showAndWait();
    }

    //Salir
    @FXML
    private void salir() {
        System.exit(0);
    }

    @FXML
    private void manual() {
        //Cargo la vista estadísticas
        FXMLLoader loader = new FXMLLoader();
        URL location = TrabajoJairo.class.getResource("../view/Manual.fxml");
        loader.setLocation(location);
        try {
            VistaManual = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TrabajoJairo.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Inicializo un nuevo escenario y asigno el principal
        Stage escenarioManual = new Stage();
        escenarioManual.setTitle("Manual");
        escenarioManual.initModality(Modality.WINDOW_MODAL);

        //Cargo la escena que contiene ese layout de estadisticas
        Scene escena = new Scene(VistaManual);
        escenarioManual.setScene(escena);
        System.out.println("");
        //Asigno el controlador
        trabajo = new TrabajoJairo();
        ManualController controller = loader.getController();

        //Muestro el escenario
        escenarioManual.show();

    }

    @FXML
    private void mostrarGrafico() {

        //Cargo la vista estadísticas
        FXMLLoader loader = new FXMLLoader();
        URL location = TrabajoJairo.class.getResource("../view/Grafico.fxml");
        loader.setLocation(location);
        try {
            vistaGrafico = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TrabajoJairo.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Inicializo un nuevo escenario y asigno el principal
        Stage escenarioEstadisticas = new Stage();
        escenarioEstadisticas.setTitle("Grafico");
        escenarioEstadisticas.initModality(Modality.WINDOW_MODAL);

        //Cargo la escena que contiene ese layout de estadisticas
        Scene escena = new Scene(vistaGrafico);
        escenarioEstadisticas.setScene(escena);
        System.out.println("");
        //Asigno el controlador
        trabajo = new TrabajoJairo();
        GraficoController controller = loader.getController();
        controller.setDatosPelicula(trabajo.getDatosPelicula());

        //Muestro el escenario
        escenarioEstadisticas.show();

    }

    //Guardo personas en un fichero
    public void guardaPeliculas() {
        File file = new File("F:\\trabajoPeliculasStock\\src\\files\\Peliculas.xml");
        try {

            //Contexto
            JAXBContext context = JAXBContext.newInstance(Empaquetador.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Empaqueto los datos de las peliculas
            Empaquetador empaquetador = new Empaquetador();
            empaquetador.setPeliculas(trabajo.getDatosPelicula());

            //Marshall y guardo XML a archivo
            m.marshal(empaquetador, file);
            System.out.println("Archivo guardado");

        } catch (Exception e) { // catches ANY exception
            //Muestro alerta
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("No se puede guardar en el archivo " + file.getPath());
            alerta.setContentText(e.toString());
            alerta.showAndWait();
        }
    }

    public void mostrarCodBarras() {

        FXMLLoader loader = new FXMLLoader();
        URL location = TrabajoJairo.class.getResource("/view/codDeBarras.fxml");
        loader.setLocation(location);

        try {
            VistaBarras = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TrabajoJairo.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Creo el escenario de edición (con modal) y establezco la escena
        Stage escenarioBarras = new Stage();
        escenarioBarras.initModality(Modality.WINDOW_MODAL);
        Scene escena = new Scene(VistaBarras);
        escenarioBarras.setScene(escena);

        //Asigno el escenario de edición y la pelicula seleccionada al controlador
        GenerarBarrasController controller = loader.getController();
        controller.setEscenarioBarras(escenarioBarras);
        trabajo = new TrabajoJairo();
        controller.datosChoiceBox(trabajo.getDatosPelicula());
        //Muestro el diálogo ahjsta que el ussuario lo cierre
        escenarioBarras.showAndWait();

    }
}

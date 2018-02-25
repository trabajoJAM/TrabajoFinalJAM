package view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Pelicula;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ManualController implements Initializable {

    private Stage escenarioManual;

    public Stage getPrimaryStage() {
        return escenarioManual;
    }

    public void crearPDFManual() throws IOException {

        //Creo un nuevo documento, una página y la añado
        PDDocument documento = new PDDocument();
        PDPage pagina = new PDPage();
        documento.addPage(pagina);
        documento.getPage(0);

        //Inicio un nuevo stream de contenido
        PDPageContentStream contentStream = new PDPageContentStream(documento, pagina);

        //Establezco la posición Y de la primera líena y el tipo de fuente
        int linea = 700;

        contentStream.setFont(PDType1Font.TIMES_ITALIC, 23);

        contentStream.beginText();
        contentStream.newLineAtOffset(250, linea);
        contentStream.showText("MANUAL");
        //contentStream.showText(p.getDireccion()+" ");
        contentStream.endText();
        //Cambio de línea
        linea -= 30;
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 13);
        //Inicio un nuevo texto y escribo los datos
        contentStream.beginText();
        contentStream.newLineAtOffset(25, linea);
        contentStream.showText("En esta aplicacion, es un control de Stock de películas. Entre sus funcionalidades podemos encontrar un lista ");
        //contentStream.showText(p.getDireccion()+" ");
        contentStream.endText();
        //Cambio de línea
        linea -= 19;
        contentStream.beginText();
        contentStream.newLineAtOffset(25, linea);
        contentStream.showText("completa de películas, donde con un boton de DETALLE, se nos abrirá una ventana nueva en la que ");
        //contentStream.showText(p.getDireccion()+" ");
        contentStream.endText();
        //Cambio de línea
        linea -= 19;
        contentStream.beginText();
        contentStream.newLineAtOffset(25, linea);
        contentStream.showText("podemos encontrar: Titulo pelicula, una sinapsis, el precio, el stock, imagenes... También existe la posibilidad ");
        //contentStream.showText(p.getDireccion()+" ");
        contentStream.endText();
        //Cambio de línea
        linea -= 19;
        contentStream.beginText();
        contentStream.newLineAtOffset(25, linea);
        contentStream.showText("de sacar en un PDF todo el listado de películas y su correspodiente código de barras para llevar un control");
        //contentStream.showText(p.getDireccion()+" ");
        contentStream.endText();
        //Cambio de línea
        linea -= 19;
        contentStream.beginText();
        contentStream.newLineAtOffset(25, linea);
        contentStream.showText("sobre las películas.Tambien podras realizar la operacion de Añadir una nueva película si lo deseas con su titulo");
        //contentStream.showText(p.getDireccion()+" ");
        contentStream.endText();
        //Cambio de línea
        linea -= 19;
        contentStream.beginText();
        contentStream.newLineAtOffset(25, linea);
        contentStream.showText(",sinapsis,imagen,stock...");
        //contentStream.showText(p.getDireccion()+" ");
        contentStream.endText();
        //Cambio de línea
        linea -= 19;
        contentStream.beginText();
        contentStream.newLineAtOffset(25, linea);
        contentStream.showText("");
        //contentStream.showText(p.getDireccion()+" ");
        contentStream.endText();
        //Cambio de línea
        linea -= 19;
        contentStream.beginText();
        contentStream.newLineAtOffset(25, linea);
        contentStream.showText("Además cuenta con un gráfico para una previsualizacion más rápida y global de todos los productos.");
        //contentStream.showText(p.getDireccion()+" ");
        contentStream.endText();
        //Cambio de línea
        linea -= 19;
        contentStream.beginText();
        contentStream.newLineAtOffset(25, linea);
        contentStream.showText("Es interesante añadir, que cuenta con un generador de código de barras,donde se podrá seleccionar la película");
        //contentStream.showText(p.getDireccion()+" ");
        contentStream.endText();
        //Cambio de línea
        linea -= 19;
        contentStream.beginText();
        contentStream.newLineAtOffset(25, linea);
        contentStream.showText("y la cantidad del mismo título, agregandolo a un documento PDF listo para imprimir si lo deseas.");
        //contentStream.showText(p.getDireccion()+" ");
        contentStream.endText();
        //Cambio de línea
        linea -= 19;
        contentStream.beginText();
        contentStream.newLineAtOffset(25, linea);
        contentStream.showText("La ubicacion de todas estas funcionalidades, se encuentran en el menu de arriba de la aplicación.");
        //contentStream.showText(p.getDireccion()+" ");
        contentStream.endText();
        //Cambio de línea
        linea -= 45;
        contentStream.beginText();
        contentStream.newLineAtOffset(250, linea);
        contentStream.showText("Trabajo realizado por: Jorge Lopez, Alvaro Martinez y Manuel Ros");
        //contentStream.showText(p.getDireccion()+" ");
        contentStream.endText();
        //Cambio de línea
        

        //Cierro el content stream
        contentStream.close();

        //INicio el file chooser
        FileChooser fileChooser = new FileChooser();

        //Filtro para la extensión
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        //Muestro el diálogo de guardar
        File archivo = fileChooser.showSaveDialog(getPrimaryStage());

        if (archivo != null) {

            //Me aseguro de que tiene la extensión correcta y si no la cambio
            String extension = "";
            if (!archivo.getPath().endsWith(extension)) {
                extension = ".pdf";
            }
            //Escribo en el archivo y lo cierro
            archivo = new File(archivo.getPath() + extension);
            documento.save(archivo);
            documento.close();

        }

        //Abro el archivo en el visor de PDF del sistema
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(archivo);
            } catch (IOException ex) {
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

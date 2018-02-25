
package controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pelicula;
import view.VistaListaPeliculaController;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import view.AñadirPeliculaController;
import view.DetallePeliculaController;
import view.GenerarBarrasController;

public class TrabajoJairo extends Application {

    private Stage escenarioPrincipal;
    private BorderPane layoutPrincipal;
    private AnchorPane vistaListaPelicula, vistaPelicula, VistaBarras, VistaAñadir;
    private final ObservableList datosPelicula = FXCollections.observableArrayList();

    public TrabajoJairo() {

        String[] tituloPeliculas = new String[]{"Coco", "La llamada", "Bataman", "Thor", "La momia", "Star Wars", "Up", "It(eso)", "Veronica", "Bright", "Dunkerque",
            "Saw", "Deadpool", "Tadeo jones", "Yo soy la juani", "Tiburon", "Dumbo", "Indiana jones", "Interstellar",
            "El exorcista", "Wonder woman", "Superman", "Vaiana", "La fiesta de las salchichas", "El renacido", "300"};

        String[] descripciones = new String[]{"Miguel es un joven con el sueño de convertirse en leyenda de la música a pesar de la prohibición de su familia. Su pasión le llevará a adentrarse en la \"Tierra de los Muertos\" para conocer su verdadero legado familiar", "Ambas sienten pasión por el reggaeton y el electro-latino, pero las sorprendentes apariciones de Dios a María comenzarán a cambiar sus vidas...,",
            " Batman se propone destruir el crimen organizado en la ciudad de Gotham. El triunvirato demuestra su eficacia, pero, de repente, aparece Joker..", "Thor está preso al otro lado del universo sin su poderoso martillo y se enfrenta a una carrera contra el tiempo. Su objetivo es volver a Asgard y parar el Ragnarok", "A pesar de estar enterrada en una tumba en lo más profundo del desierto, una antigua princesa cuyo destino le fue arrebatado injustamente, se despierta en la época actual, trayendo consigo una maldición",
            "La malvada Primera Orden se ha vuelto más poderosa y tiene contra las cuerdas a la Resistencia, liderada por la General Leia Organa. El piloto Poe Dameron encabeza una misión para intentar destruir un acorazado de la Primera Orden", "Carl Fredricksen es un viudo vendedor de globos de 78 años que, finalmente, consigue llevar a cabo el sueño de su vida: enganchar miles de globos a su casa y salir volando rumbo a América del Sur.",
            "Cuando empiezan a desparecer niños en el pueblo de Derry, un pandilla de amigos lidia con sus mayores miedos al enfrentarse a un malvado payaso llamado Pennywise", "Inspirada en una historia real sucedida en el madrileño barrio de Vallecas en los años 90. Tras hacer una ouija con unas amigas, una adolescente es asediada por aterradoras presencias", "Ambientada en un presente alternativo donde los seres humanos, orcos, elfos y hadas han convivido desde el inicio de los tiempos, dos policías con perfiles muy distintos patrullan juntos",
            "Un grupo de amigos celebran una cena que tiene un carácter anual. Pero no cuentan con el comienzo de la Segunda Guerra Mundial, que los separará en distintos bandos..", "Jigsaw está de vuelta. En esta ocasión atrapará a cinco personas y las enfrentará en una serie de juegos sangrientos como castigo por sus delitos", "Deadpool narra el origen de un ex-operativo de la fuerzas especiales llamado Wade Wilson, reconvertido a mercenario, y que tras ser sometido a un cruel experimento adquiere poderes de curación rápida",
            "Tadeo Jones viaja hasta Las Vegas para asistir a la presentación del último descubrimiento de la arqueóloga Sara Lavroff: el papiro que demuestra la existencia del Collar de Midas..", "Juani es una adolescente del extrarradio que, además de tener problemas en su casa, tiene un novio muy celoso e indeciso con el que discute constantemente.Lo primero que se propone es ser actriz", "En la costa de un pequeño pueblo del Este de los Estados Unidos, un enorme tiburón ataca a varias personas.un veterano cazador de tiburones, un oceanógrafo y el jefe de la policía local se unen para capturar al escualo",
            "La señora Dumbo, una elefanta, descubre que su bebé tiene unas orejas enormes; todas sus compañeras se ríen de él, pero la señora Dumbo lo defiende siempre, hasta el punto de ser encerrada por enfrentarse a todo aquel que se mofe de su retoño, gracias a la ayuda de un raton se convierte en una estrella de circo", "Indiana Jones es un profesor de arqueología, dispuesto a correr peligrosas aventuras con tal de conseguir valiosas reliquias históricas. Después de una infructuosa misión en Sudamérica, el gobierno estadounidense le encarga la búsqueda del Arca de la Alianza",
            "Al ver que la vida en la Tierra está llegando a su fin, un grupo de exploradores dirigidos por el piloto Cooper y la científica Amelia emprende una misión: viajar más allá de nuestra galaxia para descubrir algún planeta en otra que pueda garantizar el futuro de la raza humana.", "relata el primer encuentro del padre Lankester Marin (Stellan Skarsard) con el diablo, en África, durante la Segunda Guerra Mundial.",
            "Antes de ser Wonder Woman era Diana, princesa de las Amazonas, entrenada para ser una guerrera invencible.Hasta que un día un piloto norteamericano, que tiene un accidente y acaba en sus costas, le habla de la Primera Guerra Mundial.Diana decide salir convencida de que puede detener la terrible amenaza. Diana descubre todos sus poderes y su verdadero destino.", "comienza desde niño a desarrollar poderes sobrehumanos, y al llegar a la edad adulta llega a la conclusión de que esos poderes le exigen grandes responsabilidades, para proteger no sólo a los que quiere, sino también para representar una esperanza para el mundo.",
            " una joven que desea explorar el mundo navegando por el océano. Ella es la única hija de un importante capitán que pertenece a una familia con varias generaciones de marinos", "Narra la trágica vida de Vlad, qué dilemas tuvo que afrontar y cómo se convirtió en un vampiro.", "En las profundidades de la América salvaje, el explorador Hugh Glass participa junto a su hijo mestizo Hawk en una expedición de tramperos que recolecta pieles. Glass resulta gravemente herido por el ataque de un oso y es abandonado a su suerte por un traicionero miembro de su equipo, tendrá que sobrevivir",
            "Adaptación del cómic de Frank Miller (autor del cómic 'Sin City') sobre la famosa batalla de las Termópilas (480 a.C.). El objetivo de Jerjes, emperador de Persia, era la conquista de Grecia, Dada la gravedad de la situación, el rey Leónidas de Esparta y 300 espartanos se enfrentaron a un ejército persa que era inmensamente superior."};
        int[] precios = new int[]{21, 25, 16, 45, 32, 14, 2, 46, 23, 26, 28, 31, 36, 16, 13, 24, 65, 23, 32, 32, 14, 24, 16, 20, 25};
        int[] stocks = new int[]{13, 1, 0, 45, 12, 6, 4, 7, 69, 32, 2, 12, 32, 14, 5, 0, 12, 11, 32, 26, 24, 12, 32, 1, 25};
        int[] codigoBarras = new int[]{59992064, 45531156, 39258138, 85962021, 80719160, 84320324, 49768194, 41516112, 87007764, 87651848, 23726107, 90327952, 18280560, 43975910, 12351646, 63009412, 71397049, 13866474, 91411107, 92023629, 81826735, 64410246, 65891230, 52575752, 57247716};
        LocalDate[] fechaDeAltas = new LocalDate[]{LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15), LocalDate.of(2018, 02, 15)};

        for (int i = 0; i < 25; i++) {
            Image foto = new Image("/imgs/" + tituloPeliculas[i] + ".png");
            datosPelicula.add(new Pelicula(tituloPeliculas[i], descripciones[i], precios[i], stocks[i], codigoBarras[i], fechaDeAltas[i], fechaDeAltas[i], foto));
        }

    }

    //Método para devolver los datos como lista observable de peliculas
    public ObservableList getDatosPelicula() {

        return datosPelicula;
    }

    @Override
    public void start(Stage escenarioPrincipal) {

        //Debo hacerlo para que luego me funcione en l carga de escenas
        this.escenarioPrincipal = escenarioPrincipal;

        //Establezco el título
        this.escenarioPrincipal.setTitle("Stock peliculas");

        //Inicializo el layout principal
        initLayoutPrincipal();

        //Muestro la vista persona
        muestraVistaListaPelicula();
    }

    public void initLayoutPrincipal() {

        //Cargo el layout principal a partir de la vista VistaPrincipal.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = TrabajoJairo.class.getResource("../view/VistaPrincipal.fxml");
        loader.setLocation(location);
        try {
            layoutPrincipal = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TrabajoJairo.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Cargo y muestro la escena que contiene ese layout principal
        Scene escena = new Scene(layoutPrincipal);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();

    }

    public void muestraVistaListaPelicula() {

        //Cargo la vista persona a partir de VistaListaPeliculas.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = TrabajoJairo.class.getResource("../view/VistaListaPeliculas.fxml");
        loader.setLocation(location);
        try {
            vistaListaPelicula = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TrabajoJairo.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Añado la vista al centro del layoutPrincipal
        layoutPrincipal.setCenter(vistaListaPelicula);

        //Doy acceso al controlador VistaPersonaCOntroller a LibretaDirecciones
        VistaListaPeliculaController controller = loader.getController();
        controller.setTrabajoJairo(this);

    }

    public boolean mostrarAñadirPelicula(Pelicula pelicula) {
        FXMLLoader loader = new FXMLLoader();
        URL location = TrabajoJairo.class.getResource("/view/AñadirPelicula.fxml");
        loader.setLocation(location);
        try {
            VistaAñadir = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TrabajoJairo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Creo el escenario de edición (con modal) y establezco la escena
        Stage escenarioAñadir = new Stage();
        escenarioAñadir.initModality(Modality.WINDOW_MODAL);
        Scene escena = new Scene(VistaAñadir);
        escenarioAñadir.setScene(escena);

        //Asigno el escenario de edición y la pelicula seleccionada al controlador
        AñadirPeliculaController controller = loader.getController();
        controller.setEscenarioAñadir(escenarioAñadir);
        controller.setPelicula(pelicula);

        //Muestro el diálogo ahjsta que el ussuario lo cierre
        escenarioAñadir.showAndWait();
        return controller.isGuardarClicked();

    }

    public void generarCodigo(String titulo, int cantidad) throws DocumentException, FileNotFoundException {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(titulo);
        int codigoBarras = 0;
        List<Pelicula> peliculas = datosPelicula;
        for (Pelicula p : peliculas) {
            String titulo2 = p.getTitulo();
            if (titulo == titulo2) {
                codigoBarras = p.getCodigoBarras();
            }
        }
        Document doc = new Document();
        PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream("codigos.pdf"));
        doc.open();
        for (int j = 0; j < cantidad; j++) {
            Barcode39 code = new Barcode39();
            code.setCode(Integer.toString(codigoBarras));
            com.itextpdf.text.Image img = code.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);

            doc.add(img);

        }

        doc.close();
        System.out.println("se ha genrado el pdf");
    }

    //Invoco el método getPrimaryStage para que devuelva mi escenario principal
    public Stage getPrimaryStage() {
        return escenarioPrincipal;
    }

    public void crearPDF() throws IOException {

        
        //Creo un nuevo documento, una página y la añado
        PDDocument documento = new PDDocument();
        PDPage pagina = new PDPage();
        documento.addPage(pagina);
        documento.getPage(0);

        //Inicio un nuevo stream de contenido
        PDPageContentStream contentStream = new PDPageContentStream(documento, pagina);

        //Establezco la posición Y de la primera líena y el tipo de fuente
        int linea = 700;
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

        //Recorro la lista de peliculas
        List<Pelicula> pelicula = datosPelicula;
        for (Pelicula p : pelicula) {
            //Inicio un nuevo texto y escribo los datos
            contentStream.beginText();
            contentStream.newLineAtOffset(25, linea);
            contentStream.showText("Pelicula: " + p.getTitulo() + "." + " ");
            contentStream.showText("Codigo de barras: " + p.getCodigoBarras() + " ");
            //contentStream.showText(p.getDireccion()+" ");
            contentStream.endText();
            //Cambio de línea
            linea -= 25;
        }

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
    
    

    //Método main
    public static void main(String[] args) {
        launch(args);
    }

}

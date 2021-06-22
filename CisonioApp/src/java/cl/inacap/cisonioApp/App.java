package cl.inacap.cisonioApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import java.util.List;

import cl.inacap.cisonioApp.model.dto.Cliente;
import cl.inacap.cisonioApp.model.repository.ClienteCustomRepository;
import cl.inacap.cisonioApp.model.repository.ClienteRepository;
import cl.inacap.cisonioApp.model.repository.RepositoryFactory;
import cl.inacap.cisonioApp.model.services.ClienteServices;
/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    	Cliente cliente;
        scene = new Scene(loadFXML("primary"), 800, 480);
        scene.getStylesheets().add("/Colores.css");
        JMetro jmetro = new JMetro(Style.LIGHT);
        jmetro.setScene(scene);
        stage.setMinWidth(800.0);
        stage.setMinHeight(440.0);
        stage.setScene(scene);
        stage.show();
        javafx.scene.text.Font.getFamilies().forEach(System.out::println);
        ClienteServices clienteDAO = new ClienteServices();
        long start = System.currentTimeMillis();
        List<Cliente> clientes = clienteDAO.getAll();
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        
        long start1 = System.currentTimeMillis();
        List<Cliente> clientes2 = clienteDAO.getAll();
        long end2 = System.currentTimeMillis();
        long timeTaken2 = end2 - start1;
        long start3 = System.currentTimeMillis();
        List<Cliente> clientes3 = clienteDAO.getAll("ce");
        long end3 = System.currentTimeMillis();
        long timeTaken3 = end3 - start3;
        System.out.println("Query took " + timeTaken + "ms");
        System.out.println("2 Query took " + timeTaken2 + "ms");
        System.out.println("3 Query took " + timeTaken3 + "ms");
        
        
        
        
    }
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/cl/inacap/cisonioApp/fx/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static void newPage(String fxml, Stage padre)throws IOException {
    	Stage stage = new Stage();
    	Scene hija = new Scene(loadFXML(fxml), 540, 800);
        hija.setFill(Color.TRANSPARENT);
        JMetro jmetro = new JMetro(Style.LIGHT);
        jmetro.setScene(hija);
        stage.setMinWidth(500.0);
        stage.setMinHeight(800.0);
        try {
			stage.initOwner(padre);
			System.out.println("done");
		} catch (Exception e) {
			System.out.println("doh");
			e.printStackTrace();
			System.out.println("doh");
		}
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(hija);
        stage.show();
        
    }
}
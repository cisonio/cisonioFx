package cl.inacap.cisonioApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;
import com.sun.javafx.application.LauncherImpl;

import cl.inacap.cisonioApp.controller.Controller;
import cl.inacap.cisonioApp.controller.PrimaryController;
import cl.inacap.cisonioApp.utils.ResizeHelper;
/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public final static Rectangle2D PANTALLA  = Screen.getPrimary().getBounds();
    

    @Override
    public void start(Stage stage) throws IOException {
    	JMetro jmetro = new JMetro(Style.LIGHT);
    	FXMLLoader loader = new FXMLLoader(App.class.getResource("/cl/inacap/cisonioApp/fx/primary.fxml"));
    	PrimaryController controller= new PrimaryController();
    	loader.setController(controller);
    	scene = new Scene(loader.load(), 800, 480);
        jmetro.setScene(scene);
        stage.setMinWidth(800.0);
        stage.setMaxHeight(PANTALLA.getMaxY());
        stage.setMaxWidth(PANTALLA.getMaxX());
        stage.setMinHeight(440.0);
        stage.setScene(scene);
        stage.show();   
    }
    
    @Override
    public void init() throws Exception {
		Services.load();
    }

      

	static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }

    public static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        return fxmlLoader;
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(App.class, MyPreloader.class, args);
    }
    
    
    public static <T extends Controller> Stage newPage(String fxml, Stage padre, T controller)throws IOException {
    	Stage stage = new Stage();
    	FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
    	controller.setPadre(padre);
    	controller.setSelf(stage);
    	loader.setController(controller);
    	Scene hija = new Scene(loader.load(), 720, 800);
        hija.setFill(Color.TRANSPARENT);
        JMetro jmetro = new JMetro(Style.LIGHT);
        jmetro.setScene(hija);
        stage.setMinWidth(720.0);
        stage.setMinHeight(800.0);
        stage.setMaxHeight(PANTALLA.getMaxY());
        stage.setMaxWidth(PANTALLA.getMaxX());
        stage.initOwner(padre);
        System.out.println(PANTALLA);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(hija);
        ResizeHelper.addResizeListener(stage);
        stage.show();  
        return stage;
    }
    

}
package cl.inacap.cisonioApp;

import java.io.IOException;

import cl.inacap.cisonioApp.controller.LoadingController;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class MyPreloader extends Preloader {
	
		 private static final double WIDTH = 300;
		 private static final double HEIGHT = 200;

		    private Stage preloaderStage;
		    private Scene scene;


		    public MyPreloader() {
		       
		    }

		    @Override
		    public void init() throws Exception {
		        Platform.runLater(() -> {
		            FXMLLoader loader = new FXMLLoader(App.class.getResource("/cl/inacap/cisonioApp/fx/loading.fxml"));
		            LoadingController controller = new LoadingController();
		        	loader.setController(controller);
		        	try {
						scene = new Scene(loader.load(),300,200);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	JMetro jmetro = new JMetro(Style.LIGHT);
		            jmetro.setScene(scene);
		        });
		    }

		    @Override
		    public void start(Stage primaryStage) throws Exception {
		      

		        this.preloaderStage = primaryStage;
		        preloaderStage.centerOnScreen();
	        	preloaderStage.setMinWidth(300.0); 
	        	preloaderStage.setMinHeight(200.0);
	        	preloaderStage.initStyle(StageStyle.UNDECORATED);
	        	preloaderStage.setScene(scene);
		        preloaderStage.show();
		    }

		    
		    @Override
		    public void handleStateChangeNotification(StateChangeNotification info) {
		        // Handle state change notifications.
		        StateChangeNotification.Type type = info.getType();
		        switch (type) {
		            case BEFORE_LOAD:
		                // Called after MyPreloader#start is called.

		                break;
		            case BEFORE_INIT:
		                // Called before MyApplication#init is called.

		                break;
		            case BEFORE_START:

		                preloaderStage.hide();
		                break;
		        }
		    }
		}

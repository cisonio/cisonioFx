package cl.inacap.cisonioApp;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrimaryController {

	@FXML
	private VBox sideBarBox;
	
	@FXML
	private AnchorPane contentPane;
	
	@FXML
	private Label crearNuevoPedido;
	
	
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
   
    @FXML
    private void openNewPedido() throws IOException {
    	//Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    	Stage stage;
		try {
			stage = (Stage) crearNuevoPedido.getScene().getWindow();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("stage issue");
			stage = null;
		}
    	App.newPage("pedidos",stage);
    }
}

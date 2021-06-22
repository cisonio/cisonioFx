package cl.inacap.cisonioApp;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class PrimaryController controla a la escena primary
 */
public class PrimaryController {

	/** The side bar box es la barra de menu que contiene atajos a las funcionalidades de la aplicacion */
	@FXML
	private VBox sideBarBox;
	
	/** en el anchor pane contenPane va el contenido de la pestana */
	@FXML
	private AnchorPane contentPane;
	
	/** The crear nuevo pedido label permite abrir una nueva transaccion de pedido */
	@FXML
	private Label crearNuevoPedido;
	
   
    /**
     * Open new pedido abre un nuevo stage con la scena de nuevo pedido.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
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
    	App.newPage("/cl/inacap/cisonioApp/fx/pedidos.fxml",stage);
    }
}

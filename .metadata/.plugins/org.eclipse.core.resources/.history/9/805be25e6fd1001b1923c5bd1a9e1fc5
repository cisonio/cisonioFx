package cl.inacap.cisonioApp;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro.FlatAlert;
import jfxtras.styles.jmetro.FlatChoiceDialog;
import jfxtras.styles.jmetro.MDL2IconFont;

public class PedidoController {
	@FXML
	private AnchorPane top;
	private double xOffset;
	private double yOffset;
	
	@FXML
	private VBox back;

	@FXML
	private Button aceptarBtn;

	@FXML
	private Button cancelarBtn;

	@FXML
	private HBox pane01Info;

	@FXML
	private HBox pane02Productos;

	@FXML
	private HBox pane03Clasificar;

	@FXML
	private HBox pane04Done;

	MDL2IconFont flecha = new MDL2IconFont("\uE72B");
	
	private int page = 1;

	@FXML
	public void panePressed(MouseEvent me) {
		Stage stage = (Stage) top.getScene().getWindow();
		xOffset = me.getScreenX() - stage.getX();
		yOffset = me.getScreenY() - stage.getY();
	}

	@FXML
	public void paneDragged(MouseEvent me) {
		Stage stage = (Stage) top.getScene().getWindow();
		stage.setX(me.getScreenX() - xOffset);
		stage.setY(me.getScreenY() - yOffset);
	}

	@FXML
	public void avanzar() {
		switch (page) {
		case 1: //pasar del
			pane01Info.toBack();
			System.out.println(page);
			break;
		case 2:
			pane02Productos.toBack();
			System.out.println(page);
			break;
		case 3:
			pane03Clasificar.toBack();
			System.out.println(page);
			break;
		default:
			break;
		}
		page++;
	}

	@FXML
	public void retroceder() {
		switch (page) {
		case 2:
			pane01Info.toFront();
			System.out.println(page);
			break;
		case 3:
			pane02Productos.toFront();
			System.out.println(page);
			break;
		case 4:
			pane03Clasificar.toFront();
			System.out.println(page);
			break;
		default:
			break;
		}
		page--;

	}
	
	@FXML
    public void initialize() {
		flecha.setSize(30);
        back.getChildren().add(flecha);
    }
	
	@FXML
	public void close(ActionEvent event) {
		FlatAlert alert = new FlatAlert(
				AlertType.CONFIRMATION,
				"¿Está seguro de que desea cancelar la operación?", 
				ButtonType.YES, ButtonType.NO);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			 Stage stage = (Stage) cancelarBtn.getScene().getWindow();
			 stage.close();
		}
	   
	}

}

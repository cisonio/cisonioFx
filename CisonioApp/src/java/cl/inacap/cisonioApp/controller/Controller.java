package cl.inacap.cisonioApp.controller;

import cl.inacap.cisonioApp.App;
import cl.inacap.cisonioApp.utils.Utils;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import jfxtras.styles.jmetro.FlatAlert;
import jfxtras.styles.jmetro.MDL2IconFont;

public class Controller {
	protected Stage self;
	protected Window padre;
	protected Stage hijo;
	protected boolean windowed = true;
	
	static Utils cu = new Utils();
	/** The flecha es el icono del boton retroceder*/
	protected MDL2IconFont flecha = new MDL2IconFont("\uE72B");
	
	/** The cerrar es el icono del boton cerrar*/
	MDL2IconFont cerrar = new MDL2IconFont("\uE8BB");
	
	/** The x offset es la distancia horizontal entre el punto 0 0 de la ventana y la posicion del mouse*/
	private double xOffset;
	
	/** The y offset es la distancia vertical entre el punto 0 0 de la ventana y la posicion del mouse */
	private double yOffset;

	//*************************************************************************
	//**************************clases de la ventana***************************
	/** The top es la barra superior de la ventana es arrastlable*/
	//*************************************************************************
	@FXML
	protected AnchorPane top;
	
	
	public Stage getSelf() {
		return self;
	}

	/** The back es el panel contenedor de los card del escenario */
	@FXML
	protected VBox back;
	
	/** The back es el panel contenedor de los card del escenario */
	@FXML
	protected VBox close;
	
	/** title es el titulo de la nueva ventana*/
	@FXML
	protected Label title;
	
	public void setSelf(Stage self) {
		this.self = self;
	}

	public Window getPadre() {
		return padre;
	}

	public void setPadre(Window padre) {
		this.padre = padre;
	}
	
	public static void autoResizeColumns( TableView<?> table )
	{
	    //Set the right policy
	    table.setColumnResizePolicy( TableView.UNCONSTRAINED_RESIZE_POLICY);
	    table.getColumns().stream().forEach( (column) ->
	    {
	        //Minimal width = columnheader
	        Text t = new Text( column.getText() );
	        double max = t.getLayoutBounds().getWidth();
	        for ( int i = 0; i < table.getItems().size(); i++ )
	        {
	            //cell must not be empty
	            if ( column.getCellData( i ) != null )
	            {
	                t = new Text( column.getCellData( i ).toString() );
	                double calcwidth = t.getLayoutBounds().getWidth();
	                //remember new max-width
	                if ( calcwidth > max )
	                {
	                    max = calcwidth;
	                }
	            }
	        }
	        //set the new max-widht with some extra space
	        column.setPrefWidth( max + 10.0d );
	    } );
	}
	
	@FXML
	public void panePressed(MouseEvent me) {
		if(me.getClickCount() == 2) {
			if(!windowed) 
				minimize();
			else 
				maximize();
			
		}else if(me.getClickCount() == 1) {
			xOffset = me.getScreenX() - self.getX();
			yOffset = me.getScreenY() - self.getY();
		}
	}
	
	public void minimize(){
		self.setWidth(720);
		self.setHeight(800);
		self.centerOnScreen();
		windowed = true;
	}
	
	/**
	 * Close cierra el escenario, pide confirmacion de cierre
	 *
	 * @param event the event
	 */
	@FXML
	public void close(ActionEvent event) {
		Alert alert = new FlatAlert(
				AlertType.CONFIRMATION,
				"Está seguro de que desea cancelar la operación?", 
				ButtonType.YES, ButtonType.NO);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			 self.close();
		}
	   
	}
	
	public void maximize() {
		self.setX(App.PANTALLA.getMinX());
		self.setY(App.PANTALLA.getMinY());
		self.setHeight(App.PANTALLA.getMaxY());
		self.setWidth(App.PANTALLA.getMaxX());		
		windowed = false;
	}
	/**
	 * Pane dragged reconoce cuando se arrastra la ventana y cambia los parametros de la ventana para
	 * arrastrarla
	 *
	 * @param me the me
	 */
	@FXML
	public void paneDragged(MouseEvent me) {
		if(windowed) {
		self.setX(me.getScreenX() - xOffset);
		self.setY(me.getScreenY() - yOffset);}
	}

		
}

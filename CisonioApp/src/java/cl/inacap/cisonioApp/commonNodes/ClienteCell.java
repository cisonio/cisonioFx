package cl.inacap.cisonioApp.commonNodes;

import cl.inacap.cisonioApp.model.dto.Cliente;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jfxtras.styles.jmetro.MDL2IconFont;

public class ClienteCell extends ListCell<Cliente> {
	private HBox content;
	private Label nombre;
	private Label telefono;

	public ClienteCell() {
		super();
		nombre = new Label();
		telefono = new Label();
		VBox vBox = new VBox(nombre, telefono);
		MDL2IconFont imagen = new MDL2IconFont("\uE77B");
		imagen.setSize(30);
		content = new HBox(imagen, vBox);
		content.setSpacing(10);
	}
	
	 @Override
     protected void updateItem(Cliente item, boolean empty) {
         super.updateItem(item, empty);
         if (item != null && !empty) { // <== test for null item and empty parameter
             nombre.setText(item.getNombre());
             telefono.setText(item.getTelefono());
         } 
     }
}

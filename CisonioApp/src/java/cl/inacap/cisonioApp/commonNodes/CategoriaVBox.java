package cl.inacap.cisonioApp.commonNodes;

import cl.inacap.cisonioApp.model.dto.productos.Categoria;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import jfxtras.styles.jmetro.MDL2IconFont;

public class CategoriaVBox extends VBox{
	private Categoria categoria;
	MDL2IconFont cerrar = new MDL2IconFont("\uE8BB");
	
	public CategoriaVBox(Categoria cat) {
		this.categoria = cat;
		nombre.setText(cat.getNombre());
		this.getChildren().add(cerrar);
		this.getChildren().add(nombre);
		this.setSpacing(5);
		this.setPadding(new Insets(10));
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	private Label nombre;

	
	
	
}

package cl.inacap.cisonioApp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.cisonioApp.Services;
import cl.inacap.cisonioApp.model.dto.productos.Descripcion;
import cl.inacap.cisonioApp.model.dto.productos.Detalle;
import cl.inacap.cisonioApp.model.dto.productos.Producto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GuardarProductosController extends Controller {
	@FXML
	TableView<Producto> productosTV;
	@FXML
	TableColumn<Producto, Integer> precioCol;
	@FXML
	TableColumn<Producto, Integer> codigoCol;
	@FXML
	TableColumn<Producto, String> fechaVencimientoCol;
	@FXML
	TableColumn<Producto, String> nombreCol;
	@FXML
	StackPane stackPane;
	@FXML
	Label totalLbl;
	@FXML
	VBox vbox;
	private List<Detalle> detalles;
	private List<Producto> existencias = new ArrayList<Producto>();

	public GuardarProductosController() {

	}

	@FXML
	private void initialize() {
		if (detalles != null) {
			createProductos();
			initializetable();
		}
	}

	private void createProductos() {
		int total = 0;
		for (Detalle d : detalles) {
			total += d.getCantidad()*d.getPrecioCompra();
			for (int i = 0; i < d.getCantidad(); i++) {
				Producto p = new Producto();
				p.setFechaCompra(LocalDate.now());
				p.setPrecio((int) d.getPrecioCompra());
				p.setDescripcion(d.getProducto());
				existencias.add(p);
			}
		}totalLbl.setText(total+"");
	}

	private void initializetable() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-yy");
		DateTimeFormatter format2 = DateTimeFormatter.ofPattern("M-yy-dd");
		productosTV.setEditable(true);
		codigoCol.setCellValueFactory(data -> {
			Producto p = data.getValue();
			Descripcion d = p.getDescripcion();
			ObservableValue<Integer> codigo = new SimpleIntegerProperty(d.getCodigo()).asObject();
			return codigo;
		});
		precioCol.setCellValueFactory(data -> {
			Producto p = data.getValue();
			ObservableValue<Integer> precio = new SimpleIntegerProperty(p.getPrecio()).asObject();
			return precio;
		});
		nombreCol.setCellValueFactory(data -> {
			Producto p = data.getValue();
			Descripcion d = p.getDescripcion();
			ObservableStringValue nombre = new SimpleStringProperty(d.getNombre());
			return nombre;
		});
		fechaVencimientoCol.setCellValueFactory(data -> {
			Producto p = data.getValue();
			if (p.getFechaVencimiento() != null) {
				String fecha = p.getFechaVencimiento().format(format);
				return new SimpleStringProperty(fecha);
			}
			return new SimpleStringProperty("");
		});
		fechaVencimientoCol.setCellFactory(TextFieldTableCell.forTableColumn());
		fechaVencimientoCol.setOnEditCommit(new EventHandler<CellEditEvent<Producto, String>>() {

			@Override
			public void handle(CellEditEvent<Producto, String> event) {
				if (event.getNewValue().matches("^(0?[1-9]|1[0-2])-?([0-9]{2})$")) {
					existencias.get(event.getTablePosition().getRow())
							.setFechaVencimiento(LocalDate.parse(event.getNewValue()+"-01", format2));
				}else {
				}
				cargarTabla();
			}
		});
		
		cargarTabla();
		
	}

	private void cargarTabla() {
		productosTV.getItems().setAll(existencias);
	}

	@FXML
	public void validateFechas() {
		boolean isValido = true;
		for(Producto p : existencias) {
			if(p.getFechaVencimiento()==null)
				isValido = false;
		}
		if(isValido) {
			for(Producto p: existencias) {
				
				Services.proDAO.guardar(p);
			}
			cu.toast("Productos Guardados en Inventario", vbox);
			self.close();
		}
	}

	@FXML
	public void validateMes() {

	}

	@FXML
	public void validateAnnio() {

	}

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> productos) {
		this.detalles = productos;
	}

}

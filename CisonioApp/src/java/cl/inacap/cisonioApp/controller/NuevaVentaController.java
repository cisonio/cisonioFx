package cl.inacap.cisonioApp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import cl.inacap.cisonioApp.Services;
import cl.inacap.cisonioApp.commonNodes.AgregarClienteVBox;
import cl.inacap.cisonioApp.model.dto.Cliente;
import cl.inacap.cisonioApp.model.dto.Pago;
import cl.inacap.cisonioApp.model.dto.productos.Descripcion;
import cl.inacap.cisonioApp.model.dto.productos.Detalle;
import cl.inacap.cisonioApp.model.dto.productos.Producto;
import cl.inacap.cisonioApp.utils.FxUtilTest;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class NuevaVentaController extends Controller {
	@FXML
	TableView<Detalle> productosTV;
	@FXML
	TableColumn<Detalle, Double> precioCompra;
	@FXML
	TableColumn<Detalle, String> precioVenta;
	@FXML
	TableColumn<Detalle, Integer> cantidadCol;
	@FXML
	TableColumn<Detalle, Integer> codigoCol;
	@FXML
	TableColumn<Detalle, String> nombreCol;
	@FXML
	StackPane stackPane;
	@FXML
	Label totalLbl;
	@FXML
	VBox vbox;
	@FXML
	VBox vboxCliente;
	@FXML
	Hyperlink addHL;
	@FXML
	ComboBox<Cliente> clienteCmbox;
	@FXML
	Button aceptarBtn;
	@FXML
	HBox clientePane;
	@FXML
	HBox detallesPane;
	Cliente cliente;

	public void initialize() {
		FxUtilTest.autoCompleteComboBoxPlus(clienteCmbox, (typedText, itemToCompare) -> itemToCompare.getNombre()
				.toLowerCase().contains(typedText.toLowerCase()));
		clienteCmbox.setConverter(new StringConverter<Cliente>() {
			@Override
			public String toString(Cliente object) {
				return object != null ? object.getNombre() : "";
			}

			@Override
			public Cliente fromString(String string) {
				return clienteCmbox.getItems().stream().filter(object -> object.getNombre().equalsIgnoreCase(string))
						.findFirst().orElse(null);
			}
		});

		initializetable();
		setClientePane();
	}

	private void initializetable() {

		productosTV.setEditable(true);
		codigoCol.setCellValueFactory(data -> {
			Descripcion p = data.getValue().getProducto();
			Descripcion d = p;
			ObservableValue<Integer> codigo = new SimpleIntegerProperty(d.getCodigo()).asObject();
			return codigo;
		});
		precioCompra.setCellValueFactory(data -> {
			Double p = data.getValue().getPrecioCompra();
			ObservableValue<Double> precio = new SimpleDoubleProperty(p).asObject();
			return precio;
		});
		cantidadCol.setCellValueFactory(data -> {
			int p = data.getValue().getCantidad();
			ObservableValue<Integer> precio = new SimpleIntegerProperty(p).asObject();
			return precio;
		});
		nombreCol.setCellValueFactory(data -> {
			Detalle p = data.getValue();
			Descripcion d = p.getProducto();
			ObservableStringValue nombre = new SimpleStringProperty(d.getNombre());
			return nombre;
		});
		precioVenta.setCellValueFactory(data -> {
			Detalle p = data.getValue();
			ObservableValue<String> precio = new SimpleStringProperty(p.getPrecioVenta() + "");
			return precio;
		});
		precioVenta.setCellFactory(TextFieldTableCell.forTableColumn());
		precioVenta.setOnEditCommit(new EventHandler<CellEditEvent<Detalle, String>>() {

			@Override
			public void handle(CellEditEvent<Detalle, String> event) {
				if (event.getNewValue().matches("\\d*")) {
					detalles.get(event.getTablePosition().getRow())
							.setPrecioVenta(Double.parseDouble(event.getNewValue()));
				} else {
				}
				cargarTabla();
			}
		});
		cargarTabla();
	}

	private List<Detalle> detalles;

	@FXML
	public void validatePrecios() {
		boolean isValido = true;
		double total = 0;
		for (Detalle p : detalles) {
			total += p.getPrecioCompra();
			if (p.getPrecioCompra() <= 0)
				isValido = false;
		}
		if (isValido) {
			Pago deuda = new Pago();
			deuda.setCliente(cliente);
			deuda.setMonto((int) total);
			deuda.setFecha(LocalDate.now());
			Services.cliDAO.guardar(deuda);
			cu.toast("Productos Guardados en Inventario", vbox);
			self.close();
		}
	}

	@FXML
	public void validateCliente() {
		if (clienteCmbox.getSelectionModel().isEmpty()) {
			cu.validate("Seleccionar Cliente", vboxCliente);
		} else {
			cliente = clienteCmbox.getSelectionModel().getSelectedItem();
			detallesPane.toFront();
			aceptarBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					validatePrecios();
				}
			});
			
		}
	}

	public void agregarCliente() {
		AgregarClienteVBox aCVBox = new AgregarClienteVBox();
		vboxCliente.getChildren().add(3, aCVBox);
	}

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

	private void cargarTabla() {
		productosTV.getItems().setAll(FXCollections.observableArrayList(detalles));
		int total = 0;
		for (Detalle d : detalles) {
			total += d.getCantidad() * d.getPrecioCompra();
		}
		totalLbl.setText(total + "");
	}

	public void setClientePane() {
		clienteCmbox.getItems().setAll(Services.cliDAO.getAll());
		clientePane.toFront();
	}
}

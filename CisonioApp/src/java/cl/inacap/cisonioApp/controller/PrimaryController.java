package cl.inacap.cisonioApp.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Observable;

import com.sun.javafx.collections.ObservableSetWrapper;

import cl.inacap.cisonioApp.App;
import cl.inacap.cisonioApp.Services;
import cl.inacap.cisonioApp.commonNodes.AgregarClienteVBox;
import cl.inacap.cisonioApp.model.dto.Cliente;
import cl.inacap.cisonioApp.model.dto.Pago;
import cl.inacap.cisonioApp.model.dto.productos.Descripcion;
import cl.inacap.cisonioApp.model.dto.productos.Producto;
import cl.inacap.cisonioApp.utils.FxUtilTest;
import cl.inacap.cisonioApp.utils.Utils;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import jfxtras.styles.jmetro.MDL2IconFont;

// TODO: Auto-generated Javadoc
/**
 * The Class PrimaryController controla a la escena primary
 */
public class PrimaryController {

	Utils u = new Utils();
	/**
	 * The side bar box es la barra de menu que contiene atajos a las
	 * funcionalidades de la aplicacion
	 */
	@FXML
	private VBox sideBarBox;

	/** en el anchor pane contenPane va el contenido de la pestana */
	@FXML
	private AnchorPane contentPane;

	// pane 01 Inicio

	@FXML
	private VBox inicioBtn;

	@FXML
	private HBox pane01Inicio;
	/**
	 * The crear nuevo pedido label permite abrir una nueva transaccion de pedido
	 */
	@FXML
	private ScrollPane pane01SP;

	@FXML
	private Label crearNuevoPedido;

	@FXML
	private TableView<Producto> productosTV;

	@FXML
	private TableView<Cliente> clientesTV;

	// pane 02 Clientes

	@FXML
	private VBox clientesBtn;

	@FXML
	private VBox clienteVbox;

	@FXML
	private HBox pane02Clientes;

	@FXML
	private ScrollPane pane02SP;

	@FXML
	private ScrollPane fullClientTVSP;

	@FXML
	private TableView<Cliente> fullClientesTV;

	@FXML
	private TableColumn<Cliente, Integer> totalGastadoCol;

	@FXML
	private TableColumn<Cliente, Integer> totalPagadoCol;

	@FXML
	private TableColumn<Cliente, String> nombreClienteCol;

	@FXML
	private TableColumn<Cliente, Integer> deudaActualCol;

	@FXML
	private TableColumn<Cliente, String> nombreCliente2Col;

	@FXML
	private TableColumn<Cliente, Integer> deudaActual2Col;

	@FXML
	private ComboBox<Cliente> clienteComboBox;

	@FXML
	private TextField montoTxt;

	@FXML
	private Button agregarPagoBtn;

	@FXML
	private VBox agregarPagoVBox;

	// pane 03 Inventario

	@FXML
	private VBox inventarioBtn;

	@FXML
	private HBox pane03Inventario;

	@FXML
	private ScrollPane pane03SP;

	@FXML
	private ScrollPane fullProductoTVSP;

	@FXML
	private TableView<Producto> fullProductosTV;

	@FXML
	private TableColumn<Producto, LocalDate> fechaVencimientoCol;

	@FXML
	private TableColumn<Producto, LocalDate> fechaVencimiento2Col;

	@FXML
	private TableColumn<Producto, Integer> codigoCol;

	@FXML
	private TableColumn<Producto, String> nombreProdCol;

	@FXML
	private TableColumn<Producto, String> nombreProd2Col;

	@FXML
	private TableColumn<Producto, Integer> precioCompraCol;

	@FXML
	private TextField terminoTxt;

	@FXML
	private ChoiceBox<String> CampoCbox;

	@FXML
	private ChoiceBox<String> EmpresaCbox;

	@FXML
	private Button filtrarBtn;

	@FXML
	private FlowPane pane03HyperFlowPane;

	@FXML
	private Hyperlink agregarProdHL;

	@FXML
	private Hyperlink borrarProdHL;

	@FXML
	private Hyperlink verProdHL;

	@FXML
	private Hyperlink venderProdHL;

	@FXML
	private VBox buscarYFiltrarVBox;

	@FXML
	public void initialize() {
		fullProductoTVSP.setFitToWidth(true);
		fullClientTVSP.setFitToWidth(true);

		pane01SP.setFitToWidth(true);
		pane01SP.setFitToHeight(true);
		pane02SP.setFitToWidth(true);
		pane03SP.setFitToWidth(true);
		set01ToFront();
		pane01Inicio.toFront();
		FxUtilTest.autoCompleteComboBoxPlus(clienteComboBox, (typedText, itemToCompare) -> itemToCompare.getNombre()
				.toLowerCase().contains(typedText.toLowerCase()));
		clienteComboBox.setConverter(new StringConverter<Cliente>() {
			@Override
			public String toString(Cliente object) {
				return object != null ? object.getNombre() : "";
			}

			@Override
			public Cliente fromString(String string) {
				return clienteComboBox.getItems().stream().filter(object -> object.getNombre().equalsIgnoreCase(string))
						.findFirst().orElse(null);
			}

		});
	}

	/**
	 * Open new pedido abre un nuevo stage con la scena de nuevo pedido.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	private void openNewPedido() throws IOException {
		// Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Stage stage;
		try {
			stage = (Stage) crearNuevoPedido.getScene().getWindow();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("stage issue");
			stage = null;
		}
		PedidoController controlador = new PedidoController();
		App.newPage("/cl/inacap/cisonioApp/fx/pedidos.fxml", stage, controlador);
	}

	public void setProductoTV() {
		productosTV.getItems().setAll(Services.proDAO.productosPorVencer());
		productosTV.getSortOrder().add(fechaVencimiento2Col);
	}

	public void setClienteTV() {
		clientesTV.getItems().setAll(Services.cliDAO.getAllDeudores());
		clientesTV.getSortOrder().add(deudaActualCol);
	}

	public <T> void setTable(List<T> items, TableView<T> tabla) {
		tabla.setEditable(false);
		tabla.getItems().setAll(items);
	}

	public void setProductoColumns() {
		codigoCol.setCellValueFactory(data -> {
			Producto p = data.getValue();
			Descripcion d = p.getDescripcion();
			ObservableValue<Integer> codigo = new SimpleIntegerProperty(d.getCodigo()).asObject();
			return codigo;
		});
		nombreProdCol.setCellValueFactory(data -> {
			Producto p = data.getValue();
			Descripcion d = p.getDescripcion();
			ObservableStringValue nombre = new SimpleStringProperty(d.getNombre());
			return nombre;
		});
		nombreProd2Col.setCellValueFactory(data -> {
			Producto p = data.getValue();
			Descripcion d = p.getDescripcion();
			ObservableStringValue nombre = new SimpleStringProperty(d.getNombre());
			return nombre;
		});
		precioCompraCol.setCellValueFactory(data -> {
			Producto p = data.getValue();
			ObservableValue<Integer> precio = new SimpleIntegerProperty(p.getPrecio()).asObject();
			return precio;
		});
		fechaVencimientoCol
				.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getFechaVencimiento()));
		;
		fechaVencimientoCol.setCellFactory(column -> {
			TableCell<Producto, LocalDate> cell = new TableCell<Producto, LocalDate>() {
				private DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-yy");

				@Override
				protected void updateItem(LocalDate item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText(null);
					} else {
						setText(item.format(format));
					}
				}
			};
			return cell;
		});
		fechaVencimientoCol.setSortType(SortType.ASCENDING);
		fechaVencimiento2Col
				.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getFechaVencimiento()));
		;
		fechaVencimiento2Col.setCellFactory(column -> {
			TableCell<Producto, LocalDate> cell = new TableCell<Producto, LocalDate>() {
				private DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-yy");

				@Override
				protected void updateItem(LocalDate item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText(null);
					} else {
						setText(item.format(format));
					}
				}
			};
			return cell;
		});
		fechaVencimiento2Col.setSortType(SortType.ASCENDING);

	}

	public void editTable() {
		TableColumn<Producto, Producto> borrar = new TableColumn<Producto, Producto>("Borrar");
		TableColumn<Producto, Producto> editar = new TableColumn<Producto, Producto>("Editar");
		borrar.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		editar.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

		borrar.setCellFactory(param -> new TableCell<Producto, Producto>() {
			MDL2IconFont borrarIcon = new MDL2IconFont("\uE74D");

			@Override
			protected void updateItem(Producto objeto, boolean empty) {
				super.updateItem(objeto, empty);

				if (objeto == null) {
					setGraphic(null);
					return;
				}
				setGraphic(borrarIcon);
				borrarIcon.setOnMouseClicked(event -> {
					Services.proDAO.detele(objeto);
					getTableView().getItems().remove(objeto);
				});
			}
		});
		editar.setCellFactory(param -> new TableCell<Producto, Producto>() {
			MDL2IconFont editIcon = new MDL2IconFont("\uE70F");
			MDL2IconFont aceptarIcon = new MDL2IconFont("\uE74E");

			@Override
			protected void updateItem(Producto objeto, boolean empty) {
				super.updateItem(objeto, empty);

				if (objeto == null) {
					setGraphic(null);
					return;
				}
				setGraphic(editIcon);
				editIcon.setOnMouseClicked(event -> {
					if (getTableRow().isEditable()) {
						getTableRow().setEditable(false);
						setGraphic(editIcon);
					} else {
						getTableRow().setEditable(true);
						setGraphic(aceptarIcon);
					}
				});
			}
		});

		fullProductosTV.getColumns().add(editar);
		fullProductosTV.getColumns().add(borrar);
		agregarProdHL.setVisible(false);
		borrarProdHL.setVisible(false);
		venderProdHL.setVisible(false);
		verProdHL.setVisible(true);
	}

	public void setClienteColumns() {

		totalGastadoCol.setCellValueFactory(data -> {
			Cliente c = data.getValue();
			int gastado = 0;
			for (Pago p : c.getPagos()) {
				if (p.getMonto() < 0) {
					gastado += p.getMonto();
				}
			}
			return new ReadOnlyObjectWrapper<>(gastado);
		});

		totalPagadoCol.setCellValueFactory(data -> {
			Cliente c = data.getValue();
			int pagado = 0;
			for (Pago p : c.getPagos()) {
				if (p.getMonto() > 0) {
					pagado += p.getMonto();
				}
			}
			return new ReadOnlyObjectWrapper<>(pagado);
		});
		deudaActualCol.setSortType(SortType.ASCENDING);
		nombreClienteCol.setCellValueFactory(data -> {
			Cliente p = data.getValue();
			return new ReadOnlyStringWrapper(p.getNombre());
		});
		deudaActualCol.setCellValueFactory(data -> {
			Cliente c = data.getValue();
			int total = 0;
			for (Pago p : c.getPagos()) {
				total += p.getMonto();
			}
			return new ReadOnlyObjectWrapper<>(total);
		});
		deudaActual2Col.setSortType(SortType.ASCENDING);
		nombreCliente2Col.setCellValueFactory(data -> {
			Cliente p = data.getValue();
			return new ReadOnlyStringWrapper(p.getNombre());
		});
		deudaActual2Col.setCellValueFactory(data -> {
			Cliente c = data.getValue();
			int total = 0;
			for (Pago p : c.getPagos()) {
				total += p.getMonto();
			}
			return new ReadOnlyObjectWrapper<>(total);
		});
	}

	@FXML
	public void set02ToFront() {
		List<Cliente> clientes = Services.cliDAO.getAll();
		fullClientesTV.getItems().setAll(Services.cliDAO.getAllActive());
		clienteComboBox.setItems(FXCollections.observableArrayList(clientes));
		fullClientesTV.getSortOrder().add(deudaActualCol);
		pane02Clientes.toFront();
	}

	@FXML
	public void set03ToFront() {
		fullProductosTV.getItems().setAll(Services.proDAO.getAll());
		fullProductosTV.getSortOrder().add(fechaVencimientoCol);
		verProdHL.setVisible(false);
		pane03Inventario.toFront();
	}

	@FXML
	public void set01ToFront() {
		pane01Inicio.toFront();
		setProductoColumns();
		setClienteColumns();
		setProductoTV();
		setClienteTV();

	}

	public void agregarCliente() {
		AgregarClienteVBox aCVBox = new AgregarClienteVBox();
		clienteVbox.getChildren().add(3, aCVBox);
	}

	@FXML
	public void validarPago() {
		int errores = 0;
		Pago nuevo = new Pago();
		if (clienteComboBox.getSelectionModel().isEmpty()) {
			u.validate("Selecciones al cliente", agregarPagoVBox);
			errores++;
		} else {
			Cliente c = clienteComboBox.getValue();
			nuevo.setCliente(c);
		}
		if (!montoTxt.getText().isEmpty()) {
			if (!montoTxt.getText().matches("^-?\\d*$")) {
				u.validate("Ingrese un numero valido, si desea agregar una deuda ingrese un numero negativo",
						agregarPagoVBox);
				errores++;
			} else {
				nuevo.setMonto(Integer.parseInt(montoTxt.getText()));
			}

		} else {
			u.validate("debe ingresar un monto", agregarPagoVBox);
			errores++;
		}

		if (errores == 0) {
			try {
				nuevo.setFecha(LocalDate.now());
				Pago p = Services.cliDAO.guardar(nuevo);
				u.toast("Pago Guardado", agregarPagoVBox);
			} catch (Exception e) {
				u.validate("Pago No Guardado", agregarPagoVBox);
			}

		}
	}
}

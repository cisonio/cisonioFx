package cl.inacap.cisonioApp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cl.inacap.cisonioApp.App;
import cl.inacap.cisonioApp.Services;
import cl.inacap.cisonioApp.model.dto.productos.Descripcion;
import cl.inacap.cisonioApp.model.dto.productos.Detalle;
import cl.inacap.cisonioApp.model.dto.transacciones.Pedido;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AgregarYBuscarHBox extends HBox {

	/**
	 * The productos nuevos es la lista de descripciones de pedidos que llegaron en
	 * el pedido
	 */
	private ArrayList<Descripcion> productosNuevos = new ArrayList<Descripcion>();

	public ArrayList<Descripcion> getProductosNuevos() {
		return productosNuevos;
	}
	
	
	public void setProductosNuevos(ArrayList<Descripcion> productosNuevos) {
		this.productosNuevos = productosNuevos;
	}

	AgregarDescripcionController controller;
	// *************************************************************************
	// *******************clases ventana "Agregar Producctos"*******************
	/**
	 * The pane 02 productos es el segundo panel, donde se ingresan los productos
	 */
	// *************************************************************************

	private Stage agregarDescripciones;
	/**
	 * The productos table view es la tabla donde se obserban los productos
	 * ingresados
	 */
	@FXML
	private TableView<Detalle> productosTableView;

	/** The cantidad col es la columnna de las cantidades */
	@FXML
	private TableColumn<Detalle, String> cantidadCol;

	/** The codigo col. es la columna de los codigos */
	@FXML
	private TableColumn<Detalle, String> codigoCol;

	/** The precio col. es la columna de los precios de compra */
	@FXML
	private TableColumn<Detalle, String> precioCol;

	/** The nombre col. es la columna de los nombres */
	@FXML
	private TableColumn<Detalle, String> nombreCol;

	@FXML
	private Label totalTxt;
	
	@FXML
	ScrollPane tableSP;

	/** The agregar desc btn llama al metodo buscarYAgregar */
	@FXML
	private Button agregarDescBtn;

	/** The codigo txt permite el ingreso de los codigos de los productos nuevos */
	@FXML
	private TextField codigoTxt;

	/**
	 * The productospor clasificar son los productos del pedido que aun no han sido
	 * vendidos o guardados en el inventario
	 */
	// productos por clasificar
	private ArrayList<Detalle> detallesAgregados = new ArrayList<Detalle>();

	protected Pedido pedido;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	protected Stage self;

	public Stage getSelf() {
		return self;
	}

	public void setSelf(Stage self) {
		this.self = self;
	}

	public AgregarYBuscarHBox() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cl/inacap/cisonioApp/fx/agregarYBuscar.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
			productosTableView.setEditable(true);
			codigoCol.setCellValueFactory(data -> {
				Detalle detalle = data.getValue();
				Descripcion descripcion = detalle.getProducto();
				int codigo = descripcion.getCodigo();
				return new ReadOnlyStringWrapper(String.valueOf(codigo));
			});

			tableSP.setFitToHeight(true);
			tableSP.setFitToWidth(true);
			cantidadCol.setCellValueFactory(data -> {
				Detalle detalle = data.getValue();
				int cantidad = detalle.getCantidad();
				return new ReadOnlyStringWrapper(String.valueOf(cantidad));
			});
			nombreCol.setCellValueFactory(data -> {
				Detalle detalle = data.getValue();
				Descripcion descripcion = detalle.getProducto();
				String nombre = descripcion.getNombre();
				return new ReadOnlyStringWrapper(nombre);
			});
			precioCol.setCellValueFactory(data -> {
				Detalle detalle = data.getValue();
				String precio = String.valueOf(detalle.getPrecioCompra());
				return new ReadOnlyStringWrapper(precio);
			});
			precioCol.setCellFactory(TextFieldTableCell.forTableColumn());
			precioCol.setOnEditCommit(new EventHandler<CellEditEvent<Detalle, String>>() {

				@Override
				public void handle(CellEditEvent<Detalle, String> t) {
					try {
						getProductosporClasificar().get(t.getTablePosition().getRow())
								.setPrecioCompra(Double.parseDouble(t.getNewValue()));
						productosTableView.getFocusModel().focus(t.getTablePosition().getRow() + 1, precioCol);
					} catch (Exception e) {
						return;
					}
					setProductoTable();
				}
			});
			cantidadCol.setCellFactory(TextFieldTableCell.forTableColumn());
			cantidadCol.setOnEditCommit(new EventHandler<CellEditEvent<Detalle, String>>() {

				@Override
				public void handle(CellEditEvent<Detalle, String> t) {
					int precioNuevo = -1;
					try {
						precioNuevo = Integer.parseInt(t.getNewValue());
					} catch (Exception e) {
						return;
					}
					if (precioNuevo > 0) {
						getProductosporClasificar().get(t.getTablePosition().getRow()).setCantidad(precioNuevo);

					} else if (precioNuevo == 0) {
						Detalle aRemover = getProductosporClasificar().get(t.getTablePosition().getRow());
						getProductosporClasificar().remove(aRemover);
						productosNuevos.remove(aRemover.getProducto());
					}
					setProductoTable();

				}
			});
			System.out.println("uup");
		} catch (IOException exc) {
			System.out.println(exc);
		}
	}

	/**
	 * Buscar Y agregar.
	 * 
	 * @param string
	 */
	@FXML
	public void buscarYAgregar() {
		if (codigoTxt.getText().isEmpty()) {
			return;
		}
		int cod = 0;
		try {
			cod = Integer.parseInt(codigoTxt.getText().trim());
			buscarYAgregar(cod);
		} catch (Exception e) {
			return;
		}
	}

	public void buscarYAgregar(int codigo) {

		Descripcion producto_agregar = null;
		boolean repetido = false;
		for (Detalle d : detallesAgregados) {
			if (d.getProducto().getCodigo() == codigo) {
				d.setCantidad(d.getCantidad() + 1);
				repetido = true;
			}
		}
		if(!repetido)
			{
			try {
				producto_agregar = Services.desDAO.get(codigo).get();
			} catch (Exception e) {
				producto_agregar = null;
			}
			// si no encuentro el producto en la base de datos, creo un placeholder
			if (producto_agregar == null) {
				// Producto no encontrado agregar??
				producto_agregar = new Descripcion();
				producto_agregar.setCodigo(codigo);
				producto_agregar.setNombre("?????");
				if (pedido != null)
					producto_agregar.setEmpresa(pedido.getEmpresa());
				productosNuevos.add(producto_agregar);
			}
			Detalle nuevo_detalle = new Detalle();
			nuevo_detalle.setCantidad(1);
			nuevo_detalle.setProducto(producto_agregar);
			nuevo_detalle.setTransaccion(pedido);

			detallesAgregados.add(nuevo_detalle);
		}
		setProductoTable();
	}

	/**
	 * Sets the producto table.
	 */
	public void setProductoTable() {
		int total = 0;
		for(Detalle d : detallesAgregados) {
			total += d.getCantidad()*d.getPrecioCompra(); 
		}
		totalTxt.setText(total + "");
		productosTableView.getItems().setAll(detallesAgregados);
	}

	@FXML
	public void openAgregarDescripcion() throws IOException {
		if (agregarDescripciones == null) {
			controller = new AgregarDescripcionController();
			controller.setProductosARegistrar(productosNuevos);
			agregarDescripciones = App.newPage("/cl/inacap/cisonioApp/fx/agregarDescripcion.fxml", self, controller);
			agregarDescripciones.setOnHiding(event -> {
				reloadProductos();
			});
		}
	}

	private void reloadProductos() {
		for (Descripcion d : controller.getProductosARegistrar()) {
			buscarYAgregar(d.getCodigo());
		}
		agregarDescripciones = null;
		productosNuevos.clear();
	}

	public TextField getCodigoTxt() {
		return codigoTxt;
	}

	public void setCodigoTxt(TextField codigoTxt) {
		this.codigoTxt = codigoTxt;
	}

	public ArrayList<Detalle> getProductosporClasificar() {
		return getDetallesAgregados();
	}

	public void setProductosporClasificar(ArrayList<Detalle> productosporClasificar) {
		this.setDetallesAgregados(productosporClasificar);
	}

	public ArrayList<Detalle> getDetallesAgregados() {
		return detallesAgregados;
	}

	public void setDetallesAgregados(ArrayList<Detalle> detallesAgregados) {
		this.detallesAgregados = detallesAgregados;
	}

}

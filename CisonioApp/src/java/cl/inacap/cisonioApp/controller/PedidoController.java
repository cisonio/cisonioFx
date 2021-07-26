package cl.inacap.cisonioApp.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cl.inacap.cisonioApp.App;
import cl.inacap.cisonioApp.Services;
import cl.inacap.cisonioApp.model.dto.productos.Descripcion;
import cl.inacap.cisonioApp.model.dto.productos.Detalle;
import cl.inacap.cisonioApp.model.dto.transacciones.Pedido;
import cl.inacap.cisonioApp.model.dto.transacciones.Transaccion;
import cl.inacap.cisonioApp.utils.Utils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.FlatAlert;

// TODO: Auto-generated Javadoc
/**
 * The Class PedidoController controla la vista del stage Pedido.
 */
public class PedidoController extends Controller {

	private Stage seleccionarProductos;
	private Stage nuevaTransaccion;
	private List<Detalle> produsctosSeleccionados;
	SeleccionarDescripcionesController controller;
	NuevaVentaController ventaController;
	GuardarProductosController guardarController;
	private List<Detalle> productosPorClasificar;
	@FXML
	StackPane paneles;

	/** pedido es el nuevo pedido creado */
	// pedido a guardar
	private Pedido pedido;

	private boolean isVenta;

	/** The aceptar btn pasa de panelcar a panelcard */
	@FXML
	private Button aceptarBtn;

	/** The cancelar btn cierra la ventana y cancela la transaccion */
	@FXML
	private Button cancelarBtn;

	/** The page representa el panel que se encuentra al frente del escenario */
	private int page = 1;
	// *************************************************************************
	// **********************clases ventana "Informacion"***********************
	/**
	 * The pane 01 info. es primer panel, en el que se ingresa la informacion del
	 * pedido
	 */
	// *************************************************************************
	@FXML
	private HBox pane01Info;

	/** The empresa cbox. es el combobox donde se elige la empresa */
	@FXML
	private ChoiceBox<String> empresaCbox;

	/** The campana txt es un txt input para ingresar el numero de campana */
	@FXML
	private TextField campanaTxt;

	/** The empresa val es el mensaje de error del input empresa */
	@FXML
	private Label empresaVal;

	/** The campana val es el mensaje de error del input campana */
	@FXML
	private Label campanaVal;

	private AgregarYBuscarHBox pane02Productos;

	// *************************************************************************
	// *****************clases ventana "Clasificar Prodcuctos"******************
	/**
	 * The pane 03 clasificar permite vender o guardar los productos recien llegados
	 */
	// *************************************************************************
	@FXML
	private HBox pane03Clasificar;

	/** The pane 04 done pasa al frente cuando no quedan productos clasificables */
	@FXML
	private HBox pane04Done;

//	/** El pro DAO es el Repositorio de los Productos */
//	ProductoServices proDAO = new ProductoServices();
//	
//	/** The des DAO. es el Repositorio de las Descripciones*/
//	DescripcionServices desDAO = new DescripcionServices();
//	
//	/** The trans DAO. es el Repositorio de las Transacciones */
//	TransaccionServices transDAO = new TransaccionServices();
//	
	/**
	 * Retroceder permite ir a la pagina anterior, llama al metodo de iniciacion de
	 * la pagina anterior
	 */
	@FXML
	public void retroceder() {
		switch (page) {
		case 2:
			setInfoPane();
			break;
		case 3:
			setProdPane();
			break;
		case 4:
			pane03Clasificar.toFront();
			System.out.println(page);
			break;
		default:
			break;
		}
	}

	/**
	 * Initialize inicializa el escenario, carga los componentes de la ventana y las
	 * propiedades que deben ser cargadas solo una vez de los componentes del
	 * escenario
	 */
	@FXML
	public void initialize() {
		pane02Productos = new AgregarYBuscarHBox();
		paneles.getChildren().add(pane02Productos);
		pane02Productos.setSelf(self);
		setInfoPane();
		flecha.setSize(30);
		back.getChildren().add(flecha);
		empresaCbox.getItems().addAll("Belcorp", "Oriflame", "Natura", "Cristian Lay");

		System.out.println(self == null);
	}

	/**
	 * Sets the info pane carga la informacion necesaria del panel de informacion.
	 */
	public void setInfoPane() {
		aceptarBtn.setVisible(true);
		cancelarBtn.setVisible(true);
		aceptarBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				getInfoPane();
			}
		});
		Utils u = new Utils();
		campanaTxt.textProperty().addListener(u.addNumberChecker(campanaTxt));
		page = 1;
		pane01Info.toFront();
	}

	/**
	 * Gets the info pane realiza validaciones sobre la infomacion ingresada en el
	 * pane de informacion y la guarda en un nuevo pedido.
	 *
	 * @return the info pane
	 */
	public void getInfoPane() {
		boolean error = false;
		String empresa = empresaCbox.getValue();
		String campana = campanaTxt.getText();
		int nroCampana = 0;
		try {
			nroCampana = Integer.parseInt(campana);
			System.out.println(campana + nroCampana);
		} catch (Exception e) {
			error = true;
			campanaVal.setText("Ingrese un numero");
			campanaVal.setVisible(true);
		}
		if (empresaCbox.getSelectionModel().isEmpty()) {
			empresaVal.setVisible(true);
			error = true;
		}
		if (campana.isEmpty()) {
			campanaVal.setVisible(true);
			error = true;
		}
		if (!error) {
			LocalDate today = LocalDate.now();
			pedido = new Pedido();
			pedido.setCampania(nroCampana);
			pedido.setEmpresa(empresa);
			pedido.setFecha(today);
			pane01Info.toBack(); // redundante??
			setProdPane();
			page++;
		}

	}

	/**
	 * Sets the prod pane manda el panel de ingreso de producto al frente
	 */
	private void setProdPane() {
		// pane02Productos.getCodigoTxt().textProperty().addListener(addNumberChecker(pane02Productos.getCodigoTxt()));
		aceptarBtn.setVisible(true);
		cancelarBtn.setVisible(true);
		aceptarBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				getProdPane();
			}
		});
		pane02Productos.setPedido(pedido);
		pane02Productos.toFront();
		pane02Productos.setVisible(true);
		page = 2;

	}

	/**
	 * Gets the prod pane guarda los productos agregados al
	 *
	 * @return the prod pane
	 */
	private void getProdPane() {
		boolean error = false;
		if (!pane02Productos.getProductosNuevos().isEmpty()) {
			try {
				pane02Productos.openAgregarDescripcion();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // should the class create itself???
		} else {
			int total = 0;
			pedido.setDetalles(pane02Productos.getDetallesAgregados());
			productosPorClasificar = pane02Productos.getDetallesAgregados();
			for (Detalle d : pedido.getDetalles()) {
				total += d.getCantidad() * d.getPrecioCompra();
			}
			pedido.setTotal(total);
			Services.transDAO.guardarPedido(pedido);
			if (!error) {
				pane02Productos.toBack();
				setClasPane();
			}
		}
	}

	/**
	 * Sets the clas pane.
	 */
	private void setClasPane() {
		aceptarBtn.setVisible(false);
		cancelarBtn.setVisible(false);
		page = 3;
		pane03Clasificar.toFront();
	}

	@FXML
	private void venderProductos() {
		isVenta = true;
		seleccionarProductos();

	}

	@FXML
	private void guardarProductos() {
		isVenta = false;
		seleccionarProductos();
	}

	private void setEndPane() {
		aceptarBtn.setVisible(true);
		aceptarBtn.setText("Cerrar y Guardar");
		pane04Done.toFront();
		aceptarBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				self.hide();
			}
		});
		page = 4;
	}

	/**
	 * Hide val.
	 */
	public void hideVal() { // TODO:update to new thing
		empresaVal.setVisible(false);
		campanaVal.setVisible(false);
	}

	private void seleccionarProductos() {
		if (controller == null) {
			controller = new SeleccionarDescripcionesController();
			controller.setTodosLosProductos(productosPorClasificar);
		
		try {
			seleccionarProductos = App.newPage("/cl/inacap/cisonioApp/fx/seleccionarProductos.fxml", self, controller);
			seleccionarProductos.setOnCloseRequest(event -> continuarTransaccion());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		seleccionarProductos.setOnHiding((event -> continuarTransaccion()));}
		else {
			controller.setDetallesSubTransaccion();
			seleccionarProductos.show();
		}

	}

	private void continuarTransaccion() {
		produsctosSeleccionados = controller.getProductosSeleccionados();
		if (produsctosSeleccionados.isEmpty())
			return;
		String fxml;
		if (isVenta) {
			fxml = "/cl/inacap/cisonioApp/fx/venta.fxml";
			ventaController = new NuevaVentaController();
			ventaController.setDetalles(produsctosSeleccionados);
			try {
				nuevaTransaccion = App.newPage(fxml, self, ventaController);
				nuevaTransaccion.setOnHidden(event -> checkIfDone());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			fxml = "/cl/inacap/cisonioApp/fx/almacenamiento.fxml";
			guardarController = new GuardarProductosController();
			guardarController.setDetalles(produsctosSeleccionados);
			try {
				nuevaTransaccion = App.newPage(fxml, self, guardarController);
				nuevaTransaccion.setOnHidden(event -> checkIfDone());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void checkIfDone() {
		if(controller.isDone()) {
			setEndPane();
		}
	}

	// TODO: arreglar paso de card en card, cuando paso del card agregar productos
	// al siguiente tengo que devolverme y aceptar de nuevo para que pase lo que
	// tiene que pasar
	// TODO: que al apretar enter en el cuadro de busqueda se agregue el codigo en
	// logar de pasar a la otra ventana
	// TODO: que despues de buscar se borre el contenido del cuadro de busqueda

}

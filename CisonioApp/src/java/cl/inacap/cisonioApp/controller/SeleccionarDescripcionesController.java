package cl.inacap.cisonioApp.controller;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.cisonioApp.commonNodes.ItemListDetalleCellFactory;
import cl.inacap.cisonioApp.commonNodes.ItemLista;
import cl.inacap.cisonioApp.model.dto.productos.Detalle;
import cl.inacap.cisonioApp.model.dto.transacciones.Transaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ScrollPane;

public class SeleccionarDescripcionesController extends Controller {

	private List<Detalle> todosLosProductos;
	private List<Detalle> productosSeleccionados;
	private List<Detalle> detalles;
	boolean initialized = false;
	private Transaccion transaccion;
	boolean done = false;
	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public List<Detalle> getProductosSeleccionados() {
		return productosSeleccionados;
	}

	public void setTodosLosProductos(List<Detalle> todosLosProductos) {
		this.todosLosProductos = todosLosProductos;
	}

	@FXML
	ScrollPane listaSP;

	@FXML
	ListView<Detalle> productos;

	@FXML
	private void initialize() {
		productosSeleccionados = new ArrayList<Detalle>();
		setDetallesSubTransaccion();
		productos.setCellFactory(new ItemListDetalleCellFactory(new Callback<Detalle, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(Detalle item) {
				BooleanProperty observable = new SimpleBooleanProperty();
				observable.addListener((obs, wasSelected, isNowSelected) -> {
					if (isNowSelected) {
						productosSeleccionados.add(item);
						// System.out.println(item.getCantidad() + "cantidad item antes de modificar" );
						// todosLosProductos.get(index).Quitar(cantidad);
						// System.out.println(todosLosProductos.get(index).getCantidad() + "cantidad
						// item todos los productos");
						// System.out.println(item.getCantidad() + "cantidad item antes de modificar");
					} else {
						productosSeleccionados.remove(item);
						// todosLosProductos.get(index).Agregar(cantidad);

					}
				});
				return observable;
			}
		}, new Callback<Detalle, ObservableValue<Integer>>() {

			@Override
			public ObservableValue<Integer> call(Detalle param) {
				ObservableValue<Integer> observable = new SimpleIntegerProperty(param.getCantidad()).asObject();
				observable.addListener((obs, oldCantidad, newCantidad) -> {
					param.setCantidad(newCantidad);

				});

				// TODO Auto-generated method stub
				return observable;
			}
		}));
		listaSP.setFitToHeight(true);
		listaSP.setFitToWidth(true);
		initialized = true;
	}

	@FXML
	private void continuar() {
		
		if (!productosSeleccionados.isEmpty()) {
			arreglarCantidades();
			if(todosLosProductos.isEmpty())
				done = true;
			self.close();
		} else {
			System.out.println("no hay productos seleccionados");
		}
	}

	private void arreglarCantidades() {
		if(!initialized)
			return;
		for(Detalle d : todosLosProductos) {
			int index = todosLosProductos.indexOf(d);
			Detalle guardado = productos.getItems().get(index);
			d.Quitar(guardado.getCantidad());
		}
		todosLosProductos.removeIf(x-> x.getCantidad()<=0);
		
	}
	
	public void setDetallesSubTransaccion() {
		productos.setItems(FXCollections.observableArrayList(getDetallesSubTransaccion()));
	}

	public List<Detalle> getDetallesSubTransaccion() {
		List<Detalle> respuesta = new ArrayList<Detalle>();
		for (Detalle d : todosLosProductos) {
			Detalle copia = new Detalle();
			copia.setCantidad(d.getCantidad());
			copia.setPrecioCompra(d.getPrecioCompra());
			copia.setProducto(d.getProducto());
			respuesta.add(copia);
		}
		return respuesta;
	}

	// TODO: Make this one be initialized once

	@FXML
	private void cambiarCantidad() {

	}

	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion nueva) {
		this.transaccion = nueva;
	}
}

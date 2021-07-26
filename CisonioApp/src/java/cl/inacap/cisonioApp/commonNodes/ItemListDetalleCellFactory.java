package cl.inacap.cisonioApp.commonNodes;

import cl.inacap.cisonioApp.model.dto.productos.Detalle;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ItemListDetalleCellFactory implements Callback<ListView<Detalle>, ListCell<Detalle>>{

	Callback<Detalle, ObservableValue<Boolean>> getSelectedProperty;
	Callback<Detalle, ObservableValue<Integer>> getCantidad;
	
	public ItemListDetalleCellFactory(Callback<Detalle, ObservableValue<Boolean>> callback, Callback<Detalle, ObservableValue<Integer>> getCantidad) {
		this.getSelectedProperty = callback;
		this.getCantidad = getCantidad;
	}


	@Override
	public ListCell<Detalle> call(ListView<Detalle> param) {
		// TODO Auto-generated method stub
		return new DetalleCell(getSelectedProperty, getCantidad);
	}
	

}

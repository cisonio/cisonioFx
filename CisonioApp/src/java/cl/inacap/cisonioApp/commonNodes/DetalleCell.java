package cl.inacap.cisonioApp.commonNodes;

import java.io.IOException;

import cl.inacap.cisonioApp.model.dto.productos.Detalle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class DetalleCell extends ListCell<Detalle>{
	
	@FXML
	Label codigoLbl;
	
	@FXML
	Label nombreLbl;
	
	@FXML
	CheckBox seleccionChBx;

	@FXML
	Spinner<Integer> cantidadSpn;
	
	@FXML
	HBox itemHbox;
	
	
	private ObservableValue<Boolean> booleanProperty;
	private ObservableValue<Integer> integerProperty;
	 
		
	 // --- selected state callback property
    private ObjectProperty<Callback<Detalle,ObservableValue<Integer>>>
    		cantidadCallback =
            new SimpleObjectProperty<Callback<Detalle, ObservableValue<Integer>>>(
            this, "cantidadCallback");
    
    private ObjectProperty<Callback<Detalle, ObservableValue<Boolean>>>
	    selectedStateCallback =
	    new SimpleObjectProperty<Callback<Detalle, ObservableValue<Boolean>>>(
	    this, "selectedStateCallback");
    
    
	public DetalleCell(final Callback<Detalle, ObservableValue<Boolean>> getSelectedProperty,
			final Callback<Detalle, ObservableValue<Integer>> getCantidad) {
		
		setSelectedStateCallback(getSelectedProperty);
		setCantidadCallback(getCantidad);
		loadFXML();
	}

	public DetalleCell() {
		this(null, null);
	}

	private void loadFXML() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/cl/inacap/cisonioApp/fx/detalleCell.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
			
	}
	/**
     * Property representing the {@link Callback} that is bound to by the
     * CheckBox shown on screen.
     */
    public final ObjectProperty<Callback<Detalle, ObservableValue<Boolean>>> selectedStateCallbackProperty() {
        return selectedStateCallback;
    }
    public final ObjectProperty<Callback<Detalle, ObservableValue<Integer>>> cantidadCallbackProperty() {
        return cantidadCallback;
    }
	 /**
     * Returns the {@link Callback} that is bound to by the CheckBox shown on screen.
     */
	public final Callback<Detalle, ObservableValue<Boolean>> getSelectedStateCallback() {
        return selectedStateCallbackProperty().get();
    }
	public final Callback<Detalle, ObservableValue<Integer>> getCantidadCallback() {
        return cantidadCallbackProperty().get();
    }
	
	
	public final void setSelectedStateCallback(Callback<Detalle, ObservableValue<Boolean>> value) {
	        selectedStateCallbackProperty().set(value);
	}
	public final void setCantidadCallback(Callback<Detalle, ObservableValue<Integer>> value) {
		cantidadCallbackProperty().set(value);
}
	
	@Override
    public void updateItem(Detalle item, boolean empty) {
        super.updateItem(item, empty);
        if(empty || item == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {
        	 Callback<Detalle, ObservableValue<Boolean>> callback = getSelectedStateCallback();
        	 Callback<Detalle, ObservableValue<Integer>> callback2 = getCantidadCallback();
        	 if (callback == null) {
                 throw new NullPointerException(
                         "The CheckBoxListCell selectedStateCallbackProperty can not be null");
             }
        	codigoLbl.setText(item.getProducto().getCodigo() + "");
        	System.out.println(item.getProducto().getCodigo() + "");
        	nombreLbl.setText(item.getProducto().getNombre());
        	if(integerProperty != null) {
        	cantidadSpn.getValueFactory().valueProperty().unbindBidirectional((Property<Integer>)integerProperty);
        	}
        	cantidadSpn = new Spinner<Integer>(0,item.getCantidad(),0);
    		cantidadSpn.setEditable(true);
    		cantidadSpn.setMinWidth(100);
    		if(itemHbox.getChildren().size() == 4)
    			itemHbox.getChildren().remove(3);
    		itemHbox.getChildren().add(cantidadSpn);
        	integerProperty = callback2.call(item);
        	if(integerProperty != null) {
            	cantidadSpn.getValueFactory().valueProperty().bindBidirectional((Property<Integer>)integerProperty);
            	}
        	cantidadSpn.getValueFactory().setValue(item.getCantidad());
        	if (booleanProperty != null) {
        		seleccionChBx.selectedProperty().unbindBidirectional((BooleanProperty)booleanProperty);
            }
            booleanProperty = callback.call(item);
            if (booleanProperty != null) {
            	seleccionChBx.selectedProperty().bindBidirectional((BooleanProperty)booleanProperty);
            }
        	setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
	}
}

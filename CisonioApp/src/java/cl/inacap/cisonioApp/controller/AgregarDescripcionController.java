package cl.inacap.cisonioApp.controller;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.cisonioApp.Services;
import cl.inacap.cisonioApp.commonNodes.CategoriaVBox;
import cl.inacap.cisonioApp.commonNodes.ItemLista;
import cl.inacap.cisonioApp.model.dto.productos.Categoria;
import cl.inacap.cisonioApp.model.dto.productos.Descripcion;
import cl.inacap.cisonioApp.model.services.ClienteServices;
import cl.inacap.cisonioApp.model.services.DescripcionServices;
import cl.inacap.cisonioApp.model.services.ProductoServices;
import cl.inacap.cisonioApp.model.services.TransaccionServices;
import cl.inacap.cisonioApp.utils.Utils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

// TODO: Auto-generated Javadoc
/**
 * The Class AgregarDescripcionController controla el stage agregar descripcion que permite agregar una nueva descripcion.
 */
public class AgregarDescripcionController extends Controller{

	
	/** The productos A registrar es la lista de productos por registrar, productos solo con codigo*/
	private List<Descripcion> productosARegistrar;
	public List<Descripcion> getProductosARegistrar() {
		return productosARegistrar;
	}



	private List<Categoria> todasLasCategorias;
	private int current;
	DescripcionServices desDAO = Services.desDAO;
	ProductoServices proDAO = Services.proDAO;
	ClienteServices cliDAO = Services.cliDAO;
	TransaccionServices tranDAO = Services.transDAO;
	
	
	@FXML
	VBox form;
	/*
	 ___ ____ ___  _ ____ ____    _ _  _ ___  _  _ ___    ____ ____ ____ _  _ ___  
	|    |  | |  \ | | __ |  |    | |\ | |__] |  |  |     | __ |__/ |  | |  | |__] 
	|___ |__| |__/ | |__] |__|    | | \| |    |__|  |     |__] |  \ |__| |__| |    
	
	*/                                                                            
	
	@FXML
	VBox codigoVbox;
	
	@FXML
	TextField codigoTxt;
	
	/*
	_  _ ____ _  _ ___  ____ ____    _ _  _ ___  _  _ ___    ____ ____ ____ _  _ ___  
	|\ | |  | |\/| |__] |__/ |___    | |\ | |__] |  |  |     | __ |__/ |  | |  | |__] 
	| \| |__| |  | |__] |  \ |___    | | \| |    |__|  |     |__] |  \ |__| |__| |    
	                                                                                                         
	*/
	@FXML
	VBox nombreVbox;
	
	@FXML
	TextField nombreTxt;
	
	/*
	____ _  _ ___  ____ ____ ____ ____    _ _  _ ___  _  _ ___    ____ ____ ____ _  _ ___  
	|___ |\/| |__] |__/ |___ [__  |__|    | |\ | |__] |  |  |     | __ |__/ |  | |  | |__] 
	|___ |  | |    |  \ |___ ___] |  |    | | \| |    |__|  |     |__] |  \ |__| |__| |    
	                                                                                       
	 */
	@FXML
	VBox empresaVbox;
	
	@FXML
	ComboBox<String> empresaCbox;
	
	
	/*
	 * El usuario no será capaz de modificar las categorias, proximas implementaciones permitiran el uso de 
	 * tags creados y mantenidos por el usuario. */
	
	
	/*
	___  ____ ____ ____ ____ _ ___  ____ _ ____ _  _    _ _  _ ___  _  _ ___    ____ ____ ____ _  _ ___  
	|  \ |___ [__  |    |__/ | |__] |    | |  | |\ |    | |\ | |__] |  |  |     | __ |__/ |  | |  | |__] 
	|__/ |___ ___] |___ |  \ | |    |___ | |__| | \|    | | \| |    |__|  |     |__] |  \ |__| |__| |    
	                                                                                                     
	 * */
	@FXML
	VBox descripcionVbox;
	
	@FXML
	TextArea descripcionTextArea;
	
	
	@FXML
	Button guardarBtn;
	
	@FXML
	Button guardarYSalirBtn;
	ObservableList<ItemLista<Descripcion>> productosARegistrarListados;
	
	public void setProductosARegistrar(List<Descripcion> productosARegistrar) {
		this.productosARegistrar = productosARegistrar;
	}
	
	
	
	@FXML
	ListView<ItemLista<Descripcion>> codigos;
	
	@FXML
	 public void initialize() {
		todasLasCategorias = desDAO.getAllCategorias();
		empresaCbox.getItems().addAll("BELCORP", "ORIFLAME", "NATURA","CRISTIAN LAY");
		if(!productosARegistrar.isEmpty()) {
			productosARegistrarListados =  FXCollections.observableArrayList();
			for(Descripcion d : productosARegistrar) {
				productosARegistrarListados.add(new ItemLista<Descripcion>(d));
			}
			setcodigos();
			codigos.getSelectionModel().select(productosARegistrarListados.get(0));  //inizializar en el primer item
			setDescriptionPane();
			System.out.println(productosARegistrar.get(0).getCodigo());
			codigoTxt.setEditable(false);
			back.setVisible(false);
			}
		else {
			//TODO: implement a mode in which starting with and empty list lest you add them to the list from the form (not in this versions)
		
			System.out.println("empty");
		}
	}
	
	
	private void setcodigos() {
		codigos.setItems(productosARegistrarListados);
		codigos.setCellFactory(new Callback<ListView<ItemLista<Descripcion>>, ListCell<ItemLista<Descripcion>>>() {
		    @Override
		    public ListCell<ItemLista<Descripcion>> call(ListView<ItemLista<Descripcion>> lv) {
		        return new ListCell<ItemLista<Descripcion>>() {
		            @Override
		            public void updateItem(ItemLista<Descripcion> item, boolean empty) {
		                super.updateItem(item, empty);
		                if (item == null) {
		                    setText(null);
		                } else {
		                	setText(String.valueOf(item.getObjeto().getCodigo()));
		                    setStyle(item.isValidado()?"-fx-text-fill: -green-blue-crayola;":"-fx-text-fill: -bittersweet;");
		                    
		                }
		            }
		        };
		    }
		    
		});
	}


	/**
	 * enlaza el panel de informacion con la descripcion.
	 *
	 * @param descripcion la descripcion que sera modificada/agregada
	 */
	@FXML
	public void setDescriptionPane(){
		Descripcion aMostrar = codigos.getSelectionModel().getSelectedItem().getObjeto();
		current = codigos.getSelectionModel().getSelectedIndex();
		codigoTxt.setText(aMostrar.getCodigo() + "");
		if(aMostrar.getNombre() != null)
			nombreTxt.setText(aMostrar.getNombre());
		if(aMostrar.getEmpresa() != null)
			empresaCbox.setValue(aMostrar.getEmpresa().toUpperCase());
		if(aMostrar.getDescripcion() != null)
		descripcionTextArea.setText(aMostrar.getDescripcion());
	}
	
	/**
	 * Gets the description from pane.
	 *
	 * @return the description from pane
	 */
	public void getDescriptionFromPane() {
		
		codigos.getItems().remove(current);	
		
	}
	
	@FXML
	public void saveToList() {
		Utils util = new Utils();
		int errores = 0;
		Descripcion aModificar = codigos.getItems().get(current).getObjeto();
		try {
	        int cod = Integer.parseInt(codigoTxt.getText());
	        System.out.println(codigoTxt.getText() + cod);
	        aModificar.setCodigo(cod);
	    } catch (NumberFormatException e) {
	    	util.validate("Debe ser un valor numérico", codigoVbox);
	        errores++;
	    }
		if(!nombreTxt.getText().isEmpty() && !nombreTxt.getText().contains("?")) 
			aModificar.setNombre(nombreTxt.getText());
		else {
			util.validate("Ingrese el nombre del Producto",nombreVbox);
			errores++;
		}if(empresaCbox.getSelectionModel().isEmpty()) {
			util.validate("Ingrese el Nombre de la Empresa", empresaVbox);
			errores++;
		}
			aModificar.setDescripcion(descripcionTextArea.getText()+"");
			aModificar.setEmpresa(empresaCbox.getSelectionModel().getSelectedItem().trim());
		if (errores == 0) {
			System.out.println(aModificar.getNombre() + aModificar.getCodigo() + aModificar.getEmpresa());
			desDAO.guardar(aModificar);
			    util.toast("Producto guardado en la base de datos",form);
				codigos.getItems().get(current).setValidado(true);
				productosARegistrarListados.get(current).setValidado(true);
				System.out.println(productosARegistrarListados.get(current).isValidado());
				codigos.refresh();
				//setcodigos();
								
		}
		if (productosCreados()) {
			
			
			guardarYSalirBtn.setVisible(true);
		}
	}
	
	@FXML 
	void guardarYSalir() {
		self.close();
	}
	
	public boolean productosCreados() {
		for(ItemLista<Descripcion> d : codigos.getItems()) {
			if(!d.isValidado())
				return false;		
		}
		return true;
	}
	
	

	
	
}

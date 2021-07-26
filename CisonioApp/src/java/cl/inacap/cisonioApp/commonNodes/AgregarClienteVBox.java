package cl.inacap.cisonioApp.commonNodes;

import java.sql.SQLIntegrityConstraintViolationException;

import cl.inacap.cisonioApp.Services;
import cl.inacap.cisonioApp.model.dto.Cliente;
import cl.inacap.cisonioApp.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AgregarClienteVBox extends VBox{
	
	@FXML 
	private TextField nombreTxt;
	
	@FXML 
	private TextField telefonoTxt;
	
	@FXML 
	private Button agregarClienteBtn;
	
	Utils u = new Utils();
	
	public AgregarClienteVBox(){
		try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cl/inacap/cisonioApp/fx/AgregarCliente.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	@FXML
	public void validar() {
		int errores = 0;
		Cliente nuevo = new Cliente();
		if(!nombreTxt.getText().isEmpty()) 
			nuevo.setNombre(nombreTxt.getText());
		else {
			u.validate("Ingrese el nombre del Cliente",this);
			errores++;
		}if(!telefonoTxt.getText().isEmpty()) {
			if(!telefonoTxt.getText().matches("^(\\+?56)?(\\s?)(0?9)(\\s?)[9876543]\\d{7}$")){
				u.validate("Ingrese un numero de teléfono valido", this);
				errores++;
			}else {
				nuevo.setTelefono(telefonoTxt.getText());
			}
		}if(errores == 0) {
			try {
				Cliente c = Services.cliDAO.guardar(nuevo);
				u.toast("Cliente Guardado", this);
			}catch (Exception e) {
				u.validate("Cliente No Guardado", this);
			}
			
		}
		
	}
		

}

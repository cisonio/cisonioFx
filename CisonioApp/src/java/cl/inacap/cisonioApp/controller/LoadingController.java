package cl.inacap.cisonioApp.controller;

import cl.inacap.cisonioApp.model.services.ClienteServices;
import cl.inacap.cisonioApp.model.services.DescripcionServices;
import cl.inacap.cisonioApp.model.services.ProductoServices;
import cl.inacap.cisonioApp.model.services.TransaccionServices;

public class LoadingController {

    public void loadServices() {
		/** El pro DAO es el Repositorio de los Productos */
    	ProductoServices proDAO = new ProductoServices();
	
    	/** The des DAO. es el Repositorio de las Descripciones*/
    	DescripcionServices desDAO = new DescripcionServices();
	
    	/** The trans DAO. es el Repositorio de las Transacciones */
    	TransaccionServices transDAO = new TransaccionServices();
    	
    	/** The trans DAO. es el Repositorio de las Transacciones */
    	ClienteServices cliDAO = new ClienteServices();
	}

    
	public boolean loadedServises() {
		loadServices();
		return true;
	}
}

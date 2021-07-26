package cl.inacap.cisonioApp;

import cl.inacap.cisonioApp.model.services.ClienteServices;
import cl.inacap.cisonioApp.model.services.DescripcionServices;
import cl.inacap.cisonioApp.model.services.ProductoServices;
import cl.inacap.cisonioApp.model.services.TransaccionServices;

public class Services {
	public static ProductoServices proDAO;
	public static DescripcionServices desDAO;
	public static TransaccionServices transDAO;
	public static ClienteServices cliDAO;
	
	public static void load(){
		/** El pro DAO es el Repositorio de los Productos */
		 proDAO = new ProductoServices();

		/** The des DAO. es el Repositorio de las Descripciones*/
		 desDAO = new DescripcionServices();

		/** The trans DAO. es el Repositorio de las Transacciones */
		 transDAO = new TransaccionServices();
		
		/** The trans DAO. es el Repositorio de las Transacciones */
		 cliDAO = new ClienteServices();
	}

}

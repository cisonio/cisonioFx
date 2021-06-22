package cl.inacap.cisonioApp.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import static org.springframework.data.jpa.domain.Specification.where;
import cl.inacap.cisonioApp.model.repository.ClienteRepository;
import cl.inacap.cisonioApp.model.repository.PagoRepository;
import cl.inacap.cisonioApp.model.repository.RepositoryFactory;
import cl.inacap.cisonioApp.model.repository.utils.Filter;
import cl.inacap.cisonioApp.model.dto.Cliente;
import cl.inacap.cisonioApp.model.dto.Pago;

// TODO: Auto-generated Javadoc
/**
 * The Class ClienteServices provee una interfaz entre los repositorios creados con spring data jpa 
 * y una aplicacion sin spring boot, provee los metodos necesarios para mantener la tabla de cliente
 * y pagos.
 */
public class ClienteServices {

	/** repo es el repositorio de clientes*/
	@Autowired
	ClienteRepository repo = RepositoryFactory.getDAOCliente();
	
	/** pago es el repositorio de pagos */
	@Autowired
	PagoRepository pago = RepositoryFactory.getDAO(PagoRepository.class);
	

	/**
	 * Obtiene todos los clientes en la base de datos
	 *
	 * @return Una lista de clientes.
	 */
	public List<Cliente> getAll() {
		return repo.findAll();
	}

	/**
	 * Obtiene todos los clientes cuyo nombre se parece a name
	 *
	 * @param name el nombre al que se pareceran los nombres de los clientes en el resultado
	 * @return Lista de clientes con nombre parecido a name
	 */
	public List<Cliente> getAll(String name) {
		Specification<Cliente> nombreComo = where((root, query, criteriaBuilder) ->
		criteriaBuilder.like(root.get("nombre"), "%"+name+"%"));
        return repo.findAll(nombreComo);
	    
	}
	
	/**
	 * Encontrar por telefono.
	 *
	 * @param fono el telefono a buscar
	 * @return lista de clientes con el telefono buscado
	 */
	public List<Cliente> encontrarPorTelefono(String fono){
		return repo.findByTelefono(fono);
	}
	
	/**
	 * Guarda, (crea o updatea) un cliente
	 *
	 * @param c es el cliente a guardar
	 */
	public void guardar(Cliente c) {
		repo.saveAndFlush(c);
		return; 
	}
	
	/**
	 *  Guarda, (crea o updatea) un pago
	 *
	 * @param p es el pago a guardar
	 * @return the pago
	 */
	public void guardar(Pago p) {
		pago.saveAndFlush(p);
		return;
	}
	
	/**
	 * Devuelve todos los pagos relizados por un cliente c
	 *
	 * @param c el cliente c
	 * @return lista de pagos
	 */
	public List<Pago> estadoCuenta(Cliente c){
		return pago.findByCliente(c);
	}
	
	/**
	 * Borrar cliente c
	 *
	 * @param c el cliente a borrar
	 */
	public void borrar(Cliente c) {
		repo.delete(c);
		repo.flush();
		return;		
	}
	
	/**
	 * Borrar pago p
	 *
	 * @param p el pago a borrar
	 */
	public void borrar(Pago p) {
		pago.delete(p);
		pago.flush();
		return;
	}
	
	
}

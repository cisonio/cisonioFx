package cl.inacap.cisonioApp.model.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.inacap.cisonioApp.model.dto.Cliente;
import cl.inacap.cisonioApp.model.repository.utils.RepositorioFiltrable;

@Repository
public interface ClienteRepository extends RepositorioFiltrable<Cliente,Integer>{
	List<Cliente> findByTelefono(String fono);
	
	@Query(value="FROM Cliente WHERE (SELECT SUM(monto) from Pago WHERE cliente = idCliente) < 0")
	List<Cliente> findDebtors();
	

	@Query(value="SELECT DISTINCT cliente FROM Cliente cliente INNER JOIN cliente.pagos p WHERE cliente.idCliente = p.cliente")
	List<Cliente> findActive();
	}



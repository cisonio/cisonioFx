package cl.inacap.cisonioApp.model.repository;


import java.util.List;

import org.springframework.stereotype.Repository;

import cl.inacap.cisonioApp.model.dto.Cliente;
import cl.inacap.cisonioApp.model.repository.utils.RepositorioFiltrable;

@Repository
public interface ClienteRepository extends RepositorioFiltrable<Cliente,Integer>{
	List<Cliente> findByTelefono(String fono);
	}

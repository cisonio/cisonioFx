package cl.inacap.cisonioApp.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.inacap.cisonioApp.model.dto.Cliente;
import cl.inacap.cisonioApp.model.dto.Pago;

public interface PagoRepository extends JpaRepository<Pago,Integer>{
	List<Pago> findByCliente(Cliente cliente);

}

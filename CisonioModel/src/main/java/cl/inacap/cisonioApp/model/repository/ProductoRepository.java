package cl.inacap.cisonioApp.model.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cl.inacap.cisonioApp.model.dto.productos.Producto;
import cl.inacap.cisonioApp.model.repository.utils.RepositorioFiltrable;

//public interface ProductoRepository extends JpaRepository<Producto,Integer>,JpaSpecificationExecutor<Producto>{
public interface ProductoRepository extends RepositorioFiltrable<Producto,Integer>{
	
	@Query(value = "FROM Producto WHERE fechaVencimiento < :date")
	List<Producto> findProductoByEndDate(@Param("date") LocalDate fecha);
	
}

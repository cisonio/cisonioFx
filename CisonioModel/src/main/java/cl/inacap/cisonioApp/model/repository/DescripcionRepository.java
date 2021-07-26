
package cl.inacap.cisonioApp.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cl.inacap.cisonioApp.model.dto.productos.Descripcion;
import cl.inacap.cisonioApp.model.repository.utils.RepositorioFiltrable;

//public interface DescripcionRepository extends JpaRepository<Descripcion,Integer>,  JpaSpecificationExecutor<Descripcion>{
public interface DescripcionRepository extends RepositorioFiltrable<Descripcion,Integer>{
	List<Descripcion> findByNombre(String nombre);
	List<Descripcion> findByNombreContaining(String nombre);
	List<Descripcion> findByDescripcionContaining(String descripcion);
}

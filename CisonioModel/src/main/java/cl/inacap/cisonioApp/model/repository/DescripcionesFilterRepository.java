package cl.inacap.cisonioApp.model.repository;

import java.util.List;

import cl.inacap.cisonioApp.model.dto.productos.Descripcion;
import cl.inacap.cisonioApp.model.repository.utils.RepositorioFiltrable;

public interface DescripcionesFilterRepository extends RepositorioFiltrable<Descripcion,Integer>{
	List<Descripcion> findByNombre(String nombre);
	List<Descripcion> findByNombreContaining(String nombre);
}

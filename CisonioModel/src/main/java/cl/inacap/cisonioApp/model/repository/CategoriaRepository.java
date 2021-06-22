package cl.inacap.cisonioApp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.inacap.cisonioApp.model.dto.productos.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	
	/**
	 * Find by nombre encuentra una categoria por el nombre de esta
	 *
	 * @param nombre the nombre de la categoria
	 * @return the categoria encontrada
	 */
	public Categoria findByNombre(String nombre);
}

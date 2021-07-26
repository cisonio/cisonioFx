package cl.inacap.cisonioApp.model.services;

import cl.inacap.cisonioApp.model.repository.RepositoryFactory;

import java.util.List;
import java.util.Optional;

import cl.inacap.cisonioApp.model.dto.productos.Categoria;
import cl.inacap.cisonioApp.model.dto.productos.Descripcion;
import cl.inacap.cisonioApp.model.repository.CategoriaRepository;
import cl.inacap.cisonioApp.model.repository.DescripcionRepository;

public class DescripcionServices {
	
	DescripcionRepository descDAO = RepositoryFactory.getDescripcionDAO();
	CategoriaRepository catDAO = RepositoryFactory.getCategorioaDAO();
	
	public List<Descripcion> getAll() {
		return descDAO.findAll();
	}
	
	public Optional<Descripcion> get(Integer id){
		return descDAO.findById(id);
	}
	
	public List<Descripcion> get(List<Integer> ids){
		return descDAO.findAllById(ids);
	}
	
	public void guardar(Descripcion d) {
		RepositoryFactory.getEm().getTransaction().begin();
		descDAO.saveAndFlush(d);	
		RepositoryFactory.getEm().getTransaction().commit();
	}
	
	public List<Descripcion> guardar(List<Descripcion> des) {		
		return descDAO.saveAllAndFlush(des);	
	}
	
	public List<Descripcion> buscarNombreExacto(String nombre){
		return descDAO.findByNombre(nombre);
	}
	
	public List<Descripcion> buscarNombreContiene(String nombre){
		return descDAO.findByNombreContaining(nombre);
	}
	public List<Descripcion> buscarDescripcionContiene(String nombre){
		return descDAO.findByDescripcionContaining(nombre);
	}
	
	
	public List<Categoria> getAllCategorias(){
		return catDAO.findAll();
	}
	
		
}

package cl.inacap.cisonioApp.model.services;

import cl.inacap.cisonioApp.model.repository.RepositoryFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cl.inacap.cisonioApp.model.dao.ProductoDAO;
import cl.inacap.cisonioApp.model.dto.productos.Producto;
import cl.inacap.cisonioApp.model.repository.ProductoRepository;
/**
 * The Class ProductoServices 
 */
@Transactional
public class ProductoServices {
	@Autowired
	ProductoRepository proDAO = RepositoryFactory.getProductoDAO();
	private ProductoDAO productoDAO = new ProductoDAO();
	
	public List<Producto> getAll() {
		return proDAO.findAll();
	}
	
	//MUDA DA!
	public Producto get(Integer id){
		return proDAO.getById(id);
	}
	
	//MUDA DA!
	public List<Producto> get(List<Integer> ids){
		return proDAO.findAllById(ids);
	}

	public void save(Producto p) {
		RepositoryFactory.getEm().getTransaction().begin();
		productoDAO.add(p);
		RepositoryFactory.getEm().getTransaction().commit();
		RepositoryFactory.getEm().refresh(p);
	}
	
	public void guardar(Producto p) {
		RepositoryFactory.getEm().getTransaction().begin();
		proDAO.saveAndFlush(p);	
		RepositoryFactory.getEm().getTransaction().commit();
		RepositoryFactory.getEm().refresh(p);
	}
	
	
	public List<Producto> productosPorVencer(){
		LocalDate fecha = LocalDate.now().plusMonths(6);
		return proDAO.findProductoByEndDate(fecha);
	}
	
	public void detele(Producto p) {
		RepositoryFactory.getEm().getTransaction().begin();
		proDAO.delete(p);
		RepositoryFactory.getEm().getTransaction().commit();
		return;
	}
	
	
	
	//TODO: seach by descripcion

}
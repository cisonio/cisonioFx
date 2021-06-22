package cl.inacap.cisonioApp.model.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import cl.inacap.cisonioApp.model.dto.productos.Categoria;

/*
 * 
 * */
public class RepositoryFactory {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("cisonioModel");
	private static EntityManager em = emf.createEntityManager();
	public static RepositoryFactorySupport factory = new JpaRepositoryFactory(em);
	//then is called RepositoryFactory.factory().getRepository(CategoriaRepository.class)
	public AbastecimientoRepository getAbasDAO() {
		AbastecimientoRepository abasDAO = factory.getRepository(AbastecimientoRepository.class);
		return abasDAO;
	}
	
	static public <T extends JpaRepository<M,Integer>,M> T getDAO(Class<T> claseRepositorio) {
		T repositorio = factory.getRepository(claseRepositorio);
		return repositorio;
	}
	
	static public ClienteRepository getDAOCliente() {
		ClienteRepository repositorio = factory.getRepository(ClienteRepository.class);
		return repositorio;
	}
}


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
	public static EntityManager getEm() {
		return em;
	}

	public static void setEm(EntityManager em) {
		RepositoryFactory.em = em;
	}

	public static RepositoryFactorySupport factory = new JpaRepositoryFactory(em);
	
	private static AbastecimientoRepository abastecimientoDAO;
	private static CategoriaRepository categoriaDAO;
	private static ClienteRepository clienteDAO;
	private static DescripcionRepository descripcionDAO;
	private static DetalleRepository detalleDAO;
	private static PagoRepository pagoDAO;
	private static PedidoRepository pedidoDAO;
	private static PrecioRepository precioDAO;
	private static ProductoRepository productoDAO;
	private static VentaRepository ventaDAO;
	
	
	
	public static AbastecimientoRepository getAbastecimientoDAO() {
		if(abastecimientoDAO == null) {
			abastecimientoDAO = factory.getRepository(AbastecimientoRepository.class);
		}
		return abastecimientoDAO;
	}
	
	public static CategoriaRepository getCategorioaDAO() {
		if(categoriaDAO == null) {
			categoriaDAO = factory.getRepository(CategoriaRepository.class);
		}
		return categoriaDAO;
	}
	
	static public ClienteRepository getClienteDAO() {
		if(clienteDAO == null) {
			clienteDAO = factory.getRepository(ClienteRepository.class);
		}
		return clienteDAO;
	}
	
	public static DescripcionRepository getDescripcionDAO() {
		if(descripcionDAO == null) {
			descripcionDAO = factory.getRepository(DescripcionRepository.class);
		}
		return descripcionDAO;
	}

	public static DetalleRepository getDetalleDAO() {
		if(detalleDAO == null) {
			detalleDAO = factory.getRepository(DetalleRepository.class);
		}
		return detalleDAO;
	}

	public static PagoRepository getPagoDAO() {
		if(pagoDAO == null) {
			pagoDAO = factory.getRepository(PagoRepository.class);
		}
		return pagoDAO;
	}

	public static PrecioRepository getPrecioDAO() {
		if(precioDAO == null) {
			precioDAO = factory.getRepository(PrecioRepository.class);
		}
		return precioDAO;
	}

	public static ProductoRepository getProductoDAO() {
		if(productoDAO == null) {
			productoDAO = factory.getRepository(ProductoRepository.class);
		}
		return productoDAO;
	}

	public static VentaRepository getVentaDAO() {
		if(ventaDAO == null) {
			ventaDAO = factory.getRepository(VentaRepository.class);
		}
		return ventaDAO;
	}

	static public PedidoRepository getPedidoDAO() {
		if(pedidoDAO == null ){
			pedidoDAO = factory.getRepository(PedidoRepository.class);
		}
		return pedidoDAO;
	}
	
	
//then is called RepositoryFactory.factory().getRepository(CategoriaRepository.class)	
//	static public <T extends JpaRepository<M,Integer>,M> T getDAO(Class<T> claseRepositorio) {
//		T repositorio = factory.getRepository(claseRepositorio);
//		return repositorio;
//	}
}


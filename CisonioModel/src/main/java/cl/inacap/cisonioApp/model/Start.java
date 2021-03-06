package cl.inacap.cisonioApp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.stereotype.Component;


import cl.inacap.cisonioApp.model.dto.Cliente;
import cl.inacap.cisonioApp.model.dto.productos.Categoria;
import cl.inacap.cisonioApp.model.dto.productos.Descripcion;
import cl.inacap.cisonioApp.model.dto.productos.Producto;
import cl.inacap.cisonioApp.model.repository.AbastecimientoRepository;
import cl.inacap.cisonioApp.model.repository.CategoriaRepository;
import cl.inacap.cisonioApp.model.repository.ClienteCustomRepository;
import cl.inacap.cisonioApp.model.repository.ClienteRepository;
import cl.inacap.cisonioApp.model.repository.DescripcionRepository;
import cl.inacap.cisonioApp.model.repository.DescripcionesFilterRepository;
import cl.inacap.cisonioApp.model.repository.DetalleRepository;
import cl.inacap.cisonioApp.model.repository.PagoRepository;
import cl.inacap.cisonioApp.model.repository.PedidoRepository;
import cl.inacap.cisonioApp.model.repository.PrecioRepository;
import cl.inacap.cisonioApp.model.repository.ProductoFilterRepository;
import cl.inacap.cisonioApp.model.repository.ProductoRepository;
import cl.inacap.cisonioApp.model.repository.VentaRepository;
import cl.inacap.cisonioApp.model.repository.utils.Filter;
import cl.inacap.cisonioApp.model.repository.utils.FilterRepository;
import cl.inacap.cisonioApp.model.repository.utils.QueryOperator;

@Component
public class Start {
			@Autowired
			private AbastecimientoRepository abasDAO;			
		   	@Autowired
			private DescripcionRepository desDAO;
			@Autowired
			private DetalleRepository detDAO;
			@Autowired
			private PagoRepository pagDAO;
			@Autowired
			private PedidoRepository pedDAO;
			@Autowired
			private PrecioRepository preDAO;
			@Autowired
			private ProductoRepository proDAO;
			@Autowired
			private VentaRepository venDAO;
			//desde aqui funcionalidad es wonky
			@Autowired
			private ProductoFilterRepository pedFilDAO;
			@Autowired
			private ClienteCustomRepository clienDAO = new ClienteCustomRepository(ClienteRepository.class);
			@Autowired
			private DescripcionesFilterRepository desFilDAO;
			@Autowired
			private FilterRepository<ProductoFilterRepository, Producto> repoPedidos;
			@Autowired
			private FilterRepository<DescripcionesFilterRepository, Descripcion> repoDescripciones;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("cisonioModel");
			EntityManager em = emf.createEntityManager();
			RepositoryFactorySupport factory = new JpaRepositoryFactory(em);
			
			
			public static void main(String[] args) { 
				Start start = new Start();
				//start.categoriaTest();
				//start.clienteTest();
				//start.descripcionTest();
				//start.pagoTest();
				//start.pedidoTest();
				//start.precioTest();
				//start.productoTest();
				//start.ventaTest();
				//start.abastecimientoTest();
				
				
				
			}
			
			private void abastecimientoTest() {
				// TODO
				/*
				 * 
				 * */
				
			}

			private void productoTest() {
				// TODO Auto-generated method stub
				
			}

			private  void ventaTest() {
				// TODO Auto-generated method stub
				
			}

			private void precioTest() {
				// TODO Auto-generated method stub
				
			}

			private void pedidoTest() {
				// TODO Auto-generated method stub
				
			}

			private void pagoTest() {
				// TODO Auto-generated method stub
				
			}

			private void descripcionTest() {
				// TODO Auto-generated method stub
				
			}

			private void clienteTest() {
				clienDAO.createRepository();
				Cliente cli1 = new Cliente();
				cli1.setNombre("Sra Maria");
				cli1.setTelefono("+56943434343");
				clienDAO.repositorio.saveAndFlush(cli1);
				Filter nameLike = Filter.builder()
			                .field("nombre")
			                .operator(QueryOperator.LIKE)
			                .value("maria")
			                .build();
				List<Filter> filters = new ArrayList<>();
		        filters.add(nameLike);
		        List<Cliente> ans = clienDAO.getQueryResult(filters);
		        System.out.println(ans.get(0).getNombre());
				// TODO Crear clientes con nombre y numero de telefono (crear constructores necesarios en clase cliente)
				// TODO guardar con safeAndFlush clientes en base de datos
				// TODO crear metodos en repositorio cliente para buscar cliente por nombre 
				
			}

			public void categoriaTest() {
				CategoriaRepository catDAO = factory.getRepository(CategoriaRepository.class);
				Categoria maquillaje = new Categoria("maquillaje");
				Categoria perfumes = new Categoria("perfumeria");
				Categoria cabello = new Categoria("cabello");
				Categoria rostro = new Categoria("rostro");
				catDAO.saveAndFlush(maquillaje);
				catDAO.saveAndFlush(perfumes);
				catDAO.saveAndFlush(cabello);
				catDAO.saveAndFlush(rostro);		
				Categoria maq = catDAO.findByNombre("maquillaje");
				System.out.println(maq.getIdCategorias());
				Categoria ojos = new Categoria("ojos",maq);
				catDAO.saveAndFlush(ojos);
				Categoria maq2 = catDAO.findByNombre("maquillaje");
				System.out.println(maq2.getHijas().iterator().next().getNombre());
			}
			
			

}

package cl.inacap.cisonioApp.model.services;

import org.springframework.beans.factory.annotation.Autowired;

import cl.inacap.cisonioApp.model.dto.transacciones.Abastecimiento;
import cl.inacap.cisonioApp.model.dto.transacciones.Pedido;
import cl.inacap.cisonioApp.model.dto.transacciones.Venta;
import cl.inacap.cisonioApp.model.repository.AbastecimientoRepository;
import cl.inacap.cisonioApp.model.repository.PedidoRepository;
import cl.inacap.cisonioApp.model.repository.RepositoryFactory;
import cl.inacap.cisonioApp.model.repository.VentaRepository;

public class TransaccionServices {
	
	@Autowired
	PedidoRepository pediDAO = RepositoryFactory.getPedidoDAO();
	
	@Autowired
	VentaRepository venDAO = RepositoryFactory.getVentaDAO();
	
	@Autowired
	AbastecimientoRepository abasDAO = RepositoryFactory.getAbastecimientoDAO();
	
	public void guardarVenta(Venta v) {
		RepositoryFactory.getEm().getTransaction().begin();
		venDAO.saveAndFlush(v);
		RepositoryFactory.getEm().getTransaction().commit();
		RepositoryFactory.getEm().refresh(v);
	}
	
	public void guardarPedido(Pedido p) {
		RepositoryFactory.getEm().getTransaction().begin();
		pediDAO.saveAndFlush(p);
		RepositoryFactory.getEm().getTransaction().commit();
		RepositoryFactory.getEm().refresh(p);
		}
	
	public void guardarEnInventario(Abastecimiento a) {
		RepositoryFactory.getEm().getTransaction().begin();
		abasDAO.saveAndFlush(a);
		RepositoryFactory.getEm().getTransaction().commit();
		RepositoryFactory.getEm().refresh(a);
		RepositoryFactory.getEm().refresh(a.getDetalles());
	}
	

}

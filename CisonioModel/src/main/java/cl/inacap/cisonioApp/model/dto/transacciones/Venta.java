package cl.inacap.cisonioApp.model.dto.transacciones;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import cl.inacap.cisonioApp.model.dto.Cliente;
import cl.inacap.cisonioApp.model.dto.productos.Detalle;

// TODO: Auto-generated Javadoc
/**
 * The Class Venta corresponde a una transaccion entre el usuario de la aplicacion y un cliente
 */
@Entity
@PrimaryKeyJoinColumn(name = "idVenta")
public class Venta extends Transaccion{
	
	/** The cliente es quien se le venden los productos */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idCliente")
    private Cliente cliente;
	
	/** The pedido es el pedido en que llegaron los productos*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idPedido")
    private Pedido pedido;

    /**
     * Gets the cliente.
     *
     * @return the cliente
     */
    public Cliente getCliente() {
        return this.cliente;
    }

    /**
     * Sets the cliente.
     *
     * @param cliente the new cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

//    public List<Detalle> getProductos() {
//        return this.productos;
//    }
//
//    public void setProductos(List<Detalle> productos) {
//        this.productos = productos;
//    }

    /**
 * Gets the pedido.
 *
 * @return the pedido
 */
public Pedido getPedido() {
        return this.pedido;
    }

    /**
     * Sets the pedido.
     *
     * @param pedido the new pedido
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

//    public int getTotal() {
//        int suma = 0;
//        for(Detalle d: productos){
//            suma += d.getDescripcion().getLastPrecio()*d.getCantidad();
//        }return suma;
//    }
}

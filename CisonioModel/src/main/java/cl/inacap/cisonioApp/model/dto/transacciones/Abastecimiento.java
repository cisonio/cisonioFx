package cl.inacap.cisonioApp.model.dto.transacciones;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import cl.inacap.cisonioApp.model.dto.productos.Producto;
// TODO: Auto-generated Javadoc

/**
 * The Class Abastecimiento corresponde a un tipo de transaccion, en especifico a la accion de guardar cosas en el inventario
 */
@Entity
@PrimaryKeyJoinColumn(name = "idAbastecimiento")
public class Abastecimiento extends Transaccion{
	
	/** The pedido es el pedido en el que se guardaron las cosas en inventario */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pedido")
    private Pedido pedido;
	

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

}
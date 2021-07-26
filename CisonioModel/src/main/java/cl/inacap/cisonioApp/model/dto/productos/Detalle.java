package cl.inacap.cisonioApp.model.dto.productos;

import java.util.List;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Transient;

import cl.inacap.cisonioApp.model.dto.transacciones.Transaccion;

// TODO: Auto-generated Javadoc
/**
 *Representa un detalle, un detalle relaciona una transaccion con las descripciones de los productos
 */
@Entity
public class Detalle{
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public DetalleId getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(DetalleId id) {
		this.id = id;
	}

	/**
	 * Gets the transaccion.
	 *
	 * @return the transaccion
	 */
	public Transaccion getTransaccion() {
		return transaccion;
	}

	/**
	 * Sets the transaccion.
	 *
	 * @param transaccion the new transaccion
	 */
	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	/**
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	public Descripcion getProducto() {
		return producto;
	}

	/**
	 * Sets the producto.
	 *
	 * @param producto the new producto
	 */
	public void setProducto(Descripcion producto) {
		this.producto = producto;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad the new cantidad
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the precio compra.
	 *
	 * @return the precio compra
	 */
	public double getPrecioCompra() {
		return precioCompra;
	}

	/**
	 * Sets the precio compra.
	 *
	 * @param precioCompra the new precio compra
	 */
	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	/**
	 * Gets the precio venta.
	 *
	 * @return the precio venta
	 */
	public double getPrecioVenta() {
		return precioVenta;
	}

	/**
	 * Sets the precio venta.
	 *
	 * @param precioVenta the new precio venta
	 */
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	/**
	 * Gets the precio.
	 *
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Sets the precio.
	 *
	 * @param precio the new precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/** id del detalle */
	@EmbeddedId
	DetalleId id;
	
	/** Transaccion a la que pertenece el detalle */
	@ManyToOne
	@MapsId("transaccionId")
	@JoinColumn(name="transaccion")
	private Transaccion transaccion;
	
	/** descripcion del producto del detalle que se transa en la transaccion*/
	@ManyToOne
	@MapsId("descripcionId")
	@JoinColumn(name="producto")
	private Descripcion producto;
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Detalle other = (Detalle) obj;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}

	/** cantidad de productos transados */
    private int cantidad;
	
	/** precio al que se compro el producto */
	@Transient
    private double precioCompra;
	
	/** precio al que de vendera el producto. */
	@Transient
    private double precioVenta;
	
	/** precio del producto (dependiendo del tipo de transaccion corresponde al precio de compra o venta). */
	private double precio;

    /**
     * Instantiates a new detalle.
     */
	public void Quitar(int cantidad) {
		if(this.cantidad >= cantidad) {
			this.cantidad -= cantidad;
		}
	}
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	public void Agregar(int cantidad) {
		if(0 <= cantidad) {
			this.cantidad += cantidad;
		}
	}
	
    public Detalle() {
    	super();
    }
    
 }

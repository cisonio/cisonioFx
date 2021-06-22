package cl.inacap.cisonioApp.model.dto.productos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class Producto representa un producto concreto,  con fecha de vencimiento, un producto en inventario.
 */
@Entity
public class Producto implements Serializable{
	
	/** The idproducto. */
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private int idproducto;
	
	/** descripcion del producto */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="codigo")
    private Descripcion idescripcion;
    
    /** fecha vencimiento del producto*/
    private LocalDate fechaVencimiento;
    
    /** The fecha compra es la fecha en que se adquirio el producto */
    private LocalDate fechaCompra;
    
    /** The precio compra es el precio al que se adquirio el producto */
    //no se si incluir precio aqui o no
    private int precioCompra; 
    
    /**
     * Instantiates a new producto.
     */
    public Producto() {
    	super();
    }
    
    /**
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public Descripcion getDescripcion() {
        return this.idescripcion;
    }

    /**
     * Sets the descripcion.
     *
     * @param descripcion the new descripcion
     */
    public void setDescripcion(Descripcion descripcion) {
        this.idescripcion = descripcion;
    }

    /**
     * Gets the fecha vencimiento.
     *
     * @return the fecha vencimiento
     */
    public LocalDate getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    /**
     * Sets the fecha vencimiento.
     *
     * @param fechaVencimiento the new fecha vencimiento
     */
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Gets the fecha compra.
     *
     * @return the fecha compra
     */
    public LocalDate getFechaCompra() {
        return this.fechaCompra;
    }

    /**
     * Sets the fecha compra.
     *
     * @param fechaCompra the new fecha compra
     */
    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    /**
     * Gets the precio.
     *
     * @return the precio
     */
    public int getPrecio() {
        return this.precioCompra;
    }

    /**
     * Sets the precio.
     *
     * @param precio the new precio
     */
    public void setPrecio(int precio) {
        this.precioCompra = precio;
    }

}
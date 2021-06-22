package cl.inacap.cisonioApp.model.dto.transacciones;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import cl.inacap.cisonioApp.model.dto.productos.Detalle;


// TODO: Auto-generated Javadoc
/**
 * The Class Transaccion representa una transaccion, como compra, venta y guardar en inventario o abasteciomiento
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Transaccion {
	
	/** The id transaccion. */
	@Id
    protected Integer idTransaccion;
    
    /** fecha corresponde a la fecha en la que se realiza la transaccion*/
    protected LocalDate fecha;
    
    /** Total corresponde al dinero intercabiado en la transaccion */
    protected int total;
    
    /** los detalles corresponden a los productos transados en la transaccion. */
    @OneToMany(mappedBy = "transaccion")
    Set<Detalle> detalles;

    /**
     * Instantiates a new transaccion.
     */
    public Transaccion() {
    	super();
    }
    
    /**
     * Gets the codigo.
     *
     * @return the codigo
     */
    public Integer getCodigo() {
        return this.idTransaccion;
    }

    /**
     * Sets the codigo.
     *
     * @param codigo the new codigo
     */
    public void setCodigo(Integer codigo) {
        this.idTransaccion = codigo;
    }

    /**
     * Gets the fecha.
     *
     * @return the fecha
     */
    public LocalDate getFecha() {
        return this.fecha;
    }

    /**
     * Sets the fecha.
     *
     * @param fecha the new fecha
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Gets the total.
     *
     * @return the total
     */
    public int getTotal() {
        return this.total;
    }

    /**
     * Sets the total.
     *
     * @param total the new total
     */
    public void setTotal(int total) {
        this.total = total;
    }
}

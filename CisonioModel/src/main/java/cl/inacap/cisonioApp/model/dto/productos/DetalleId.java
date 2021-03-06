package cl.inacap.cisonioApp.model.dto.productos;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import cl.inacap.cisonioApp.model.dto.transacciones.Transaccion;

// TODO: Auto-generated Javadoc
/**
 * The Class DetalleId es el id del detalle
 */
@Embeddable
public class DetalleId implements java.io.Serializable{
	
	/**
	 * Instantiates a new detalle id.
	 */
	public DetalleId() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the transaccion id.
	 *
	 * @return the transaccion id
	 */
	public Integer getTransaccionId() {
		return transaccionId;
	}

	/**
	 * Sets the transaccion id.
	 *
	 * @param transaccionId the new transaccion id
	 */
	public void setTransaccionId(Integer transaccionId) {
		this.transaccionId = transaccionId;
	}

	/**
	 * Gets the descripcion id.
	 *
	 * @return the descripcion id
	 */
	public Integer getDescripcionId() {
		return descripcionId;
	}

	/**
	 * Sets the descripcion id.
	 *
	 * @param descripcionId the new descripcion id
	 */
	public void setDescripcionId(Integer descripcionId) {
		this.descripcionId = descripcionId;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + descripcionId;
		result = prime * result + transaccionId;
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleId other = (DetalleId) obj;
		if (descripcionId != other.descripcionId)
			return false;
		if (transaccionId != other.transaccionId)
			return false;
		return true;
	}

	/** Id de la transaccion a la que pertenece el detalle */
	@Column(name="transaccion")
	private Integer transaccionId;
	
	/** Id de la descripcion del producto transado del detalle */
	@Column(name="producto")
	private Integer descripcionId;
}

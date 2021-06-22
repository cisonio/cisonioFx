package cl.inacap.cisonioApp.model.dto.productos;

import java.time.LocalDate;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

// TODO: Auto-generated Javadoc
/**
 * The Class Precio representa el precio de un producto en un rango de tiempo
 */
@Entity
public class Precio {
	
	/** The id. */
	@Id 
	@GeneratedValue( strategy=GenerationType.AUTO )
	
	int id;
	
	/** finicio es la fecha en la que se empezo a usar ese precio*/
	LocalDate finicio;
	
	/** The ffin es la fecha en la que se deja de usar el precio */
	LocalDate ffin;
	
	/** The valor es el precio */
	Double valor;
	
	/** The producto. */
	@ManyToOne
	@JoinColumn(name="producto")
	Descripcion producto;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the finicio.
	 *
	 * @return the finicio
	 */
	public LocalDate getFinicio() {
		return finicio;
	}
	
	/**
	 * Sets the finicio.
	 *
	 * @param finicio the new finicio
	 */
	public void setFinicio(LocalDate finicio) {
		this.finicio = finicio;
	}
	
	/**
	 * Gets the ffin.
	 *
	 * @return the ffin
	 */
	public LocalDate getFfin() {
		return ffin;
	}
	
	/**
	 * Sets the ffin.
	 *
	 * @param ffin the new ffin
	 */
	public void setFfin(LocalDate ffin) {
		this.ffin = ffin;
	}
	
	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}
	
	/**
	 * Sets the valor.
	 *
	 * @param valor the new valor
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
}

package cl.inacap.cisonioApp.model.dto;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Representa un cambio de estado en la cuenta del cliente, ya sea un pago o una deuda
 * @author Catalina
 *
 */
@Entity
public class Pago {
	/**
	 * id del pago
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	/**Getter fecha
	 * @return fecha del Pago
	 */
	public LocalDate getFecha() {
		return fecha;
	}
	/** Setter fecha
	 * @param fecha en la que se ingresa el pago
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	/**Getter monto
	 * @return monto del pago
	 */
	public int getMonto() {
		return monto;
	}
	/**
	 * Setter del Monto de Pago
	 * @param monto de pago
	 */
	public void setMonto(int monto) {
		this.monto = monto;
	}
	/**
	 * Getter del Cliente
	 * @return cliente 
	 */
	public Cliente getCliente() {
		return cliente;
	}
	/**
	 * Setter del Cliente
	 * @param cliente 
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	/**
	 * fecha de pago/deuda
	 */
	@Basic
	private LocalDate fecha;
	/**
	 * monto pagado/debido
	 */
	private int monto;
	/**
	 * cliente a quien pertenece el Pago
	 */
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente cliente;
}

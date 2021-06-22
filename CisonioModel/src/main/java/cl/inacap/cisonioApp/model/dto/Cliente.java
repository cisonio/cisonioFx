
package cl.inacap.cisonioApp.model.dto;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Representa a un Cliente
 * @author Catalina
 * 
 */
@Entity
public class Cliente{
	
	
	/**
	 * id del Cliente
	 */
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	int idCliente;
    /**
     *
     */
    @Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", telefono=" + telefono + "]";
	}
    
	/**
	 * Nombre del Cliente
	 */
	String nombre;
    /**
     * Numero de telefono del cliente en formato +569_________
     */
    String telefono;

    /**
     * Constructor vacio de la clase cliente
     */
    public Cliente() {
    	super();
    }
    
    /**
     * Constructor con parametros nombre y telefono de la clase cliente
     * @param nombre nombre del cliente
     * @param telefono telefono del cliente
     */
    public Cliente(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }


    /**
     * Getter del nombre del cliente
     * @return nombre del Cliente
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Setter del atributo nombre
     * @param nombre nombre del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter del atributo telefono
     * @return telefono del cliente
     */
    public String getTelefono() {
        return this.telefono;
    }

    /**
     * Setter del atributo telefono
     * @param telefono telefono del cliente
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    /**
     * Metodo equals
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cliente)) {
            return false;
        }
        Cliente cliente = (Cliente) o;
        return Objects.equals(nombre, cliente.nombre) && telefono == cliente.telefono;
    }

    /**
     *Metodo hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, telefono);
    }


}
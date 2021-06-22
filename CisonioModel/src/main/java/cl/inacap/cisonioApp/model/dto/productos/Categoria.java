package cl.inacap.cisonioApp.model.dto.productos;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

// TODO: Auto-generated Javadoc
/**
 * Representa la Categorias que puede adquirir un producto
 */
@Entity
public class Categoria {
	
	/**
	 * Instantiates a new categoria.
	 */
	public Categoria() {
		super();
	}
	
	/**
	 * Gets the id categorias.
	 *
	 * @return the id categorias
	 */
	public Integer getIdCategorias() {
		return idCategorias;
	}
	
	/**
	 * Sets the id categorias.
	 *
	 * @param idCategorias the new id categorias
	 */
	public void setIdCategorias(Integer idCategorias) {
		this.idCategorias = idCategorias;
	}
	
	/**
	 * Gets the padre.
	 *
	 * @return the padre
	 */
	public Categoria getPadre() {
		return padre;
	}
	
	/**
	 * Sets the padre.
	 *
	 * @param padre the new padre
	 */
	public void setPadre(Categoria padre) {
		this.padre = padre;
	}
	
	/**
	 * Gets the hijas.
	 *
	 * @return the hijas
	 */
	public Collection<Categoria> getHijas() {
		return hijas;
	}
	
	/**
	 * Sets the hijas.
	 *
	 * @param hijas the new hijas
	 */
	public void setHijas(Collection<Categoria> hijas) {
		this.hijas = hijas;
	}
	
	/**
	 * Gets the descripciones.
	 *
	 * @return the descripciones
	 */
	public List<Descripcion> getDescripciones() {
		return descripciones;
	}
	
	/**
	 * Sets the descripciones.
	 *
	 * @param descripciones the new descripciones
	 */
	public void setDescripciones(List<Descripcion> descripciones) {
		this.descripciones = descripciones;
	}
	
	/**
	 * Instantiates a new categoria.
	 *
	 * @param nombre the nombre
	 * @param padre the padre
	 */
	public Categoria(String nombre, Categoria padre) {
		super();
		this.nombre = nombre;
		this.padre = padre;
	}
	
	/**
	 * Instantiates a new categoria.
	 *
	 * @param nombre the nombre
	 */
	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/** The id categorias. */
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Integer idCategorias;
	
	/** Nombre de la categoria. */
	String nombre;
	
	/** Categoria padre de la Categoria */
	@ManyToOne
	@JoinColumn(name = "padre")
	private Categoria padre;
	
	/** Categorias hijas de la categoria */
	@OneToMany(mappedBy="padre")
	private Collection<Categoria> hijas;
	
	/** Descripciones(o producos) pertenecientes a la categoria. */
	@ManyToMany(mappedBy="categoria")
	private List<Descripcion> descripciones;
}

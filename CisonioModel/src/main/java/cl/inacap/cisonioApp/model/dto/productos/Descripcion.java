package cl.inacap.cisonioApp.model.dto.productos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

/**
 * Representa a la descripcion de producto, atributos que son aplicables a todas las instancias de un producto.
 * @author Catalina
 *
 */
@Entity
public class Descripcion {
    /**
     * Codigo del Producto, asignada por empresa
     */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
	private Integer codigo;
    /**
     * Nombre del Producto
     */
    private String nombre;
    /**
     * Descripcion del Producto, prosa.
     */
    private String descripcion; //descripcion en palabras
    /**
     * Empresa que vende el producto
     */
    private String empresa;
    
    /**
     * Historial de Precios del producto
     */
    @OneToMany(mappedBy="producto")
    private List<Precio> precios = new ArrayList<Precio>();
    
    /**
     * Detalles de transacciones en las que se ha transado este produto
     */
    @OneToMany(mappedBy="producto")
    private Set<Detalle> detalles;
    
    /**
     * Categorias del Producto
     */
    @ManyToMany
    @JoinTable(
    		name = "catproductos",
    		joinColumns = @JoinColumn(name="producto"),
    		inverseJoinColumns = @JoinColumn(name="categoria"))
    private List<Categoria> categoria;
    
    /**
     * Constructor de Descripcion
     */
    public Descripcion() {
    	super();
    }
    /**
     * Getter descripcion del producto.
     * @return
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion.
     *
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the empresa.
     *
     * @return the empresa
     */
    public String getEmpresa() {
        return this.empresa;
    }

    /**
     * Sets the empresa.
     *
     * @param empresa the new empresa
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
  

    /**
     * Instantiates a new descripcion.
     *
     * @param codigo the codigo
     * @param nombre the nombre
     */
    public Descripcion(int codigo, String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
    }
    public Descripcion(int i) {
    }

    
    /**
     * Gets the codigo.
     *
     * @return the codigo
     */
    public int getCodigo() {
        return this.codigo;
    }

    /**
     * Sets the codigo.
     *
     * @param codigo the new codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre the new nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


  
}
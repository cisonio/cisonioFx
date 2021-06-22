package cl.inacap.cisonioApp.model.dto.transacciones;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


// TODO: Auto-generated Javadoc
/**
 * The Class Pedido es un tipo de transaccion en el que se transfieren productos desde la empresa al usuario de la aplicacion
 */
@Entity
@PrimaryKeyJoinColumn(name = "idPedido")
public class Pedido extends Transaccion{
    
    /** La empresa es la empresa a la que se le compraron los productos */
    private String empresa;
    
    /** The campania es el numero de campana de la revista-empresa */
    private int campania;
//    private Map<Descripcion,Integer> productos;
//    
//    public Pedido(Map<Descripcion, Integer> nuevosProductos, LocalDate hoy) {
//		this.productos = nuevosProductos;
//		this.fecha = hoy;
//	}

    /**
 * Instantiates a new pedido.
 */
public Pedido() {
    	
    }
	
	/**
	 * Instantiates a new pedido.
	 *
	 * @param nuevosProductos the nuevos productos
	 * @param hoy the hoy
	 */
	public Pedido(Map<cl.inacap.cisonioApp.model.dto.productos.Descripcion, Integer> nuevosProductos, LocalDate hoy) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	//private List<Detalle> productos;
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
     * Gets the campania.
     *
     * @return the campania
     */
    public int getCampania() {
        return this.campania;
    }

    /**
     * Sets the campania.
     *
     * @param campania the new campania
     */
    public void setCampania(int campania) {
        this.campania = campania;
    }


//    public List<Descripcion> getProductos() {
//        return this.productos;
//    }
//
//    public void setProductos(List<Descripcion> productos) {
//        this.productos = productos;
//    }
}

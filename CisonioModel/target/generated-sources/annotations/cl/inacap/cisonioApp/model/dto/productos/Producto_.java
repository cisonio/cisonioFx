package cl.inacap.cisonioApp.model.dto.productos;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Producto.class)
public abstract class Producto_ {

	public static volatile SingularAttribute<Producto, LocalDate> fechaCompra;
	public static volatile SingularAttribute<Producto, LocalDate> fechaVencimiento;
	public static volatile SingularAttribute<Producto, Integer> precioCompra;
	public static volatile SingularAttribute<Producto, Integer> idproducto;
	public static volatile SingularAttribute<Producto, Descripcion> idescripcion;

	public static final String FECHA_COMPRA = "fechaCompra";
	public static final String FECHA_VENCIMIENTO = "fechaVencimiento";
	public static final String PRECIO_COMPRA = "precioCompra";
	public static final String IDPRODUCTO = "idproducto";
	public static final String IDESCRIPCION = "idescripcion";

}


package cl.inacap.cisonioApp.model.dto.productos;

import cl.inacap.cisonioApp.model.dto.transacciones.Transaccion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Detalle.class)
public abstract class Detalle_ {

	public static volatile SingularAttribute<Detalle, Double> precio;
	public static volatile SingularAttribute<Detalle, Transaccion> transaccion;
	public static volatile SingularAttribute<Detalle, DetalleId> id;
	public static volatile SingularAttribute<Detalle, Descripcion> producto;
	public static volatile SingularAttribute<Detalle, Integer> cantidad;

	public static final String PRECIO = "precio";
	public static final String TRANSACCION = "transaccion";
	public static final String ID = "id";
	public static final String PRODUCTO = "producto";
	public static final String CANTIDAD = "cantidad";

}


package main.java.cl.inacap.cisonioApp.model.dto.transacciones;

import cl.inacap.cisonioApp.model.dto.productos.Detalle;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Transaccion.class)
public abstract class Transaccion_ {

	public static volatile SingularAttribute<Transaccion, Integer> idTransaccion;
	public static volatile SingularAttribute<Transaccion, LocalDate> fecha;
	public static volatile SingularAttribute<Transaccion, Integer> total;
	public static volatile ListAttribute<Transaccion, Detalle> detalles;

	public static final String ID_TRANSACCION = "idTransaccion";
	public static final String FECHA = "fecha";
	public static final String TOTAL = "total";
	public static final String DETALLES = "detalles";

}


package main.java.cl.inacap.cisonioApp.model.dto;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pago.class)
public abstract class Pago_ {

	public static volatile SingularAttribute<Pago, LocalDate> fecha;
	public static volatile SingularAttribute<Pago, Cliente> cliente;
	public static volatile SingularAttribute<Pago, Integer> monto;
	public static volatile SingularAttribute<Pago, Integer> id;

	public static final String FECHA = "fecha";
	public static final String CLIENTE = "cliente";
	public static final String MONTO = "monto";
	public static final String ID = "id";

}


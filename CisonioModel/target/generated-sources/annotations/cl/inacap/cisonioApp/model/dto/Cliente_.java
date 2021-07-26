package cl.inacap.cisonioApp.model.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ {

	public static volatile SingularAttribute<Cliente, Integer> idCliente;
	public static volatile SingularAttribute<Cliente, String> telefono;
	public static volatile SingularAttribute<Cliente, String> nombre;
	public static volatile ListAttribute<Cliente, Pago> pagos;

	public static final String ID_CLIENTE = "idCliente";
	public static final String TELEFONO = "telefono";
	public static final String NOMBRE = "nombre";
	public static final String PAGOS = "pagos";

}


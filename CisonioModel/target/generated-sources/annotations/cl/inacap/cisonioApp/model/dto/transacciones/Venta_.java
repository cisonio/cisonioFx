package cl.inacap.cisonioApp.model.dto.transacciones;

import cl.inacap.cisonioApp.model.dto.Cliente;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Venta.class)
public abstract class Venta_ extends cl.inacap.cisonioApp.model.dto.transacciones.Transaccion_ {

	public static volatile SingularAttribute<Venta, Cliente> cliente;
	public static volatile SingularAttribute<Venta, Pedido> pedido;

	public static final String CLIENTE = "cliente";
	public static final String PEDIDO = "pedido";

}


package cl.inacap.cisonioApp.model.dto.transacciones;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedido.class)
public abstract class Pedido_ extends cl.inacap.cisonioApp.model.dto.transacciones.Transaccion_ {

	public static volatile SingularAttribute<Pedido, String> empresa;
	public static volatile SingularAttribute<Pedido, Integer> campania;

	public static final String EMPRESA = "empresa";
	public static final String CAMPANIA = "campania";

}


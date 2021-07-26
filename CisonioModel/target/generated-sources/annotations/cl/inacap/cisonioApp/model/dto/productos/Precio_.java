package cl.inacap.cisonioApp.model.dto.productos;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Precio.class)
public abstract class Precio_ {

	public static volatile SingularAttribute<Precio, Double> valor;
	public static volatile SingularAttribute<Precio, LocalDate> ffin;
	public static volatile SingularAttribute<Precio, Integer> id;
	public static volatile SingularAttribute<Precio, Descripcion> producto;
	public static volatile SingularAttribute<Precio, LocalDate> finicio;

	public static final String VALOR = "valor";
	public static final String FFIN = "ffin";
	public static final String ID = "id";
	public static final String PRODUCTO = "producto";
	public static final String FINICIO = "finicio";

}


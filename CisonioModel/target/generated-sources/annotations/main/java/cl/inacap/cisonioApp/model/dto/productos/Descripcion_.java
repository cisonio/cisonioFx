package main.java.cl.inacap.cisonioApp.model.dto.productos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Descripcion.class)
public abstract class Descripcion_ {

	public static volatile SingularAttribute<Descripcion, String> descripcion;
	public static volatile SingularAttribute<Descripcion, Integer> codigo;
	public static volatile ListAttribute<Descripcion, Categoria> categoria;
	public static volatile SetAttribute<Descripcion, Detalle> detalles;
	public static volatile SingularAttribute<Descripcion, String> empresa;
	public static volatile SingularAttribute<Descripcion, String> nombre;
	public static volatile ListAttribute<Descripcion, Precio> precios;

	public static final String DESCRIPCION = "descripcion";
	public static final String CODIGO = "codigo";
	public static final String CATEGORIA = "categoria";
	public static final String DETALLES = "detalles";
	public static final String EMPRESA = "empresa";
	public static final String NOMBRE = "nombre";
	public static final String PRECIOS = "precios";

}


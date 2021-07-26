package cl.inacap.cisonioApp.model.dto.productos;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Categoria.class)
public abstract class Categoria_ {

	public static volatile SingularAttribute<Categoria, Integer> idCategorias;
	public static volatile SingularAttribute<Categoria, Categoria> padre;
	public static volatile CollectionAttribute<Categoria, Categoria> hijas;
	public static volatile ListAttribute<Categoria, Descripcion> descripciones;
	public static volatile SingularAttribute<Categoria, String> nombre;

	public static final String ID_CATEGORIAS = "idCategorias";
	public static final String PADRE = "padre";
	public static final String HIJAS = "hijas";
	public static final String DESCRIPCIONES = "descripciones";
	public static final String NOMBRE = "nombre";

}


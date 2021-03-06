package cl.inacap.cisonioApp.model.repository.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.stereotype.Component;

import static org.springframework.data.jpa.domain.Specification.where;

public abstract class FilterRepository<M extends RepositorioFiltrable<T,Integer>, T> {
	final Class<M> typeParameterClass;
	public M repositorio;
	public FilterRepository(Class<M> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
		
	}
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("cisonioModel");
	EntityManager em = emf.createEntityManager();
	RepositoryFactorySupport factory = new JpaRepositoryFactory(em);
	
	public void createRepository() {
		repositorio = factory.getRepository(typeParameterClass);
	}
	
	public List<T> getQueryResult(List<Filter> filters){
	        if(filters.size()>0) {
	            return repositorio.findAll(getSpecificationFromFilters(filters));
	        }else {
	            return repositorio.findAll();
	        }
	    }

	    private Specification<T> getSpecificationFromFilters(List<Filter> filter) {
	        Specification<T> specification = where(createSpecification(filter.remove(0)));
	        for (Filter input : filter) {
	            specification = specification.and(createSpecification(input));
	        }
	        return specification;
	    }

	    private Specification<T> createSpecification(Filter input) {
	        switch (input.getOperator()){
	            case EQUALS:
	                return (root, query, criteriaBuilder) ->
	                        criteriaBuilder.equal(root.get(input.getField()),
	                        castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
	            case NOT_EQ:
	                return (root, query, criteriaBuilder) ->
	                        criteriaBuilder.notEqual(root.get(input.getField()),
	                        castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
	            case GREATER_THAN:
	                return (root, query, criteriaBuilder) ->
	                        criteriaBuilder.gt(root.get(input.getField()),
	                        (Number) castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
	            case LESS_THAN:
	                return (root, query, criteriaBuilder) ->
	                        criteriaBuilder.lt(root.get(input.getField()),
	                        (Number) castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue()));
	            case LIKE:
	                return (root, query, criteriaBuilder) ->
	                        criteriaBuilder.like(root.get(input.getField()), "%"+input.getValue()+"%");
	            case IN:
	                return (root, query, criteriaBuilder) ->
	                        criteriaBuilder.in(root.get(input.getField()))
	                        .value(castToRequiredType(root.get(input.getField()).getJavaType(), input.getValues()));
	            default:
	                throw new RuntimeException("Operation not supported yet");
	        }
	    }

	    private Object castToRequiredType(Class fieldType, String value) {
	        if(fieldType.isAssignableFrom(Double.class)){
	            return Double.valueOf(value);
	        }else if(fieldType.isAssignableFrom(Integer.class)){
	            return Integer.valueOf(value);
	        }else if(Enum.class.isAssignableFrom(fieldType)){
	            return Enum.valueOf(fieldType, value);
	        }
	        return null;
	    }

	    private Object castToRequiredType(Class fieldType, List<String> value) {
	        List<Object> lists = new ArrayList<>();
	        for (String s : value) {
	            lists.add(castToRequiredType(fieldType, s));
	        }
	        return lists;
	    }
}
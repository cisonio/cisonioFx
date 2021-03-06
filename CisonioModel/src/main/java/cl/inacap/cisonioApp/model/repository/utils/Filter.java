package cl.inacap.cisonioApp.model.repository.utils;



import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * Gets the values.
 *
 * @return the values
 */
@Getter

/**
 * Sets the values.
 *
 * @param values the new values
 */
@Setter

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Builder
public class Filter {
	
	/** The field corresponde al campo que sera filtrado */
	private String field;
    
    /** The operator  corresponde al tipo de filtro usado */
    private QueryOperator operator;
    
    /** The value es el valor por el que sera filtrado el campo */
    private String value;
    
    /** The values son los valores que seran usados en el filtro en el caso de que el operator sea IN */
    private List<String> values;//Used in case of IN operator
}
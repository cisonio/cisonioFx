package cl.inacap.cisonioApp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.inacap.cisonioApp.model.dto.transacciones.Transaccion;

/**
 * The Interface TransaccionRepository extiende JpaRepository, esta clase es el DAO de la transacciones.
 *
 * @param <T> the generic type debe ser un tipo de transaccion
 */
public interface TransaccionRepository<T extends Transaccion> extends JpaRepository<Transaccion,Integer>{

}

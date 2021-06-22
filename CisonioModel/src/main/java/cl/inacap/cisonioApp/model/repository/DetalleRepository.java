package cl.inacap.cisonioApp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.inacap.cisonioApp.model.dto.productos.Detalle;

public interface DetalleRepository extends JpaRepository<Detalle,Integer>{

}

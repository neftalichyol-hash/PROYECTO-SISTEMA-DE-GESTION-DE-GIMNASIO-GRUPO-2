package com.gimnasio.gimnasio_backend.modulo2.repository;

import com.gimnasio.gimnasio_backend.modulo2.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByUsuarioUsuarioId(Integer usuarioId);

    // Mapea directamente la vista SQL dbo.vw_ClientesRiesgoDesercion
    @Query(value = "SELECT * FROM dbo.vw_ClientesRiesgoDesercion", nativeQuery = true)
    List<ClienteRiesgoProjection> obtenerClientesEnRiesgo();
}
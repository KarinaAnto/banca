package com.upc.banca.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.upc.banca.models.entity.Movimiento;

@Repository
public interface IMovimientoDao extends JpaRepository<Movimiento, Long> {
	@Query("select m from CuentaBancaria cb join fetch cb.cliente c join fetch cb.movimientos m join fetch cb.banco b where m.id=?1")
	public Movimiento fetchByIdWithClienteWhithMovimientosWithBanco(Long id);
}

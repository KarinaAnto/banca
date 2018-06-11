package com.upc.banca.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.upc.banca.models.entity.CuentaBancaria;

@Repository
public interface ICuentaBancariaDao extends JpaRepository<CuentaBancaria,Long>{
	
	@Query("select cb from CuentaBancaria cb join fetch cb.cliente c join fetch cb.movimientos m join fetch cb.banco b where cb.id=?1")
	public CuentaBancaria fetchByIdWithClienteWhithMovimientosWithBanco(Long id);
}
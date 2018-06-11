package com.upc.banca.service;

import java.util.List;

import com.upc.banca.models.entity.Movimiento;

public interface IMovimientoService {
	public List<Movimiento> findAll();
	public void save(Movimiento movimiento);
	public Movimiento findById(Long id);
	public void delete(Long id);
	public Movimiento fetchCuentaBancariaByIdWithClienteWhitMovimientoWithBanco(Long id);
}

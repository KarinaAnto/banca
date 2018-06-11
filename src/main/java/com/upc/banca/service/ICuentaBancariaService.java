package com.upc.banca.service;

import java.util.List;

import com.upc.banca.models.entity.CuentaBancaria;


public interface ICuentaBancariaService {
	public List<CuentaBancaria> findAll();
	public void save(CuentaBancaria cuenta);
	public CuentaBancaria findById(Long id);
	public void delete(Long id);
	public CuentaBancaria fetchCuentaBancariaByIdWithClienteWhitMovimientoWithBanco(Long id);
}

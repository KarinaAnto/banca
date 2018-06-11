package com.upc.banca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.upc.banca.models.dao.ICuentaBancariaDao;
import com.upc.banca.models.entity.CuentaBancaria;
import com.upc.banca.service.ICuentaBancariaService;

public class CuentaBancariaServiceImpl implements ICuentaBancariaService{

	@Autowired
	private ICuentaBancariaDao cuentaDao;
	
	@Override
	@Transactional
	public List<CuentaBancaria> findAll() {
		return cuentaDao.findAll();
	}

	@Override
	@Transactional
	public void save(CuentaBancaria cuenta) {
		cuentaDao.save(cuenta);
	}

	@Override
	@Transactional(readOnly = true)
	public CuentaBancaria findById(Long id) {
		return cuentaDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		cuentaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public CuentaBancaria fetchCuentaBancariaByIdWithClienteWhitMovimientoWithBanco(Long id) {
		return cuentaDao.fetchByIdWithClienteWhithMovimientosWithBanco(id);
	}

}

package com.upc.banca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.upc.banca.models.dao.IMovimientoDao;
import com.upc.banca.models.entity.Movimiento;
import com.upc.banca.service.IMovimientoService;

public class MovimientoServiceImpl implements IMovimientoService{

	@Autowired
	private IMovimientoDao movimientoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Movimiento> findAll() {
		return movimientoDao.findAll();
	}

	@Override
	@Transactional
	public void save(Movimiento movimiento) {
		movimientoDao.save(movimiento);
	}

	@Override
	@Transactional(readOnly = true)
	public Movimiento findById(Long id) {
		return movimientoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		movimientoDao.deleteById(id);
	}

	@Override
	public Movimiento fetchCuentaBancariaByIdWithClienteWhitMovimientoWithBanco(Long id) {
		return movimientoDao.fetchByIdWithClienteWhithMovimientosWithBanco(id);
	}

}

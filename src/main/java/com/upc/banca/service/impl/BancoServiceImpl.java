package com.upc.banca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.upc.banca.models.dao.IBancoDao;
import com.upc.banca.models.dao.IClienteDao;
import com.upc.banca.models.entity.Banco;
import com.upc.banca.service.IBancoService;

public class BancoServiceImpl implements IBancoService{

	@Autowired
	private IBancoDao bancoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Banco> findAll() {
		return bancoDao.findAll();
	}

	@Override
	@Transactional
	public void save(Banco banco) {
		bancoDao.save(banco);
	}

	@Override
	@Transactional(readOnly = true)
	public Banco findById(Long id) {
		return bancoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		bancoDao.deleteById(id);
	}

	@Override
	public List<Banco> findByNombre(String term) {
		return bancoDao.findByNombreLikeIgnoreCase(term);
	}

}

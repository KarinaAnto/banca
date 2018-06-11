package com.upc.banca.service;

import java.util.List;

import com.upc.banca.models.entity.Banco;

public interface IBancoService {
	public List<Banco> findAll();
	public void save(Banco banco);
	public Banco findById(Long id);
	public void delete(Long id);
	public List<Banco> findByNombre(String term);
}

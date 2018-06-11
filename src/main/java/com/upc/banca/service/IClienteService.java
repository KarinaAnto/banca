package com.upc.banca.service;

import java.util.List;

import com.upc.banca.models.entity.Cliente;


public interface IClienteService {
	public List<Cliente> findAll();
	public void save(Cliente cliente);
	public Cliente findById(Long id);
	public void delete(Long id);
}

package com.upc.banca.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.upc.banca.models.entity.Cliente;

@Repository
public interface IClienteDao extends JpaRepository<Cliente, Long> {
	public List<Cliente> findByNombreLikeIgnoreCase(String term);
}

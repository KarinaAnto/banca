package com.upc.banca.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upc.banca.models.entity.Banco;

@Repository
public interface IBancoDao extends JpaRepository<Banco, Long>{
	public List<Banco> findByNombreLikeIgnoreCase(String term);
}

package com.upc.banca.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upc.banca.models.entity.Movimiento;

@Repository
public interface IMovimientoDao extends JpaRepository<Movimiento, Long> {

}

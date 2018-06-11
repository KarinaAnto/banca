package com.upc.banca.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "movimientos")
public class Movimiento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String tipo;
	
	@NotNull
	private Double monto;
	
	private Integer movXdia;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private CuentaBancaria cuenta;
	
	public void prePersist() {
        createAt = new Date();
    }
	public boolean variar_monto() {
		if (tipo=="retiro") {
			if (monto>cuenta.getSaldoBase()) {
				return false;
			}
			else {
				cuenta.setSaldoBase(cuenta.getSaldoBase()-monto);
				return true;
			}
		}
		if (tipo=="deposito") {
			cuenta.setSaldoBase(cuenta.getSaldoBase()+ monto);
			return true;
		}
		else {
			return false;
		}
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public Integer getMovXdia() {
		return movXdia;
	}
	public void setMovXdia(Integer movXdia) {
		this.movXdia = movXdia;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public CuentaBancaria getCuenta() {
		return cuenta;
	}
	public void setCuenta(CuentaBancaria cuenta) {
		this.cuenta = cuenta;
	}
}

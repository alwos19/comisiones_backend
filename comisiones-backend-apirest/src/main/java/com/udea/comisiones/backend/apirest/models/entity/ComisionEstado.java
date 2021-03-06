package com.udea.comisiones.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name="comisiones_estados")
public class ComisionEstado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	//Foreign Keys
	
	@JsonIgnoreProperties({"comisionEstado", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	private Comision comision;
	
	@JsonIgnoreProperties({"comisionEstado", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	private Estado estado;
	
	
	//
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	
	//Getters and Setters


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Comision getComision() {
		return comision;
	}


	public void setComision(Comision comision) {
		this.comision = comision;
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	private static final long serialVersionUID = 1L;
}


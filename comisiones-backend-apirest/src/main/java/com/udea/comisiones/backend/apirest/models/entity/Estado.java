package com.udea.comisiones.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="estados")
public class Estado implements Serializable{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	//@Column(name = "comision_estado")
	@JsonIgnoreProperties({"estado", "hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estado", cascade = CascadeType.ALL)
	private List<ComisionEstado> comisionEstado;

	
	//
	
	public Estado() {
		this.comisionEstado = new ArrayList<ComisionEstado>();
	}
	

	//
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public List<ComisionEstado> getComisionEstado() {
		return comisionEstado;
	}


	public void setComisionEstado(List<ComisionEstado> comisionEstado) {
		this.comisionEstado = comisionEstado;
	}




	private static final long serialVersionUID = 1L;
}

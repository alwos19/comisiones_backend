package com.udea.comisiones.backend.apirest.models.entity;

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

@Entity
@Table(name = "tipos_solicitud")
public class TipoSolicitud {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String descripcion;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoSolicitud", cascade = CascadeType.ALL)
	private List<Comision> comisiones;

	
	//
	
	public TipoSolicitud() {
		this.comisiones = new ArrayList<Comision>();
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

	public List<Comision> getComisiones() {
		return comisiones;
	}

	public void setComisiones(List<Comision> comisiones) {
		this.comisiones = comisiones;
	}
	
	

}

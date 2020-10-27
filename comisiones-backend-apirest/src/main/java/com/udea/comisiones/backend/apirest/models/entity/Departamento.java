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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="departamentos")
public class Departamento implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String descripcion;
	
	@JsonIgnoreProperties({"departamento", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	private Facultad facultad;
	
	@JsonIgnoreProperties({"departamento", "hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departamento", cascade = CascadeType.ALL)
	private List<Usuario> usuario;
	
	//
	
	@JsonCreator
	public Departamento() {
		this.usuario = new ArrayList<Usuario>();
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


	public Facultad getFacultad() {
		return facultad;
	}
	

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}
	

	public List<Usuario> getUsuario() {
		return usuario;
	}
	

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}





	private static final long serialVersionUID = 1L;

}

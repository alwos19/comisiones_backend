package com.udea.comisiones.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "facultades")
public class Facultad implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "no debe estar en blanco")
	@Size(max=50, message = "el tamaño debe estar entre 1 y 50 caracteres")
	@Column(nullable=false)
	private String nombre;
	
	@NotNull(message = "no debe ser nulo")
	@Column(name = "centro_de_costo", nullable=false)
	private Integer centroDeCosto;
	
	@Size(max=255, message = "el tamaño debe ser de máximo 255 caracteres")
	private String descripcion;

	
    //Foreign Keys
	
	@JsonIgnoreProperties({"facultad", "hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facultad", cascade = CascadeType.ALL)
	private List<Departamento> departamento;

	
	//Constructor

	@JsonCreator
	public Facultad() {
		this.departamento = new ArrayList<Departamento>();
	}

	//Getters and Setters

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

	public Integer getCentroDeCosto() {
		return centroDeCosto;
	}

	public void setCentroDeCosto(Integer centroDeCosto) {
		this.centroDeCosto = centroDeCosto;
	}

	public List<Departamento> getDepartamento() {
		return departamento;
	}

	public void setDepartamento(List<Departamento> departamento) {
		this.departamento = departamento;
	}

	private static final long serialVersionUID = 1L;

}

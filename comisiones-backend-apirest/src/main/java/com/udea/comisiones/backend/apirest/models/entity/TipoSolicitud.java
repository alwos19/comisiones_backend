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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tipos_solicitud")
public class TipoSolicitud implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "no debe estar en blanco")
	@Size(max=45, message = "el tamaño debe estar entre 1 y 45 caracteres")
	@Column(nullable=false, unique = true)
	private String nombre;
	
	@Size(max=255, message = "el tamaño debe ser máximo de 255 caracteres")
	private String descripcion;
	
	//Foreign Keys
	
	@JsonIgnoreProperties({"tipoSolicitud", "hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoSolicitud", cascade = CascadeType.ALL)
	private List<Comision> comision;

	
	//
	
	@JsonCreator
	public TipoSolicitud() {
		this.comision = new ArrayList<Comision>();
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
	
	
	public List<Comision> getComision() {
		return comision;
	}

	public void setComision(List<Comision> comision) {
		this.comision = comision;
	}




	private static final long serialVersionUID = 1L;
	

}

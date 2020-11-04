package com.udea.comisiones.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "documentos")
public class Documento implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max=45, message = "el tamaño debe de máximo 45 caracteres")
	private String nombre;
	
	@NotNull(message = "no debe estar vacio")
	@Column(name = "es_anexo", nullable=false)
	private Boolean esAnexo;
	
	@NotNull(message = "no debe estar vacio")
	@Column(name = "es_cumplido", nullable=false)
	private Boolean esCumplido;
	
	//Foreign Keys
	
	@JsonIgnoreProperties({"documento", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	private Comision comision;
	
	
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

	public Boolean getEsAnexo() {
		return esAnexo;
	}

	public void setEsAnexo(Boolean esAnexo) {
		this.esAnexo = esAnexo;
	}

	public Boolean getEsCumplido() {
		return esCumplido;
	}

	public void setEsCumplido(Boolean esCumplido) {
		this.esCumplido = esCumplido;
	}

	public Comision getComision() {
		return comision;
	}

	public void setComision(Comision comision) {
		this.comision = comision;
	}
	
	private static final long serialVersionUID = 1L;

}

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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cumplidos")
public class Cumplido implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "no debe estar en blanco")
	@Size(max=20, message = "el tamaño debe estar entre 1 y 20 caracteres")
	@Column(nullable = false)
	private String nombre;
	
	@NotBlank(message = "no debe estar en blanco")
	@Size(max=255, message = "el tamaño debe estar entre 1 y 255 caracteres")
	@Column(nullable = false)
	private String descripcion;
	
	@Column(name = "fecha_envio", nullable = false)
	private Date fechaEnvio;
	
	@Column(name = "fecha_confirmacion", nullable = false)
	private Date fechaConfirmacion;
	
	@NotBlank(message = "no debe estar en blanco")
	@Column(nullable = false)
	private String correo;
	
	@Column(name = "informacion_complementaria")
	private String informacionComplementaria;
	
	//Foreign Keys
	
	@JsonIgnoreProperties({"cumplido", "hibernateLazyInitializer", "handler"})
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Date getFechaConfirmacion() {
		return fechaConfirmacion;
	}

	public void setFechaConfirmacion(Date fechaConfirmacion) {
		this.fechaConfirmacion = fechaConfirmacion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getInformacionComplementaria() {
		return informacionComplementaria;
	}

	public void setInformacionComplementaria(String informacionComplementaria) {
		this.informacionComplementaria = informacionComplementaria;
	}

	public Comision getComision() {
		return comision;
	}

	public void setComision(Comision comision) {
		this.comision = comision;
	}
	
	
	private static final long serialVersionUID = 1L;


}

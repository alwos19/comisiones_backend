package com.udea.comisiones.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "comisiones")
public class Comision implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date fechaInicio;
	private Date fechaFin;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	private Date fechaActulizacion;
	private String justificacion;
	private String resolucion;
	private Date fechaResolucion;
	private String respuestaDevolucion;
	private String idioma;
	private String lugar;

	@ManyToMany
	Set<EstadosComision> muchosEstados;

	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getFechaActulizacion() {
		return fechaActulizacion;
	}

	public void setFechaActulizacion(Date fechaActulizacion) {
		this.fechaActulizacion = fechaActulizacion;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public Date getFechaResolucion() {
		return fechaResolucion;
	}

	public void setFechaResolucion(Date fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}

	public String getRespuestaDevolucion() {
		return respuestaDevolucion;
	}

	public void setRespuestaDevolucion(String respuestaDevolucion) {
		this.respuestaDevolucion = respuestaDevolucion;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

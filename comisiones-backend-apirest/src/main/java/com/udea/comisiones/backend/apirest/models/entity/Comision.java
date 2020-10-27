package com.udea.comisiones.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "comisiones")
public class Comision implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha_inicio")
	private Date fechaInicio;

	@Column(name = "fecha_fin")
	private Date fechaFin;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@Column(name = "fecha_actualizacion")
	private Date fechaActulizacion;

	private String justificacion;
	private String resolucion;

	@Column(name = "fecha_resolucion")
	private Date fechaResolucion;

	@Column(name = "respuesta_devolucion")
	private String respuestaDevolucion;

	private String idioma;
	private String lugar;

	
	@JsonIgnoreProperties({"comision", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	@JsonIgnoreProperties({"comision", "hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comision", cascade = CascadeType.ALL)
	private List<Documento> documento;
	
	@JsonIgnoreProperties({"comision", "hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comision", cascade = CascadeType.ALL)
	private List<Cumplido> cumplido;

	@JsonIgnoreProperties({"comision", "hibernateLazyInitializer", "handler"})
	@Column(name = "comision_estado")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comision", cascade = CascadeType.ALL)
	private List<ComisionEstado> comisionEstado;
	
	@JsonIgnoreProperties({"comision", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_solicitud_id")
	private TipoSolicitud tipoSolicitud;
	
	//

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	@JsonCreator
	public Comision() {
		this.documento = new ArrayList<Documento>();
		this.cumplido = new ArrayList<Cumplido>();
		this.comisionEstado = new ArrayList<ComisionEstado>();
	}

	
	//

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

	public List<Documento> getDocumento() {
		return documento;
	}

	public void setDocumento(List<Documento> documento) {
		this.documento = documento;
	
	}

	public List<Cumplido> getCumplido() {
		return cumplido;
	}

	public void setCumplido(List<Cumplido> cumplido) {
		this.cumplido = cumplido;
	}

	public List<ComisionEstado> getComisionEstado() {
		return comisionEstado;
	}

	public void setComisionEstado(List<ComisionEstado> comisionEstado) {
		this.comisionEstado = comisionEstado;
	}
	
	public TipoSolicitud getTipoSolicitud() {
		return tipoSolicitud;
	}

	public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	
	//


	private static final long serialVersionUID = 1L;

}

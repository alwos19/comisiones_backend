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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "no debe estar en blanco")
	@Size(max=5, message = "el tamaño debe estar entre 1 y 5 caracteres")
	@Column(name = "tipo_identificacion", nullable=false)
	private String tipoIdentificacion;

	@NotNull(message = "no debe estar nulo")
	@Column(unique = true, nullable=false)
	private Integer identificacion;
	
	@NotBlank(message = "no debe estar en blanco")
	@Size(max=30, message = "el tamaño debe estar entre 1 y 30 caracteres")
	@Column(nullable=false)
	private String nombre;
	
	@Size(max=30, message = "el tamaño debe estar entre 1 y 30 caracteres")
	@Column(nullable=false)
	private String apellido;
	
	@NotBlank(message = "no debe estar en blanco")
	@Size(max=100, message = "el tamaño debe estar entre 1 y 100 caracteres")
	@Email
	@Column(unique = true, nullable=false)
	private String email;
	
	@NotEmpty(message = "no debe estar vacio")
	@Size(max=100)
	@Column(nullable = false)
	private String contrasena;
	
	@NotNull(message = "no debe estar vacio")
	@Column(nullable = false)
	private Integer estado;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	//Foreign Keys

	@JsonIgnoreProperties({"usuario", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	private Rol rol;

	@JsonIgnoreProperties({"usuario", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	private Departamento departamento;
	
	//

	@JsonIgnoreProperties({"usuario", "hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Comision> comision;

	
	//Constructor

	@JsonCreator
	public Usuario() {
		this.comision = new ArrayList<>();

	}

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

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	public Integer getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Integer identificacion) {
		this.identificacion = identificacion;
	}

	public List<Comision> getComision() {
		return comision;
	}

	public void setComision(List<Comision> comision) {
		this.comision = comision;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}



	private static final long serialVersionUID = 1L;

}

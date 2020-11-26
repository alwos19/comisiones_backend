package com.udea.comisiones.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udea.comisiones.backend.apirest.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
	
	public Usuario findByIdentificacion(Integer identificacion);
	
	public List<Usuario> findByApellidoIgnoreCase(String apellido);
	
	public Usuario findByEmail(String email);
	
	public Usuario findByUsername(String username);

	//public Page<Usuario> findAll(Pageable pageable);


}

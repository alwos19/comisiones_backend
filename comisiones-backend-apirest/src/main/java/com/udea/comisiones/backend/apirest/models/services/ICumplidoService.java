package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.udea.comisiones.backend.apirest.models.entity.Cumplido;

public interface ICumplidoService {

	public List<Cumplido> findAll();
	
	public Page<Cumplido> findAll(Pageable pageable);
	
	public Cumplido findById(Long id);
	
	public Cumplido save(Cumplido cumplido);
	
	public void delete(Long id);
}

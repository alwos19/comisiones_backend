package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udea.comisiones.backend.apirest.models.dao.IDepartamentoDao;
import com.udea.comisiones.backend.apirest.models.entity.Departamento;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService{

	@Autowired
	private IDepartamentoDao departamentoDao;
	
	//
	
	@Override
	@Transactional(readOnly = true)
	public List<Departamento> findAll() {
		return (List<Departamento>) departamentoDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Departamento> findAll(Pageable pageable) {
		return departamentoDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Departamento findById(Long id) {
		return departamentoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Departamento save(Departamento departamento) {
		return departamentoDao.save(departamento);
	}

	@Override
	public Departamento findByNombreIgnoreCase(String nombre) {
		return departamentoDao.findByNombreIgnoreCase(nombre);
	}


}

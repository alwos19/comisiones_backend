package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udea.comisiones.backend.apirest.models.dao.IComisionDao;
import com.udea.comisiones.backend.apirest.models.entity.Comision;

@Service
public class ComisionServiceImpl implements IComisionService {
	
	@Autowired
	private IComisionDao comisionDao;
	
	
	
	//

	@Override
	@Transactional(readOnly = true)
	public List<Comision> findAll() {
		
		return (List<Comision>) comisionDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Comision findById(Long id) {
		return comisionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Comision save(Comision comision) {
		return comisionDao.save(comision);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		comisionDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Comision> findByLugarContainingIgnoreCase(String lugar) {
		return comisionDao.findByLugarContainingIgnoreCase(lugar);
	}


}

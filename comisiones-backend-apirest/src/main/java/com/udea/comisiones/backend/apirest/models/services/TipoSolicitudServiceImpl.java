package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udea.comisiones.backend.apirest.models.dao.ITipoSolicitudDao;
import com.udea.comisiones.backend.apirest.models.entity.TipoSolicitud;

@Service
public class TipoSolicitudServiceImpl implements ITipoSolicitudService{
	
	@Autowired
	private ITipoSolicitudDao tipoSolicitudDao;
	
	//

	@Override
	@Transactional(readOnly = true)
	public List<TipoSolicitud> findAll() {
		
		return (List<TipoSolicitud>) tipoSolicitudDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<TipoSolicitud> findAll(Pageable pageable) {
		return tipoSolicitudDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public TipoSolicitud findById(Long id) {
		return tipoSolicitudDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public TipoSolicitud save(TipoSolicitud tipoSolicitud) {
		return tipoSolicitudDao.save(tipoSolicitud);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		tipoSolicitudDao.deleteById(id);
		
	}

	@Override
	public TipoSolicitud findByNombreIgnoreCase(String nombre) {
		return tipoSolicitudDao.findByNombreIgnoreCase(nombre);
	}




}

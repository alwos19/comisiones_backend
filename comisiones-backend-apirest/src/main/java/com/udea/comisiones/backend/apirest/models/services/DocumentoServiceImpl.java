package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udea.comisiones.backend.apirest.models.dao.IDocumentoDao;
import com.udea.comisiones.backend.apirest.models.entity.Documento;

@Service
public class DocumentoServiceImpl implements IDocumentoService{

	@Autowired
	private IDocumentoDao documentoDao;
	
	//
	
	@Override
	@Transactional(readOnly = true)
	public List<Documento> findAll() {
		
		return (List<Documento>) documentoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Documento findById(Long id) {
		return documentoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Documento save(Documento documento) {
		return documentoDao.save(documento);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		documentoDao.deleteById(id);
		
	}
}

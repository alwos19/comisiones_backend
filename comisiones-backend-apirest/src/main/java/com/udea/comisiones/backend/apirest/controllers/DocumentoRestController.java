package com.udea.comisiones.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.udea.comisiones.backend.apirest.models.entity.Documento;
import com.udea.comisiones.backend.apirest.models.services.IDocumentoService;

@CrossOrigin (origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class DocumentoRestController {
	
	@Autowired
	private IDocumentoService documentoService;
	
	//
	
	@GetMapping("/documentos")
	public List<Documento> index(){
		return documentoService.findAll();
	}
	
	@GetMapping("/documentos/{id}")
	public Documento show(@PathVariable Long id) {
		return documentoService.findById(id);
	}
	
	@PostMapping("/documentos")
	@ResponseStatus(HttpStatus.CREATED)
	public Documento create(@RequestBody Documento documento) {
		return documentoService.save(documento);
	}
	
	@PutMapping("/documentos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Documento update(@RequestBody Documento documento, @PathVariable Long id) {
		Documento documentoActual = documentoService.findById(id);
		
		documentoActual.setNombre(documento.getNombre());
		documentoActual.setEsCumplido(documento.getEsCumplido());
		documentoActual.setEsAnexo(documento.getEsAnexo());

		
		return documentoService.save(documentoActual);
	}
	
	@DeleteMapping("/documentos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		documentoService.delete(id);
	}
	
	@GetMapping("/documentos/filtrar-documentos/{nombre}")
	@ResponseStatus(HttpStatus.OK)
	public List<Documento> filtrarDocumentos(@PathVariable String nombre) {
		return documentoService.findByNombreIgnoreCase(nombre);
	} 

}

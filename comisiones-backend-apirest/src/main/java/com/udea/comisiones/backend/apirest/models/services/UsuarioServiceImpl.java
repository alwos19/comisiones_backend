package com.udea.comisiones.backend.apirest.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.udea.comisiones.backend.apirest.auth.UserAuthenticationToken;
import com.udea.comisiones.backend.apirest.models.dao.IUsuarioDao;
import com.udea.comisiones.backend.apirest.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	
	private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	// Autenticación Usuarios
	 
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioDao.findByUsername(username);
		List<GrantedAuthority> grantedAuthorities =  new ArrayList<GrantedAuthority>();
		UserDetails userDetails = null;
		
		if (usuario == null){
			logger.error("Error en el loggin: No existe el usuario '" + username + "' en el sistema!");
			throw new UsernameNotFoundException("Error en el loggin: No existe el usuario '" + username + "' en el sistema!");
		}else {
            
            grantedAuthorities.add( new SimpleGrantedAuthority( usuario.getRol().getNombre() ) );
            
            userDetails = new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, grantedAuthorities);
           
            Authentication authentication = new UserAuthenticationToken(usuario, userDetails, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		return userDetails;
		//username, enable, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities:
		//return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, grantedAuthorities);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		
		return (List<Usuario>) usuarioDao.findAll();
	}
	

	@Override
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioDao.findAll(pageable);
	}



	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuario findByIdentificacion(Integer identificacion) {
		return usuarioDao.findByIdentificacion(identificacion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findByApellidoIgnoreCase(String apellido) {
		return usuarioDao.findByApellidoIgnoreCase(apellido);
	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}



}

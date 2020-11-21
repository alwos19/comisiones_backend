package com.udea.comisiones.backend.apirest.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@SuppressWarnings("deprecation")
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	//dar acceso a los clientes a los recursos de la aplicación, si el token es válido

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() 
		.antMatchers(HttpMethod.GET, "/api/usuarios").permitAll() //get_todos

		.antMatchers(HttpMethod.POST,"/api/usuarios").hasAnyRole( "ADMIN")
		.antMatchers(HttpMethod.PUT,"/api/usuarios/{id}").hasAnyRole( "ADMIN")
		.antMatchers(HttpMethod.DELETE,"/api/usuarios/{id}").hasAnyRole( "ADMIN")
		.antMatchers(HttpMethod.GET,"/api/usuarios/filtrar-identificacion-usuarios/**").hasAnyRole( "ADMIN")
		.antMatchers(HttpMethod.GET,"/api/usuarios/filtrar-apellido-usuarios/**").hasAnyRole( "ADMIN")

		.antMatchers(HttpMethod.POST, "/api/comisiones").hasAnyRole("PROFESOR", "ESTUDIANTE") 
		.antMatchers(HttpMethod.PUT, "/api/comisiones/{id}").hasAnyRole("PROFESOR", "ESTUDIANTE") 
		.antMatchers(HttpMethod.DELETE, "/api/comisiones/{id}").hasAnyRole("PROFESOR", "ESTUDIANTE") 
	
		.antMatchers("/api/documentos").hasRole("ADMIN")  
		.antMatchers("/api/documentos/**").hasAnyRole("PROFESOR", "ESTUDIANTE") //get_id, post, get, delete
		
		.antMatchers("/api/cumplidos").hasRole("ADMIN") 
		.antMatchers("/api/cumplidos/**").hasAnyRole("PROFESOR", "ESTUDIANTE") //get_id, post, get, delete
		
		.antMatchers("/api/deparamentos", "/api/deparamentos/**").hasRole("ADMIN") 

		.antMatchers("/api/roles", "/api/roles/**").hasRole("ADMIN")
		
		.antMatchers("/api/estados", "/api/estados/**").hasRole("ADMIN") 
		
		.antMatchers("/api/tipos-solicitud", "/api/tipos-solicitud/**").hasRole("ADMIN") 
		
		.antMatchers("/api/facultades", "/api/facultades/**").hasRole("ADMIN") 
		
		.anyRequest().authenticated();
		
	}
	
}

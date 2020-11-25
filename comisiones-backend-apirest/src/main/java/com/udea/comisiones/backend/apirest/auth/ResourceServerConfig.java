package com.udea.comisiones.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
@SuppressWarnings("deprecation")
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	//dar acceso a los clientes a los recursos de la aplicación, si el token es válido

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() 
		.antMatchers(HttpMethod.GET, "/api/usuarios").permitAll() //get_todos

		/*.antMatchers(HttpMethod.POST,"/api/usuarios").hasAnyRole( "ADMIN")
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
		
		.antMatchers("/api/facultades", "/api/facultades/**").hasRole("ADMIN") */
		
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
		
	}
	
	@Bean
	 CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("https://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>( new CorsFilter( corsConfigurationSource() ));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		return bean;
	}
	
	
}

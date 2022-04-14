package com.tvSoftware.newton.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tvSoftware.newton.services.DBServices;

/*
 * Classe de configuração do banco de desenvolvimento
 * */
@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBServices dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;
	
	@Bean
	public boolean instanciaDB() {
		if(value.equals("create")) {
			this.dbService.instanciaDB();
		}
		return false;
	}
}
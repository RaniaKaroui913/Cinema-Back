package org.sid.cinema;

import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Salle;
import org.sid.cinema.service.Cinemainitint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {

	@Autowired
	private Cinemainitint cinemainitint;
	@Autowired
	private RepositoryRestConfiguration repositoryconfig;
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		repositoryconfig.exposeIdsFor(Film.class,Salle.class);
		cinemainitint.initvilles();
		cinemainitint.initcinemas();
		cinemainitint.initsalles();
		cinemainitint.initplaces();
		cinemainitint.initseances();
		cinemainitint.initcategories();
		
		cinemainitint.iniFilms();
		cinemainitint.initcprojections();
		cinemainitint.inittickets();
	}

}

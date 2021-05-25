package org.sid.cinema.service;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
public interface Cinemainitint  {
	public void initcinemas();
	public void initcategories();
	public void iniFilms();
	public void initplaces();
	public void initcprojections();
	public void initsalles();
	public void initseances();
	public void inittickets();
	public void initvilles();
	

}

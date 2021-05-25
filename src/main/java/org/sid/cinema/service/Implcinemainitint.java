package org.sid.cinema.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.sid.cinema.dao.*;
import org.sid.cinema.dao.Sallerep;
import org.sid.cinema.dao.Villerep;
import org.sid.cinema.entities.*;
import org.sid.cinema.entities.Place;
import org.sid.cinema.entities.Salle;
import org.sid.cinema.entities.Seance;
import org.sid.cinema.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class Implcinemainitint implements Cinemainitint{
	
		@Autowired
		private Villerep villerep;
		@Autowired
		private Cinemarep cinemarep;
		@Autowired
		private Sallerep sallerep;
		@Autowired
		private Categorierep categorierep;
		@Autowired
		private Filmrep filmrep;
		@Autowired
		private Placerep placerep;
		@Autowired
		private Projectionrep projectionrep;
		@Autowired
		private Seancerep seancerep;
		@Autowired
		private Ticketrep ticketrep;

	@Override
	public void initcinemas() {
		// TODO Auto-generated method stub
		villerep.findAll().forEach(v->{
			Stream.of("cine1","cine2","cine3","cine4","cine5")
			.forEach(cinename->{
				Cinema cinema=new Cinema();
				cinema.setName(cinename);
				cinema.setNombresalle(3+(int)(Math.random()*7));
				cinema.setVille(v);
				cinemarep.save(cinema);
			});
		});
		
	}

	@Override
	public void initcategories() {
		// TODO Auto-generated method stub
		Stream.of("Histoire","Actions","Fiction","Drama").forEach(cat->{
			Categorie categorie=new Categorie();
			categorie.setName(cat);
			categorierep.save(categorie);
			
		});
		
	}

	@Override
	public void iniFilms() {
		// TODO Auto-generated method stub
		double duree[]=new double[] {1,1.5,2,2.5,3};
		java.util.List<Categorie> categories=categorierep.findAll();
		Stream.of("film1","film2","film3","film4","film5").forEach(f->{
			Film film=new Film();
			film.setTitre(f);
			film.setDuree(duree[new Random().nextInt(duree.length)]);
			film.setPhoto(f.replaceAll(" ", " ")+".jpg");
			film.setCategorie(categories.get(new Random().nextInt(categories.size())));
			filmrep.save(film);
		});
		
	}

	@Override
	public void initplaces() {
		// TODO Auto-generated method stub
		sallerep.findAll().forEach(salle->{
			for(int i=0;i<salle.getNombreplace();i++) {
				Place place=new Place();
				place.setNumero(i+1);
				place.setSalle(salle);
				placerep.save(place	);
			}
		});
	}

	@Override
	public void initcprojections() {
		// TODO Auto-generated method stub
		double[] prix=new double[] {30,50,60,70,80,90,100};
		List<Film> films=filmrep.findAll();
		villerep.findAll().forEach(ville->{
			ville.getCinemas().forEach(cinema->{
				cinema.getSalles().forEach(salle->{
					int index=new Random().nextInt(films.size());
					Film film=films.get(index);
						seancerep.findAll().forEach(seance->{
							Projection projection=new Projection();
							projection.setDateprojection(new Date());
							projection.setFilm(film);
							projection.setPrix(prix[new Random().nextInt(prix.length)]);
							projection.setSalle(salle);
							projection.setSeance(seance);
							projectionrep.save(projection);
						});
					
				});
			});
		});
		
	}

	@Override
	public void initsalles() {
		// TODO Auto-generated method stub
		cinemarep.findAll().forEach(cinema->{
			for(int i=0;i<cinema.getNombresalle();i++) {
				Salle salle =new Salle();
				salle.setName("salle "+(i+1));
				salle.setCinema(cinema);
				salle.setNombreplace(15+(int)(Math.random()*20));
				sallerep.save(salle);
			}
		});
		
	}

	@Override
	public void initseances() {
		// TODO Auto-generated method stub
		DateFormat dateformat= new SimpleDateFormat("HH:mm");
		Stream.of("12:00","15:00","17:00","20:00").forEach(s->{
			Seance seance =new Seance();
			try {
				seance.setHeuredebut(dateformat.parse(s));
				seancerep.save(seance);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		
	}

	@Override
	public void inittickets() {
		// TODO Auto-generated method stub
		projectionrep.findAll().forEach(p->{
			p.getSalle().getPlaces().forEach(place->{
				Ticket ticket =new Ticket();
				ticket.setPlace(place);
				ticket.setPrix(p.getPrix());
				ticket.setProjection(p);
				ticket.setReserve(false);
				ticketrep.save(ticket);
			});
			
			
		});
		
	}

	@Override
	public void initvilles() {
		Stream.of("casa","rabat","tanger").forEach(nameville->{
			Ville ville=new Ville();
			ville.setName(nameville);
			villerep.save(ville);			
		});

		
	}

}

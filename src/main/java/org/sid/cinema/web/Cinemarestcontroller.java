package org.sid.cinema.web;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.sid.cinema.dao.Filmrep;
import org.sid.cinema.dao.Ticketrep;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;


@RestController
@CrossOrigin("*")
public class Cinemarestcontroller {
	@Autowired
	private Filmrep filmrep;
	@Autowired
	private Ticketrep ticketrep;
	/*
	@GetMapping("/listfilms")
	public List<Film> films(){
		return filmrep.findAll();
		
	}*/
	
	@GetMapping(path="/imagefilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
	public byte [] image (@PathVariable (name="id")Long id) throws Exception{
		Film f=filmrep.findById(id).get();
		String imagename=f.getPhoto();
		File file=new File(System.getProperty("user.home")+"\\cinema\\images\\"+imagename);
		java.nio.file.Path path=Paths.get(file.toURI());	
		return Files.readAllBytes(path);
	}
	
	@PostMapping("/payerticket")
	@Transactional
	public List<Ticket> payerticket(@RequestBody Ticketfrom ticketfrom){
		List<Ticket> listtickets=new ArrayList<>();
		ticketfrom.getTickets().forEach(idticket->{
			Ticket ticket=ticketrep.findById(idticket).get();
			ticket.setNomclient(ticketfrom.getNomclient());
			ticket.setReserve(true);
			ticketrep.save(ticket);
			listtickets.add(ticket);
		});
		return listtickets;
	}
	
	@Data
	class Ticketfrom{
		private String nomclient;
		private List<Long> tickets=new ArrayList<>();
	}

}

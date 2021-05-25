package org.sid.cinema.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="ticketproj",types=Ticket.class)

public interface Ticketproj {
	public Long getid();
	public String getnomclient();
	public double getprix();
	public int getcodepayement();
	public boolean getreserve();
	public Place getplace();

}

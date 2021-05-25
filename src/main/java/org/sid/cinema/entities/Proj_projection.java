package org.sid.cinema.entities;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p1",types = {org.sid.cinema.entities.Projection.class})
public interface Proj_projection {
	public Long getid();
	public double getprix();
	public Date getdateprojection();
	public Salle getsalle();
	public Film getfilm();
	public Seance getseance();
	public Collection<Ticket> gettickets();

}

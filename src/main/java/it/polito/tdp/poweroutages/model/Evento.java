package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Evento {
	// meglio local date time per avere anche il tempo
	//duration.getduration per calcolare la differenza di tempo
	private int id;
	private int customerAffected;
	private int nerc_id;
	private LocalDate date_event_began;
	private LocalDate date_event_finished;
	private int timeMinute;
	
	public int getId() {
		return id;
	}
	public Evento(int id, int customerAffected, int nerc_id, LocalDate date_event_began,
			LocalDate date_event_finished, int timeMinute) {
		this.id = id;
		this.customerAffected = customerAffected;
		this.nerc_id = nerc_id;
		this.date_event_began = date_event_began;
		this.date_event_finished = date_event_finished;
		this.timeMinute = timeMinute;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerAffected() {
		return customerAffected;
	}
	public void setCustomerAffected(int customerAffected) {
		this.customerAffected = customerAffected;
	}
	public int getNerc_id() {
		return nerc_id;
	}
	public void setNerc_id(int nerc_id) {
		this.nerc_id = nerc_id;
	}
	public LocalDate getDate_event_began() {
		return date_event_began;
	}
	public void setDate_event_began(LocalDate date_event_began) {
		this.date_event_began = date_event_began;
	}
	public LocalDate getDate_event_finished() {
		return date_event_finished;
	}
	public void setDate_event_finished(LocalDate date_event_finished) {
		this.date_event_finished = date_event_finished;
	}
	public int getTimeMinute() {
		return timeMinute;
	}
	public void setTimeMinute(int timeMinute) {
		this.timeMinute = timeMinute;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerAffected;
		result = prime * result + ((date_event_began == null) ? 0 : date_event_began.hashCode());
		result = prime * result + ((date_event_finished == null) ? 0 : date_event_finished.hashCode());
		result = prime * result + timeMinute;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (customerAffected != other.customerAffected)
			return false;
		if (date_event_began == null) {
			if (other.date_event_began != null)
				return false;
		} else if (!date_event_began.equals(other.date_event_began))
			return false;
		if (date_event_finished == null) {
			if (other.date_event_finished != null)
				return false;
		} else if (!date_event_finished.equals(other.date_event_finished))
			return false;
		if (timeMinute != other.timeMinute)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Evento [id=" + id + ", customerAffected=" + customerAffected + ", nerc_id=" + nerc_id
				+ ", date_event_began=" + date_event_began + ", date_event_finished=" + date_event_finished + "]";
	}
	
	
}

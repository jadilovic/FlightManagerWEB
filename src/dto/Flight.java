package dto;

import java.util.ArrayList;

public class Flight {
	//unique ID
	private Integer id;
	
	//flight name
	private String flight_name;
	
	//which airline owns this flight
	private Airline airline;
	
	//from which airport flight takes of
	private Airport airport;
	
	//all seats in this flight
	private ArrayList<Seat> seats;
	
	//city where it takes off from
	private String origin;
	
	//city where the flight is going
	private String destination;
	
	public Flight() {
		
	}
	
	public Flight(Integer id, String flight_name, String origin, String destination, String airport, String airline){
		this.id = id;
		this.setFlight_name(flight_name);
		this.origin = origin;
		this.destination = destination;
		this.airport = new Airport(airport, origin);
		this.airline = new Airline(airline);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public ArrayList<Seat> getSeats() {
		return seats;
	}

	public void setSeats(ArrayList<Seat> seats) {
		this.seats = seats;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public String getFlight_name() {
		return flight_name;
	}

	public void setFlight_name(String flight_name) {
		this.flight_name = flight_name;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", flight_name=" + flight_name + ", airline=" + airline + ""
				+ ", seats=" + seats + ", origin=" + origin
				+ ", destination=" + destination + ", airport=" + airport;
	}
	
	

}

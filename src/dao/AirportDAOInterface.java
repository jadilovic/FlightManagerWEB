package dao;

import java.sql.SQLException;
import java.util.List;

import dto.Airport;

public interface AirportDAOInterface {

	// method to get all airport
	public List<Airport> getAllAirports() throws SQLException;

	// method to get a specific airport
	public Airport getAirport(String airportName) throws SQLException;

	// method to add a airport
	// public void addAirport() throws SQLException;

	// method to print a specific airport
	public void printAirport(Airport airport);

	// printing all airports
	void printAllAirports(List<Airport> airports) throws SQLException;

	// method to add a airport through website
	void addAirport(String airportName, String airportCity) throws SQLException;
}

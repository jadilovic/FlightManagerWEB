/*
 * Flight Manager App
 * By: Jasmin Adilovic
 * E-mail: adilovic79yahoo.com
 * Date: OCT 2019
 * ***Flight manager application***

1. An airport must have a name consisting of exactly three alphabetical
 characters. No two airports can have the same name.
2. An airline has a name that must have less than 6 alphabetic
 characters. No two airlines can have the same name.
3. Each flight consists of seats organized in rows. Each row is labeled
 with ("A", "B", "C", "D", "E", "F"). Each row has number of seats 
in row.
4. Every flight has an airport,airline origin destination and seats.
 
 User can:
 	-Create airport
 	-Create airline
 	-Create flight
 	-Book a seat on a flight
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.*;

/**
 * IMPLEMENTATION OF ALL DAO INTERFACES
 */

public class SystemManagerDAO implements AirlineDAOInterface, AirportDAOInterface, 
												FlightDAOInterface, SeatDAOInterface {

	// connect to the database
	private Connection connection;
	
	// message from the database
	private String message;

	public SystemManagerDAO(Connection conn) {
		this.connection = conn;
	}
	
	// IMPLEMENTATION OF AIRLINE INTERFACE
	
	@Override
	public List<Airline> getAllAirlines() throws SQLException {
		// create list of all airlines
		List<Airline> listOfAirlines = new ArrayList<>();
		// create an SELECT SQL query
		String query = "SELECT * FROM airline";
		// create a new ResultSet
		ResultSet rs = null;
		try (// java.sql.Statement
				Statement statement = connection.createStatement();) {
			// execute the query
			rs = statement.executeQuery(query);
			// add airlines to the list
			while (rs.next()) {
				listOfAirlines.add(new Airline(rs.getString("name")));
			}
		}
		return listOfAirlines;
	}

	@Override
	public Airline getAirline(String airlineName) throws SQLException {
		// airline object
		Airline airline = null;
		// create an SELECT SQL query
		String query = "SELECT * FROM airline WHERE name = ?";
		// create a new ResultSet
		ResultSet rs = null;
		try (// java.sql.Statement
			PreparedStatement pstatement = connection.prepareStatement(query);) {
			// fill in the placeholders /parameters
			pstatement.setString(1, airlineName);
			// execute the query
			rs = pstatement.executeQuery();
			// set the cursor
			if (rs.next()) {
				// populate airline
				airline = new Airline(rs.getString("name"));
				// close the ResultSet
				rs.close();
			}
		}
		return airline;
	}

	@Override
	public void addAirline(String airlineName) throws SQLException {
		// create an SELECT SQL query
		String query = "INSERT INTO airline (name) VALUES (?)";
		// creating new airline
		//System.out.print("Enter airline name: ");
		//String name = enterString();
		// checking airline name and if it exists
		if(validAirlineName(airlineName) && !airlineExists(airlineName)){
			try (// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {
				// fill in the placeholders/parameters
				statement.setString(1, airlineName.toUpperCase());
				// execute the query
				statement.executeUpdate();
				message = "Airline " + airlineName.toUpperCase() + " added to the database.";
			}
		}
	}

	// checking if airline exists in the database already
	private boolean airlineExists(String name) throws SQLException {
		if(getAirline(name) != null){
			message = "Entered Airline name '" + name + "' already exists. Please try again.";
			return true;
		}
		return false;
	}

	// checking if the given airline name is less than 5 characters and all alphabetic characters
	private boolean validAirlineName(String name) throws SQLException {
		if(name.length() > 5){
			message = "Length of the Airline name '" + name + "' is greater than 5 characters. Please try again.";
			return false;
		}
		else if(!onlyAlphabets(name)){
			message = "Entered Airline name '" + name + "' does not contain all alphabets. Please try again.";
			return false;
		} else
		return true;
	}

	// Checking if given name is all alphabets
	private static boolean onlyAlphabets(String name) {
        return ((name != null) 
                && (!name.equals("")) 
                && (name.matches("^[a-zA-Z]*$")));
	}

	@Override
	public void printAirline(Airline airline) {
		if (airline != null) {
			System.out.println("Airline: " + airline.getName());
		} else {
			System.out.println("No airline to print.");
		}
	}
	
	@Override
	public void printAllAirlines(List<Airline> airlines) throws SQLException {
		for(Airline airline: airlines)
			printAirline(airline);
	}
	
	
	// AIRPORT INTERFACE IMPLEMENTATION

	@Override
	public List<Airport> getAllAirports() throws SQLException {
		// create list of all Airport objects
		List<Airport> listOfAirports = new ArrayList<>();
		// create an SELECT SQL query
		String query = "SELECT * FROM airport";
		// create a new ResultSet
		ResultSet rs = null;
		try (// java.sql.Statement
			Statement statement = connection.createStatement();) {
			// execute the query
			rs = statement.executeQuery(query);
			// add airports to the arrayList
			while (rs.next()) {
				listOfAirports.add(new Airport(rs.getString("name"), rs.getString("city")));
			}
		}
		return listOfAirports;
	}

	@Override
	public Airport getAirport(String airportName) throws SQLException {
		// new airport object
		Airport airport = null;
		// create an SELECT SQL query
		String query = "SELECT * FROM airport WHERE name = ?";
		// create a new ResultSet
		ResultSet rs = null;
		try (// java.sql.Statement
			PreparedStatement pstatement = connection.prepareStatement(query);) {
			// fill in the placeholders/parameters
			pstatement.setString(1, airportName);
			// execute the query
			rs = pstatement.executeQuery();
			// set the cursor
			if (rs.next()) {
				// populate airport
				airport = new Airport(rs.getString("name"), rs.getString("city"));
				// close the ResultSet
				rs.close();
			}
		}
		return airport;
	}

	@Override
	public void addAirport(String airportName, String airportCity) throws SQLException {
		System.out.println("Add airport");
		// create an SELECT SQL query
		String query = "INSERT INTO airport (name, city) VALUES (?, ?)";
		
		// giving name for the new airport
		//System.out.print("Enter airport name: ");
		//String name = enterString();
		// giving city for the new airport
		//System.out.print("Enter city: ");
		//String city = enterString();
		
		// checking validity of the name given and if it already exists
		if(validAirportName(airportName) && !airportExists(airportName)){
			try (// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {
				// fill in the placeholders/parameters
				statement.setString(1, airportName.toUpperCase());
				statement.setString(2, airportCity);
				// execute the query
				statement.executeUpdate();
				//System.out.println("Airport " + name + " in the city " + city + " added to the database.");
				message = "Airport " + airportName.toUpperCase() + " in the city " + airportCity + " added to the database.";
			}
		}
	}

	// method to check length of the name and if it contains only alphabets
	private boolean validAirportName(String name) throws SQLException {
		if(name.length() != 3){
			message = "Length of the Airport name '" + name + "' must be exactly 3 characters. Please try again.";
			return false;
		}
		else if(!onlyAlphabets(name)){
			message = "Entered Airport name '" + name + "' does not contain all alphabets. Please try again.";
			return false;
		} else 
		return true;
	}

	// method checking if airport already exists
	private boolean airportExists(String name) throws SQLException {
			if(getAirport(name) != null){
				message = "Entered Airport name '" + name + "' already exists. Please try again.";
				return true;
			}
		return false;
	}

	@Override
	public void printAirport(Airport airport) {
		if (airport != null) {
			System.out.println("Airport name: " + airport.getName() + ", city: " + airport.getCity());
		} else {
			System.out.println("No airport to print.");
		}
	}
	
	@Override
	public void printAllAirports(List<Airport> airports) throws SQLException {
		for(Airport airport: airports)
			printAirport(airport);
	}
	
	
	// FLIGHT INTERFACE IMPLEMENTATION

	@Override
	public List<Flight> getAllFlights() throws SQLException {
		// create list of all flights
		List<Flight> listOfFlights = new ArrayList<>();
		// create an SELECT SQL query
		String query = "SELECT * FROM flight";
		// create a new ResultSet
		ResultSet rs = null;
		try (// java.sql.Statement
			Statement statement = connection.createStatement();) {
			// execute the query
			rs = statement.executeQuery(query);
			// add airports to the arrayList
			while (rs.next()) {
				listOfFlights.add(new Flight(rs.getInt("id"), rs.getString("flight_name"), rs.getString("origin"), 
						rs.getString("destination"), rs.getString("airport"), rs.getString("airline")));
			}
		}
		return listOfFlights;
	}

	@Override
	public Flight getFlight(Integer id) throws SQLException {
		// new flight
		Flight flight = new Flight();
		// create an SELECT SQL query
		String query = "SELECT * FROM flight WHERE id = ?";
		// create a new ResultSet
		ResultSet rs = null;
		try (// java.sql.Statement
			PreparedStatement pstatement = connection.prepareStatement(query);) {
			// fill in the placeholders/parameters
			pstatement.setInt(1, id);
			// execute the query
			rs = pstatement.executeQuery();
			// set the cursor
			if (rs.next()) {
				// populate flight
				flight = new Flight(rs.getInt("id"), rs.getString("flight_name"), rs.getString("origin"), 
						rs.getString("destination"), rs.getString("airport"), rs.getString("airline"));
				// close the ResultSet
				rs.close();
			}
		}
		return flight;
	}

	@Override
	public void addFlight(String flightId2, String flightName, String origin, String destination, 
			String airport, String airline, String seatsPerRow) throws SQLException {
		// create an SELECT SQL query
		String query = "INSERT INTO flight (id, flight_name, origin, destination, airport, airline) "
						+ "VALUES (?, ?, ?, ?, ?, ?)";
		// giving unique id to the new flight
		//System.out.print("Enter unique ID of the flight: ");
		int flightId = Integer.parseInt(flightId2);
		// checking if flight ID already exists
		if(!flightIdExists(flightId)){
			//System.out.print("Enter flight name: ");
			//String flightName = enterString();
			// checking if flight name already exists
			if(!flightNameExists(flightName.toUpperCase())){
				//System.out.print("Enter origin: ");
				//String origin = enterString();
				// checking if origin location exists
				if(locationExists(origin)){
					//System.out.print("Enter destination: ");
					//String destination = enterString();
					// checking if destination location exists
					if(locationExists(destination)){
						//System.out.print("Enter airport: ");
						//String airport = enterString();
						// checking if airport exists and that origin valid for the airport
						Airport enteredAirport = getAirport(airport.toUpperCase());
						if(enteredAirport != null && originOfAirport(enteredAirport, origin)){
							//System.out.print("Enter airline: ");
							//String airline = enterString();
							// checking if airline exists
							if(getAirline(airline.toUpperCase()) != null){
								//System.out.println("Please enter number of seats per row");
								int numSeatsInRow = Integer.parseInt(seatsPerRow);
			// creating seats for the new created flight
			createSeats(numSeatsInRow, flightId);
			try (// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {
					// fill in the placeholders/parameters
					statement.setInt(1, flightId);
					statement.setString(2, flightName.toUpperCase());
					statement.setString(3, origin);
					statement.setString(4, destination);
					statement.setString(5, airport);
					statement.setString(6, airline);
					// execute the query
					statement.executeUpdate();
		message = "Flight with id " + flightId + " and flight name " + flightName + " "
									+ " origin " + origin + " and destination " + destination + " "
									+ "added to the database.";
										}	
									} else {
										message = "Entered airline does not exist";
									}
								} else {
									message = "Entered airport does not exist";
								}
							} else {
								message = "Entered destination does not exist";
							}
						} else {
							message = "Entered origin does not exist";
						}
					} else {
						message = "Entered flight name already exist";
					}
				} else {
					message = "Entered flight ID already exist";
				}
			}	
	
	// checking if flight id exists
	private boolean flightIdExists(int flightId) throws SQLException {
		Flight flight = getFlight(flightId);
		if(flight.getId() == null)
			return false;
		else
		return true;
	}

	// checking if origin is same as the the city of the airport
	private boolean originOfAirport(Airport enteredAirport, String origin) {
		if(enteredAirport.getCity().equals(origin))
			return true;
		else{
			System.out.println("Entered airport and origin do not match");
			return false;
		}
	}

	// Checking if the flight name already exists
	private boolean flightNameExists(String flightName) throws SQLException {
		List<Flight> flights = getAllFlights();
		for(Flight flight: flights){
			if(flight.getFlight_name().equals(flightName))
				return true;
		}
		return false;
	}

	// checking if origins and destinations exist in the list of airports
	private boolean locationExists(String location) throws SQLException {
		List<Airport> airports = getAllAirports();
		for(Airport airport: airports){
			if(location.equals(airport.getCity()))
			return true;
		}
		return false;
	}

	// entering integer
	private Integer enterInteger() {
		Scanner input = new Scanner(System.in);
		Integer value = input.nextInt();
		// input.close();
		return value;
	}

	// entering string
	private String enterString() {
		// new Scanner
		java.util.Scanner input = new java.util.Scanner(System.in);
			String text = input.next();
		// close the scanner
		//input.close();
		return text;
	}
	
	// finding a flight with given origin and destination
	public void enterOriginAndDestination() throws SQLException {
		System.out.println("Please enter origin of the flight");
		String origin = enterString();
		System.out.println("Please enter destination of the flight");
		String destination = enterString();
		int count = 0;
		for(Flight flight: getAllFlights()){
			if(flight.getOrigin().equals(origin) && flight.getDestination().equals(destination)){
				System.out.println("Available flight:");
				printFlight(flight);
				count++;
			} else {
				continue;
			}
		}
		if(count == 0)
		System.out.println("There are ne flight available with entered origin and destination");
	}

	@Override
	public void printFlight(Flight flight) {
		if (flight != null) {
			System.out.println("Flight name: " + flight.getFlight_name() + ", flight id: " + flight.getId() + ""
					+ ", airline: " + flight.getAirline() + ", origin: " + flight.getOrigin() + ""
							+ " and destination " + flight.getDestination());
		} else {
			System.out.println("No airport to print.");
		}
	}
	
	@Override
	public void printAllFlights(List<Flight> flights) throws SQLException {
		for(Flight flight: flights)
			printFlight(flight);
	}

	// SEAT INTERFACE IMPLEMENTATION

	// list of seats for one flight
	@Override
	public List<Seat> getAllFlightSeats(Integer flightId) throws SQLException {
		// creating list of seats for one particular flight
		List<Seat> listOfSeats = new ArrayList<>();
		// create an SELECT SQL query
		String query = "SELECT * FROM seats WHERE flightID = ?";
		// create a new ResultSet
		ResultSet rs = null;
		try (// java.sql.Statement
			PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setInt(1, flightId);
			// execute the query
			rs = statement.executeQuery();
			// add seat to the list of seats
			while (rs.next()) {
				listOfSeats.add(new Seat(rs.getInt("seatID"), rs.getString("rowLetter"), rs.getInt("seat_number"), 
						rs.getBoolean("available"), rs.getInt("flightID")));
			}
		}
		return listOfSeats;
	}

	@Override
	public Seat getSeatById(Integer id) throws SQLException {
		// Seat object
		Seat seat = null;
		// create an SELECT SQL query
		String query = "SELECT * FROM seats WHERE seatID = ?";
		// create a new ResultSet
		ResultSet rs = null;
		try (// java.sql.Statement
			PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setInt(1, id);
			// execute the query
			rs = statement.executeQuery();
			// add airports to the arrayList
			while (rs.next()) {
				seat = new Seat(rs.getInt("seatID"), rs.getString("rowLetter"), rs.getInt("seat_number"), 
						rs.getBoolean("available"), rs.getInt("flightID"));
			}
		}
		return seat;
	}

	@Override
	public Seat getSeatByOther(String row, int seatNum, int flightId) throws SQLException {
		// Seat object
		Seat seat = null;
		// create an SELECT SQL query
		String query = "SELECT * FROM seats WHERE rowLetter = ? AND seat_number = ? AND flightID = ?";
		// create a new ResultSet
		ResultSet rs = null;
		try (// java.sql.Statement
			PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, row);
			statement.setInt(2, seatNum);
			statement.setInt(3, flightId);
			// execute the query
			rs = statement.executeQuery();
			// getting a seat
			while (rs.next()) {
				seat = new Seat(rs.getInt("seatID"), rs.getString("rowLetter"), rs.getInt("seat_number"), 
						rs.getBoolean("available"), rs.getInt("flightID"));
			}
		}
		return seat;
	}

	// creating seats for the new created flight
	@Override
	public void createSeats(int numSeatsInRow, Integer flightID) throws SQLException {
		// Insert query
		String query = "INSERT INTO seats (rowLetter, seat_number, available, flightID) "
				+ "VALUES (?, ?, ?, ?)";
		// creating seats based on number of seats in the row and adding it to the flight with given ID
		String [] rowsSQL = {"A", "B", "C", "D", "E", "F"};
		for(int i = 0; i < rowsSQL.length; i++){
			for(int j = 1; j <= numSeatsInRow; j++){
				try(PreparedStatement pstat = connection.prepareStatement(query);){
					pstat.setString(1, rowsSQL[i]);
					pstat.setInt(2, j);
					pstat.setBoolean(3, true);
					pstat.setInt(4, flightID);
					pstat.executeUpdate();
				}
			}
		}
	}

	@Override
	public void bookSeat() throws SQLException {
		System.out.println("On what flight you would like to book a seat? Please enter flight id.");
		// entering flight id to get a flight
		Integer flightId = enterInteger();
		// Checking if given flight exists
		if(flightIdExists(flightId)){
			List<Seat> seats = null;
			// getting all seats for the flight
			seats = getAllFlightSeats(flightId);
			System.out.println("     SeatID Row Seat# Available FlightID");
			// creating list of id's for each seat on the flight
			List<Integer> listOfSeatsIds = new ArrayList<>();
			// adding all id's to the list
			for(Seat seat: seats){
				listOfSeatsIds.add(seat.getSeatID());
				// showing all seats to the user on the screen
				printSeat(seat);
			}
			System.out.println("Please choose from available seats. Enter seat id.");
			// entering seat id
			int seatId = enterInteger();
			if(listOfSeatsIds.contains(seatId)){
				if(getSeatById(seatId).isAvailable() == true){
					// create an SELECT SQL query
					String query = "UPDATE seats SET available = ? WHERE seatID = ?";
					try (// java.sql.Statement
						PreparedStatement statement = connection.prepareStatement(query);) {
						// fill in the placeholders/parameters
						statement.setBoolean(1, false);
						statement.setInt(2, seatId);
						// execute the query
						statement.executeUpdate();
						// printing booked seat
						printSeat(getSeatById(seatId));
						System.out.println("was booked");
					}
				} else {
					System.out.println("Selected seat is not available");
				}
			} else {
				System.out.println("There is no seat with given seat id");
			}
		} else
			System.out.println("There is no flight with given flight id");
	}
	
	@Override
	public void printSeat(Seat seat) {
		System.out.println("Seat: " + seat.getSeatID() + ",    " + seat.getRow() + ""
				+ ",    " + seat.getSeatNumber() + ",    " + seat.isAvailable() + ","
						+ "    " + seat.getFlightID());
	}
	
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}

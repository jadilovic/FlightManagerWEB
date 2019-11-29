package dao;

import java.sql.SQLException;
import java.util.List;

import dto.Seat;

public interface SeatDAOInterface {

	// method to get all seats
	public List<Seat> getAllFlightSeats(Integer flightId) throws SQLException;

	// method to get a specific seat
	public Seat getSeatById(Integer seatId) throws SQLException;
	
	// method to get a specific seat
	public Seat getSeatByOther(String row, int seatNum, int flightId) throws SQLException;

	// method to create seats
	public void createSeats(int numSeatsInRow, Integer flightId) throws SQLException;
	
	// method to book a seat on specific flight
	public void bookSeat() throws SQLException;

	// method to print a specific seat
	public void printSeat(Seat seat);
}

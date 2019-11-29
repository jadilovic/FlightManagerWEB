package dto;

public class Seat {
	
	// seat id
	private int seatID;
	
	// row ("A", "B", "C", "D", "E", "F")
	private String row;
	
	// seat number ex. 1,5,16
	private Integer seatNumber;
	
	//is it booked, or it is available
	private boolean available;
	
	// flight id number
	private int flightID;
	
	public Seat() {
		
	}
	
	public Seat(int seatID, String rowLetter, int seatNumber, boolean available, int flightID){
		this.seatID = seatID;
		row = rowLetter;
		this.seatNumber = seatNumber;
		this.available = available;
		this.flightID = flightID;
	}
	
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public Integer getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getSeatID() {
		return seatID;
	}

	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}

	public int getFlightID() {
		return flightID;
	}

	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}

	@Override
	public String toString() {
		return "Seat: id=" + seatID + ", row=" + row + ", seatNumber=" + seatNumber + ""
				+ ", available=" + available + ", flightID=" + flightID;
	}
	
	
	

}

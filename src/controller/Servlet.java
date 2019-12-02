package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import dao.SystemManagerDAO;
import dto.Airport;
import javafx.scene.shape.Ellipse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private javax.sql.DataSource ds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			InitialContext initialContext = new InitialContext();
			Context env = (Context) initialContext.lookup("java:comp/env");
			ds = (javax.sql.DataSource) env.lookup("jdbc/flights");
			
		} catch (NamingException e) {
			throw new ServletException();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selectedOption = request.getParameter("option");
		String page;
		if(selectedOption == null || selectedOption.equals("home")) {
			page = "/home.jsp";
		} else if(selectedOption.equals("Create Airport")) {
			page = "/createairport.jsp";
		} else if (selectedOption.equals("Create Airline")){
			page = "/createairline.jsp";
		} else if(selectedOption.equals("Create Flight")) {
			page = "/createflight.jsp";
		} else if (selectedOption.equals("List Airports")){
			page = "/listairports.jsp";
		} else if (selectedOption.equals("List Airlines")){
			page = "/listairlines.jsp";
		} else if(selectedOption.equals("List Flights")) {
			page = "/listflights.jsp";
		} else if (selectedOption.equals("Find a Flight")){
			page = "/findflight.jsp";
		}else if(selectedOption.equals("Book a Seat")) {
			page = "/bookseat.jsp";
		} else if (selectedOption.equals("Exit Application")){
			page = "/exitapplication.jsp";
		}else {
			page = "/home.jsp";
			String message = "Wrong option selected. Please try again.";
			request.setAttribute("message", message);
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		String message = "";
		Connection connection;
		try {
			connection = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		// use connection	
		System.out.println("Connection Established");
		SystemManagerDAO manager = new SystemManagerDAO(connection);
		PrintWriter out = response.getWriter();
		
		if(option.equals("Create Airport")) {
			String airportName = request.getParameter("airportName");
			String airportCity = request.getParameter("airportCity");
			try {
				manager.addAirport(airportName, airportCity);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(option.equals("Create Airline")) {
			String airlineName = request.getParameter("airlineName");
			try {
				manager.addAirline(airlineName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(option.equals("Create Flight")) {
			String flightId = request.getParameter("flightId");
			String flightName = request.getParameter("flightName");
			String originCity = request.getParameter("originCity");
			String destinationCity = request.getParameter("destinationCity");
			String airportName = request.getParameter("airportName");
			String airlineName = request.getParameter("airlineName");
			String seatsPerRow = request.getParameter("seatsPerRow");
			try {
				manager.addFlight(flightId, flightName, originCity, destinationCity, airportName, airlineName, seatsPerRow);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// LIST OF AIRPORTS
		} else if(option.equals("List Airports")) {

		}
		message = manager.getMessage();
		request.setAttribute("message", message);
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}

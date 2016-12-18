package luchthavenbeheer.app;

import com.couchbase.lite.Document;

import java.time.LocalDateTime;

public class FlightDetails
{
	public Location FlyFrom;
	public Location FlyTo;
	public  int FlightNr;
	public LocalDateTime LeaveHour;
	public LocalDateTime ArrivalHour;
	public String Pilot;
	public int AirplaneNr;
	public int Price;
	public Airline airline;
	public FlightDetails()
	{

	}
    public FlightDetails(Location FlyFrom, Location FlyTo, int FlightNr, LocalDateTime LeaveHour, LocalDateTime ArrivalHour, String Pilot, int AirplaneNr, int Price, Airline airline)
    {
        this.FlyFrom = FlyFrom;
        this.FlyTo = FlyTo;
        this.FlightNr = FlightNr;
        this.LeaveHour = LeaveHour;
        this.ArrivalHour = ArrivalHour;
        this.Pilot = Pilot;
        this.AirplaneNr = AirplaneNr;
		this.Price = Price;
		this.airline = airline;
    }
	public FlightDetails CastDocumentToFlightDetails(Document document)
	{
		Location FlightFrom = Location.valueOf(String.valueOf(document.getProperty("FlightFrom")));
		Location FlightTo = Location.valueOf(String.valueOf(document.getProperty("FlightTo")));
		LocalDateTime LeaveHour = LocalDateTime.parse((String)document.getProperty("LeaveHour"));
		LocalDateTime ArrivalHour = LocalDateTime.parse((String)document.getProperty("ArrivalHour"));
		String Pilot = (String)document.getProperty("Pilot");
		int AirplaneNr = (int)document.getProperty("AirplaneNr");
		int flightNr = (int)document.getProperty("FlightNr");
		int Price = (int)document.getProperty("Price");
		Airline airline = Airline.valueOf(String.valueOf(document.getProperty("Airline")));


		FlightDetails flightDetails = new FlightDetails(FlightFrom,FlightTo, flightNr,
				LeaveHour, ArrivalHour, Pilot, AirplaneNr, Price, airline);
		return flightDetails;
	}
}
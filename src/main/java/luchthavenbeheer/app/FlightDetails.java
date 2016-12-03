package luchthavenbeheer.app;

import com.couchbase.lite.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class FlightDetails
{
	public enum Location{
		Brussel, Zurich, Zagreb, Athene, NewYork, London
	}
	public Location FlyFrom;
	public Location FlyTo;
	public  int FlightNr;
	public LocalDateTime LeaveHour;
	public LocalDateTime ArrivalHour;
	public String Pilot;
	public int AirplaneNr;
	public int Price;
	public FlightDetails()
	{

	}
    public FlightDetails(Location FlyFrom, Location FlyTo, int FlightNr, LocalDateTime LeaveHour, LocalDateTime ArrivalHour, String Pilot, int AirplaneNr, int Price)
    {
        this.FlyFrom = FlyFrom;
        this.FlyTo = FlyTo;
        this.FlightNr = FlightNr;
        this.LeaveHour = LeaveHour;
        this.ArrivalHour = ArrivalHour;
        this.Pilot = Pilot;
        this.AirplaneNr = AirplaneNr;
		this.Price = Price;
    }
	public FlightDetails CastDocumentToFlightDetails(Document document)
	{
		String loc = String.valueOf(document.getProperty("FlightFrom"));
		FlightDetails.Location FlightFrom = FlightDetails.Location.valueOf(loc);
		FlightDetails.Location FlightTo = FlightDetails.Location.valueOf(String.valueOf(document.getProperty("FlightTo")));
		LocalDateTime LeaveHour = LocalDateTime.parse((String)document.getProperty("LeaveHour"));
		LocalDateTime ArrivalHour = LocalDateTime.parse((String)document.getProperty("ArrivalHour"));
		String Pilot = (String)document.getProperty("Pilot");
		int AirplaneNr = (int)document.getProperty("AirplaneNr");
		int flightNr = (int)document.getProperty("FlightNr");
		int Price = (int)document.getProperty("Price");


		FlightDetails flightDetails = new FlightDetails(FlightFrom,FlightTo, flightNr,
				LeaveHour, ArrivalHour, Pilot, AirplaneNr, Price);
		return flightDetails;
	}
	public void ShowDetails()
	{
	}

}
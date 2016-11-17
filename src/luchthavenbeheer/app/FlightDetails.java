package luchthavenbeheer.app;

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
	private String Pilot;
	private int AirplaneNr;

    public FlightDetails(Location FlyFrom, Location FlyTo, int FlightNr, LocalDateTime LeaveHour, LocalDateTime ArrivalHour, String Pilot, int AirplaneNr)
    {
        this.FlyFrom = FlyFrom;
        this.FlyTo = FlyTo;
        this.FlightNr = FlightNr;
        this.LeaveHour = LeaveHour;
        this.ArrivalHour = ArrivalHour;
        this.Pilot = Pilot;
        this.AirplaneNr = AirplaneNr;
    }
	public void ShowDetails()
	{
	}

}
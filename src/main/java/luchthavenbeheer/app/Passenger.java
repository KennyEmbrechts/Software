package luchthavenbeheer.app;

import com.couchbase.lite.Document;

public class Passenger
{
	public Boolean HasLuggage;
	public Boolean IsCheckedIn;
	public String Name;
	public String FirstName;
	public int FlightNr;
    public int NrTickets;

	public Passenger(Boolean HasLuggage, Boolean IsCheckedIn, String Name, String FirstName, int FlightNr, int NrTickets)
	{
        this.HasLuggage = HasLuggage;
        this.IsCheckedIn = IsCheckedIn;
        this.Name = Name;
        this.FirstName = FirstName;
        this.FlightNr = FlightNr;
        this.NrTickets = NrTickets;
	}

	public Passenger() {}

    public Passenger CastDocumentToPassenger(Document document)
    {
        Name = (String)document.getProperty("Name");
        FirstName = (String)document.getProperty("FirstName");
        HasLuggage = Boolean.parseBoolean(String.valueOf(document.getProperty("HasLuggage")));
        IsCheckedIn = Boolean.parseBoolean(String.valueOf(document.getProperty("IsCheckedIn")));
        FlightNr = Integer.valueOf(String.valueOf(document.getProperty("FlightNr")));
        NrTickets = Integer.valueOf(String.valueOf(document.getProperty("NrTickets")));
        return new Passenger(HasLuggage, IsCheckedIn, Name, FirstName, FlightNr, NrTickets);
    }
}
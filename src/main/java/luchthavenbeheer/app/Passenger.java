package luchthavenbeheer.app;

import com.couchbase.lite.Document;

public class Passenger
{
	public Boolean HasLuggage;
	public Boolean IsCheckedIn;
	public String Name;
	public int FlightNr;
    public int NrTickets;

	public Passenger(Boolean HasLuggage, Boolean IsCheckedIn, String Name, int FlightNr, int NrTickets)
	{
        this.HasLuggage = HasLuggage;
        this.IsCheckedIn = IsCheckedIn;
        this.Name = Name;
        this.FlightNr = FlightNr;
        this.NrTickets = NrTickets;
	}

	public Passenger() {}

    public Passenger CastDocumentToPassenger(Document document)
    {
        Name = (String)document.getProperty("Name");
        HasLuggage = Boolean.parseBoolean((String)document.getProperty("HasLuggage"));
        IsCheckedIn = Boolean.parseBoolean((String)document.getProperty("IsCheckedIn"));
        FlightNr = Integer.valueOf((String)document.getProperty("FlightNr"));
        NrTickets = Integer.valueOf((String)document.getProperty("NrTickets"));
        return new Passenger(HasLuggage, IsCheckedIn, Name, FlightNr, NrTickets);
    }

	public void Checkin()
	{
	}
	
	public void LuggageCheckin()
	{
	}
	
	public void Boarding()
	{
	}
}
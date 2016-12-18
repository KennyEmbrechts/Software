package luchthavenbeheer.app;

import com.couchbase.lite.Document;

import java.util.LinkedHashMap;

public class Passenger
{
	public Boolean HasLuggage;
	public Boolean IsCheckedIn;
	public String Name;
	public String FirstName;
	public int FlightNr;
    public int NrTickets;
    public Ticket ticket;

	public Passenger(Boolean HasLuggage, Boolean IsCheckedIn, String Name, String FirstName, int FlightNr, int NrTickets, Ticket ticket)
	{
        this.HasLuggage = HasLuggage;
        this.IsCheckedIn = IsCheckedIn;
        this.Name = Name;
        this.FirstName = FirstName;
        this.FlightNr = FlightNr;
        this.NrTickets = NrTickets;
        this.ticket = ticket;
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
        LinkedHashMap ticketHash = LinkedHashMap.class.cast(document.getProperty("Ticket"));
        Ticket ticket = new Ticket(Integer.valueOf(ticketHash.get("FlightNr").toString()), String.valueOf(ticketHash.get("Class")), Float.valueOf(ticketHash.get("Price").toString()), Integer.valueOf(ticketHash.get("luggage").toString()));
        return new Passenger(HasLuggage, IsCheckedIn, Name, FirstName, FlightNr, NrTickets, ticket);
    }
}
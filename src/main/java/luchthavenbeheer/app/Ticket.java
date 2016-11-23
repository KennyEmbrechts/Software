package luchthavenbeheer.app;

public class Ticket
{
	public int TicketNr;
	public int FlightNr;
	public Boolean IsPayed;
	public String Class;
	public float Price;

	public Ticket(int TicketNr, int FlightNr, Boolean IsPayed, String Class, float Price)
	{
        this.TicketNr = TicketNr;
        this.FlightNr = FlightNr;
        this.IsPayed = IsPayed;
        this.Class = Class;
        this.Price = Price;
	}
}

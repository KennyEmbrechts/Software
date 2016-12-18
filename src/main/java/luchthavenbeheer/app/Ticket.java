package luchthavenbeheer.app;

public class Ticket
{
	public int FlightNr;
	public String Class;
	public float Price;
	public int luggage;

	public Ticket(int FlightNr, String Class, float Price, int luggage)
	{
        this.FlightNr = FlightNr;
        this.Class = Class;
        this.Price = Price;
        this.luggage = luggage;
	}
}

package luchthavenbeheer.app;

import java.util.List;

public class Flight
{
	private List Passengers;
	private int FlightNr;
	private List<Integer> LuggageNrs;

    public Flight(List Passengers, int FlightNr, List<Integer> LuggageNrs)
    {
        this.Passengers = Passengers;
        this.FlightNr = FlightNr;
        this.LuggageNrs = LuggageNrs;
    }

	public void IsComplete()
	{
	}
}
package luchthavenbeheer.app.Controllers;

/**
 * Created by Jente on 21/11/2016.
 */
public class Context {
    private final static Context instance = new Context();

    public static Context getInstance()
    {
        return instance;
    }

    private int Price = 0;

    public void setPrice(int Price)
    {
        this.Price = Price;
    }

    public int getPrice()
    {
        return Price;
    }

    private int NrOFPassengers;

    public void setNrOFPassengers(int NrOFPassengers)
    {
        this.NrOFPassengers = NrOFPassengers;
    }

    public int getNrOFPassengers()
    {
        return NrOFPassengers;
    }

    private boolean HasLuggage;

    public void setHasLuggage(boolean HasLuggage)
    {
        this.HasLuggage = HasLuggage;
    }

    public boolean getHasLuggage()
    {
        return HasLuggage;
    }

    private int FlightNr;

    public void setFlightNr(int FlightNr)
    {
        this.FlightNr = FlightNr;
    }

    public int getFlightNr()
    {
        return FlightNr;
    }
}

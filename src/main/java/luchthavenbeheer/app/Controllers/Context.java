package luchthavenbeheer.app.Controllers;

public class Context {
    private final static Context instance = new Context();

    public static Context getInstance()
    {
        return instance;
    }

    private float Price = 0;

    public void setPrice(float Price)
    {
        this.Price = Price;
    }

    public float getPrice()
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

    private int Luggage;

    public void setLuggage(int Luggage)
    {
        this.Luggage = Luggage;
    }

    public int getLuggage()
    {
        return Luggage;
    }
}

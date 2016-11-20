package luchthavenbeheer.app;


public class Passenger
{
	public Boolean HasLuggage;
	public Boolean IsCheckedIn;
	public String Name;

	public Passenger(Boolean HasLuggage, Boolean IsCheckedIn, String Name)
	{
        this.HasLuggage = HasLuggage;
        this.IsCheckedIn = IsCheckedIn;
        this.Name = Name;
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
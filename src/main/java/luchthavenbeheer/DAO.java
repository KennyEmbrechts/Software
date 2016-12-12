package luchthavenbeheer;

import com.couchbase.lite.*;
import com.couchbase.lite.util.Log;
import luchthavenbeheer.app.FlightDetails;
import luchthavenbeheer.app.Passenger;
import java.io.IOException;
import java.util.*;
import java.util.List;
import static com.couchbase.lite.Document.TAG;

public class DAO {
    private Database database = null;
    private Manager manager = null;
    private Document document = null;
    FlightDetails flightDetails = new FlightDetails();
    Passenger passenger = new Passenger();

    public DAO()
    {
        JavaContext context = new JavaContext();

        try {
            manager = new Manager(context, Manager.DEFAULT_OPTIONS);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try {
            database = manager.getDatabase("luchthavenbeheerdb");
        }
        catch (CouchbaseLiteException e)
        {
            e.printStackTrace();
        }
    }

    public void getAll()
    {
        Query query = database.createAllDocumentsQuery();
        query.setAllDocsMode(Query.AllDocsMode.ALL_DOCS);
        QueryEnumerator result = null;
        try {
            result = query.run();
        }
        catch (CouchbaseLiteException e)
        {
            Log.e(TAG, "Cannot execute query", e);
        }
        for(Iterator<QueryRow> it = result; it.hasNext();)
        {
            QueryRow row = it.next();
            document = database.getDocument(String.valueOf(row.getDocumentId()));
            Log.w("Results: ", document.getProperties().toString());
        }
    }

    //Passengers
    public int CreatePassenger(Passenger passenger)
    {
        Map<String, Object> properties = new HashMap<>();
        properties.put("type", "Passenger");
        properties.put("Name", passenger.Name);
        properties.put("FirstName", passenger.FirstName);
        properties.put("HasLuggage", passenger.HasLuggage);
        properties.put("IsCheckedIn", passenger.IsCheckedIn);
        properties.put("FlightNr", passenger.FlightNr);
        properties.put("NrTickets", passenger.NrTickets);
        int TicketNr = (passenger.Name+passenger.FirstName+passenger.FlightNr).hashCode();
        document = database.getDocument(String.valueOf(TicketNr));

        try {
            document.putProperties(properties);
            return TicketNr;
        }
        catch (CouchbaseLiteException e)
        {
            Log.e(TAG, "Cannot save document", e);
            return -1;
        }
    }

    public Passenger GetPassenger(int TicketNr)
    {
        document = database.getDocument(String.valueOf(TicketNr));
        passenger = passenger.CastDocumentToPassenger(document);
        return passenger;
    }

    public Boolean UpdatePassenger(int TicketNr)
    {
        document = database.getDocument(String.valueOf(TicketNr));
        document.createRevision();
        Map<String, Object> properties = new HashMap<>();
        properties.putAll(document.getProperties());
        properties.put("IsCheckedIn", true);
        document = database.getDocument(String.valueOf(TicketNr));
        try {
            document.putProperties(properties);
            return true;
        } catch (CouchbaseLiteException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    //Flights
    public Boolean CreateFlightDetails(FlightDetails details)
    {
        Map<String, Object> properties = new HashMap<>();
        properties.put("type", "FlightDetails");
        properties.put("FlightNr", details.FlightNr);
        properties.put("FlightFrom", details.FlyFrom);
        properties.put("FlightTo", details.FlyTo);
        properties.put("LeaveHour", details.LeaveHour.toString());
        properties.put("ArrivalHour", details.ArrivalHour.toString());
        properties.put("Pilot", details.Pilot);
        properties.put("AirplaneNr", details.AirplaneNr);
        properties.put("Price", details.Price);
        document = database.getDocument(String.valueOf(details.FlightNr));

        try {
            document.putProperties(properties);
            return true;
        }
        catch (CouchbaseLiteException e)
        {
            Log.e(TAG, "Cannot save document", e);
            return false;
        }
    }


    public FlightDetails getFlightDetails(int flightNr)
    {
        document = database.getDocument(String.valueOf(flightNr));

        flightDetails = flightDetails.CastDocumentToFlightDetails(document);

        return flightDetails;
    }

    public List<FlightDetails> getAllFlightDetails()
    {
        List<FlightDetails> details = new ArrayList<>();
        Query query = database.createAllDocumentsQuery();
        query.setAllDocsMode(Query.AllDocsMode.ALL_DOCS);
        QueryEnumerator result = null;
        try {
            result = query.run();
        }
        catch (CouchbaseLiteException e)
        {
            Log.e(TAG, "Cannot execute query", e);
        }
        for(Iterator<QueryRow> it = result; it.hasNext();)
        {
            QueryRow row = it.next();
            document = database.getDocument(String.valueOf(row.getDocumentId()));
            String type = (String)document.getProperty("type");
            if(type.equals("FlightDetails"))
                details.add(flightDetails.CastDocumentToFlightDetails(document));
        }
        return details;
    }


}

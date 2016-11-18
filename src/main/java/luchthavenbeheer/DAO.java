package luchthavenbeheer;

import com.couchbase.lite.*;

import com.couchbase.lite.util.Log;
import luchthavenbeheer.app.FlightDetails;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.couchbase.lite.Document.TAG;

/**
 * Created by Jente on 17/11/2016.
 */
public class DAO {
    private Database database = null;
    private Manager manager = null;
    private Document document = null;

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

    public Boolean CreateFlightDetails(FlightDetails details)
    {
        Map<String, Object> properties = new HashMap<>();
        properties.put("FlightFrom", details.FlyFrom);
        properties.put("FlightTo", details.FlyTo);
        properties.put("LeaveHour", details.LeaveHour.toString());
        properties.put("ArrivalHour", details.ArrivalHour.toString());
        properties.put("Pilot", details.Pilot);
        properties.put("AirplaneNr", details.AirplaneNr);
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
        FlightDetails.Location FlightFrom = FlightDetails.Location.valueOf((String)document.getProperty("FlightFrom"));
        FlightDetails.Location FlightTo = FlightDetails.Location.valueOf((String)document.getProperty("FlightTo"));
        LocalDateTime LeaveHour = LocalDateTime.parse((String)document.getProperty("LeaveHour"));
        LocalDateTime ArrivalHour = LocalDateTime.parse((String)document.getProperty("ArrivalHour"));
        String Pilot = (String)document.getProperty("Pilot");
        int AirplaneNr = (int)document.getProperty("AirplaneNr");

        FlightDetails flightDetails = new FlightDetails(FlightFrom,FlightTo, flightNr,
                LeaveHour, ArrivalHour, Pilot, AirplaneNr);
        return flightDetails;
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
}

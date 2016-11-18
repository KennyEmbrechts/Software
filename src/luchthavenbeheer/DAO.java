package luchthavenbeheer;

import com.couchbase.lite.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jente on 17/11/2016.
 */
public class DAO {
    private Database database = null;
    private Manager manager = null;
    public DAO()
    {
        Logger log = Logger.getLogger("app");
        log.setLevel(Level.ALL);
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

        // The properties that will be saved on the document
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("title", "Couchbase Mobile");
        properties.put("sdk", "Java");

        Document document = database.createDocument();

        try {
            document.putProperties(properties);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        log.info(String.format("Document ID :: %s", document.getId()));
        log.info(String.format("Learning %s with %s", (String) document.getProperty("title"), (String) document.getProperty("sdk")));
    }


}

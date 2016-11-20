package luchthavenbeheer;

import com.couchbase.lite.CouchbaseLiteException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import luchthavenbeheer.app.FlightDetails;

import java.time.LocalDateTime;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/HomePage.fxml"));
        primaryStage.setTitle("Luchthaven beheer");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args){

        DAO dao = new DAO();
        /*FlightDetails details = new FlightDetails(FlightDetails.Location.Athene, FlightDetails.Location.Brussel, 1574, LocalDateTime.of(2016,12,13,20,15), LocalDateTime.of(2016,12,23,19,20), "Jan", 5987,80);
        FlightDetails details2 = new FlightDetails(FlightDetails.Location.Athene, FlightDetails.Location.Zurich, 5874, LocalDateTime.of(2017,01,13,20,15), LocalDateTime.of(2016,01,23,19,20), "Jan", 5987,90);
        FlightDetails details3 = new FlightDetails(FlightDetails.Location.Athene, FlightDetails.Location.Zagreb, 9854, LocalDateTime.of(2017,02,13,20,15), LocalDateTime.of(2016,02,23,19,20), "Jan", 5987,110);
        FlightDetails details4 = new FlightDetails(FlightDetails.Location.Athene, FlightDetails.Location.NewYork, 2358, LocalDateTime.of(2017,03,13,20,15), LocalDateTime.of(2016,03,23,19,20), "Jan", 5987,75);
        dao.CreateFlightDetails(details2);
        dao.CreateFlightDetails(details3);
        dao.CreateFlightDetails(details4);
        //dao.getAll();*/
        List<FlightDetails> a = dao.getAllFlightDetails();

        launch(args);
    }
}

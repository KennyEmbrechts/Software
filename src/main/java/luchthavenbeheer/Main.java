package luchthavenbeheer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/HomePage.fxml"));
        primaryStage.setTitle("Airport Management");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args){

        DAO dao = new DAO();
        /*FlightDetails details = new FlightDetails(Location.Athene, Location.Brussel, 1574, LocalDateTime.of(2016,12,13,20,15), LocalDateTime.of(2016,12,23,19,20), "Jan", 5987,80, Airline.AirBoliv);
        FlightDetails details2 = new FlightDetails(Location.Athene, Location.Zurich, 5874, LocalDateTime.of(2017,01,13,20,15), LocalDateTime.of(2016,01,23,19,20), "Jan", 5987,90, Airline.BonkAir);
        FlightDetails details3 = new FlightDetails(Location.Athene, Location.Zagreb, 9854, LocalDateTime.of(2017,02,13,20,15), LocalDateTime.of(2016,02,23,19,20), "Jan", 5987,110, Airline.SPAir);
        FlightDetails details4 = new FlightDetails(Location.Athene, Location.NewYork, 2358, LocalDateTime.of(2017,03,13,20,15), LocalDateTime.of(2016,03,23,19,20), "Jan", 5987,75, Airline.BonkAir);
        dao.CreateFlightDetails(details2);
        dao.CreateFlightDetails(details3);
        dao.CreateFlightDetails(details4);*/
        //dao.getAll();
        //List<FlightDetails> a = dao.getAllFlightDetails();
        //Passenger pas = dao.GetPassenger(-279777088);
        //System.out.println(pas.IsCheckedIn);
        dao.getAll();
        launch(args);
    }
}

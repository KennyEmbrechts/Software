package luchthavenbeheer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import luchthavenbeheer.app.FlightDetails;

import java.time.LocalDateTime;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/HomePage.fxml"));
        primaryStage.setTitle("Luchthaven beheer");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {

        DAO dao = new DAO();
        FlightDetails details = new FlightDetails(FlightDetails.Location.Athene, FlightDetails.Location.Brussel, 1574, LocalDateTime.of(16,12,13,20,15), LocalDateTime.of(16,12,23,19,20), "Jan", 5987);
        dao.CreateFlightDetails(details);
        FlightDetails details1 = dao.getFlightDetails(1574);
        System.out.println(details1.FlightNr);
        dao.getAll();

        launch(args);
    }
}

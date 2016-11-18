package luchthavenbeheer.app.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class BuyTicketCtrl implements Initializable {
    @FXML
    private Button BookTicket;

    @FXML
    private TextField Persons;

    @FXML
    private TextField Luggage;

    @FXML
    private ListView Flights;

    @FXML
    private ListView FlightDetails;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assert BookTicket != null : "fx:id=\"TicketBoekenKnop\" was not injected: check your FXML file 'simple.fxml'.";
        assert Persons != null : "fx:id=\"PersonenTextField\" was not injected: check your FXML file 'simple.fxml'.";
        assert Luggage != null : "fx:id=\"BagageTextField\" was not injected: check your FXML file 'simple.fxml'.";
        assert Flights  != null : "fx:id=\"VluchtenListView\" was not injected: check your FXML file 'simple.fxml'.";
        assert FlightDetails != null : "fx:id=\"VluchtDetailsListView\" was not injected: check your FXML file 'simple.fxml'.";

    }

}

package luchthavenbeheer.app.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import luchthavenbeheer.DAO;
import luchthavenbeheer.app.FlightDetails;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public class BuyTicketCtrl implements Initializable {
    DAO dao = null;
    @FXML
    private Button BookTicket;

    @FXML
    private TextField Persons;

    @FXML
    private TextField Luggage;

    @FXML
    private ListView ListTickets;

    @FXML
    private ListView ListDetails;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assert BookTicket != null : "fx:id=\"TicketBoekenKnop\" was not injected: check your FXML file 'simple.fxml'.";
        assert Persons != null : "fx:id=\"PersonenTextField\" was not injected: check your FXML file 'simple.fxml'.";
        assert Luggage != null : "fx:id=\"BagageTextField\" was not injected: check your FXML file 'simple.fxml'.";
        assert ListTickets  != null : "fx:id=\"ListTickets\" was not injected: check your FXML file 'simple.fxml'.";
        assert ListDetails != null : "fx:id=\"ListDetails\" was not injected: check your FXML file 'simple.fxml'.";
        setData();
    }

    public void setData()
    {
        dao = new DAO();
        List<FlightDetails> details = dao.getAllFlightDetails();
        ObservableList<String> oDetails = FXCollections.observableArrayList();
        for (FlightDetails detail: details) {
            oDetails.add(String.valueOf(detail.FlightNr) + ": " + String.valueOf(detail.FlyFrom) + " - " + String.valueOf(detail.FlyTo));
        }
        ListTickets.setItems(oDetails);
    }

    @FXML
    public void OnItemClicked(MouseEvent arg0)
    {
        String SelectedItem[] = ListTickets.getSelectionModel().selectedItemProperty().get().toString().split(":");
        FlightDetails details = dao.getFlightDetails(Integer.valueOf(SelectedItem[0]));
        ObservableList<String> oDetails = FXCollections.observableArrayList();
        oDetails.add(details.LeaveHour.toString());
        oDetails.add(details.ArrivalHour.toString());
        oDetails.add(String.valueOf(details.AirplaneNr));
        ListDetails.setItems(oDetails);
    }

}

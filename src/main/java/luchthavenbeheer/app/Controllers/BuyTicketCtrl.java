package luchthavenbeheer.app.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import luchthavenbeheer.DAO;
import luchthavenbeheer.app.FlightDetails;

import java.io.IOException;
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
        oDetails.add("Departure: "+details.LeaveHour.toString());
        oDetails.add("Arrival: "+details.ArrivalHour.toString());
        oDetails.add("FlightNr: "+String.valueOf(details.AirplaneNr));
        oDetails.add("Price: "+String.valueOf(details.Price));
        ListDetails.setItems(oDetails);
    }

    @FXML
    private void BookTicketClicked (ActionEvent event) throws IOException
    {
        Stage stage;
        Parent root;
        //get reference to the button's stage
        stage=(Stage) BookTicket.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/View/Pay.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

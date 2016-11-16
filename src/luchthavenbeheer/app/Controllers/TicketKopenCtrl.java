package luchthavenbeheer.app.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.control.ListView;
import luchthavenbeheer.app.Ticket;
import luchthavenbeheer.app.Vluchtdetails;

import java.net.URL;
import java.util.ResourceBundle;

public class TicketKopenCtrl implements Initializable {
    @FXML
    private Button TicketBoeken;

    @FXML
    private TextField Personen;

    @FXML
    private TextField Bagage;

    @FXML
    private ListView Vluchten;

    @FXML
    private ListView VluchtDetails;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assert TicketBoeken != null : "fx:id=\"TicketBoekenKnop\" was not injected: check your FXML file 'simple.fxml'.";
        assert Personen != null : "fx:id=\"PersonenTextField\" was not injected: check your FXML file 'simple.fxml'.";
        assert Bagage != null : "fx:id=\"BagageTextField\" was not injected: check your FXML file 'simple.fxml'.";
        assert Vluchten  != null : "fx:id=\"VluchtenListView\" was not injected: check your FXML file 'simple.fxml'.";
        assert VluchtDetails != null : "fx:id=\"VluchtDetailsListView\" was not injected: check your FXML file 'simple.fxml'.";

    }

}

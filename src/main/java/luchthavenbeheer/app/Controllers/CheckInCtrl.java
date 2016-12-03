package luchthavenbeheer.app.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import luchthavenbeheer.DAO;
import luchthavenbeheer.app.FlightDetails;
import luchthavenbeheer.app.Passenger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CheckInCtrl implements Initializable {
    @FXML
    private Button SearchBtn;
    @FXML
    private Button btnBack;
    @FXML
    private Button LuggageCheckInBtn;
    @FXML
    private Button CheckInBtn;
    @FXML
    private Label TicketNrLbl;
    @FXML
    private TextField TicketNr;
    @FXML
    private TextField Name;
    @FXML
    private ListView Details;
    @FXML
    private Pane CheckInPane;
    @FXML
    private Label TicketNrCheck;

    private DAO dao;
    private Regex regex;
    private Boolean IsFlightNR= false;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assert btnBack != null : "fx:id=\"BackBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert SearchBtn != null : "fx:id=\"ZoekIncheckBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert LuggageCheckInBtn != null : "fx:id=\"LuggageIncheckenBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert CheckInBtn != null : "fx:id=\"IncheckBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert TicketNrLbl != null : "fx:id=\"TicketNrLbl\" was not injected: check your FXML file 'simple.fxml'.";
        assert TicketNr != null : "fx:id=\"TicketNr\" was not injected: check your FXML file 'simple.fxml'.";
        assert Name != null : "fx:id=\"Naam\" was not injected: check your FXML file 'simple.fxml'.";
        assert Details != null : "fx:id=\"Details\" was not injected: check your FXML file 'simple.fxml'.";
        assert CheckInPane != null : "fx:id=\"IncheckPane\" was not injected: check your FXML file 'simple.fxml'.";
        assert TicketNrCheck != null : "fx:id=\"IncheckPane\" was not injected: check your FXML file 'simple.fxml'.";

        CheckInPane.setVisible(false);

        dao = new DAO();
        regex = new Regex();

        // Listen for TextField text changes
        TicketNr.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(regex.CheckFieldValues(Regex.Regexs.Integer,TicketNr))
                {
                    TicketNrCheck.setVisible(false);
                    IsFlightNR = true;
                }
                else
                {
                    TicketNrCheck.setVisible(true);
                    IsFlightNR = false;
                }
            }
        });
    }
    @FXML
    private void ClickedBackBtn (ActionEvent event) throws IOException
    {
        Stage stage;
        Parent root;
        //get reference to the button's stage
        stage=(Stage) btnBack.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/View/HomePage.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ClickedSearchBtn(ActionEvent event) throws IOException
    {
        if(IsFlightNR)
        {
            CheckInPane.setVisible(true);
            Passenger pas = dao.GetPassenger(Integer.valueOf(TicketNr.getText()));
            ObservableList<String> oDetails = FXCollections.observableArrayList();
            FlightDetails detail = dao.getFlightDetails(pas.FlightNr);
            oDetails.add("Flightnumber: "+String.valueOf(detail.FlightNr));
            oDetails.add("Coming from: "+String.valueOf(detail.FlyFrom));
            oDetails.add("Arriving at: "+String.valueOf(detail.FlyTo)+" at "+ detail.ArrivalHour);
            Details.setItems(oDetails);
        }
    }
}

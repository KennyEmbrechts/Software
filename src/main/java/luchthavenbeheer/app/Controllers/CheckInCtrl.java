package luchthavenbeheer.app.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private TextField TicketNr;
    @FXML
    private TextField Name;
    @FXML
    private ListView Details;
    @FXML
    private Pane CheckInPane;
    @FXML
    private Label TicketNrCheck;
    @FXML
    private TextField FirstName;
    @FXML
    private Label NameWarning;
    @FXML
    private Label FirstNameWarning;

    private DAO dao;
    private Regex regex;
    private Boolean IsFlightNR= false;
    private Boolean LuggageCheckedIn = false;

    private Passenger pas = null;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assert btnBack != null : "fx:id=\"BackBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert SearchBtn != null : "fx:id=\"ZoekIncheckBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert LuggageCheckInBtn != null : "fx:id=\"LuggageIncheckenBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert CheckInBtn != null : "fx:id=\"IncheckBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert TicketNr != null : "fx:id=\"TicketNr\" was not injected: check your FXML file 'simple.fxml'.";
        assert Name != null : "fx:id=\"Naam\" was not injected: check your FXML file 'simple.fxml'.";
        assert Details != null : "fx:id=\"Details\" was not injected: check your FXML file 'simple.fxml'.";
        assert CheckInPane != null : "fx:id=\"IncheckPane\" was not injected: check your FXML file 'simple.fxml'.";
        assert TicketNrCheck != null : "fx:id=\"IncheckPane\" was not injected: check your FXML file 'simple.fxml'.";
        assert FirstName != null : "fx:id=\"FirstNameLbl\" was not injected: check your FXML file 'simple.fxml'.";
        assert NameWarning != null : "fx:id=\"NameWarning\" was not injected: check your FXML file 'simple.fxml'.";
        assert FirstNameWarning != null : "fx:id=\"FirstNameWarning\" was not injected: check your FXML file 'simple.fxml'.";

        CheckInPane.setVisible(false);

        dao = new DAO();
        regex = new Regex();

        // Listen for TextField text changes
        TicketNr.textProperty().addListener((observable, oldValue, newValue)-> {
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
        });
        Name.textProperty().addListener((observable, oldValue, newValue)-> {
            if(regex.CheckFieldValues(Regex.Regexs.Name,Name))
            {
                NameWarning.setVisible(false);
            }
            else
            {
                NameWarning.setVisible(true);
            }
        });
        FirstName.textProperty().addListener((observable, oldValue, newValue)-> {
            if(regex.CheckFieldValues(Regex.Regexs.Name,FirstName))
            {
                FirstNameWarning.setVisible(false);
            }
            else
            {
                FirstNameWarning.setVisible(true);
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
            pas = dao.GetPassenger(Integer.valueOf(TicketNr.getText()));
            ObservableList<String> oDetails = FXCollections.observableArrayList();
            FlightDetails detail = dao.getFlightDetails(pas.FlightNr);
            oDetails.add("Flightnumber: "+String.valueOf(detail.FlightNr));
            oDetails.add("Coming from: "+String.valueOf(detail.FlyFrom));
            oDetails.add("Arriving at: "+String.valueOf(detail.FlyTo)+" at "+ detail.ArrivalHour);
            oDetails.add("Opperated by: " +  String.valueOf(detail.airline));
            Details.setItems(oDetails);
        }
    }

    @FXML
    private void ClickedCheckinLuggageBtn(ActionEvent event) throws IOException
    {
        if(pas.HasLuggage == true)
        {
            LuggageCheckedIn = true;
            dao.UpdatePassenger(Integer.parseInt(TicketNr.getText()));
            infoBox("Luggage is succecfully checkedin", "luggage checkin", "Luggage checkin message", Alert.AlertType.CONFIRMATION);
        }
        else
        {
            infoBox("You cannot checkin luggage", "luggage checkin", "Luggage checkin message", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void ClickedCheckinBtn(ActionEvent event) throws IOException
    {
        if(regex.CheckFieldValues(Regex.Regexs.Name,Name) && regex.CheckFieldValues(Regex.Regexs.Name,FirstName))
        {
            if(pas.FirstName.equals(FirstName.getText()) && pas.Name.equals(Name.getText()))
            {
                if(pas.HasLuggage && LuggageCheckedIn == false)
                {
                    infoBox("You Forgot to checkin your luggage", "Luggage checkin", "Luggage checkin message", Alert.AlertType.ERROR);
                }
                else
                {
                    infoBox("You are succecfully checkedin", "Checkin", "Checkin message", Alert.AlertType.CONFIRMATION);
                    Stage stage;
                    Parent root;
                    //get reference to the button's stage
                    stage=(Stage) CheckInBtn.getScene().getWindow();
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("/View/Boarding.fxml"));

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            }
            else
            {
                infoBox("Your name does not match your tickets name", "Checkin", "Checkin message", Alert.AlertType.ERROR);
            }
        }
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage, Alert.AlertType type)
    {
        Alert alert = new Alert(type);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
}

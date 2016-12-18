package luchthavenbeheer.app.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import luchthavenbeheer.DAO;
import luchthavenbeheer.app.FlightDetails;
import luchthavenbeheer.app.Passenger;
import luchthavenbeheer.app.Plane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jente on 12/12/2016.
 */
public class BoardingCtrl implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private Button btnBoard;
    @FXML
    private TextField TicketNr;
    @FXML
    private TextField Name;
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
    private Passenger pas;

    private Plane plane;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assert btnBack != null : "fx:id=\"BackBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert btnBoard != null : "fx:id=\"btnBoard\" was not injected: check your FXML file 'simple.fxml'.";
        assert TicketNr != null : "fx:id=\"TicketNr\" was not injected: check your FXML file 'simple.fxml'.";
        assert Name != null : "fx:id=\"Name\" was not injected: check your FXML file 'simple.fxml'.";
        assert TicketNrCheck != null : "fx:id=\"IncheckPane\" was not injected: check your FXML file 'simple.fxml'.";
        assert FirstName != null : "fx:id=\"FirstNameLbl\" was not injected: check your FXML file 'simple.fxml'.";
        assert NameWarning != null : "fx:id=\"NameWarning\" was not injected: check your FXML file 'simple.fxml'.";
        assert FirstNameWarning != null : "fx:id=\"FirstNameWarning\" was not injected: check your FXML file 'simple.fxml'.";

        regex = new Regex();
        plane = new Plane();

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
        root = FXMLLoader.load(getClass().getResource("/View/CheckIn.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ClickedBoardBtn (ActionEvent event) throws IOException
    {
        dao = new DAO();
        if(IsFlightNR) {
            pas = dao.GetPassenger(Integer.valueOf(TicketNr.getText()));
            if (pas.IsCheckedIn) {
                if (pas.FirstName.equals(FirstName.getText()) && pas.Name.equals(Name.getText())) {
                    FlightDetails fd;
                    fd = dao.getFlightDetails(pas.FlightNr);
                    plane.setLuggage(pas.ticket.luggage);
                    infoBox("You have reached your destination", "Destination reached", "You have reached: " + fd.FlyTo, Alert.AlertType.CONFIRMATION);
                }
                else
                {
                    infoBox("Your name does not match your tickets name", "Checkin", "Checkin message", Alert.AlertType.ERROR);
                }
            }
            else
            {
                infoBox("You are not checkedin","Not CheckedIn", "not Chekedin", Alert.AlertType.ERROR);
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

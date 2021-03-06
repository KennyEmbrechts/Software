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
import luchthavenbeheer.app.Passenger;
import luchthavenbeheer.app.Ticket;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class PayCtrl implements Initializable {
    @FXML
    private Button PayBtn;
    @FXML
    private Button btnBack;
    @FXML
    private Label PriceLbl;
    @FXML
    private TextField Name;
    @FXML
    private TextField FirstName;
    @FXML
    private TextField AccountNr;
    @FXML
    private TextField SecurityNr;
    @FXML
    private Label NameWarning;
    @FXML
    private Label FirstNameWarning;
    @FXML
    private Label AccountWarning;
    @FXML
    private Label SecurityNrWarning;
    @FXML
    private Label ButtonWarning;

    private DAO dao;
    private Boolean HasLuggage;
    private int FlightNr;
    private int NrOfPassengers;
    private float Price;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert PayBtn != null : "fx:id=\"PayBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert btnBack != null : "fx:id=\"BackBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert PriceLbl != null : "fx:id=\"PriceLbl\" was not injected: check your FXML file 'simple.fxml'.";
        assert Name != null : "fx:id=\"Name\" was not injected: check your FXML file 'simple.fxml'.";
        assert FirstName != null : "fx:id=\"FirstName\" was not injected: check your FXML file 'simple.fxml'.";
        assert AccountNr != null : "fx:id=\"AccountNr\" was not injected: check your FXML file 'simple.fxml'.";
        assert SecurityNr != null : "fx:id=\"SecurityNr\" was not injected: check your FXML file 'simple.fxml'.";
        assert NameWarning != null : "fx:id=\"NameWarning\" was not injected: check your FXML file 'simple.fxml'.";
        assert FirstNameWarning != null : "fx:id=\"FirstNameWarning\" was not injected: check your FXML file 'simple.fxml'.";
        assert AccountWarning != null : "fx:id=\"AccountWarning\" was not injected: check your FXML file 'simple.fxml'.";
        assert SecurityNrWarning != null : "fx:id=\"SecurityNrWarning\" was not injected: check your FXML file 'simple.fxml'.";
        assert ButtonWarning != null : "fx:id=\"ButtonWarning\" was not injected: check your FXML file 'simple.fxml'.";

        dao = new DAO();

        // Listen for TextField text changes
        Name.textProperty().addListener((observable, oldValue, newValue) -> {
                if(CheckFieldValues(Regex.Regexs.Name,Name))
                {
                    NameWarning.setVisible(false);
                }
                else
                {
                    NameWarning.setVisible(true);
                }
        });
        FirstName.textProperty().addListener((observable, oldValue, newValue) -> {
                if(CheckFieldValues(Regex.Regexs.Name,FirstName))
                {
                    FirstNameWarning.setVisible(false);
                }
                else
                {
                    FirstNameWarning.setVisible(true);
                }
        });
        AccountNr.textProperty().addListener((observable,oldValue, newValue) -> {

                if(CheckFieldValues(Regex.Regexs.AccountNr,AccountNr))
                {
                    AccountWarning.setVisible(false);
                }
                else
                {
                    AccountWarning.setVisible(true);
                }
        });
        SecurityNr.textProperty().addListener((observable, oldValue, newValue) -> {
                if(CheckFieldValues(Regex.Regexs.FourDigitNr,SecurityNr))
                {
                    SecurityNrWarning.setVisible(false);
                }
                else
                {
                    SecurityNrWarning.setVisible(true);
                }
        });
        Price = Context.getInstance().getPrice();
        PriceLbl.setText("Total Price: €" + Price);
    }

    @FXML
    private void ClickedBackBtn (ActionEvent event) throws IOException
    {
        Stage stage;
        Parent root;
        //get reference to the button's stage
        stage=(Stage) btnBack.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/View/BuyTicket.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void PayBtnClicked(ActionEvent event) throws IOException {
        if (CheckFieldValues(Regex.Regexs.Name,Name) && CheckFieldValues(Regex.Regexs.Name,FirstName) && CheckFieldValues(Regex.Regexs.AccountNr,AccountNr) && CheckFieldValues(Regex.Regexs.FourDigitNr,SecurityNr))
        {
            FlightNr = Context.getInstance().getFlightNr();
            NrOfPassengers = Context.getInstance().getNrOFPassengers();
            HasLuggage = Context.getInstance().getHasLuggage();

            int TicketNR = dao.CreatePassenger(new Passenger(HasLuggage, false, Name.getText(), FirstName.getText(), FlightNr, NrOfPassengers, new Ticket(FlightNr,"Economy", Price, Context.getInstance().getLuggage())));
            if(TicketNR==-1)
            {
                infoBox("You have already bought tickets for this flight","FATAL ERROR","Invalid input");
                Stage stage;
                Parent root;
                //get reference to the button's stage
                stage = (Stage) PayBtn.getScene().getWindow();
                //load up OTHER FXML document
                root = FXMLLoader.load(getClass().getResource("/View/BuyTicket.fxml"));

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else {
                ButtonWarning.setVisible(false);
                infoBox("This is your ticket number: " + TicketNR + " please keep this safe at all times", "Ticket Number", "Please save your ticket number!");
                Stage stage;
                Parent root;
                //get reference to the button's stage
                stage = (Stage) PayBtn.getScene().getWindow();
                //load up OTHER FXML document
                root = FXMLLoader.load(getClass().getResource("/View/HomePage.fxml"));

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else
        {
            ButtonWarning.setVisible(true);
        }
    }

    public Boolean CheckFieldValues(Regex.Regexs reg, TextField txt)
    {
        return Pattern.matches(reg.toString(), txt.getText());
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
}

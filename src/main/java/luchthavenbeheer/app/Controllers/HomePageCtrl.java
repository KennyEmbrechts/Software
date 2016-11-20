package luchthavenbeheer.app.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomePageCtrl implements Initializable {

    @FXML
    private Button btnBuyTicket;
    @FXML
    private Button btnCheckIn;
    @FXML
    private Button btnDetails;


    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assert btnBuyTicket != null : "fx:id=\"BtnBuyTicket\" was not injected: check your FXML file 'simple.fxml'.";
        assert btnCheckIn != null : "fx:id=\"BtnCheckIn\" was not injected: check your FXML file 'simple.fxml'.";
        assert btnDetails != null : "fx:id=\"BtnDetails\" was not injected: check your FXML file 'simple.fxml'.";
    }

    @FXML
    private void ClickedBuyTicketBtn (ActionEvent event) throws IOException
    {
        Stage stage;
        Parent root;
        //get reference to the button's stage
        stage=(Stage) btnBuyTicket.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/View/BuyTicket.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ClickedCheckInBtn (ActionEvent event) throws IOException
    {
        Stage stage;
        Parent root;
        //get reference to the button's stage
        stage=(Stage) btnCheckIn.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/View/CheckIn.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void ClickedDetailsBtn (ActionEvent event) throws IOException
    {
        Stage stage;
        Parent root;
        //get reference to the button's stage
        stage=(Stage) btnDetails.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/View/FlightDetails.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

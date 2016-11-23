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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import luchthavenbeheer.DAO;
import luchthavenbeheer.app.FlightDetails;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FlightDetailsCtrl implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField FlightNumber;
    @FXML
    private ListView lstDetails;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assert btnBack != null : "fx:id=\"BackBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert btnSearch != null : "fx:id=\"SearchBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert FlightNumber != null : "fx:id=\"FlightNr\" was not injected: check your FXML file 'simple.fxml'.";
        assert lstDetails != null : "fx:id=\"lstDetails\" was not injected: check your FXML file 'simple.fxml'.";
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
    private void ClickSearchBtn (ActionEvent event) throws IOException
    {
        DAO dao = new DAO();
        ObservableList<String> oDetails = FXCollections.observableArrayList();
        String value = FlightNumber.getText();
        FlightDetails detail = dao.getFlightDetails(Integer.valueOf(FlightNumber.getText()));
        oDetails.add("Flightnumber: "+String.valueOf(detail.FlightNr));
        oDetails.add("Coming from: "+String.valueOf(detail.FlyFrom));
        oDetails.add("Arriving at: "+String.valueOf(detail.FlyTo)+" at "+ detail.ArrivalHour);
        lstDetails.setItems(oDetails);
    }
}

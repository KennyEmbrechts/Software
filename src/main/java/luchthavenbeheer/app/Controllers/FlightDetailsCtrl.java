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
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by jente on 5/11/16.
 */
public class FlightDetailsCtrl implements Initializable {

    @FXML
    private Button BackBtn;
    @FXML
    private Button SearchBtn;
    @FXML
    private TextField FlightNr;
    @FXML
    private ListView lstDetails;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assert BackBtn != null : "fx:id=\"BackBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert SearchBtn != null : "fx:id=\"SearchBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert FlightNr != null : "fx:id=\"FlightNr\" was not injected: check your FXML file 'simple.fxml'.";
        assert lstDetails != null : "fx:id=\"lstDetails\" was not injected: check your FXML file 'simple.fxml'.";
    }

    @FXML
    private void ClickedBackBtn (ActionEvent event) throws IOException
    {
        Stage stage;
        Parent root;
        //get reference to the button's stage
        stage=(Stage) BackBtn.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/luchthavenbeheer/app/View/HomePageCtrl.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ClickSearchBtn (ActionEvent event) throws IOException
    {
        DAO dao = new DAO();
        List<FlightDetails> details = dao.getAllFlightDetails();
        ObservableList<String> oDetails = FXCollections.observableArrayList();
        for (FlightDetails detail: details) {
            oDetails.add(String.valueOf(detail.FlightNr) + ": " + String.valueOf(detail.FlyFrom) + " - " + String.valueOf(detail.FlyTo));
        }
        lstDetails.setItems(oDetails);
    }
}

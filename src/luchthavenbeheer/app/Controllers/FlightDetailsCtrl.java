package luchthavenbeheer.app.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
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
    private ListView Details;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assert BackBtn != null : "fx:id=\"BackBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert SearchBtn != null : "fx:id=\"SearchBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert FlightNr != null : "fx:id=\"FlightNr\" was not injected: check your FXML file 'simple.fxml'.";
        assert Details != null : "fx:id=\"Details\" was not injected: check your FXML file 'simple.fxml'.";
    }
}

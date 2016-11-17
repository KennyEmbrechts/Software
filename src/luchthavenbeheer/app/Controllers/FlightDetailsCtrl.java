package luchthavenbeheer.app.Controllers;

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

import java.io.IOException;
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
}

package luchthavenbeheer.app.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jente on 5/11/16.
 */
public class PayCtrl implements Initializable{
    @FXML
    private Button PayBtn;
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

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert PayBtn != null : "fx:id=\"PayBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert PriceLbl != null : "fx:id=\"PriceLbl\" was not injected: check your FXML file 'simple.fxml'.";
        assert Name != null : "fx:id=\"Name\" was not injected: check your FXML file 'simple.fxml'.";
        assert FirstName != null : "fx:id=\"FirstName\" was not injected: check your FXML file 'simple.fxml'.";
        assert AccountNr != null : "fx:id=\"AccountNr\" was not injected: check your FXML file 'simple.fxml'.";
        assert SecurityNr != null : "fx:id=\"SecurityNr\" was not injected: check your FXML file 'simple.fxml'.";
    }


}

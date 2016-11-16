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
public class IncheckenCtrl implements Initializable {
    @FXML
    private Button ZoekBtn;
    @FXML
    private Button BagageIncheckBtn;
    @FXML
    private Button IncheckBtn;
    @FXML
    private Label TicketNrLbl;
    @FXML
    private TextField TicketNr;
    @FXML
    private TextField Naam;
    @FXML
    private ListView Details;
    @FXML
    private Pane IncheckPane;



    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assert ZoekBtn != null : "fx:id=\"ZoekIncheckBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert BagageIncheckBtn != null : "fx:id=\"BagageIncheckenBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert IncheckBtn != null : "fx:id=\"IncheckBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert TicketNrLbl != null : "fx:id=\"TicketNrLbl\" was not injected: check your FXML file 'simple.fxml'.";
        assert TicketNr != null : "fx:id=\"TicketNr\" was not injected: check your FXML file 'simple.fxml'.";
        assert Naam != null : "fx:id=\"Naam\" was not injected: check your FXML file 'simple.fxml'.";
        assert Details != null : "fx:id=\"Details\" was not injected: check your FXML file 'simple.fxml'.";
        assert IncheckPane != null : "fx:id=\"IncheckPane\" was not injected: check your FXML file 'simple.fxml'.";
    }
}

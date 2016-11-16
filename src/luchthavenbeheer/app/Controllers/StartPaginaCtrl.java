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

public class StartPaginaCtrl implements Initializable {

    @FXML
    private Button KoopTicketKnop;
    @FXML
    private Button IncheckKnop;


    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assert KoopTicketKnop != null : "fx:id=\"KoopTicketKnop\" was not injected: check your FXML file 'simple.fxml'.";
        assert IncheckKnop != null : "fx:id=\"IncheckKnop\" was not injected: check your FXML file 'simple.fxml'.";
    }

    @FXML
    private void KoopTicketKnopgeklikt (ActionEvent event) throws IOException
    {
        Stage stage;
        Parent root;
        //get reference to the button's stage
        stage=(Stage) KoopTicketKnop.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/luchthavenbeheer/app/View/TicketKopen.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void IncheckKnopgeklikt (ActionEvent event) throws IOException
    {
        Stage stage;
        Parent root;
        //get reference to the button's stage
        stage=(Stage) IncheckKnop.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("/luchthavenbeheer/app/View/Inchecken.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

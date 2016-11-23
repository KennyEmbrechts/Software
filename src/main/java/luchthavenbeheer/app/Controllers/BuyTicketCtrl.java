package luchthavenbeheer.app.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import luchthavenbeheer.DAO;
import luchthavenbeheer.app.FlightDetails;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class BuyTicketCtrl implements Initializable {
    DAO dao = null;
    @FXML
    private Button BookTicket;
    @FXML
    private Button btnBack;

    @FXML
    private TextField Persons;

    @FXML
    private TextField Luggage;

    @FXML
    private Label lblPrice;

    private FlightDetails details;

    @FXML
    private ListView ListTickets;

    @FXML
    private ListView ListDetails;

    public static boolean HasBagage = false;
    public static String[] FlightNr;
    public static int Price;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assert BookTicket != null : "fx:id=\"TicketBoekenKnop\" was not injected: check your FXML file 'simple.fxml'.";
        assert Persons != null : "fx:id=\"PersonenTextField\" was not injected: check your FXML file 'simple.fxml'.";
        assert Luggage != null : "fx:id=\"BagageTextField\" was not injected: check your FXML file 'simple.fxml'.";
        assert ListTickets  != null : "fx:id=\"ListTickets\" was not injected: check your FXML file 'simple.fxml'.";
        assert ListDetails != null : "fx:id=\"ListDetails\" was not injected: check your FXML file 'simple.fxml'.";
        assert btnBack != null : "fx:id=\"BackBtn\" was not injected: check your FXML file 'simple.fxml'.";
        assert lblPrice != null : "fx:id=\"lblPrice\" was not injected: check your FXML file 'simple.fxml'.";
        setData();

        Persons.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                setPrice(details);
            }
        });
        Luggage.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                setPrice(details);
            }
        });
    }
    public void setPrice(FlightDetails Details)
    {
        int luggage, persons;
        if(Persons.getText().equals(""))
        {
            persons = 0;
        }
        else {
            persons = Integer.valueOf(Persons.getText());
        }
        Context.getInstance().setNrOFPassengers(persons);
        if(Luggage.getText().equals(""))
        {
            luggage = 0;
            Context.getInstance().setHasLuggage(false);
        }
        else {
            luggage = Integer.valueOf(Luggage.getText());
            Context.getInstance().setHasLuggage(true);
        }
        int totalPrice = (persons*details.Price)+luggage*25;
        lblPrice.setText("Total Price: "+String.valueOf(totalPrice)+"€");
        Context.getInstance().setPrice(totalPrice);

    }
    public void setData()
    {
        dao = new DAO();
        List<FlightDetails> details = dao.getAllFlightDetails();
        ObservableList<String> oDetails = FXCollections.observableArrayList();
        for (FlightDetails detail: details) {
            oDetails.add(String.valueOf(detail.FlightNr) + ": " + String.valueOf(detail.FlyFrom) + " - " + String.valueOf(detail.FlyTo));
        }
        ListTickets.setItems(oDetails);
    }

    @FXML
    public void OnItemClicked(MouseEvent arg0)
    {
        String SelectedItem[] = ListTickets.getSelectionModel().selectedItemProperty().get().toString().split(":");
        details = dao.getFlightDetails(Integer.valueOf(SelectedItem[0]));
        ObservableList<String> oDetails = FXCollections.observableArrayList();
        oDetails.add("Departure: "+details.LeaveHour.toString());
        oDetails.add("Arrival: "+details.ArrivalHour.toString());
        oDetails.add("FlightNr: "+String.valueOf(details.AirplaneNr));
        oDetails.add("Price: "+String.valueOf(details.Price)+"€");
        ListDetails.setItems(oDetails);
        Context.getInstance().setFlightNr(Integer.getInteger(SelectedItem[0]));
    }

    @FXML
    private void BookTicketClicked (ActionEvent event) throws IOException
    {

        Persons.getText();


        if(Pattern.matches(Regex.Regexs.OneDigitNrNotNul.toString(), Persons.getText()))
        {
            if(Pattern.matches(Regex.Regexs.OneDigitNr.toString(), Luggage.getText()))
            {
                if(ListTickets.getSelectionModel().getSelectedIndex() > -1)
                {
                    Stage stage;
                    Parent root;
                    //get reference to the button's stage
                    stage=(Stage) BookTicket.getScene().getWindow();
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("/View/Pay.fxml"));
                    
                    if(Integer.valueOf(Luggage.getText()) > 0)
                        HasBagage = true;
                    FlightNr = ListTickets.getSelectionModel().selectedItemProperty().get().toString().split(":");
                    String nrOfPersons = Persons.getText();


                    PayCtrl payCtrl = new PayCtrl();


                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    //payCtrl.setParams(HasBagage, Integer.getInteger(FlightNr[0]),Integer.getInteger(nrOfPersons), Price);
                    stage.show();
                }
                else
                {
                    infoBox("Please inform us of your flight", "Flight Error Message","Flight information");
                }

            }
            else
            {
                infoBox("Please inform us of your luggage", "Luggage Error Message","Luggage information");
            }
        }
        else
        {
            infoBox("The amount of persons you're trying to buy tickets is incorrect (You can't buy more than 10 tickets for the same flight)", "Persons Error Message","Person information");
        }
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
    public static void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }


}

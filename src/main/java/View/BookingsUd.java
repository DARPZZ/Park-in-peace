package View;

import Model.DaoObject.PlotOwner;
import Model.DaoObject.Resevations;
import Model.DaoObject.User;
import Model.Implements.DaoOwner;
import Model.Implements.DaoResevations;
import com.example.park.HelloApplication;
import com.example.park.SceneName;
import com.example.park.UserSubscriber;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

public class BookingsUd extends Header implements UserSubscriber
{
    private TableView<PlotOwner> tableView = new TableView<>();
    List<PlotOwner> PloOwnerDataList = new ArrayList<>();
    DaoOwner daoOwner = new DaoOwner();

    Label yourePlots = new Label("Youre plots");
    List<PlotOwner> plotOwnerList;
    private int currentUserID;
    Button lejerButton = new Button("Lejer");
    Button udLejerButton = new Button("Udlejer");
  
    public BookingsUd()
    {
        currentUserID=0;
        plotOwnerList = daoOwner.GetAll();
        setScene();
     ANCHOR_PANE.getChildren().addAll(udLejerButton,lejerButton,tableView,yourePlots);
    }
    public void  setScene()
    {
        tableView.setLayoutX(50);
        tableView.setLayoutY(250);
        tableView.setPrefWidth(350);
        lejerButton.setLayoutX(225);
        lejerButton.setPrefWidth(150);
        lejerButton.setLayoutY(125);
        udLejerButton.setPrefWidth(lejerButton.getPrefWidth());
        udLejerButton.setLayoutY(lejerButton.getLayoutY());
        udLejerButton.setLayoutX(lejerButton.getLayoutX()+165);
        tableView.setPrefWidth(350);
        yourePlots.setLayoutX(190);
        yourePlots.setLayoutY(225);
        changeBack();
    }
    public void changeBack()
    {
        lejerButton.setOnAction(event -> HelloApplication.changeScene(SceneName.Bookings));
    }
    public void getData()
    {
        for (PlotOwner plo : plotOwnerList) {
            int plotID = plo.getPlotID();
            String location = plo.getLocation();
            int zipcode = plo.getZipCode();
            int userID = plo.getUserID();
            Date startDate = plo.getStartDate();
            Date endDate = plo.getSlutDate();
            PlotOwner plotOwner = new PlotOwner(plotID, location, zipcode,userID, startDate, endDate);

            if (currentUserID == userID) { // Check for userID match
                PloOwnerDataList.add(plotOwner);
            }
        }
        createTable();
    }
    @Override
    public void onUserReceived(User user)
    {
        currentUserID = user.getUserId();
        getData();
    }
    public void createTable()
    {
        tableView.setEditable(true);

        TableColumn<PlotOwner, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<PlotOwner, Integer> zipcodeColumn = new TableColumn<>("Zip Code");
        zipcodeColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getZipCode()).asObject());
        zipcodeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        TableColumn<PlotOwner, Date> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStartDate()));
        startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));

        TableColumn<PlotOwner, Date> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSlutDate()));
        endDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));


        tableView.getColumns().addAll(addressColumn, zipcodeColumn, startDateColumn, endDateColumn);

        ObservableList<PlotOwner> data = FXCollections.observableArrayList(PloOwnerDataList);
        tableView.setItems(data);

    }

}
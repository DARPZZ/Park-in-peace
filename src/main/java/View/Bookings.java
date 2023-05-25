package View;

import Model.DaoObject.Combine;
import Model.DaoObject.PlotOwner;
import Model.DaoObject.User;
import Model.Implements.DaoCombine;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bookings extends Header implements UserSubscriber
{
    Button lejerButton = new Button("Lejer");
    Button udLejerButton = new Button("Udlejer");
    Label youreResevations = new Label("Youre resevations");
    private int currentUserID;
    private TableView<Combine> tableView = new TableView<>();
    List<Combine> combineDataList = new ArrayList<>();
    DaoCombine daoCombine = new DaoCombine();
    DaoResevations daoResevations = new DaoResevations();
    List<Combine> combineList;




    public Bookings()
    {
        currentUserID = 0;
        combineList = daoCombine.GetAll();
        setScene();
    }


    public void setScene()
    {
        tableView.setLayoutX(50);
        tableView.setLayoutY(250);
        tableView.setPrefWidth(350);
        youreResevations.setLayoutX(190);
        youreResevations.setLayoutY(225);
        lejerButton.setLayoutX(225);
        lejerButton.setPrefWidth(150);
        lejerButton.setLayoutY(125);
        udLejerButton.setPrefWidth(lejerButton.getPrefWidth());
        udLejerButton.setLayoutY(lejerButton.getLayoutY());
        udLejerButton.setLayoutX(lejerButton.getLayoutX()+165);
        udLejerButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {HelloApplication.changeScene(SceneName.BookingsUd);}});
        ANCHOR_PANE.getChildren().addAll(tableView,udLejerButton,youreResevations,lejerButton);
    }

    public void getData()
    {

        for (Combine com : combineList) {
            int userID = com.getUserID();
            String location = com.getLocation();
            int zipcode = com.getZipCode();
            Date startDate = com.getStartDate();
            Date endDate = com.getEndDate();

            Combine combine = new Combine(userID, location, zipcode, startDate, endDate);

            if (currentUserID == userID) {
                combineDataList.add(combine);
            }
        }

        createTable();
    }

    public void createTable()
    {
        tableView.setEditable(true);
        TableColumn<Combine, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Combine, Integer> zipcodeColumn = new TableColumn<>("Zip Code");
        zipcodeColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getZipCode()).asObject());
        zipcodeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        TableColumn<Combine, Date> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStartDate()));
        startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));

        TableColumn<Combine, Date> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getEndDate()));
        endDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        tableView.getColumns().addAll(addressColumn, zipcodeColumn, startDateColumn, endDateColumn);

        ObservableList<Combine> data = FXCollections.observableArrayList(combineDataList);
        tableView.setItems(data);

        addressColumn.setOnEditCommit(table ->
        {table.getTableView().getItems().get(table.getTablePosition().getRow()).setLocation(table.getNewValue());
            //daoResevations.Update(table.getTableView().getItems().get(table.getTablePosition().getRow()));
        });
        zipcodeColumn.setOnEditCommit(table ->
        {
            table.getTableView().getItems().get(table.getTablePosition().getRow()).setLocation(String.valueOf(table.getNewValue()));
        });

        startDateColumn.setOnEditCommit(table ->
        {
            table.getTableView().getItems().get(table.getTablePosition().getRow()).setLocation(String.valueOf(table.getNewValue()));
        });

        endDateColumn.setOnEditCommit(table ->
        {
            table.getTableView().getItems().get(table.getTablePosition().getRow()).setLocation(String.valueOf(table.getNewValue()));
        });

    }

    @Override
    public void onUserReceived(User user)
    {
        currentUserID = user.getUserId();
        getData();
    }


}

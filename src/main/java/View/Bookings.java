package View;

import Model.DaoObject.Combine;
import Model.DaoObject.Plot;
import Model.DaoObject.User;
import Model.Implements.DaoCombine;
import Model.Implements.DaoUser;
import com.example.park.Login;
import com.example.park.UserSubscriber;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bookings extends Header implements UserSubscriber
{
    TableView tableView = new TableView();

    List<Combine> combineDataList = new ArrayList<>();
    Model.Implements.DaoCombine daoCombine = new DaoCombine();


    public Bookings()
    {
        setScene();
    }
    public void setScene()
    {
        getData();
        ANCHOR_PANE.getChildren().addAll(tableView);
    }
    public void getData()
    {

        List<Combine> combineList = daoCombine.GetAll();
        for (Combine com : combineList) {
            String location = com.getLocation();
            int zipcode = com.getZipCode();
            Date startDate = com.getStartDate();
            Date endDate = com.getEndDate();
            Combine combine = new Combine(location, zipcode, startDate, endDate);
            combineDataList.add(combine);
            createTableViewLejer();
        }
    }

    public void createTableViewLejer()
    {
        tableView.setLayoutX(50);
        tableView.setLayoutY(300);
        TableColumn<Combine, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));

        TableColumn<Combine, Integer> zipcodeColumn = new TableColumn<>("Zip Code");
        zipcodeColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getZipCode()).asObject());

        TableColumn<Combine, Date> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStartDate()));

        TableColumn<Combine, Date> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getEndDate()));

        tableView.getColumns().addAll(addressColumn, zipcodeColumn, startDateColumn, endDateColumn);

        ObservableList<Combine> data = FXCollections.observableArrayList(combineDataList);
        tableView.setItems(data);

    }

    @Override
    public void onUserReceived(User user)
    {
        System.out.println(user.getName()+ "ko");

    }
}



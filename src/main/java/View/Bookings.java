package View;

import Model.DaoObject.Combine;
import Model.DaoObject.User;
import Model.Implements.DaoCombine;
import com.example.park.UserSubscriber;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bookings extends Header implements UserSubscriber {
    Label yourePlots = new Label();
    private int currentUserID;
    private TableView<Combine> tableView = new TableView<>();
    List<Combine> combineDataList = new ArrayList<>();
    DaoCombine daoCombine = new DaoCombine();
    List<Combine> combineList;

    public Bookings() {
        currentUserID = 0;
        combineList = daoCombine.GetAll();
        tableView.setLayoutX(50);
        tableView.setLayoutY(200);
        setScene();
    }



    public void setScene() {
        ANCHOR_PANE.getChildren().addAll(tableView);
    }

    public void getData() {

        for (Combine com : combineList) {
            int userID = com.getUserID();
            String location = com.getLocation();
            int zipcode = com.getZipCode();
            Date startDate = com.getStartDate();
            Date endDate = com.getEndDate();
            System.out.println(userID + "use");
            System.out.println(location + " loc");

            Combine combine = new Combine(userID, location, zipcode, startDate, endDate);

            if (currentUserID == userID) { // Check for userID match
                combineDataList.add(combine);
            }
        }

        createTable();
    }
    public void createTable()
    {

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
    public void onUserReceived(User user) {
        currentUserID = user.getUserId();

        getData();
        System.out.println(currentUserID  + " reeeee");
    }

}

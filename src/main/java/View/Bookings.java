package View;

import Model.DaoObject.Combine;
import Model.DaoObject.PlotOwner;
import Model.DaoObject.User;
import Model.Implements.DaoCombine;
import Model.Implements.DaoOwner;
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

public class Bookings extends Header implements UserSubscriber
{
    Label yourePlots = new Label();
    private int currentUserID;
    private TableView<PlotOwner> tableView = new TableView<>();
    List<Combine> combineDataList = new ArrayList<>();
    DaoCombine daoCombine = new DaoCombine();
    List<Combine> combineList;

    List<PlotOwner> PloOwnerDataList = new ArrayList<>();
    DaoOwner daoOwner = new DaoOwner();
    List<PlotOwner> plotOwnerList;



    public Bookings()
    {
        currentUserID = 0;
        combineList = daoCombine.GetAll();
        plotOwnerList = daoOwner.GetAll();
        tableView.setLayoutX(50);
        tableView.setLayoutY(200);
        setScene();
    }


    public void setScene()
    {
        ANCHOR_PANE.getChildren().addAll(tableView);
    }

    public void getData()
    {

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

        //createTable();
    }
/*
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

 */



    public void getData2()
    {

        System.out.println(plotOwnerList.size() + " pååååå");

        for (PlotOwner plo : plotOwnerList) {
            int plotID = plo.getPlotID();
            String location = plo.getLocation();
            int zipcode = plo.getZipCode();
            int userID = plo.getUserID();
            Date startDate = plo.getStartDate();
            Date endDate = plo.getSlutDate();



            PlotOwner plotOwner = new PlotOwner(plotID, location, zipcode,userID, startDate, endDate);
            System.out.println(currentUserID + "current id");
            System.out.println(plotOwner.getUserID() + "userID");


            if (currentUserID == userID) { // Check for userID match
                PloOwnerDataList.add(plotOwner);
            }
        }
        createTable2();
        System.out.println(plotOwnerList.size() + " sizeeeeeeeee");
    }
    @Override
    public void onUserReceived(User user)
    {
        currentUserID = user.getUserId();
        getData2();
        getData();

    }
    public void createTable2()
    {

        TableColumn<PlotOwner, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));

        TableColumn<PlotOwner, Integer> zipcodeColumn = new TableColumn<>("Zip Code");
        zipcodeColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getZipCode()).asObject());

        TableColumn<PlotOwner, Date> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStartDate()));

        TableColumn<PlotOwner, Date> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSlutDate()));

        tableView.getColumns().addAll(addressColumn, zipcodeColumn, startDateColumn, endDateColumn);

        ObservableList<PlotOwner> data = FXCollections.observableArrayList(PloOwnerDataList);

        tableView.setItems(data);
    }


}

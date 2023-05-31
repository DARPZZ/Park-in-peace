package View;

import Model.DaoObject.*;
import Model.DatabaseWorker.PlotList;
import Model.DatabaseWorker.ReservationList;
import com.example.park.UserSubscriber;
import Model.Implements.DaoResevations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Bookings extends Header implements UserSubscriber
{
    Button removeResevationButton = new Button("Remove resevations");
    Button lejerButton = new Button("Lejer");
    Button udLejerButton = new Button("Udlejer");
    Label infoLabel = new Label();
    private int currentUserID;
    private TableView<Combine> tableView = new TableView<>();
    List<Combine> combineDataList = new ArrayList<>();
    List<Combine> combineDataListUd = new ArrayList<Combine>();

    Resevations resevations = new Resevations();
    List<Plot> plotList = PlotList.getSingleton().getList();
    List<Resevations> reservationList = ReservationList.getSingleton().getList();
    public Bookings()
    {
        currentUserID = 0;
        setScene();
        infoLabel.setText("Youre resevations");

    }
    public void setScene()
    {
        tableView.setLayoutX(50);
        tableView.setLayoutY(250);
        tableView.setPrefWidth(400);
        infoLabel.setLayoutX(190);
        infoLabel.setLayoutY(225);
        lejerButton.setLayoutX(225);
        lejerButton.setPrefWidth(150);
        lejerButton.setLayoutY(125);
        udLejerButton.setPrefWidth(lejerButton.getPrefWidth());
        udLejerButton.setLayoutY(lejerButton.getLayoutY());
        udLejerButton.setLayoutX(lejerButton.getLayoutX()+165);
        removeResevationButton.setLayoutX(160);
        removeResevationButton.setLayoutY(660);
        ANCHOR_PANE.getChildren().addAll(tableView,udLejerButton, infoLabel,lejerButton,removeResevationButton);
    }
    public void getData() {
        updateTabels();
        tableView.getColumns().clear();
        reservationList.clear();
        ReservationList.getSingleton().setList();
        combineDataList.clear();
        combineDataListUd.clear();
        List<Integer> plotOwnerDataList = new ArrayList<>();
        List<Integer> reservedPlotIds = new ArrayList<>();
        for (Resevations res : reservationList) {
            int userID = res.getUserID();
            int reservationID = res.getReservationID();
            LocalDate localStartDate = res.getStartDate();
            LocalDate localEndDate = res.getEndDate();
            Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());


            String location ="";
            int zipCode =0;
            int plotOwner = 0;

            for (Plot plot : plotList) {
                if (res.getPlotID() == plot.getPlotID()) {
                    plotOwner = plot.getUserID();
                    location = plot.getLocation();
                    zipCode = plot.getZipCode();
                }
            }
            if (currentUserID == userID) {
                reservedPlotIds.add(res.getPlotID());
                Combine combine = new Combine(userID, reservationID, location, zipCode, startDate, endDate);

                combineDataList.add(combine);
            }
            if (plotOwner == currentUserID) {

                Combine combine = new Combine(userID, reservationID, location, zipCode, startDate, endDate);

                combineDataListUd.add(combine);
            }
        }
        createTable(combineDataList);

    }

    public void createTable(List arrayList) {


        StringConverter<Date> converter = new DateStringConverter("yyyy-MM-dd");
        TableColumn<Combine, String> resevationsIdColumn = new TableColumn<>("Resevations ID");
        resevationsIdColumn.setCellValueFactory(cellData -> cellData.getValue().resevationsIDProperty().asString());
        resevationsIdColumn.setVisible(false);

        TableColumn<Combine, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addressColumn.setEditable(false);

        TableColumn<Combine, Integer> zipcodeColumn = new TableColumn<>("Zip Code");
        zipcodeColumn.setCellValueFactory(cellData -> cellData.getValue().zipCodeProperty().asObject());
        zipcodeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        zipcodeColumn.setEditable(false);

        TableColumn<Combine, Date> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
        startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));


        //startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        TableColumn<Combine, Date> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setCellValueFactory(cellData -> cellData.getValue().endDateProperty());
        endDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        removeResevationButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                deleteResevations();
            }
        });

        endDateColumn.setOnEditCommit(table ->
        {
            //table.getTableView().getItems().get(table.getTablePosition().getRow()).setLocation(String.valueOf(table.getNewValue()));
            int resid = table.getTableView().getItems().get(table.getTablePosition().getRow()).getResevationsID();
            DateFormat dtformat = new SimpleDateFormat("yyyy-MM-dd");
            String endDate = dtformat.format(table.getNewValue());
          // Resevations resevations = new Resevations();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(endDate, formatter);
            resevations.setReservationID(resid);
            resevations.setEndDate(localDate);
            updateEndDate(resevations);
        });

        startDateColumn.setOnEditCommit(table ->
        {
            int resid = table.getTableView().getItems().get(table.getTablePosition().getRow()).getResevationsID();
            DateFormat dtformat = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = dtformat.format(table.getNewValue());
            //Resevations resevations = new Resevations();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(startDate, formatter);
            resevations.setReservationID(resid);
            resevations.setStartDate(localDate);
            updateStartDate(resevations);
        });
        tableView.getColumns().addAll(resevationsIdColumn, addressColumn, zipcodeColumn, startDateColumn, endDateColumn);


        ObservableList<Combine> data = FXCollections.observableArrayList(arrayList);
        tableView.setItems(data);
    }

    public void updateTabels()
    {
        bookingsBtn.setOnAction(event ->
        {
            infoLabel.setText("Youre resevations");
            tableView.setEditable(true);
            getData();
          tableView.getColumns().clear();
            createTable(combineDataList);
        });
        lejerButton.setOnAction(event ->
        {
            infoLabel.setText("Youre resevations");
            tableView.setEditable(true);
            getData();
            tableView.getColumns().clear();
            createTable(combineDataList);
        });
        udLejerButton.setOnAction(event ->
        {
            infoLabel.setText("Youre plots");
            tableView.setEditable(false);
            getData();
            tableView.getColumns().clear();
            createTable(combineDataListUd);
        });
    }
    //region Subcriber
    @Override
    public void onUserReceived(User user) {
        currentUserID = user.getUserId();

        getData();
    }
    //endregion
    public void updateEndDate(Resevations resevations)
    {
        new DaoResevations().Update(resevations,"fldEndDate",String.valueOf(resevations.getEndDate()));
    }
    public void updateStartDate(Resevations resevations)
    {
        new DaoResevations().Update(resevations, "fldStartDate",String.valueOf(resevations.getStartDate()));
    }
    public void deleteResevations()
    {
        int resid = tableView.getSelectionModel().getSelectedItem().getResevationsID();
        Resevations resevations = new Resevations();
        tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
        new DaoResevations().Delete(resevations,resid);

    }

}

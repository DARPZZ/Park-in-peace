package View;

import Model.DaoObject.Plot;
import Model.DatabaseWorker.PlotList;
import Model.DatabaseWorker.ReservationList;
import com.example.park.UserSubscriber;
import Model.DaoObject.Combine;
import Model.DaoObject.Resevations;
import Model.DaoObject.User;
import Model.Implements.DaoResevations;
import com.example.park.HelloApplication;
import com.example.park.SceneName;
import com.example.park.UserSubscriber;
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
    Button lejerButton = new Button("Lejer");
    Button udLejerButton = new Button("Udlejer");
    Label youreResevations = new Label("Youre resevations");
    private int currentUserID;
    private TableView<Combine> tableView = new TableView<>();
    List<Combine> combineDataList = new ArrayList<>();


    public Bookings()
    {
        currentUserID = 0;
        setScene();
        updateTabels();

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

/*
        executorService = Executors.newSingleThreadScheduledExecutor();
        Runnable getData = this::getData;

        executorService.scheduleAtFixedRate(getData, 0, 4, TimeUnit.SECONDS);

 */
        udLejerButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {HelloApplication.changeScene(SceneName.BookingsUd);}});
        ANCHOR_PANE.getChildren().addAll(tableView,udLejerButton,youreResevations,lejerButton);
    }

    public void getData()
    {

        for (Plot plotList:PlotList.getSingleton().getList()) {

            for (Resevations res : ReservationList.getSingleton().getList()) {
                int userID = res.getUserID();
                int resevationsID = res.getReservationID();
                String location = plotList.getLocation();
                int zipcode = plotList.getZipCode();
                LocalDate localStartDate = res.getStartDate();
                LocalDate localEndDate = res.getEndDate();
                Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Combine combine = new Combine(userID, resevationsID, location, zipcode, startDate, endDate);
                if (currentUserID == userID) {
                    combineDataList.add(combine);
                }
            }
        }
        createTable();
    }


    public void createTable() {

        // if (tableView.getColumns().isEmpty()) {

        tableView.setEditable(true);
        TableColumn<Combine, String> resevationsIdColumn = new TableColumn<>("Resevations ID");
        resevationsIdColumn.setCellValueFactory(cellData -> cellData.getValue().resevationsIDProperty().asString());
        resevationsIdColumn.setVisible(false);

        TableColumn<Combine, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Combine, Integer> zipcodeColumn = new TableColumn<>("Zip Code");
        zipcodeColumn.setCellValueFactory(cellData -> cellData.getValue().zipCodeProperty().asObject());
        zipcodeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        TableColumn<Combine, Date> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());

        StringConverter<Date> converter = new DateStringConverter("yyyy-MM-dd");
        startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));
        startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        TableColumn<Combine, Date> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setCellValueFactory(cellData -> cellData.getValue().endDateProperty());
        endDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        endDateColumn.setOnEditCommit(table ->
        {
            table.getTableView().getItems().get(table.getTablePosition().getRow()).setLocation(String.valueOf(table.getNewValue()));
            int resid = table.getTableView().getItems().get(table.getTablePosition().getRow()).getResevationsID();
            DateFormat dtformat = new SimpleDateFormat("yyyy-MM-dd");
            String endDate = dtformat.format(table.getNewValue());
            Resevations resevations = new Resevations();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(endDate, formatter);
            resevations.setReservationID(resid);
            resevations.setEndDate(localDate);
            updateEndDate(resevations);


        });
        tableView.getColumns().addAll(resevationsIdColumn, addressColumn, zipcodeColumn, startDateColumn, endDateColumn);

        //}
        ObservableList<Combine> data = FXCollections.observableArrayList(combineDataList);
        tableView.setItems(data);
    }




    @Override
    public void onUserReceived(User user) {
        currentUserID = user.getUserId();
        getData();

    }


    public void updateEndDate(Resevations resevations)
    {
        new DaoResevations().Update(resevations,"fldEndDate",String.valueOf(resevations.getEndDate()));
    }

    public void updateTabels()
    {
        bookingsBtn.setOnAction(event ->
        {
            tableView.getColumns().clear();
            getData();
        });
        lejerButton.setOnAction(event ->
        {
            tableView.getColumns().clear();
            getData();
        });
        udLejerButton.setOnAction(event ->
        {
            tableView.getColumns().clear();
            getData();
        });
    }

}

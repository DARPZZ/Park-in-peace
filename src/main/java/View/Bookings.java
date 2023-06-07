package View;

import Controller.ResevationController;
import Model.DaoObject.*;
import Model.DatabaseWorker.PlotList;
import Model.DatabaseWorker.ReservationList;
import com.example.park.UserSubscriber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Bookings extends Header implements UserSubscriber
{
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    ResevationController resController = new ResevationController();
    Button removeResevationButton = new Button("Remove resevations");
    Button lejerButton = new Button("Lejer");
    Button udLejerButton = new Button("Udlejer");
    Label infoLabel = new Label();
   int currentUserID = 0;
    public TableView<Combine> tableView = new TableView<>();
    Resevations resevations = new Resevations();
    List<Plot> plotList = PlotList.getSingleton().getList();
    List<Resevations> reservationList = ReservationList.getSingleton().getList();

    /**
     * Constructor of the bookings class
     */
    public Bookings()
    {
        currentUserID = 0;
        setScene();
        infoLabel.setText("Dine reservationer");
    }

    /**
     * Adding the diffrent nodes to the scene
     */
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
        anchorPane.getChildren().addAll(tableView,udLejerButton, infoLabel,lejerButton,removeResevationButton);
    }

    /**
     * Get information from resevation controller
     */
    public void getData() {
        updateTabels();
        resController.getResevationData(currentUserID,tableView);
        createTable(resController.getCombineDataList());
    }

    /**
     * creates a tabel view with Adress,zipcode, startdate, end date and resevation ID
     * It also handels updates to tabel view f.eks. if a user decide to remove a resevation
     * @param arrayList Depends on if you're a landlord or tenant. Two diffrent arrays
     */

    public void createTable(List arrayList) {
        tableView.setEditable(true);
        DateTimeFormatter converter;
         converter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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


        TableColumn<Combine, LocalDate> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
        startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter(converter, converter)));


        //startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        TableColumn<Combine, LocalDate> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setCellValueFactory(cellData -> cellData.getValue().endDateProperty());
        endDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter(converter, converter)));


        removeResevationButton.setOnAction(event -> resController.deleteResevationsFromDb(tableView));

        endDateColumn.setOnEditCommit(event -> onEndDateEditCommit(event));
        startDateColumn.setOnEditCommit(event -> onStartDateEditCommit(event));
        tableView.getColumns().addAll(resevationsIdColumn, addressColumn, zipcodeColumn, startDateColumn, endDateColumn);


        ObservableList<Combine> data = FXCollections.observableArrayList(arrayList);
        tableView.setItems(data);
    }

    /**
     * Sets on action for the button to update for the latest information
     */
    public void updateTabels()
    {
        getBookingsButton().setOnAction(event ->
        {
            removeResevationButton.setVisible(true);
            removeResevationButton.setDisable(false);
            infoLabel.setText("Dine reservationer");
            tableView.setEditable(true);
            resController.getResevationData(currentUserID,tableView);
            createTable(resController.getCombineDataList());
        });

        lejerButton.setOnAction(event ->
        {
            removeResevationButton.setVisible(true);
            removeResevationButton.setDisable(false);
            infoLabel.setText("Dine reservationer");
            tableView.setEditable(true);
            resController.getResevationData(currentUserID,tableView);
            createTable(resController.getCombineDataList());
        });
        udLejerButton.setOnAction(event ->
        {
            infoLabel.setText("Dine Pladser");
            tableView.setEditable(false);
            removeResevationButton.setVisible(false);
            removeResevationButton.setDisable(true);
            resController.getResevationData(currentUserID,tableView);
            createTable(resController.getCombineDataListUd());
        });
    }
    //region Subcriber

    /**
     * Gets the current user ID
     * @param user the user is the user that is login via publish/subscribe pattern
     */
    @Override
    public void onUserReceived(User user) {
        currentUserID = user.getUserId();
        getData();
    }

    /**
     * Will uupdate the end date in the tabelview, and thereafter send that information to the database
     * @param table  The CellEditEvent containing the updated end date in the TableView.
     */
    public void onEndDateEditCommit(TableColumn.CellEditEvent<Combine, LocalDate> table) {
        int resid = table.getTableView().getItems().get(table.getTablePosition().getRow()).getResevationsID();
        String endDate = String.valueOf(table.getNewValue());
        LocalDate localDate = LocalDate.parse(endDate, formatter);
        resevations.setReservationID(resid);
        resevations.setEndDate(localDate);
        resController.updateEndDate(resevations);
    }
    /**
     * Will uupdate the end date in the tabelview, and thereafter send that information to the database
     * @param table  The CellEditEvent containing the updated end date in the TableView.
     */
    public void onStartDateEditCommit(TableColumn.CellEditEvent<Combine, LocalDate> table) {
        int resid = table.getTableView().getItems().get(table.getTablePosition().getRow()).getResevationsID();
        String startDate = String.valueOf(table.getNewValue());
        LocalDate localDate = LocalDate.parse(startDate, formatter);
        resevations.setReservationID(resid);
        resevations.setStartDate(localDate);
        resController.updateStartDate(resevations);
    }
    //endregion
}

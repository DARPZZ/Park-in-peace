package View;

import Controller.ReservationsController;
import Model.DaoObject.*;

import Service.UserSubscriber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    ReservationsController resController = new ReservationsController();
    Button removeReservationsButton = new Button("Remove reservations");
    Button lejerButton = new Button("Lejer");
    Button udLejerButton = new Button("Udlejer");
    Label infoLabel = new Label();
    int currentUserID = 0;
    public TableView<Combine> tableView = new TableView<>();
    Reservations reservations = new Reservations();

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
        tableView.setLayoutX(anchorPane.getWidth()/4);
        tableView.setLayoutY(250);
        tableView.setPrefWidth(625);
        infoLabel.setLayoutX(tableView.getLayoutX()*2-75);
        infoLabel.setLayoutY(225);
        lejerButton.setLayoutX(225);
        lejerButton.setPrefWidth(150);
        lejerButton.setLayoutY(125);
        udLejerButton.setPrefWidth(lejerButton.getPrefWidth());
        udLejerButton.setLayoutY(lejerButton.getLayoutY());
        udLejerButton.setLayoutX(lejerButton.getLayoutX()+165);
        removeReservationsButton.setLayoutX(tableView.getLayoutX()*2-75);
        removeReservationsButton.setLayoutY(660);
        anchorPane.getChildren().addAll(tableView,udLejerButton, infoLabel,lejerButton, removeReservationsButton);
    }

    /**
     * Get information from reservations controller
     */
    public void getData() {
        updateTabels();
        resController.getReservationsData(currentUserID,tableView);
        createTable(resController.getCombineDataList());
    }

    /**
     * creates a tabel view with Adress,zipcode, startdate, end date and reservations ID
     * It also handels updates to tabel view f.eks. if a user decide to remove a reservations
     * @param arrayList Depends on if you're a landlord or tenant. Two diffrent arrays
     */

    public void createTable(List arrayList) {
        tableView.setEditable(true);
        DateTimeFormatter converter;
         converter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        TableColumn<Combine, String> reservationsIdColumn = new TableColumn<>("Reservations ID");
        reservationsIdColumn.setCellValueFactory(cellData -> cellData.getValue().reservationsIDProperty().asString());
        reservationsIdColumn.setVisible(false);


        TableColumn<Combine, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addressColumn.setEditable(false);
        addressColumn.setPrefWidth(150);

        TableColumn<Combine, Integer> zipcodeColumn = new TableColumn<>("Zip Code");
        zipcodeColumn.setCellValueFactory(cellData -> cellData.getValue().zipCodeProperty().asObject());
        zipcodeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        zipcodeColumn.setEditable(false);
        zipcodeColumn.setPrefWidth(150);


        TableColumn<Combine, LocalDate> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
        startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter(converter, converter)));
        startDateColumn.setPrefWidth(150);

        TableColumn<Combine, LocalDate> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setCellValueFactory(cellData -> cellData.getValue().endDateProperty());
        endDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter(converter, converter)));
        endDateColumn.setPrefWidth(150);

        removeReservationsButton.setOnAction(event -> resController.deleteReservationsFromDb(tableView));

        endDateColumn.setOnEditCommit(event -> onEndDateEditCommit(event));
        startDateColumn.setOnEditCommit(event -> onStartDateEditCommit(event));
        tableView.getColumns().addAll(reservationsIdColumn, addressColumn, zipcodeColumn, startDateColumn, endDateColumn);

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
            removeReservationsButton.setVisible(true);
            removeReservationsButton.setDisable(false);
            infoLabel.setText("Dine reservationer");
            tableView.setEditable(true);
            resController.getReservationsData(currentUserID,tableView);
            createTable(resController.getCombineDataList());
        });

        lejerButton.setOnAction(event ->
        {
            removeReservationsButton.setVisible(true);
            removeReservationsButton.setDisable(false);
            infoLabel.setText("Dine reservationer");
            tableView.setEditable(true);
            resController.getReservationsData(currentUserID,tableView);
            createTable(resController.getCombineDataList());
        });
        udLejerButton.setOnAction(event ->
        {
            infoLabel.setText("Dine Pladser");
            tableView.setEditable(false);
            removeReservationsButton.setVisible(false);
            removeReservationsButton.setDisable(true);
            resController.getReservationsData(currentUserID,tableView);
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
        int resid = table.getTableView().getItems().get(table.getTablePosition().getRow()).getReservationsID();
        String endDate = String.valueOf(table.getNewValue());
        LocalDate localDate = LocalDate.parse(endDate, formatter);
        reservations.setReservationID(resid);
        reservations.setEndDate(localDate);
        resController.updateEndDate(reservations);
    }
    /**
     * Will uupdate the end date in the tabelview, and thereafter send that information to the database
     * @param table  The CellEditEvent containing the updated end date in the TableView.
     */
    public void onStartDateEditCommit(TableColumn.CellEditEvent<Combine, LocalDate> table) {
        int resid = table.getTableView().getItems().get(table.getTablePosition().getRow()).getReservationsID();
        String startDate = String.valueOf(table.getNewValue());
        LocalDate localDate = LocalDate.parse(startDate, formatter);
        reservations.setReservationID(resid);
        reservations.setStartDate(localDate);
        resController.updateStartDate(reservations);
    }
    //endregion
}

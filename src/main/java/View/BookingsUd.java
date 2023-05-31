package View;

import Model.DaoObject.PlotOwner;
import Model.DaoObject.User;
import Model.Implements.DaoPlotOwner;
import com.example.park.HelloApplication;
import com.example.park.SceneName;
import com.example.park.UserSubscriber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingsUd extends Header implements UserSubscriber
{
    private TableView<PlotOwner> tableView = new TableView<>();
    List<PlotOwner> PloOwnerDataList = new ArrayList<>();
    DaoPlotOwner daoPlotOwner = new DaoPlotOwner();

    Label yourePlots = new Label("Youre plots");
    List<PlotOwner> plotOwnerList;
    private int currentUserID;
    Button lejerButton = new Button("Lejer");
    Button udLejerButton = new Button("Udlejer");

    public BookingsUd()
    {
        currentUserID=0;

        setScene();
        ANCHOR_PANE.getChildren().addAll(udLejerButton,lejerButton,tableView,yourePlots);
    }
    public void  setScene()
    {

        tableView.setLayoutX(50);
        tableView.setLayoutY(250);
        tableView.setPrefWidth(400);
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
        plotOwnerList = daoPlotOwner.GetAll();
        for (PlotOwner plo : plotOwnerList) {
            int plotID = plo.getPlotID();
            String location = plo.getLocation();
            int zipcode = plo.getZipCode();
            int userID = plo.getUserID();
            Date startDate = (Date) plo.getStartDate();
            Date endDate = (Date) plo.getSlutDate();


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
        StringConverter<Date> converter = new DateStringConverter("yyyy-MM-dd");
        tableView.setEditable(true);

        TableColumn<PlotOwner, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<PlotOwner, Integer> zipcodeColumn = new TableColumn<>("Zip Code");
        zipcodeColumn.setCellValueFactory(cellData -> cellData.getValue().zipCodeProperty().asObject());
        zipcodeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        TableColumn<PlotOwner, Date> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
        startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

        TableColumn<PlotOwner, Date> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setCellValueFactory(cellData -> cellData.getValue().slutDateProperty());
        endDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));


        tableView.getColumns().addAll(addressColumn, zipcodeColumn, startDateColumn, endDateColumn);

        ObservableList<PlotOwner> data = FXCollections.observableArrayList(PloOwnerDataList);
        tableView.setItems(data);

    }


}
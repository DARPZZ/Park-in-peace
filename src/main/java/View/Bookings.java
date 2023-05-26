package View;

import Model.DaoObject.Combine;
import Model.DaoObject.Resevations;
import Model.DaoObject.User;
import Model.Implements.DaoCombine;
import Model.Implements.DaoResevations;
import com.example.park.HelloApplication;
import com.example.park.SceneName;
import com.example.park.UserSubscriber;
import javafx.application.Platform;
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
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.ToDoubleBiFunction;

public class Bookings extends Header implements UserSubscriber
{
    Button lejerButton = new Button("Lejer");
    Button udLejerButton = new Button("Udlejer");
    Label youreResevations = new Label("Youre resevations");
    private int currentUserID;
    private TableView<Combine> tableView = new TableView<>();
    List<Combine> combineDataList = new ArrayList<>();
    private boolean isUpdatingStartDate = false;
    private ScheduledExecutorService executorService;


    public Bookings()
    {
        currentUserID = 0;
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

        executorService = Executors.newSingleThreadScheduledExecutor();
        Runnable getData = this::getData;

        executorService.scheduleAtFixedRate(getData, 0, 5, TimeUnit.SECONDS);
        udLejerButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {HelloApplication.changeScene(SceneName.BookingsUd);}});
        ANCHOR_PANE.getChildren().addAll(tableView,udLejerButton,youreResevations,lejerButton);
    }

    public void getData()
    {
        DaoCombine daoCombine = new DaoCombine();
        List<Combine> combineList = daoCombine.GetAll();
        combineDataList.clear();
        for (Combine com : combineList) {
            int userID = com.getUserID();
            int resevationsID = com.getResevationsID();
            String location = com.getLocation();
            int zipcode = com.getZipCode();
            Date startDate = (Date) com.getStartDate();
            Date endDate = (Date) com.getEndDate();

            Combine combine = new Combine(userID,resevationsID, location, zipcode, startDate, endDate);
            if (currentUserID == userID) {
                combineDataList.add(combine);
            }
        }
        Platform.runLater(() -> {
            if (!isUpdatingStartDate) {
                createTable();
            }
        });
    }

    public void createTable() {
        if (tableView.getColumns().isEmpty()) {
            tableView.setEditable(true);

            TableColumn<Combine, String> resevationsIdColumn = new TableColumn<>("Resevations ID");
            resevationsIdColumn.setCellValueFactory(cellData -> cellData.getValue().resevationsIDProperty().asString());

            TableColumn<Combine, String> addressColumn = new TableColumn<>("Address");
            addressColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());

            TableColumn<Combine, Integer> zipcodeColumn = new TableColumn<>("Zip Code");
            zipcodeColumn.setCellValueFactory(cellData -> cellData.getValue().zipCodeProperty().asObject());

            TableColumn<Combine, Date> startDateColumn = new TableColumn<>("Start Date");
            startDateColumn.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
            StringConverter<Date> converter = new DateStringConverter("yyyy-MM-dd");
            startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

            TableColumn<Combine, Date> endDateColumn = new TableColumn<>("End Date");
            endDateColumn.setCellValueFactory(cellData -> cellData.getValue().endDateProperty());
            endDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

            tableView.getColumns().addAll(resevationsIdColumn, addressColumn, zipcodeColumn, startDateColumn, endDateColumn);
        }

        ObservableList<Combine> data = FXCollections.observableArrayList(combineDataList);
        tableView.getItems().setAll(data);
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
//TODO updateStartDate

}

package Controller;

import Model.DaoObject.Combine;
import Model.DaoObject.Plot;
import Model.DaoObject.Reservations;
import Controller.DatabaseWorker.PlotList;
import Controller.DatabaseWorker.ReservationList;
import Model.Implements.DaoReservations;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationsController
{
     private List<Combine> combineDataListUd = new ArrayList<>();
     private  List<Plot> plotList = PlotList.getSingleton().getList();
     private List<Reservations> reservationsList = ReservationList.getSingleton().getList();
     private List<Integer> plotOwnerDataList = new ArrayList<>();
     private List<Integer> reservedPlotIds = new ArrayList<>();
     private List<Combine> combineDataList = new ArrayList<>();
     String location ="";
    int zipCode =0;
    int plotOwner = 0;

    /**
     * Gets the current reservation the  current user has made
     * Gets the current reservation that is on the current user´s plot
     * @param currentUserID The user that is login to the system
     * @param tableView Gets the tableview so I can clear it
     */

        public void getReservationsData(int currentUserID, TableView tableView)
        {
           clearTabel(tableView);
            for (Reservations res : reservationsList) {
                int userID = res.getUserID();
                int reservationID = res.getReservationID();
                LocalDate localStartDate = res.getStartDate();
                LocalDate localEndDate = res.getEndDate();
                Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                LocalDate localStartDate2 = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate localEndDate2 = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                for (Plot plot : plotList) {
                    if (res.getPlotID() == plot.getPlotID()) {
                        plotOwner = plot.getUserID();
                        location = plot.getLocation();
                        zipCode = plot.getZipCode();
                    }
                }
                if (currentUserID == userID) {
                    reservedPlotIds.add(res.getPlotID());
                    Combine combine = new Combine(userID, reservationID, location, zipCode, localStartDate2, localEndDate2);
                    combineDataList.add(combine);
                }
                if (plotOwner == currentUserID) {
                    Combine combine = new Combine(userID, reservationID, location, zipCode, localStartDate2, localEndDate2);
                    combineDataListUd.add(combine);
                }
            }
        }
        public void updateEndDate(Reservations reservations)
        {
            new DaoReservations().Update(reservations,"fldEndDate",String.valueOf(reservations.getEndDate()));
        }
        public void updateStartDate(Reservations reservations)
        {
            new DaoReservations().Update(reservations, "fldStartDate",String.valueOf(reservations.getStartDate()));
        }

    /**
     * Clears the tableview
     * @param tableView gets the tableview to be able to clear the collums
     */
        public void clearTabel(TableView tableView)
        {
            tableView.getColumns().clear();
            reservationsList.clear();
            ReservationList.getSingleton().setList();
            combineDataList.clear();
            combineDataListUd.clear();
        }

    /**
     * Deltes the reservation from the databse
     * @param tableView
     */
    public void deleteReservationsFromDb(TableView<Combine> tableView)
    {
        int resid = tableView.getSelectionModel().getSelectedItem().getReservationsID();
        Reservations reservations = new Reservations();
        tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
        new DaoReservations().Delete(reservations,resid);
    }

    //region get and set

    public List<Combine> getCombineDataListUd()
    {
        return combineDataListUd;
    }

    public void setCombineDataListUd(List<Combine> combineDataListUd)
    {
        this.combineDataListUd = combineDataListUd;
    }

    public List<Plot> getPlotList()
    {
        return plotList;
    }

    public void setPlotList(List<Plot> plotList)
    {
        this.plotList = plotList;
    }

    public List<Reservations> getReservationsList()
    {
        return reservationsList;
    }

    public void setReservationsList(List<Reservations> reservationsList)
    {
        this.reservationsList = reservationsList;
    }

    public List<Combine> getCombineDataList()
    {
        return combineDataList;
    }

    public void setCombineDataList(List<Combine> combineDataList)
    {
        this.combineDataList = combineDataList;
    }


    //endregion
}

package Controller;

import Model.DaoObject.Combine;
import Model.DaoObject.Plot;
import Model.DaoObject.Resevations;
import Model.DatabaseWorker.PlotList;
import Model.DatabaseWorker.ReservationList;
import Model.Implements.DaoResevations;
import View.Bookings;
import javafx.scene.control.TableView;

import java.nio.channels.Pipe;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResevationController
{
 private List<Combine> combineDataListUd = new ArrayList<>();
 private  List<Plot> plotList = PlotList.getSingleton().getList();
 private List<Resevations> reservationList = ReservationList.getSingleton().getList();
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

        public void getResevationData(int currentUserID, TableView tableView)
        {
           clearTabel(tableView);
            for (Resevations res : reservationList) {
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
        public void updateEndDate(Resevations resevations)
        {
            new DaoResevations().Update(resevations,"fldEndDate",String.valueOf(resevations.getEndDate()));
        }
        public void updateStartDate(Resevations resevations)
        {
            new DaoResevations().Update(resevations, "fldStartDate",String.valueOf(resevations.getStartDate()));
        }

    /**
     * Clears the tableview
     * @param tableView gets the tableview to be able to clear the collums
     */
        public void clearTabel(TableView tableView)
        {
            tableView.getColumns().clear();
            reservationList.clear();
            ReservationList.getSingleton().setList();
            combineDataList.clear();
            combineDataListUd.clear();
        }

    /**
     * Deltes the reservation from the databse
     * @param tableView
     */
    public void deleteResevationsFromDb(TableView<Combine> tableView)
    {
        int resid = tableView.getSelectionModel().getSelectedItem().getResevationsID();
        Resevations resevations = new Resevations();
        tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
        new DaoResevations().Delete(resevations,resid);
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

    public List<Resevations> getReservationList()
    {
        return reservationList;
    }

    public void setReservationList(List<Resevations> reservationList)
    {
        this.reservationList = reservationList;
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

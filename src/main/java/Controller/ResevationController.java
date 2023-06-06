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
 private List<Combine> combineDataListUd = new ArrayList<Combine>();
 private  List<Plot> plotList = PlotList.getSingleton().getList();
 private List<Resevations> reservationList = ReservationList.getSingleton().getList();
   private List<Integer> plotOwnerDataList = new ArrayList<>();
   private List<Integer> reservedPlotIds = new ArrayList<>();
    private List<Combine> combineDataList = new ArrayList<>();
    String location ="";
    int zipCode =0;
    int plotOwner = 0;

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

        public void clearTabel(TableView tableView)
        {
            tableView.getColumns().clear();
            reservationList.clear();
            ReservationList.getSingleton().setList();
            combineDataList.clear();
            combineDataListUd.clear();
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

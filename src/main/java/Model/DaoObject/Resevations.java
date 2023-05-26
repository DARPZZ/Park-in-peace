package Model.DaoObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Resevations
{
    private int reservationID;
    private LocalDate startDate;
    private LocalDate endDate;
    private IntegerProperty userID;
    private IntegerProperty plotID;

    public Resevations( LocalDate start, LocalDate end, int uID, int ploID)
    {

        this.startDate = start;
        this.endDate = end;
        this.userID = new SimpleIntegerProperty(uID);
        this.plotID = new SimpleIntegerProperty(ploID);

    }
    public Resevations(int resID, LocalDate start, LocalDate end, int uID, int ploID)
    {
        this.reservationID = resID;
        this.startDate = start;
        this.endDate = end;
        this.userID = new SimpleIntegerProperty(uID);
        this.plotID = new SimpleIntegerProperty(ploID);

    }

    public Resevations()
    {

    }

    public int getReservationID()
    {
        return reservationID;
    }

    public void setReservationID(int reservationID)
    {
        this.reservationID = reservationID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getUserID() {
        return userID.get();
    }

    public IntegerProperty userIDProperty() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID.set(userID);
    }

    public int getPlotID() {
        return plotID.get();
    }

    public IntegerProperty plotIDProperty() {
        return plotID;
    }

    public void setPlotID(int plotID) {
        this.plotID.set(plotID);
    }

}

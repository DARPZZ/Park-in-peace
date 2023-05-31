package Model.DaoObject;

import javafx.beans.property.*;

import java.util.Date;

public class PlotOwner
{
    private IntegerProperty plotID;
    private StringProperty location;
    private IntegerProperty zipCode;
    private IntegerProperty userID;
    private ObjectProperty startDate;
    private ObjectProperty slutDate;

    public PlotOwner(int plotID, String location, int zipCode, int userID, Date startDate, Date slutDate)
    {
        this.plotID = new SimpleIntegerProperty(plotID);
        this.location = new SimpleStringProperty(location);
        this.zipCode = new SimpleIntegerProperty(zipCode);
        this.userID = new SimpleIntegerProperty(userID);
        this.startDate = new SimpleObjectProperty(startDate) ;
        this.slutDate = new SimpleObjectProperty(slutDate);
    }

    public int getPlotID()
    {
        return plotID.get();
    }

    public IntegerProperty plotIDProperty()
    {
        return plotID;
    }

    public void setPlotID(int plotID)
    {
        this.plotID.set(plotID);
    }

    public String getLocation()
    {
        return location.get();
    }

    public StringProperty locationProperty()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location.set(location);
    }

    public int getZipCode()
    {
        return zipCode.get();
    }

    public IntegerProperty zipCodeProperty()
    {
        return zipCode;
    }

    public void setZipCode(int zipCode)
    {
        this.zipCode.set(zipCode);
    }

    public int getUserID()
    {
        return userID.get();
    }

    public IntegerProperty userIDProperty()
    {
        return userID;
    }

    public void setUserID(int userID)
    {
        this.userID.set(userID);
    }

    public Object getStartDate()
    {
        return startDate.get();
    }

    public ObjectProperty startDateProperty()
    {
        return startDate;
    }

    public void setStartDate(Object startDate)
    {
        this.startDate.set(startDate);
    }

    public Object getSlutDate()
    {
        return slutDate.get();
    }

    public ObjectProperty slutDateProperty()
    {
        return slutDate;
    }

    public void setSlutDate(Object slutDate)
    {
        this.slutDate.set(slutDate);
    }
}
package Model.DaoObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class PlotOwner
{
    private IntegerProperty plotID;
    private StringProperty location;
    private IntegerProperty zipCode;
    private int userID;
    private Date startDate;
    private Date slutDate;

    public PlotOwner(int plotID, String location, int zipCode, int userID, Date startDate, Date slutDate)
    {
        this.plotID = new SimpleIntegerProperty(plotID);
        this.location = new SimpleStringProperty(location);
        this.zipCode = new SimpleIntegerProperty(zipCode);
        this.userID = userID;
        this.startDate = startDate;
        this.slutDate = slutDate;
    }

    public int getUserID()
    {
        return userID;
    }

    public void setUserID(int userID)
    {
        this.userID = userID;
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

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getSlutDate()
    {
        return slutDate;
    }

    public void setSlutDate(Date slutDate)
    {
        this.slutDate = slutDate;
    }
}

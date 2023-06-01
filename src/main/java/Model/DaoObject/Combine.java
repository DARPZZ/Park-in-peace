package Model.DaoObject;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Combine
{
    private IntegerProperty userID;
    private IntegerProperty plotID;
    private IntegerProperty resevationsID;
    private StringProperty location;
    private String description;
    private String plotSize;
    private IntegerProperty zipCode;
    private String image;
    private boolean toilet;
    private boolean el;
    private boolean Water;
    private ObjectProperty startDate;
    private ObjectProperty endDate;
    private String seasonName;
    private float lowSeasonPrice;
    private float midSeasonPrice;
    private float highSeasonPrice;


    public Combine(int userID, int resevationsID, String location, int zipCode, LocalDate startDate, LocalDate endDate)
    {
        this.userID = new SimpleIntegerProperty(userID) ;
        this.resevationsID = new SimpleIntegerProperty(resevationsID) ;
        this.location = new SimpleStringProperty(location) ;
        this.zipCode = new SimpleIntegerProperty(zipCode) ;
        this.startDate = new SimpleObjectProperty(startDate) ;
        this.endDate = new SimpleObjectProperty(endDate)  ;
    }

    public Combine(int userID,int plotID, int resevationsID, String location, String description, String plotSize, int zipCode, String image, boolean toilet, boolean el, boolean water, LocalDate startDate, LocalDate endDate, String seasonName, float lowSeasonPrice, float midSeasonPrice, float highSeasonPrice)
    {
        this.userID = new SimpleIntegerProperty(userID) ;
        this.plotID = new SimpleIntegerProperty(plotID) ;
        this.resevationsID = new SimpleIntegerProperty(resevationsID) ;
        this.location = new SimpleStringProperty(location);
        this.description = description;
        this.plotSize = plotSize;
        this.zipCode = new SimpleIntegerProperty(zipCode) ;
        this.image = image;
        this.toilet = toilet;
        this.el = el;
        this.Water = water;
        this.startDate = new SimpleObjectProperty(startDate) ;
        this.endDate = new SimpleObjectProperty(endDate)  ;
        this.seasonName = seasonName;
        this.lowSeasonPrice = lowSeasonPrice;
        this.midSeasonPrice = midSeasonPrice;
        this.highSeasonPrice = highSeasonPrice;
    }

    public Combine()
    {

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

    public int getResevationsID()
    {
        return resevationsID.get();
    }

    public IntegerProperty resevationsIDProperty()
    {
        return resevationsID;
    }

    public void setResevationsID(int resevationsID)
    {
        this.resevationsID.set(resevationsID);
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getPlotSize()
    {
        return plotSize;
    }

    public void setPlotSize(String plotSize)
    {
        this.plotSize = plotSize;
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

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public boolean isToilet()
    {
        return toilet;
    }

    public void setToilet(boolean toilet)
    {
        this.toilet = toilet;
    }

    public boolean isEl()
    {
        return el;
    }

    public void setEl(boolean el)
    {
        this.el = el;
    }

    public boolean isWater()
    {
        return Water;
    }

    public void setWater(boolean water)
    {
        Water = water;
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

    public Object getEndDate()
    {
        return endDate.get();
    }

    public ObjectProperty endDateProperty()
    {
        return endDate;
    }

    public void setEndDate(Object endDate)
    {
        this.endDate.set(endDate);
    }

    public String getSeasonName()
    {
        return seasonName;
    }

    public void setSeasonName(String seasonName)
    {
        this.seasonName = seasonName;
    }

    public float getLowSeasonPrice()
    {
        return lowSeasonPrice;
    }

    public void setLowSeasonPrice(float lowSeasonPrice)
    {
        this.lowSeasonPrice = lowSeasonPrice;
    }

    public float getMidSeasonPrice()
    {
        return midSeasonPrice;
    }

    public void setMidSeasonPrice(float midSeasonPrice)
    {
        this.midSeasonPrice = midSeasonPrice;
    }

    public float getHighSeasonPrice()
    {
        return highSeasonPrice;
    }

    public void setHighSeasonPrice(float highSeasonPrice)
    {
        this.highSeasonPrice = highSeasonPrice;
    }
}

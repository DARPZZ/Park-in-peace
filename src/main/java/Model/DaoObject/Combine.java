package Model.DaoObject;

import java.util.Date;

public class Combine
{
    private int userID;
    private int plotID;
    private String location;
    private String description;
    private String plotSize;
    private int zipCode;
    private String image;
    private boolean toilet;
    private boolean el;
    private boolean Water;

    private Date startDate;
    private Date endDate;

    private String seasonName;
    private float lowSeasonPrice;
    private float midSeasonPrice;
    private float highSeasonPrice;


    public Combine(int userID,String location, int zipCode, Date startDate, Date endDate)
    {
        this.userID = userID;
        this.location = location;
        this.zipCode = zipCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Combine(int userID,int plotID, String location, String description, String plotSize, int zipCode, String image, boolean toilet, boolean el, boolean water, Date startDate, Date endDate, String seasonName, float lowSeasonPrice, float midSeasonPrice, float highSeasonPrice)
    {
        this.userID = userID;
        this.plotID = plotID;
        this.location = location;
        this.description = description;
        this.plotSize = plotSize;
        this.zipCode = zipCode;
        this.image = image;
        this.toilet = toilet;
        this.el = el;
        this.Water = water;
        this.startDate = startDate;
        this.endDate = endDate;
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
        return userID;
    }

    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    public int getPlotID()
    {
        return plotID;
    }

    public void setPlotID(int plotID)
    {
        this.plotID = plotID;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
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
        return zipCode;
    }

    public void setZipCode(int zipCode)
    {
        this.zipCode = zipCode;
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

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
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

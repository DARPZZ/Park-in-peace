package Model.DaoObjects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Plot
{
    private IntegerProperty plotID;
    private StringProperty location;
    private StringProperty description;
    private StringProperty plotSize;
    private IntegerProperty zipCode;

    public Plot(IntegerProperty plotID, StringProperty location, StringProperty description, StringProperty plotSize, IntegerProperty zipCode)
    {
        this.plotID = plotID;
        this.location = location;
        this.description = description;
        this.plotSize = plotSize;
        this.zipCode = zipCode;
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

    public String getDescription()
    {
        return description.get();
    }

    public StringProperty descriptionProperty()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description.set(description);
    }

    public String getPlotSize()
    {
        return plotSize.get();
    }

    public StringProperty plotSizeProperty()
    {
        return plotSize;
    }

    public void setPlotSize(String plotSize)
    {
        this.plotSize.set(plotSize);
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
}

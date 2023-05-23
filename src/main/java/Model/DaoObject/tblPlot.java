package Model.DaoObject;

public class tblPlot
{
    private int plotID;
    private String location;
    private String description;
    private String image;
    private int plotSizeID;
    private int zipCode;

    public tblPlot(int plotID, String location, String description, String image, int plotSizeID, int zipCode)
    {
        this.plotID = plotID;
        this.location = location;
        this.description = description;
        this.image = image;
        this.plotSizeID = plotSizeID;
        this.zipCode = zipCode;
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

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public int getPlotSizeID()
    {
        return plotSizeID;
    }

    public void setPlotSizeID(int plotSizeID)
    {
        this.plotSizeID = plotSizeID;
    }

    public int getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(int zipCode)
    {
        this.zipCode = zipCode;
    }
}

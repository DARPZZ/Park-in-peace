package Model.DaoObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

public class Plot
{
    private IntegerProperty plotID;
    private StringProperty location;
    private StringProperty description;
    private StringProperty plotSize;
    private IntegerProperty zipCode;

    private IntegerProperty lowPrice;
    private IntegerProperty midPrice;
    private IntegerProperty highPrice;

    private boolean toilet;
    private boolean water;
    private boolean electric;

    public Plot(String location, String description, String plotSize, int zipCode, int low,int mid, int high, boolean toilet, boolean water, boolean electric)
    {
        //this.plotID = new SimpleIntegerProperty(plotID) ;
        this.location =new SimpleStringProperty( location);
        this.description =new SimpleStringProperty( description);
        this.plotSize =new SimpleStringProperty( plotSize);
        this.zipCode =new SimpleIntegerProperty( zipCode);
        this.lowPrice = new SimpleIntegerProperty(low);
        this.midPrice = new SimpleIntegerProperty(mid);
        this.highPrice =new SimpleIntegerProperty(high);
        this.toilet = toilet;
        this.water  = water;
        this.electric = electric;
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

    public int getLowPrice() {
        return lowPrice.get();
    }

    public IntegerProperty lowPriceProperty() {
        return lowPrice;
    }

    public void setLowPrice(int lowPrice) {
        this.lowPrice.set(lowPrice);
    }

    public int getMidPrice() {
        return midPrice.get();
    }

    public IntegerProperty midPriceProperty() {
        return midPrice;
    }

    public void setMidPrice(int midPrice) {
        this.midPrice.set(midPrice);
    }

    public int getHighPrice() {
        return highPrice.get();
    }

    public IntegerProperty highPriceProperty() {
        return highPrice;
    }

    public void setHighPrice(int highPrice) {
        this.highPrice.set(highPrice);
    }

    public boolean isToilet() {
        return toilet;
    }

    public void setToilet(boolean toilet) {
        this.toilet = toilet;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isElectric() {
        return electric;
    }

    public void setElectric(boolean electric) {
        this.electric = electric;
    }
}

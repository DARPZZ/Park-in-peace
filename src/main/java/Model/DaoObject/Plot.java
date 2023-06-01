package Model.DaoObject;

import javafx.beans.property.*;
import javafx.scene.control.TextField;

public class Plot {
    private IntegerProperty userID;
    private IntegerProperty plotID;
    private StringProperty location;
    private StringProperty description;
    private String imagePath;
    private StringProperty plotSize;
    private IntegerProperty zipCode;

    private FloatProperty lowPrice;
    private FloatProperty midPrice;
    private FloatProperty highPrice;

    private BooleanProperty toilet;
    private BooleanProperty water;
    private BooleanProperty electric;

    public Plot(int userID, int plotID, String location, String description, String img, String plotSize, int zipCode, boolean toilet, boolean water, boolean electric, float low, float mid, float high) {
        this.userID = new SimpleIntegerProperty(userID);
        this.plotID = new SimpleIntegerProperty(plotID);
        this.location = new SimpleStringProperty(location);
        this.description = new SimpleStringProperty(description);
        this.imagePath = img;
        this.plotSize = new SimpleStringProperty(plotSize);
        this.zipCode = new SimpleIntegerProperty(zipCode);
        this.lowPrice = new SimpleFloatProperty(low);
        this.midPrice = new SimpleFloatProperty(mid);
        this.highPrice = new SimpleFloatProperty(high);
        this.toilet = new SimpleBooleanProperty(toilet);
        this.water = new SimpleBooleanProperty(water);
        this.electric = new SimpleBooleanProperty(electric);
    }

    public Plot(int userID, String location, String description, String img, String plotSize, int zipCode, boolean toilet, boolean water, boolean electric, float low, float mid, float high) {
        this.userID = new SimpleIntegerProperty(userID);
        this.plotID = new SimpleIntegerProperty(0);
        this.location = new SimpleStringProperty(location);
        this.description = new SimpleStringProperty(description);
        this.imagePath = img;
        this.plotSize = new SimpleStringProperty(plotSize);
        this.zipCode = new SimpleIntegerProperty(zipCode);
        this.lowPrice = new SimpleFloatProperty(low);
        this.midPrice = new SimpleFloatProperty(mid);
        this.highPrice = new SimpleFloatProperty(high);
        this.toilet = new SimpleBooleanProperty(toilet);
        this.water = new SimpleBooleanProperty(water);
        this.electric = new SimpleBooleanProperty(toilet);
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

    public String getLocation() {
        return location.get();
    }

    public StringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPlotSize() {
        return plotSize.get();
    }

    public StringProperty plotSizeProperty() {
        return plotSize;
    }

    public void setPlotSize(String plotSize) {
        this.plotSize.set(plotSize);
    }

    public int getZipCode() {
        return zipCode.get();
    }

    public IntegerProperty zipCodeProperty() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode.set(zipCode);
    }

    public float getLowPrice() {
        return lowPrice.get();
    }

    public FloatProperty lowPriceProperty() {
        return lowPrice;
    }

    public void setLowPrice(float lowPrice) {
        this.lowPrice.set(lowPrice);
    }

    public float getMidPrice() {
        return midPrice.get();
    }

    public FloatProperty midPriceProperty() {
        return midPrice;
    }

    public void setMidPrice(float midPrice) {
        this.midPrice.set(midPrice);
    }

    public float getHighPrice() {
        return highPrice.get();
    }

    public FloatProperty highPriceProperty() {
        return highPrice;
    }

    public void setHighPrice(float highPrice) {
        this.highPrice.set(highPrice);
    }

    public boolean isToilet() {
        return toilet.get();
    }

    public BooleanProperty toiletProperty() {
        return toilet;
    }

    public void setToilet(boolean toilet) {
        this.toilet.set(toilet);
    }

    public boolean isWater() {
        return water.get();
    }

    public BooleanProperty waterProperty() {
        return water;
    }

    public void setWater(boolean water) {
        this.water.set(water);
    }

    public boolean isElectric() {
        return electric.get();
    }

    public BooleanProperty electricProperty() {
        return electric;
    }

    public void setElectric(boolean electric) {
        this.electric.set(electric);
    }
}
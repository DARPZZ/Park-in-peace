package Model.DaoObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ParkingService
{
    private IntegerProperty parkingServiceId;
    private IntegerProperty plotId;
    private IntegerProperty serviceId;

    public ParkingService(int parkingServiceId, int plotId, int serviceId)
    {
        this.parkingServiceId = new SimpleIntegerProperty(parkingServiceId) ;
        this.plotId =new SimpleIntegerProperty(plotId) ;
        this.serviceId =new SimpleIntegerProperty(serviceId) ;
    }

    public int getParkingServiceId()
    {
        return parkingServiceId.get();
    }

    public IntegerProperty parkingServiceIdProperty()
    {
        return parkingServiceId;
    }

    public void setParkingServiceId(int parkingServiceId)
    {
        this.parkingServiceId.set(parkingServiceId);
    }

    public int getPlotId()
    {
        return plotId.get();
    }

    public IntegerProperty plotIdProperty()
    {
        return plotId;
    }

    public void setPlotId(int plotId)
    {
        this.plotId.set(plotId);
    }

    public int getServiceId()
    {
        return serviceId.get();
    }

    public IntegerProperty serviceIdProperty()
    {
        return serviceId;
    }

    public void setServiceId(int serviceId)
    {
        this.serviceId.set(serviceId);
    }
}

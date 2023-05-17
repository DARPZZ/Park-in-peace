package Model.DaoObjects;

import javafx.beans.property.IntegerProperty;

public class ParkingService
{
    private IntegerProperty parkingServiceId;
    private IntegerProperty plotId;
    private IntegerProperty serviceId;

    public ParkingService(IntegerProperty parkingServiceId, IntegerProperty plotId, IntegerProperty serviceId)
    {
        this.parkingServiceId = parkingServiceId;
        this.plotId = plotId;
        this.serviceId = serviceId;
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

package Model.DaoObject;

public class tblService
{
    private int servideID;
    private boolean toilet;
    private boolean el;
    private boolean Water;

    public tblService(int servideID, boolean toilet, boolean el, boolean water)
    {
        this.servideID = servideID;
        this.toilet = toilet;
        this.el = el;
        Water = water;
    }

    public int getServideID()
    {
        return servideID;
    }

    public void setServideID(int servideID)
    {
        this.servideID = servideID;
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
}

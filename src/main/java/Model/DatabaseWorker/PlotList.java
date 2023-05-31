package Model.DatabaseWorker;

import Model.DaoObject.Plot;
import Model.Implements.DaoPlot;

import java.util.ArrayList;

public final class PlotList
{
    private static DaoPlot plotWorker = new DaoPlot();
    private static PlotList INSTANCE;
    private static ArrayList<Plot> plotList = new ArrayList<>();

    private PlotList()
    {}

    public static PlotList getSingleton()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new PlotList();
        }
        return INSTANCE;
    }
    public ArrayList<Plot> getList()
    {
        return plotList;
    }
    public void setList()
    {

        for (Plot p:plotWorker.GetAll())
        {
            plotList.add(p);
        }
        System.out.println("");
    }
    public void addToList(Plot plot)
    {plotList.add(plot);}

    public void CreatePlot(Plot tblPlot)
    {   plotWorker.Create(tblPlot);
        plotList.add(tblPlot);
    }

    public void UpdatePlot(Plot tblPlot, String fieldname, String value)
    {
        plotWorker.Update(tblPlot, fieldname, value);
    }
    public void Delete(Plot tblPlot, int ID)
    {plotWorker.Delete(tblPlot, ID);}
    public ArrayList<String> getAllSizeTypes()
    {
        return plotWorker.getAllSizeTypes();
    }
}

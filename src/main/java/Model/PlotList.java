package Model;

import Model.DaoObject.Plot;

import java.util.ArrayList;
import java.util.HashMap;

public final class PlotList
{
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
}

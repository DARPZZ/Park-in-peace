package Model;

import java.util.HashMap;

public final class PlotMap
{
    private static PlotMap INSTANCE;
    private static HashMap plotMap = new HashMap<>(); // Key plotID, value: plot object

    private PlotMap()
    {}

    public static PlotMap getMap ()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new PlotMap();
        }
        return INSTANCE;
    }
}

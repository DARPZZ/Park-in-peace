package Controller;

import Model.DaoObject.Plot;
import Model.DaoObject.User;
import Controller.DatabaseWorker.PlotList;
import Service.UserSubscriber;

import java.io.File;
import java.util.ArrayList;

public class PlotController implements UserSubscriber
{
    private File defaultDir=  new File("Billeder").getAbsoluteFile();
    private String chosenFileName;
    private User activeUser;
    private ArrayList<Plot> plotArrayList = new ArrayList<>();

    public ArrayList initPlotPage()
    {

        for (Plot p: PlotList.getSingleton().getList())
        {
            if (p.getUserID() == activeUser.getUserId())
            {
                plotArrayList.add(p);
            }
        }
        return plotArrayList;
    }

    @Override
    public void onUserReceived(User user)
    {
        activeUser = user;
    }

    public void createNewPlot(String address, String description, String filepath, String size, int zip, boolean toilet, boolean water, boolean electricity, float lowPrice, float medPrice, float highPrice)
    {
        Plot plot = new Plot(
                activeUser.getUserId(),
                address,
                description,
                filepath,
                size,
                zip,
                toilet,
                water,
                electricity,
                lowPrice,
                medPrice,
                highPrice
        );
        PlotList.getSingleton().CreatePlot(plot);
        addPlotList(plot);
    }
    public void addPlotList(Plot plot)
    {
        plotArrayList.add(plot);
    }
    public ArrayList<Plot> getPlotArrayList() {
        return plotArrayList;
    }

    public File getDefaultDir() {
        return defaultDir;
    }

    public String getChosenFileName() {
        return chosenFileName;
    }

    public void setChosenFileName(String chosenFileName) {
        this.chosenFileName = chosenFileName;
    }
}
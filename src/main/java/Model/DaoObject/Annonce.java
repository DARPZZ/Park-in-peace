package Model.DaoObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Annonce
{
    private StringProperty name;
    private Plot activePlot;
    private User owner;

    public Annonce(String name, Plot activePlot, User owner)
    {
        this.name = new SimpleStringProperty(name);
        this.activePlot = activePlot;
        this.owner = owner;
    }
}



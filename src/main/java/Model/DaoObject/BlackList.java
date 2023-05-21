package Model.DaoObject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BlackList
{
    private IntegerProperty blackListID;
    private BooleanProperty BlackListStatus;

    public BlackList(int blackListID, boolean blackListStatus)
    {
        this.blackListID = new SimpleIntegerProperty(blackListID);
        BlackListStatus = new SimpleBooleanProperty(blackListStatus);
    }

    public int getBlackListID() {
        return blackListID.get();
    }

    public IntegerProperty blackListIDProperty() {
        return blackListID;
    }

    public void setBlackListID(int blackListID) {
        this.blackListID.set(blackListID);
    }

    public boolean isBlackListStatus() {
        return BlackListStatus.get();
    }

    public BooleanProperty blackListStatusProperty() {
        return BlackListStatus;
    }

    public void setBlackListStatus(boolean blackListStatus) {
        this.BlackListStatus.set(blackListStatus);
    }
}
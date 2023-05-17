package Model.DaoObjects;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

public class BlackList
{
    private IntegerProperty blackListID;
    private BooleanProperty BlackListStatus;

    public BlackList(IntegerProperty blackListID, BooleanProperty blackListStatus)
    {
        this.blackListID = blackListID;
        BlackListStatus = blackListStatus;
    }

    public int getBlackListID()
    {
        return blackListID.get();
    }

    public IntegerProperty blackListIDProperty()
    {
        return blackListID;
    }

    public void setBlackListID(int blackListID)
    {
        this.blackListID.set(blackListID);
    }

    public boolean isBlackListStatus()
    {
        return BlackListStatus.get();
    }

    public BooleanProperty blackListStatusProperty()
    {
        return BlackListStatus;
    }

    public void setBlackListStatus(boolean blackListStatus)
    {
        this.BlackListStatus.set(blackListStatus);
    }
}
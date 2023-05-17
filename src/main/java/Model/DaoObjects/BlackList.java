package Model.DaoObjects;

public class BlackList
{
    private int blackListID;



    public void setBlackListStatus(boolean blackListStatus)
    {
        BlackListStatus = blackListStatus;
    }

    private boolean BlackListStatus;
    public BlackList(int blackListID, boolean blackListStatus)
    {
        this.blackListID = blackListID;
        BlackListStatus = blackListStatus;
    }

    public int getBlackListID()
    {
        return blackListID;
    }

    public void setBlackListID(int blackListID)
    {
        this.blackListID = blackListID;
    }

    public boolean isBlackListStatus()
    {
        return BlackListStatus;
    }

}

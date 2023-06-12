package Controller.DatabaseWorker;

import Model.DaoObject.User;
import Model.Implements.DaoUser;

import java.util.ArrayList;

public final class BlackList
{
    private static DaoUser userWorker = new DaoUser();
    private static BlackList INSTANCE;
    private static ArrayList<Integer> blackList = new ArrayList<>();

    private BlackList()
    {}

    public static BlackList getSingleton()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new BlackList();
        }
        return INSTANCE;
    }

    public void setBlackList(User user)
    {
        for (Integer i: userWorker.getBlackListedBy(user))
        {
        blackList.add(i);
        }
    }
    public void addBlackList(int i)
    {
        blackList.add(i);
    }

    public ArrayList<Integer> getBlackList()
    {return blackList;}

    public void CreateUser(User user)
    {userWorker.Create(user);}
    public void UpdateUser( User user, String fieldname, String value)
    {userWorker.Update(user, fieldname, value);}
    public void DeleteUser(User user, int ID)
    {userWorker.Delete(user, ID);}
    public void removeBlackList(User user, int blacklist)
    {userWorker.removeBlackList(user, blacklist);}
    public void addBlackList(User user, int blacklist)
    {userWorker.addBlackList(user, blacklist);}
    public User checkLogin(String Username, String password)
    {
        return userWorker.checkLogin(Username,password);
    }
}

package Model.DaoObject;

import Model.Implements.DaoInterface;
import Model.Implements.DaoUser;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class tblUser
{
    private int userID;
    private String name;
    private String phoneNumber;
    private String password;
    private String address;
    private int acounterNumber;
    private String email;
    private int zipCode;
    private int blackListID;

    public tblUser(int userId, String name, String phoneNumber, String password, String address, int acounterNumber, String email, int zipCode, int blackListId)
    {
        this.userID = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.acounterNumber = acounterNumber;
        this.email = email;
        this.zipCode = zipCode;
        this.blackListID = blackListId;
    }
    //region getter setter
    public int getUserId()
    {
        return userID;
    }


    public void setUserId(int userId)
    {
        this.userID = userId;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public int getAcounterNumber()
    {
        return acounterNumber;
    }
    public void setAcounterNumber(int acounterNumber)
    {
        this.acounterNumber = acounterNumber;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getZipCode()
    {
        return zipCode;
    }
    public void setZipCode(int zipCode)
    {
        this.zipCode = zipCode;
    }
    //region end
}

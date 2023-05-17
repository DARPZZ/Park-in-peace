package Model.DaoObjects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class User
{
    private IntegerProperty userId;
    private StringProperty name;
    private StringProperty phoneNumber;
    private StringProperty Password;
    private StringProperty address;
    private IntegerProperty acounterNumber;
    private StringProperty email;
    private IntegerProperty zipCode;
    private IntegerProperty blackListId;

    public User(IntegerProperty userId, StringProperty name, StringProperty phoneNumber, StringProperty password, StringProperty address, IntegerProperty acounterNumber, StringProperty email, IntegerProperty zipCode, IntegerProperty blackListId)
    {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        Password = password;
        this.address = address;
        this.acounterNumber = acounterNumber;
        this.email = email;
        this.zipCode = zipCode;
        this.blackListId = blackListId;
    }

    public int getUserId()
    {
        return userId.get();
    }

    public IntegerProperty userIdProperty()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId.set(userId);
    }

    public String getName()
    {
        return name.get();
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public String getPhoneNumber()
    {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber.set(phoneNumber);
    }

    public String getPassword()
    {
        return Password.get();
    }

    public StringProperty passwordProperty()
    {
        return Password;
    }

    public void setPassword(String password)
    {
        this.Password.set(password);
    }

    public String getAddress()
    {
        return address.get();
    }

    public StringProperty addressProperty()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address.set(address);
    }

    public int getAcounterNumber()
    {
        return acounterNumber.get();
    }

    public IntegerProperty acounterNumberProperty()
    {
        return acounterNumber;
    }

    public void setAcounterNumber(int acounterNumber)
    {
        this.acounterNumber.set(acounterNumber);
    }

    public String getEmail()
    {
        return email.get();
    }

    public StringProperty emailProperty()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email.set(email);
    }

    public int getZipCode()
    {
        return zipCode.get();
    }

    public IntegerProperty zipCodeProperty()
    {
        return zipCode;
    }

    public void setZipCode(int zipCode)
    {
        this.zipCode.set(zipCode);
    }

    public int getBlackListId()
    {
        return blackListId.get();
    }

    public IntegerProperty blackListIdProperty()
    {
        return blackListId;
    }

    public void setBlackListId(int blackListId)
    {
        this.blackListId.set(blackListId);
    }
}

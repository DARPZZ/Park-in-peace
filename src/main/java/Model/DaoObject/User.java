package Model.DaoObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
    public User() {

    }

    public User(String name, String phoneNumber, String password, String address, int acounterNumber, String email, int zipCode )
    {
        this.name = new SimpleStringProperty(name);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        Password = new SimpleStringProperty(password);
        this.address = new SimpleStringProperty(address);
        this.acounterNumber = new SimpleIntegerProperty(acounterNumber);
        this.email = new SimpleStringProperty(email);
        this.zipCode = new SimpleIntegerProperty(zipCode);
    }
    public User(int userId, String name, String phoneNumber, String password, String address, int acounterNumber, String email, int zipCode)
    {
        this.userId = new SimpleIntegerProperty(userId);
        this.name = new SimpleStringProperty(name);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        Password = new SimpleStringProperty(password);
        this.address = new SimpleStringProperty(address);
        this.acounterNumber = new SimpleIntegerProperty(acounterNumber);
        this.email = new SimpleStringProperty(email);
        this.zipCode = new SimpleIntegerProperty(zipCode);

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


}

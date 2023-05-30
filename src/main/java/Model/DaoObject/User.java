package Model.DaoObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

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
    private ArrayList<Integer> blackList = new ArrayList<>();
    public User() {

    }

    public User(int ID,String name, String phoneNumber, String password, String address, int acounterNumber, String email, int zipCode, ArrayList<Integer> blackList)
    {
        this.userId = new SimpleIntegerProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        Password = new SimpleStringProperty(password);
        this.address = new SimpleStringProperty(address);
        this.acounterNumber = new SimpleIntegerProperty(acounterNumber);
        this.email = new SimpleStringProperty(email);
        this.zipCode = new SimpleIntegerProperty(zipCode);
        this.blackList = blackList;
    }
    public User(String name, String phoneNumber, String password, String address, String email, int zipCode)
    {
        this.userId = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty(name);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        Password = new SimpleStringProperty(password);
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
        this.zipCode = new SimpleIntegerProperty(zipCode);
        this.acounterNumber = new SimpleIntegerProperty(0);
    }
    public User(int ID,String name, String phoneNumber, String password, String address, String email, int zipCode)
    {
        this.acounterNumber = new SimpleIntegerProperty(0);
        this.userId = new SimpleIntegerProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        Password = new SimpleStringProperty(password);
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
        this.zipCode = new SimpleIntegerProperty(zipCode);
        this.blackList = blackList;
    }
    public User(int id)
    {this.userId = new SimpleIntegerProperty(id);}

    public User(int userid, String username, String phonenr, String password, String adress, int acNR, String email, int zip) {
        this.acounterNumber = new SimpleIntegerProperty(acNR);
        this.userId = new SimpleIntegerProperty(userid);
        this.name = new SimpleStringProperty(username);
        this.phoneNumber = new SimpleStringProperty(phonenr);
        Password = new SimpleStringProperty(password);
        this.address = new SimpleStringProperty(adress);
        this.email = new SimpleStringProperty(email);
        this.zipCode = new SimpleIntegerProperty(zip);
        this.blackList = blackList;
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
        this.userId.setValue(userId);
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


    public ArrayList<Integer> getBlackList() {
        return blackList;
    }

    public void setBlackList(ArrayList<Integer> blackList) {
        this.blackList = blackList;
    }
}

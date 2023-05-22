package Model.DaoObject;

public class tblZipCode
{
    private int zipCode;
    private int city;

    public tblZipCode(int zipCode, int city)
    {
        this.zipCode = zipCode;
        this.city = city;
    }

    public tblZipCode(int zipCode)
    {
        this.zipCode = zipCode;
    }

    public int getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(int zipCode)
    {
        this.zipCode = zipCode;
    }

    public int getCity()
    {
        return city;
    }

    public void setCity(int city)
    {
        this.city = city;
    }
}

package Model.DaoObjects;

public class Season
{
    private int seasonId;
    private String seasonName;
    private int lowSeasonPrice;
    private int highSeasonPrice;
    private String mediumSeasonPrice;

    public Season(int seasonId, String seasonName, int lowSeasonPrice, int highSeasonPrice, String mediumSeasonPrice)
    {
        this.seasonId = seasonId;
        this.seasonName = seasonName;
        this.lowSeasonPrice = lowSeasonPrice;
        this.highSeasonPrice = highSeasonPrice;
        this.mediumSeasonPrice = mediumSeasonPrice;
    }

    public int getSeasonId()
    {
        return seasonId;
    }

    public void setSeasonId(int seasonId)
    {
        this.seasonId = seasonId;
    }

    public String getSeasonName()
    {
        return seasonName;
    }

    public void setSeasonName(String seasonName)
    {
        this.seasonName = seasonName;
    }

    public int getLowSeasonPrice()
    {
        return lowSeasonPrice;
    }

    public void setLowSeasonPrice(int lowSeasonPrice)
    {
        this.lowSeasonPrice = lowSeasonPrice;
    }

    public int getHighSeasonPrice()
    {
        return highSeasonPrice;
    }

    public void setHighSeasonPrice(int highSeasonPrice)
    {
        this.highSeasonPrice = highSeasonPrice;
    }

    public String getMediumSeasonPrice()
    {
        return mediumSeasonPrice;
    }

    public void setMediumSeasonPrice(String mediumSeasonPrice)
    {
        this.mediumSeasonPrice = mediumSeasonPrice;
    }
}

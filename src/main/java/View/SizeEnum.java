package View;

public enum SizeEnum
{
    VERYSMALL("verysmall", "Meget lille"),
    SMALL("small", "Lille"),
    MID("mid", "Mellem stor"),
    LARGE("large", "Stor"),
    VERYLARGE("verylarge", "Meget stor");

    private final String value;
    private final String translation;

    SizeEnum(String value, String translation)
    {
        this.value = value;
        this.translation = translation;
    }

    public static SizeEnum fromString(String value)
    {
        for (SizeEnum size : SizeEnum.values())
        {
            if (size.value.equalsIgnoreCase(value))
            {
                return size;
            }
        }
        return null;
    }

    public String getValue()
    {
        return value;
    }

    public String getTranslation()
    {
        return translation;
    }
}

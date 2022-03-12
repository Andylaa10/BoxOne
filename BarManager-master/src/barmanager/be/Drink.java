package barmanager.be;

public class Drink
{
    private String name;
    private int numberOfCentiliters;

    public Drink(String name, int numberOfCentiliters)
    {
        this.name = name;
        this.numberOfCentiliters = numberOfCentiliters;
    }

    public String getName()
    {
        return name;
    }

    public int getNumberOfCentiliters()
    {
        return numberOfCentiliters;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setNumberOfCentiliters(int numberOfCentiliters)
    {
        this.numberOfCentiliters = numberOfCentiliters;
    }

    @Override
    public String toString()
    {
        return name + " (" + numberOfCentiliters + " cl)";
    }
}

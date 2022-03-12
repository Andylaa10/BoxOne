package barmanager.bll;

public class BarFactory {

    public static final String EZ_DRINKS = "Easy drinks";
    public static final String GG_DRINKS = "Hardcore drinks";


    public static IDrinkScale getDrinkScale(String proofDescription){

        switch (proofDescription){

            case EZ_DRINKS:
                return new EZDrinks();

            case GG_DRINKS:
                return new GGDrinks();
        }
        return null;
    }
}

package barmanager.bll;

import barmanager.be.Drink;

public class GGDrinks implements IDrinkScale{
    @Override
    public String[] getProducts() {
        return new String[] {"Cider", "Pina colada", "Vodka"};
    }

    @Override
    public Drink createDrink(String proofDescription) {
        switch (proofDescription)
        {
            case MILD_PROOF:
                return new Drink("Cider", 33);
            case MEDIUM_PROOF:
                return new Drink("Pina colada", 75);
            case STRONG_PROOF:
                return new Drink("Vodka", 2);
        }
        return null;
    }
}

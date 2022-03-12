package barmanager.bll;

import barmanager.be.Drink;

public class EZDrinks implements IDrinkScale {
    @Override
    public String[] getProducts() {
        return new String[] {"Beer", "Wine", "Tequilla shot"};
    }

    @Override
    public Drink createDrink(String proofDescription) {
        switch (proofDescription)
        {
            case MILD_PROOF:
                return new Drink("Beer", 33);
            case MEDIUM_PROOF:
                return new Drink("Wine", 75);
            case STRONG_PROOF:
                return new Drink("Tequilla shot", 2);
        }
        return null;
    }
}


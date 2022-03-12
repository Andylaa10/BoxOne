package barmanager.bll;

import barmanager.be.Drink;

public interface IDrinkScale {

    String MILD_PROOF = "Mild";
    String MEDIUM_PROOF = "Medium";
    String STRONG_PROOF = "Strong";

    String[] getProducts();
    Drink createDrink(String proofDescription);

}

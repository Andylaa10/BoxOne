package barmanager.gui;

import barmanager.be.Drink;
import barmanager.bll.BarFactory;
import barmanager.bll.IDrinkScale;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class BarController implements Initializable {

    private IDrinkScale drinkScale;

    @FXML
    private ChoiceBox<String> cbProof;

    @FXML
    private Label lblProducts;

    @FXML
    private ListView<String> lstOrderedProducts;

    public BarController() {
        drinkScale = BarFactory.getDrinkScale(BarFactory.GG_DRINKS);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prepareProofs();
        prepareProducts();
    }

    @FXML
    private void orderDrink() {
        String proof = cbProof.getValue();
        Drink drink = drinkScale.createDrink(proof);
        lstOrderedProducts.getItems().add(drink.toString());
    }

    private void prepareProofs() {
        cbProof.getItems().addAll(
                IDrinkScale.MILD_PROOF,
                IDrinkScale.MEDIUM_PROOF,
                IDrinkScale.STRONG_PROOF
        );
    }

    private void prepareProducts() {
        lblProducts.setText(String.join(", ", drinkScale.getProducts()));
    }
}


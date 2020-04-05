package gow.gui.price_parameters;

import gow.generator.configuration.MarketPrice;
import gow.generator.configuration.weapon.WeaponType;
import gow.gui.ExceptionWithDialog;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class WeaponConfigController implements Initializable {
    public TextField tfieldMarketDefPrice;
    public TextField tfield1hSwordMaxDamage;
    public TextField tfield1hSwordDmgPriceModifier;
    public TextField tfield2hSwordMaxDamage;
    public TextField tfield2hSwordDmgPriceModifier;
    public TextField tfield1hHammerMaxDamage;
    public TextField tfield1hHammerDmgPriceModifier;
    public TextField tfield2hHammerMaxDamage;
    public TextField tfield2hHammerDmgPriceModifier;
    public TextField tfield1hAxeMaxDamage;
    public TextField tfield1hAxeDmgPriceModifier;
    public TextField tfield2hAxeMaxDamage;
    public TextField tfield2hAxeDmgPriceModifier;
    public TextField tfieldDaggerMaxDamage;
    public TextField tfieldDaggerDmgPriceModifier;
    public TextField tfieldBowMaxDamage;
    public TextField tfieldBowDmgPriceModifier;
    public TextField tfieldCrossbowMaxDamage;
    public TextField tfieldCrossbowDmgPriceModifier;
    public Button btnCancel;
    public Button btnSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTextFields();
    }

    private void fillTextFields() {
        tfieldMarketDefPrice.setText(String.valueOf(MarketPrice.getInstance().dmgPoint()));
        tfield1hSwordMaxDamage.setText(String.valueOf(WeaponType.SWORD_1H.getMaxDamage()));
        tfield1hSwordDmgPriceModifier.setText(String.valueOf(WeaponType.SWORD_1H.getDmgPriceModifier()));

        tfield2hSwordMaxDamage.setText(String.valueOf(WeaponType.SWORD_2H.getMaxDamage()));
        tfield2hSwordDmgPriceModifier.setText(String.valueOf(WeaponType.SWORD_2H.getDmgPriceModifier()));

        tfield1hHammerMaxDamage.setText(String.valueOf(WeaponType.HAMMER_1H.getMaxDamage()));
        tfield1hHammerDmgPriceModifier.setText(String.valueOf(WeaponType.HAMMER_1H.getDmgPriceModifier()));

        tfield2hHammerMaxDamage.setText(String.valueOf(WeaponType.HAMMER_2H.getMaxDamage()));
        tfield2hHammerDmgPriceModifier.setText(String.valueOf(WeaponType.HAMMER_2H.getDmgPriceModifier()));

        tfield1hAxeMaxDamage.setText(String.valueOf(WeaponType.AXE_1H.getMaxDamage()));
        tfield1hAxeDmgPriceModifier.setText(String.valueOf(WeaponType.AXE_1H.getDmgPriceModifier()));

        tfield2hAxeMaxDamage.setText(String.valueOf(WeaponType.AXE_2H.getMaxDamage()));
        tfield2hAxeDmgPriceModifier.setText(String.valueOf(WeaponType.AXE_2H.getDmgPriceModifier()));

        tfieldDaggerMaxDamage.setText(String.valueOf(WeaponType.DAGGER.getMaxDamage()));
        tfieldDaggerDmgPriceModifier.setText(String.valueOf(WeaponType.DAGGER.getDmgPriceModifier()));

        tfieldBowMaxDamage.setText(String.valueOf(WeaponType.BOW.getMaxDamage()));
        tfieldBowDmgPriceModifier.setText(String.valueOf(WeaponType.BOW.getDmgPriceModifier()));

        tfieldCrossbowMaxDamage.setText(String.valueOf(WeaponType.CROSSBOW.getMaxDamage()));
        tfieldCrossbowDmgPriceModifier.setText(String.valueOf(WeaponType.CROSSBOW.getDmgPriceModifier()));
    }

    public void onSave(ActionEvent actionEvent) {
        try {
            float newMarketPrice = Float.parseFloat(tfieldMarketDefPrice.getText());
            int new1hSwMaxDmg = Integer.parseInt(tfield1hSwordMaxDamage.getText());
            float new1hSwDmgPriceMod = Float.parseFloat(tfield1hSwordDmgPriceModifier.getText());

            int new2hSwMaxDmg = Integer.parseInt(tfield2hSwordMaxDamage.getText());
            float new2hSwDmgPriceMod = Float.parseFloat(tfield2hSwordDmgPriceModifier.getText());

            int new1hHamMaxDmg = Integer.parseInt(tfield1hHammerMaxDamage.getText());
            float new1hHamDmgPriceMod = Float.parseFloat(tfield1hHammerDmgPriceModifier.getText());

            int new2hHamMaxDmg = Integer.parseInt(tfield2hHammerMaxDamage.getText());
            float new2hHamDmgPriceMod = Float.parseFloat(tfield2hHammerDmgPriceModifier.getText());

            int new1hAxeMaxDmg = Integer.parseInt(tfield1hAxeMaxDamage.getText());
            float new1hAxeDmgPriceMod = Float.parseFloat(tfield1hAxeDmgPriceModifier.getText());

            int new2hAxeMaxDmg = Integer.parseInt(tfield2hAxeMaxDamage.getText());
            float new2hAxeDmgPriceMod = Float.parseFloat(tfield2hAxeDmgPriceModifier.getText());

            int newDaggerMaxDmg = Integer.parseInt(tfieldDaggerMaxDamage.getText());
            float newDaggerDmgPriceMod = Float.parseFloat(tfieldDaggerDmgPriceModifier.getText());

            int newBowMaxDmg = Integer.parseInt(tfieldBowMaxDamage.getText());
            float newBowDmgPriceMod = Float.parseFloat(tfieldBowDmgPriceModifier.getText());

            int newCrossbowMaxDmg = Integer.parseInt(tfieldCrossbowMaxDamage.getText());
            float newCrossbowDmgPriceMod = Float.parseFloat(tfieldCrossbowDmgPriceModifier.getText());

            MarketPrice.getInstance().setDefPrice(newMarketPrice);
            WeaponType.SWORD_1H.setMaxDamage(new1hSwMaxDmg);
            WeaponType.SWORD_1H.setDmgPriceModifier(new1hSwDmgPriceMod);

            WeaponType.SWORD_2H.setMaxDamage(new2hSwMaxDmg);
            WeaponType.SWORD_2H.setDmgPriceModifier(new2hSwDmgPriceMod);

            WeaponType.HAMMER_1H.setMaxDamage(new1hHamMaxDmg);
            WeaponType.HAMMER_1H.setDmgPriceModifier(new1hHamDmgPriceMod);

            WeaponType.HAMMER_2H.setMaxDamage(new2hHamMaxDmg);
            WeaponType.HAMMER_2H.setDmgPriceModifier(new2hHamDmgPriceMod);

            WeaponType.AXE_1H.setMaxDamage(new1hAxeMaxDmg);
            WeaponType.AXE_1H.setDmgPriceModifier(new1hAxeDmgPriceMod);

            WeaponType.AXE_2H.setMaxDamage(new2hAxeMaxDmg);
            WeaponType.AXE_2H.setDmgPriceModifier(new2hAxeDmgPriceMod);

            WeaponType.DAGGER.setMaxDamage(newDaggerMaxDmg);
            WeaponType.DAGGER.setDmgPriceModifier(newDaggerDmgPriceMod);

            WeaponType.BOW.setMaxDamage(newBowMaxDmg);
            WeaponType.BOW.setDmgPriceModifier(newBowDmgPriceMod);

            WeaponType.CROSSBOW.setMaxDamage(newCrossbowMaxDmg);
            WeaponType.CROSSBOW.setDmgPriceModifier(newCrossbowDmgPriceMod);
        } catch (NumberFormatException e) {
            throw new ExceptionWithDialog(e);
        }
        closeWindow();
    }

    public void onCancel(ActionEvent actionEvent) {
        closeWindow();
    }

    private void closeWindow() {
        btnCancel.getScene().getWindow().hide();
    }
}

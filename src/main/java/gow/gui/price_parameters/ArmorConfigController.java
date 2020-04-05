package gow.gui.price_parameters;

import gow.generator.configuration.MarketPrice;
import gow.generator.configuration.armor.ArmorClass;
import gow.generator.configuration.armor.BodyPart;
import gow.gui.ExceptionWithDialog;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ArmorConfigController implements Initializable {
    public TextField tfieldMarketDefPrice;
    public TextField tfieldArmorIntPriceModifier;
    public TextField tfieldArmorIntFullsetMaxarmor;
    public TextField tfieldArmorDexPriceModifier;
    public TextField tfieldArmorDexFullsetMaxarmor;
    public TextField tfieldArmorStrPriceModifier;
    public TextField tfieldArmorStrFullsetMaxarmor;

    List<TextField> tfieldsWithDefPercents = new ArrayList<>();
    public TextField tfieldArmorBPHeadDefencePercent;
    public TextField tfieldArmorBPShouldersDefencePercent;
    public TextField tfieldArmorBPChestDefencePercent;
    public TextField tfieldArmorBPWristDefencePercent;
    public TextField tfieldArmorBPBootsDefencePercent;
    public TextField tfieldArmorBPHandsDefencePercent;
    public TextField tfieldArmorBPLegsDefencePercent;

    public TextField tfieldArmorBPSumDefencePercent;

    public Button btnSave;
    public Button btnCancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateInitialValues();
        gatherFieldsIntoList();
        listenToPercentUpdates();
        recountPercentSum();
    }

    private void gatherFieldsIntoList() {
        tfieldsWithDefPercents.add(tfieldArmorBPHeadDefencePercent);
        tfieldsWithDefPercents.add(tfieldArmorBPShouldersDefencePercent);
        tfieldsWithDefPercents.add(tfieldArmorBPChestDefencePercent);
        tfieldsWithDefPercents.add(tfieldArmorBPWristDefencePercent);
        tfieldsWithDefPercents.add(tfieldArmorBPBootsDefencePercent);
        tfieldsWithDefPercents.add(tfieldArmorBPHandsDefencePercent);
        tfieldsWithDefPercents.add(tfieldArmorBPLegsDefencePercent);
    }

    private void updateInitialValues() {
        tfieldMarketDefPrice.setText(String.valueOf(MarketPrice.getInstance().defPoint()));
        tfieldArmorIntPriceModifier.setText(String.valueOf(ArmorClass.INT.priceModifier()));
        tfieldArmorIntFullsetMaxarmor.setText(String.valueOf(ArmorClass.INT.getFullSetMaxArmor()));
        tfieldArmorDexPriceModifier.setText(String.valueOf(ArmorClass.DEX.priceModifier()));
        tfieldArmorDexFullsetMaxarmor.setText(String.valueOf(ArmorClass.DEX.getFullSetMaxArmor()));
        tfieldArmorStrPriceModifier.setText(String.valueOf(ArmorClass.STR.priceModifier()));
        tfieldArmorStrFullsetMaxarmor.setText(String.valueOf(ArmorClass.STR.getFullSetMaxArmor()));

        tfieldArmorBPHeadDefencePercent.setText(String.valueOf(BodyPart.HEAD.getDefencePercent()));
        tfieldArmorBPShouldersDefencePercent.setText(String.valueOf(BodyPart.SHOULDERS.getDefencePercent()));
        tfieldArmorBPChestDefencePercent.setText(String.valueOf(BodyPart.CHEST.getDefencePercent()));
        tfieldArmorBPWristDefencePercent.setText(String.valueOf(BodyPart.WRIST.getDefencePercent()));
        tfieldArmorBPBootsDefencePercent.setText(String.valueOf(BodyPart.BOOTS.getDefencePercent()));
        tfieldArmorBPHandsDefencePercent.setText(String.valueOf(BodyPart.HANDS.getDefencePercent()));
        tfieldArmorBPLegsDefencePercent.setText(String.valueOf(BodyPart.LEGS.getDefencePercent()));
    }

    private void listenToPercentUpdates() {
        tfieldArmorBPHeadDefencePercent.textProperty().addListener((observable, oldValue, newValue) -> recountPercentSum());
        tfieldArmorBPShouldersDefencePercent.textProperty().addListener((observable, oldValue, newValue) -> recountPercentSum());
        tfieldArmorBPChestDefencePercent.textProperty().addListener((observable, oldValue, newValue) -> recountPercentSum());
        tfieldArmorBPWristDefencePercent.textProperty().addListener((observable, oldValue, newValue) -> recountPercentSum());
        tfieldArmorBPBootsDefencePercent.textProperty().addListener((observable, oldValue, newValue) -> recountPercentSum());
        tfieldArmorBPHandsDefencePercent.textProperty().addListener((observable, oldValue, newValue) -> recountPercentSum());
        tfieldArmorBPLegsDefencePercent.textProperty().addListener((observable, oldValue, newValue) -> recountPercentSum());
    }

    private void recountPercentSum() {
        float percentSum = 0;
        for (TextField tfieldDefP : tfieldsWithDefPercents) {
            try {
                percentSum += Float.parseFloat(tfieldDefP.getText());
            } catch (NumberFormatException ignored) {}
        }
        tfieldArmorBPSumDefencePercent.setText(String.valueOf(percentSum));
    }

    public void onSave(ActionEvent ignored) {
        if (!"1.0".equals(tfieldArmorBPSumDefencePercent.getText())) {
            new Alert(Alert.AlertType.WARNING, "Sum of defence percents must be 1", ButtonType.OK).showAndWait();
            return;
        }
        try {
            float newMarketPrice = Float.parseFloat(tfieldMarketDefPrice.getText());

            float newIntPriceModifier = Float.parseFloat(tfieldArmorIntPriceModifier.getText());
            int newIntFullsetMaxarmor = Integer.parseInt(tfieldArmorIntFullsetMaxarmor.getText());

            float newDexPriceModifier = Float.parseFloat(tfieldArmorDexPriceModifier.getText());
            int newDexFullsetMaxarmor = Integer.parseInt(tfieldArmorDexFullsetMaxarmor.getText());

            float newStrPriceModifier = Float.parseFloat(tfieldArmorStrPriceModifier.getText());
            int newStrFullsetMaxarmor = Integer.parseInt(tfieldArmorStrFullsetMaxarmor.getText());

            float newHeadDefencePercent = Float.parseFloat(tfieldArmorBPHeadDefencePercent.getText());
            float newShouldersDefencePercent = Float.parseFloat(tfieldArmorBPShouldersDefencePercent.getText());
            float newChestDefencePercent = Float.parseFloat(tfieldArmorBPChestDefencePercent.getText());
            float newWristDefencePercent = Float.parseFloat(tfieldArmorBPWristDefencePercent.getText());
            float newBootsDefencePercent = Float.parseFloat(tfieldArmorBPBootsDefencePercent.getText());
            float newHandsDefencePercent = Float.parseFloat(tfieldArmorBPHandsDefencePercent.getText());
            float newLegsDefencePercent = Float.parseFloat(tfieldArmorBPLegsDefencePercent.getText());

            MarketPrice.getInstance().setDefPrice(newMarketPrice);
            ArmorClass.INT.setPriceModifier(newIntPriceModifier);
            ArmorClass.INT.setFullSetMaxArmor(newIntFullsetMaxarmor);

            ArmorClass.DEX.setPriceModifier(newDexPriceModifier);
            ArmorClass.DEX.setFullSetMaxArmor(newDexFullsetMaxarmor);

            ArmorClass.STR.setPriceModifier(newStrPriceModifier);
            ArmorClass.STR.setFullSetMaxArmor(newStrFullsetMaxarmor);

            BodyPart.HEAD.setDefencePercent(newHeadDefencePercent);
            BodyPart.SHOULDERS.setDefencePercent(newShouldersDefencePercent);
            BodyPart.CHEST.setDefencePercent(newChestDefencePercent);
            BodyPart.WRIST.setDefencePercent(newWristDefencePercent);
            BodyPart.BOOTS.setDefencePercent(newBootsDefencePercent);
            BodyPart.HANDS.setDefencePercent(newHandsDefencePercent);
            BodyPart.LEGS.setDefencePercent(newLegsDefencePercent);
        } catch (NumberFormatException e) {
            throw new ExceptionWithDialog(e);
        }
        closeWindow();
    }

    public void onCancel(ActionEvent ignored) {
        closeWindow();
    }

    private void closeWindow() {
        btnCancel.getScene().getWindow().hide();
    }
}

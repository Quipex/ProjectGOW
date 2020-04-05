package gow.gui.generator;

import gow.generator.Characteristic;
import gow.generator.ItemGenerator;
import gow.generator.armor.Armor;
import gow.generator.armor.ArmorGenerator;
import gow.generator.configuration.armor.BodyPart;
import gow.gui.cell.ArmorCell;
import javafx.scene.control.*;

public class ArmorGeneratorController extends ItemGeneratorController<Armor> {
    private ArmorGenerator armorGenerator = new ArmorGenerator(Characteristic.STRENGTH);

    @Override
    protected String itemTypeLocalizedName() {
        return "Part of body";
    }

    @Override
    protected ListCell<Armor> itemCellFactory() {
        return new ArmorCell();
    }

    @Override
    protected Enum<?>[] itemTypeValues() {
        return BodyPart.values();
    }

    @Override
    protected String generatorConfigFxmlPath() {
        return "fxml/price-parameters/ArmorConfig.fxml";
    }

    @Override
    protected BodyPart itemType(String enumAsString) {
        return BodyPart.valueOf(enumAsString);
    }

    @Override
    protected ItemGenerator<Armor> generator() {
        return armorGenerator;
    }
}

package gow.gui.generator;

import gow.generator.Characteristic;
import gow.generator.ItemGenerator;
import gow.generator.configuration.ItemType;
import gow.generator.configuration.weapon.WeaponType;
import gow.generator.weapon.Weapon;
import gow.generator.weapon.WeaponGenerator;
import gow.gui.cell.WeaponCell;
import javafx.scene.control.*;

public class WeaponGeneratorController extends ItemGeneratorController<Weapon> {
    private WeaponGenerator weaponGenerator = new WeaponGenerator(Characteristic.STRENGTH);

    @Override
    protected String itemTypeLocalizedName() {
        return "Type of weapon";
    }

    @Override
    protected ListCell<Weapon> itemCellFactory() {
        return new WeaponCell();
    }

    @Override
    protected Enum<?>[] itemTypeValues() {
        return WeaponType.values();
    }

    @Override
    protected String generatorConfigFxmlPath() {
        return "fxml/price-parameters/WeaponConfig.fxml";
    }

    @Override
    protected Enum<? extends ItemType> itemType(String value) {
        return WeaponType.valueOf(value);
    }

    @Override
    protected ItemGenerator<Weapon> generator() {
        return weaponGenerator;
    }
}

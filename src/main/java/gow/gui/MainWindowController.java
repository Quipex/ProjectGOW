package gow.gui;

import gow.gui.generator.ArmorGeneratorController;
import gow.gui.generator.WeaponGeneratorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class MainWindowController {
    public Tab tabArmor;
    public Tab tabWeapon;

    public void initialize() {
        try {
            URL fxmlLocation = Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/item-generators/ItemGenerator.fxml"));
            FXMLLoader armorLoader = new FXMLLoader(fxmlLocation);
            armorLoader.setController(new ArmorGeneratorController());
            Parent armorRoot = armorLoader.load();
            tabArmor.setContent(armorRoot);

            FXMLLoader weaponLoader = new FXMLLoader(fxmlLocation);
            weaponLoader.setController(new WeaponGeneratorController());
            Parent weaponRoot = weaponLoader.load();
            tabWeapon.setContent(weaponRoot);
        } catch (IOException e) {
            throw new ExceptionWithDialog(e);
        }

    }
}

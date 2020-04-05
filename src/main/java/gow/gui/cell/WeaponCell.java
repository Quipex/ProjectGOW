package gow.gui.cell;

import gow.generator.configuration.armor.BodyPart;
import gow.generator.configuration.weapon.WeaponType;
import gow.generator.weapon.Weapon;
import gow.gui.ExceptionWithDialog;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.util.Objects;

public class WeaponCell extends ItemCell<Weapon> {
    private Label lblWeaponType = new Label();
    private Label lblDamage = new Label();

    @Override
    protected void mainBlockUpdate(Weapon item) {
        lblWeaponType.setText(item.getWeaponType().name());
        lblDamage.setText(String.valueOf(item.getDamage()));
    }

    @Override
    protected Image itemTypeImage(Weapon item) {
        return new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(getIconPath(item.getWeaponType()))));
    }

    private String getIconPath(WeaponType weaponType) {
        String path = "static/icons/weapon-cell/";
        switch (weaponType) {
            case SWORD_1H:
                path += "sword";
                break;
            case SWORD_2H:
                path += "sword_2h";
                break;
            case AXE_1H:
                path += "axe_1h";
                break;
            case AXE_2H:
                path += "axe_2h";
                break;
            case HAMMER_1H:
            case HAMMER_2H:
                path += "hammer";
                break;
            case CROSSBOW:
                path += "crossbow";
                break;
            case BOW:
                path += "bow";
                break;
            case DAGGER:
                path += "dagger";
                break;
            default:
                throw new ExceptionWithDialog("Unknown weapon type " + weaponType.name());
        }
        path += ".png";
        return path;

    }

    @Override
    protected Node mainBlock() {
        GridPane rootPane = new GridPane();
        rootPane.setHgap(5);
        rootPane.setVgap(5);

        rootPane.add(new Label("Weapon type"), 0, 0);
        rootPane.add(new Label("Damage"), 0, 1);

        rootPane.add(lblWeaponType, 1, 0);
        rootPane.add(lblDamage, 1, 1);
        return rootPane;
    }
}

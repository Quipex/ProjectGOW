package gow.gui.cell;

import gow.generator.armor.Armor;
import gow.generator.configuration.armor.BodyPart;
import gow.gui.ExceptionWithDialog;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.util.Objects;


public class ArmorCell extends ItemCell<Armor> {
    private Label lblBodyPart = new Label();
    private Label lblArmorClass = new Label();
    private Label lblDefence = new Label();


    private String getIconPath(BodyPart bodyPart) {
        switch (bodyPart) {
            case HEAD:
                return "static/icons/armor-cell/icon-helmet-32.png";
            case LEGS:
                return "static/icons/armor-cell/icon-legs-32.png";
            case HANDS:
                return "static/icons/armor-cell/icon-gloves-32.png";
            case BOOTS:
                return "static/icons/armor-cell/icon-boots-32.png";
            case CHEST:
                return "static/icons/armor-cell/icon-chest-32.png";
            case SHOULDERS:
                return "static/icons/armor-cell/icon-shoulders-32.png";
            case WRIST:
                return "static/icons/armor-cell/icon-wrist-32.png";
        }
        throw new ExceptionWithDialog("Unknown body part " + bodyPart.name());
    }

    @Override
    protected void mainBlockUpdate(Armor item) {
        lblBodyPart.setText(item.getBodyPart().name());
        lblArmorClass.setText(item.getArmorClass().name());
        lblDefence.setText(item.getDefence() +"🛡");
    }

    @Override
    protected Image itemTypeImage(Armor item) {
//        todo: cache images
        return new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(getIconPath(item.getBodyPart()))));
    }

    @Override
    protected Node mainBlock() {
        GridPane rootPane = new GridPane();
        rootPane.setHgap(5);
        rootPane.setVgap(5);

        rootPane.add(new Label("Body part"), 0, 0);
        rootPane.add(new Label("Armor class"), 0, 1);
        rootPane.add(new Label("Defence"), 0, 2);

        rootPane.add(lblBodyPart, 1, 0);
        rootPane.add(lblArmorClass, 1, 1);
        rootPane.add(lblDefence, 1, 2);
        return rootPane;
    }
}

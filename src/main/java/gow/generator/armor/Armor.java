package gow.generator.armor;

import gow.generator.Item;
import gow.generator.configuration.armor.ArmorClass;
import gow.generator.configuration.armor.BodyPart;
import lombok.ToString;

@ToString(callSuper = true)
public class Armor extends Item {
    private int defence;
    private BodyPart bodyPart;
    private ArmorClass armorClass;

    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public ArmorClass getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(ArmorClass armorClass) {
        this.armorClass = armorClass;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    @Override
    public String toPrettyDescription() {
        return bodyPart.name() + ", " + armorClass.name() + "\n" +
                defence + " защиты[max=" + ArmorPriceCalculator.calcMaxArmor(armorClass, bodyPart) + "]. " +
                "(цена за 1 поинт " + ArmorPriceCalculator.priceOf(1, armorClass, bodyPart) + ")\n" +
                prettyPrintTraits() +
                "\nОбщая цена " + getPrice();

    }
}

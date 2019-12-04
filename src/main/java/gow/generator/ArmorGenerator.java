package gow.generator;

import gow.Item;
import gow.MainCharacteristic;
import gow.armor.Armor;

public class ArmorGenerator implements ItemGenerator {
    @Override
    public Item generate(MainCharacteristic mainCharacteristic, int lowerPrice, int upperPrice) {
        Armor armor = new Armor();
        switch (mainCharacteristic) {
            case DEX:
                break;
            case INT:
                break;
            case STR:
                break;
            case COMMON:
                break;
        }
        return null;
    }
}

package gow.generator.configuration.armor;

import gow.generator.Characteristic;

public enum ArmorClass {
    INT(1.5f, 30),
    DEX(1.3f, 40),
    STR(0.85f, 65);

    private float priceModifier;
    private int fullSetMaxArmor;

    ArmorClass(float priceModifier, int fullSetMaxArmor) {
        this.priceModifier = priceModifier;
        this.fullSetMaxArmor = fullSetMaxArmor;
    }

    /**
     * @return ArmorClass according to given Characteristic
     */
    public static ArmorClass fromCharacteristic(Characteristic characteristic) {
        switch (characteristic) {
            case STRENGTH:
                return ArmorClass.STR;
            case DEXTERITY:
            case VITALITY:
                return ArmorClass.DEX;
            case INTELLIGENCE:
                return ArmorClass.INT;
            default:
                throw new UnsupportedOperationException("Unknown characteristic " + characteristic);
        }
    }

    public float priceModifier() {
        return priceModifier;
    }

    public int getFullSetMaxArmor() {
        return fullSetMaxArmor;
    }
}

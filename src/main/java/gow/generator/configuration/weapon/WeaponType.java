package gow.generator.configuration.weapon;

import gow.generator.configuration.ItemType;

public enum WeaponType implements ItemType {
    SWORD_1H(10, true, 1.4f),
    SWORD_2H(21, false, 1.2f),
    HAMMER_1H(12, true, 1.5f),
    HAMMER_2H(26, false, 1.25f),
    AXE_1H(11, true, 1.3f),
    AXE_2H(23, false, 1),
    DAGGER(7, true, 1.6f),
    BOW(26, false, 1.35f),
    CROSSBOW(28, false, 1.15f);

    private int maxDamage;
    private boolean oneHanded;
    private float dmgPriceModifier;

    WeaponType(int maxDamage, boolean oneHanded, float dmgPriceModifier) {
        this.maxDamage = maxDamage;
        this.oneHanded = oneHanded;
        this.dmgPriceModifier = dmgPriceModifier;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public boolean isOneHanded() {
        return oneHanded;
    }

    public float getDmgPriceModifier() {
        return dmgPriceModifier;
    }
}

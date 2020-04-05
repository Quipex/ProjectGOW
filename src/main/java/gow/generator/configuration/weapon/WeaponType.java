package gow.generator.configuration.weapon;

import gow.generator.configuration.ItemType;

public enum WeaponType implements ItemType {
    SWORD_1H(10, true, 1.4f, 1.5f, 1f),
    SWORD_2H(21, false, 1.2f, 9f, 1f),
    HAMMER_1H(12, true, 1.5f, 1f, 1f),
    HAMMER_2H(26, false, 1.25f, 1f, 1f),
    AXE_1H(11, true, 1.3f, 1f, 1f),
    AXE_2H(23, false, 1, 1f, 1f),
    DAGGER(7, true, 1.6f, 1f, 1f),
    BOW(26, false, 1.35f, 1f, 1f),
    CROSSBOW(28, false, 1.15f, 1f, 1f);

    private int maxDamage;
    private boolean oneHanded;
    private float dmgPriceModifier;
    private float minWeight;
    private float maxWeight;

    WeaponType(int maxDamage, boolean oneHanded, float dmgPriceModifier, float minWeight, float maxWeight) {
        this.maxDamage = maxDamage;
        this.oneHanded = oneHanded;
        this.dmgPriceModifier = dmgPriceModifier;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
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

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public void setOneHanded(boolean oneHanded) {
        this.oneHanded = oneHanded;
    }

    public void setDmgPriceModifier(float dmgPriceModifier) {
        this.dmgPriceModifier = dmgPriceModifier;
    }

    public float getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(float minWeight) {
        this.minWeight = minWeight;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }
}

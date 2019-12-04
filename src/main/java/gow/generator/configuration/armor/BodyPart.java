package gow.generator.configuration.armor;

import gow.generator.configuration.ItemType;

public enum BodyPart implements ItemType {
    HEAD(0.15f),
    SHOULDERS(0.075f),
    CHEST(0.4f),
    WRIST(0.05f),
    BOOTS(0.075f),
    HANDS(0.05f),
    LEGS(0.2f);

    /**
     * stands for percent from overall max armor
     */
    private float defencePercent;

    BodyPart(float defencePercent) {
        this.defencePercent = defencePercent;
    }

    public float getDefencePercent() {
        return defencePercent;
    }

}

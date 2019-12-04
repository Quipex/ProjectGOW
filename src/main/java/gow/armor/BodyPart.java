package gow.armor;

public enum BodyPart {
    HEAD(0.1f),
    SHOULDERS(0.1f),
    CHEST(0.4f),
    WRIST(0.05f),
    BOOTS(0.1f),
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

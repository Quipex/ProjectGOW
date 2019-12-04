package gow.armor;

public enum ArmorClass {
    INT(20, 40),
    DEX(30, 60),
    STR(50, 100);

    private int minDefence;
    private int maxDefence;

    ArmorClass(int minDefence, int maxDefence) {
        this.minDefence = minDefence;
        this.maxDefence = maxDefence;
    }

    public int getMinDefence() {
        return minDefence;
    }

    public int getMaxDefence() {
        return maxDefence;
    }
}

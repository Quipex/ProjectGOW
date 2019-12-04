package gow.weapon;

public enum WeaponType {
    SWORD_1H(5, 10, true),
    SWORD_2H(11, 21, false),
    HAMMER_1H(7, 12, true),
    HAMMER_2H(13, 26, false),
    AXE_1H(6, 11, true),
    AXE_2H(12, 23, false),
    DAGGER(3, 7, true),
    BOW(14, 26, false),
    CROSSBOW(16, 28, false);

    private int minDamage;
    private int maxDamage;
    private boolean isOneHanded;

    WeaponType(int minDamage, int maxDamage, boolean isOneHanded) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.isOneHanded = isOneHanded;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public boolean isOneHanded() {
        return isOneHanded;
    }
}

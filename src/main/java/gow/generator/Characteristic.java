package gow.generator;

public enum Characteristic {
    INTELLIGENCE("Интеллект"),
    STRENGTH("Сила"),
    DEXTERITY("Ловкость"),
    VITALITY("Выносливость");

    private String localizedName;

    Characteristic(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getLocalizedName() {
        return localizedName;
    }
}

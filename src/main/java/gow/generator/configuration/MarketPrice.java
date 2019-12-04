package gow.generator.configuration;

public final class MarketPrice {
    private static final float DMG_PRICE = 10f;
    private static final float DEF_PRICE = 10f;
    private static MarketPrice INSTANCE;

    public static MarketPrice getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MarketPrice();
        }
        return INSTANCE;
    }

    private MarketPrice() {
    }

    /**
     * @return market price per single damage point
     */
    public float dmgPoint() {
        return DMG_PRICE;
    }

    /**
     * @return market price per single defence point
     */
    public float defPoint() {
        return DEF_PRICE;
    }
}

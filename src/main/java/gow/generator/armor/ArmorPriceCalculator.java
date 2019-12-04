package gow.generator.armor;

import gow.generator.configuration.MarketPrice;
import gow.generator.configuration.armor.ArmorClass;
import gow.generator.configuration.armor.BodyPart;

public final class ArmorPriceCalculator {
    /**
     * Get price of armor points depending on armor class and body part
     * @return non-negative rounded (Math.round()) number that represents the price.
     */
    public static int priceOf(int armorPoints, ArmorClass armorClass, BodyPart bodyPart) {
        float priceForPoint = calcPriceForDefencePoint(armorClass, bodyPart);
        return Math.round(priceForPoint * armorPoints);
    }

    private static float calcPriceForDefencePoint(ArmorClass armorClass, BodyPart bodyPart) {
        float percentOfPrice = 1 - bodyPart.getDefencePercent();
        return percentOfPrice * armorClass.priceModifier() * MarketPrice.getInstance().defPoint();
    }

    /**
     * Get the max amount of defence for your configuration
     * @return non-negative rounded (Math.round()) number that represents the defence.
     */
    public static int maxDefenceFor(int price, ArmorClass armorClass, BodyPart bodyPart) {
        int maxArmor = calcMaxArmor(armorClass, bodyPart);
        int armorForPrice = (int) Math.floor(price / calcPriceForDefencePoint(armorClass, bodyPart));
        return Math.min(armorForPrice, maxArmor);
    }

    /**
     * @return max possible armor for given parameters
     */
    public static int calcMaxArmor(ArmorClass armorClass, BodyPart bodyPart) {
        return Math.round(bodyPart.getDefencePercent() * armorClass.getFullSetMaxArmor());
    }
}

package gow.generator.weapon;

import gow.generator.configuration.MarketPrice;
import gow.generator.configuration.weapon.WeaponType;

public final class WeaponPriceCalculator {
    public static int priceOf(int damage, WeaponType weaponType) {
        return Math.round(damage * priceForPoint(weaponType));
    }

    private static float priceForPoint(WeaponType weaponType) {
        return weaponType.getDmgPriceModifier() * MarketPrice.getInstance().dmgPoint();
    }

    public static int damageFor(int price, WeaponType weaponType) {
        int maxPossibleDamage = weaponType.getMaxDamage();
        int maxDamageForPrice = (int) Math.floor(price / priceForPoint(weaponType));
        return Math.min(maxDamageForPrice, maxPossibleDamage);
    }
}

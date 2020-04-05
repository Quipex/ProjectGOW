package gow.generator.weapon;

import gow.generator.Item;
import gow.generator.configuration.weapon.WeaponType;
import lombok.ToString;

@ToString(callSuper = true)
public class Weapon extends Item {
    private int damage;
    private WeaponType weaponType;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    @Override
    public String toPrettyDescription() {
        return weaponType.name() + "\n" +
                damage + " атаки[max=" + weaponType.getMaxDamage() + "]. " +
                "(цена за 1 поинт " + WeaponPriceCalculator.priceOf(1, weaponType) + ")\n" +
                prettyPrintTraits() +
                "\nОбщая цена " + getPrice();
    }

    @Override
    protected String itemPoints() {
        return "Урон +" + getDamage();
    }

}

package gow.generator.weapon;

import gow.generator.Characteristic;
import gow.generator.ItemGeneratorTest;
import gow.generator.configuration.weapon.WeaponType;
import org.junit.jupiter.api.Test;

class WeaponGeneratorTest extends ItemGeneratorTest<Weapon> {
    private WeaponGenerator generator = new WeaponGenerator(Characteristic.STRENGTH);

    @Test
    public void generateSampleWeapon() {
        Weapon weapon = generator.generate(500);
        System.out.println(weapon.toPrettyDescription());
    }

    @Override
    protected WeaponGenerator getGenerator() {
        return generator;
    }

    @Override
    protected WeaponType[] getItemTypes() {
        return WeaponType.values();
    }
}

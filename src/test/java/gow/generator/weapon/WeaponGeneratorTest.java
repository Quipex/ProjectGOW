package gow.generator.weapon;

import gow.generator.Characteristic;
import gow.generator.ItemGenerator;
import gow.generator.ItemGeneratorTest;
import gow.generator.configuration.ItemType;
import gow.generator.configuration.weapon.WeaponType;
import org.junit.jupiter.api.Test;

class WeaponGeneratorTest extends ItemGeneratorTest {
    private WeaponGenerator generator = new WeaponGenerator(Characteristic.STRENGTH);

    @Test
    public void generateSampleWeapon() {
        Weapon weapon = generator.generate(500);
        System.out.println(weapon.toPrettyDescription());
    }

    @Override
    protected ItemGenerator getGenerator() {
        return generator;
    }

    @Override
    protected ItemType[] getItemTypes() {
        return WeaponType.values();
    }
}

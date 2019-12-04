package gow.generator.armor;

import gow.generator.Characteristic;
import gow.generator.Item;
import gow.generator.ItemGenerator;
import gow.generator.ItemGeneratorTest;
import gow.generator.configuration.ItemType;
import gow.generator.configuration.armor.BodyPart;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class ArmorGeneratorTest extends ItemGeneratorTest {
    private ArmorGenerator generator = new ArmorGenerator(Characteristic.STRENGTH);

    @Test
    public void generateSampleArmor() {
        ArmorGenerator generator = new ArmorGenerator(Characteristic.STRENGTH);
        Armor armor = generator.generate(500, BodyPart.CHEST);
        System.out.println(armor.toPrettyDescription());
    }

    @Override
    protected ItemGenerator getGenerator() {
        return generator;
    }

    @Override
    protected ItemType[] getItemTypes() {
        return BodyPart.values();
    }

    @Override
    protected void pointsSumPrint(List<Item> items) {
        List<Armor> armors = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Armor) {
                armors.add((Armor) item);
            } else {
                throw new IllegalStateException("Somehow Armor test received not an instance of Armor");
            }
        }
        System.out.println("Общий армор = " + armors.stream().mapToInt(Armor::getDefence).sum());
    }
}

package gow.generator;

import com.google.common.collect.Lists;
import gow.generator.configuration.ItemType;
import org.junit.jupiter.api.Test;

import java.util.List;

public abstract class ItemGeneratorTest<T extends Item> {
    private int[] prices = new int[]{50, 100, 200, 500, 800, 1000, 1500, 2000};

    public int[] getPrices() {
        return prices;
    }

    @Test
    public void generateFullSetOfEach() {
        ItemGenerator<T> generator = getGenerator();
        for (int price : getPrices()) {
            System.out.println("====Предмет за " + price + "г.====");
            for (Characteristic characteristic : Characteristic.values()) {
                System.out.println("\nОсновной параметр " + characteristic);
                generator.setMainCharacteristic(characteristic);
                List<Item> items = Lists.newArrayList();
                for (Enum<? extends ItemType> itemType : getItemTypes()) {
                    Item item = generator.generate(price, itemType);
                    items.add(item);
                    System.out.println(item.toPrettyDescription());
                    System.out.println("-------");
                }
                System.out.println("Стоимость всех [" + characteristic.name() + "] (" + items.size() + "шт) = " +
                        items.stream().mapToInt(Item::getPrice).sum());
                pointsSumPrint(items);
            }
        }
    }

    /**
     * If a child needs to print out it's specific characteristics (armor, damage etc.) override this.
     * Otherwise ignore
     */
    protected void pointsSumPrint(List<Item> items) {}

    protected abstract ItemGenerator<T> getGenerator();

    protected abstract Enum<? extends ItemType>[] getItemTypes();
}

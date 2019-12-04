package gow.generator.trait;

import gow.generator.Characteristic;
import gow.generator.ItemClass;
import gow.generator.utils.TraitsJsonParser;

import java.util.Set;
import java.util.stream.Collectors;

public class UniqueTraitsRepository {
    private ItemClass itemClass;

    public UniqueTraitsRepository(ItemClass itemClass) {
        this.itemClass = itemClass;
    }

    private TraitsJsonParser traitsParser = TraitsJsonParser.getInstance();

    public Set<UniqueTrait> findCheaperOrEqual(int price, Characteristic characteristic) {
        return traitsParser.lazyLoadTraits().stream()
                .filter(uniqueTrait -> uniqueTrait.getPrice() <= price)
                .filter(uniqueTrait -> uniqueTrait.getItemClass().equals(itemClass))
                .filter(uniqueTrait -> uniqueTrait.getMainCharacteristic().equals(characteristic))
                .collect(Collectors.toSet());
    }
}

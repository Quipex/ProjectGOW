package gow.generator.trait;

import gow.generator.Characteristic;
import gow.generator.ItemClass;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
public class UniqueTrait extends Trait {
    private ItemClass itemClass;
    private Characteristic mainCharacteristic;

    public UniqueTrait(String name, int price) {
        super(name, price);
    }

    public UniqueTrait(Trait trait, ItemClass type, Characteristic mainCharacteristic) {
        this(trait.getName(), trait.getPrice());
        itemClass = type;
        this.mainCharacteristic = mainCharacteristic;
    }
}

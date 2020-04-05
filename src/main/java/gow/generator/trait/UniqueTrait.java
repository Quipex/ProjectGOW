package gow.generator.trait;

import com.google.common.base.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UniqueTrait that = (UniqueTrait) o;
        return itemClass == that.itemClass &&
                mainCharacteristic == that.mainCharacteristic;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), itemClass, mainCharacteristic);
    }
}

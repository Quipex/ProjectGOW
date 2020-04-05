package gow.generator.trait;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Trait {
    private String name;
    private int price;

    public Trait() {
    }

    public Trait(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trait trait = (Trait) o;
        return price == trait.price &&
                Objects.equal(name, trait.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, price);
    }
}

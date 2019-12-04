package gow.generator.trait;

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
}

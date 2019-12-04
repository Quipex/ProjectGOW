package gow;

import java.util.Map;

public abstract class Item {
    private Map<Characteristic, Integer> requirements;
    private int price;

    public Map<Characteristic, Integer> getRequirements() {
        return requirements;
    }

    public void setRequirements(Map<Characteristic, Integer> requirements) {
        this.requirements = requirements;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

package gow.generator;

import gow.generator.trait.UniqueTrait;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ToString
public abstract class Item {
    private Map<Characteristic, Integer> requirements;
    private List<UniqueTrait> traits;
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

    public List<UniqueTrait> getTraits() {
        return traits;
    }

    public void setTraits(List<UniqueTrait> traits) {
        this.traits = traits;
    }

    public abstract String toPrettyDescription();

    protected String prettyPrintTraits() {
        return "Спец. абилки:" +
                getTraits().stream().map(tr ->
                        "\n" + tr.getName() + "\nЦена " + tr.getPrice())
                        .collect(Collectors.joining(";"));
    }
}

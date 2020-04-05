package gow.generator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import gow.generator.trait.UniqueTrait;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ToString
public abstract class Item {
    private Map<Characteristic, Integer> requirements = Maps.newHashMap();
    private List<UniqueTrait> traits = Lists.newArrayList();
    private int price;
    private float weight;
    private String description = "A sample description";

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

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
        return getTraits().stream().map(tr -> "[8b008b]" + tr.getName() + "[-]")
                .collect(Collectors.joining("\n"));
    }

    public String toGowItemDescription() {
        StringBuilder description = new StringBuilder();
        if (ifNotEmpty(itemPoints())) {
            description.append(itemPoints()).append("\n\n");
        }
        if (ifNotEmpty(getDescription())) {
            description.append("[i]").append(getDescription()).append("[/i]").append("\n\n");
        }
        if (ifNotEmpty(prettyPrintTraits())) {
            description.append(prettyPrintTraits()).append("\n\n");
        }
        if (!getRequirements().isEmpty()) {
            description.append("{");
            String reqs = getRequirements().entrySet().stream()
                    .map(charaToLevel -> charaToLevel.getKey().getLocalizedName() + ":" + charaToLevel.getValue())
                    .collect(Collectors.joining("; "));
            description.append(reqs);
            description.append("}");
        }
        description.append("kg:").append(getWeight());
        return description.toString();
    }

    private boolean ifNotEmpty(String s) {
        return !"".equals(s) && s != null;
    }

    protected abstract String itemPoints();
}

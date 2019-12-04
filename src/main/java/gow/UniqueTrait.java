package gow;

public class UniqueTrait {
    private String name;
    private float weight;
    private MainCharacteristic characteristic;

    public UniqueTrait(String name, float weight, MainCharacteristic characteristic) {
        this.name = name;
        this.weight = weight;
        this.characteristic = characteristic;
    }

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }

    public MainCharacteristic getCharacteristic() {
        return characteristic;
    }
}

package gow.generator.armor;

import gow.generator.Characteristic;
import gow.generator.ItemClass;
import gow.generator.ItemGenerator;
import gow.generator.configuration.ItemType;
import gow.generator.configuration.armor.ArmorClass;
import gow.generator.configuration.armor.BodyPart;
import gow.generator.trait.UniqueTraitsRepository;
import gow.generator.utils.RandomUtils;
import lombok.extern.java.Log;

import java.util.Random;

@Log
public class ArmorGenerator extends ItemGenerator<Armor> {
    /**
     * When generating random defence for an item, this parameter states how many percents of maximum possible defence
     * will be guaranteed to be generated.
     * Example: maximum possible defence to be generated is 100. This parameter is 100, every time the generated defence
     * will be 100. If this parameter is 90, the generated defence will be between 90 and 100 inclusive.
     */
    private static final int STABLE_DEFENCE_PERCENT = 80;

    private Random random = new Random();
    private Characteristic mainCharacteristic;
    private ArmorClass armorClass;
    private UniqueTraitsRepository traitsRepository;

    public ArmorGenerator(Characteristic mainCharacteristic) {
        this.traitsRepository = new UniqueTraitsRepository(ItemClass.ARMOR);
        this.setMainCharacteristic(mainCharacteristic);
    }

    @Override
    protected float calcWeight(Armor generatedItem) {
        return 0;
    }

    @Override
    protected BodyPart randomItemType() {
        BodyPart[] bodyParts = BodyPart.values();
        int randomIndex = random.nextInt(bodyParts.length);
        return bodyParts[randomIndex];
    }

    @Override
    protected void fillRequirements(Armor generatedItem) {
        //todo implement
    }

    @Override
    protected int fillMajorityAndGetCost(int moneyLeft, Armor generatedItem, Enum<? extends ItemType> bodyPart) {
        try {
            int randomDefence = getRandomDefence(moneyLeft, (BodyPart) bodyPart, armorClass);
            generatedItem.setDefence(randomDefence);
            generatedItem.setArmorClass(armorClass);
            generatedItem.setBodyPart((BodyPart) bodyPart);
            return ArmorPriceCalculator.priceOf(randomDefence, armorClass, (BodyPart) bodyPart);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Expected <Armor> item and itemType, got " + generatedItem.getClass() +
                    " and " + bodyPart.getClass(), e);
        }
    }

    @Override
    protected Armor createEmpty() {
        return new Armor();
    }

    private int getRandomDefence(int priceLeft, BodyPart bodyPart, ArmorClass armorClass) {
        int maxDefence = ArmorPriceCalculator.maxDefenceFor(priceLeft, armorClass, bodyPart);
        return Math.round(maxDefence * RandomUtils.getPercent(STABLE_DEFENCE_PERCENT));
    }

    @Override
    public void setMainCharacteristic(Characteristic mainCharacteristic) {
        this.mainCharacteristic = mainCharacteristic;
        this.armorClass = ArmorClass.fromCharacteristic(mainCharacteristic);
    }

    @Override
    public Characteristic getMainCharacteristic() {
        return mainCharacteristic;
    }

    @Override
    protected UniqueTraitsRepository traitsRepository() {
        return traitsRepository;
    }
}

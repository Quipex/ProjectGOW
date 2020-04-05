package gow.generator;

import com.google.common.collect.Lists;
import gow.generator.configuration.ItemType;
import gow.generator.trait.Trait;
import gow.generator.trait.UniqueTrait;
import gow.generator.trait.UniqueTraitsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class ItemGenerator<T extends Item> {

    protected Random random = new Random();

    public T generate(int price) {
        return generate(price, randomItemType());
    }

    public T generate(int price, Enum<? extends ItemType> itemType) {
        return generate(price, itemType, true);
    }

    public T generate(int price, Enum<? extends ItemType> itemType, boolean useTraits) {
        int moneyLeft = price;
        T generatedItem = createEmpty();
        moneyLeft -= fillMajorityAndGetCost(moneyLeft, generatedItem, itemType);
        assert moneyLeft >= 0;
        if (useTraits) {
            moneyLeft -= fillTraitsAndGetCost(moneyLeft, generatedItem);
            assert moneyLeft >= 0;
        }
        generatedItem.setPrice(price - moneyLeft);
        fillRequirements(generatedItem);
        generatedItem.setWeight(calcWeight(generatedItem));
        return generatedItem;
    }

    /**
     * @return calculated weight of an item according to it's characteristics
     */
    protected abstract float calcWeight(T generatedItem);

    private int fillTraitsAndGetCost(int moneyLeft, Item generatedItem) {
        List<UniqueTrait> randomTraits = getTraitsForMoney(moneyLeft, getMainCharacteristic());
        generatedItem.setTraits(randomTraits);
        return randomTraits.stream().mapToInt(Trait::getPrice).sum();
    }

    protected List<UniqueTrait> getTraitsForMoney(int priceLeft, Characteristic mainRequiredCharacteristic) {
        int restOfMoney = priceLeft;
        List<UniqueTrait> availableTraits = new ArrayList<>(traitsRepository()
                .findCheaperOrEqual(restOfMoney, mainRequiredCharacteristic));

        ArrayList<UniqueTrait> randomPickedTraits = Lists.newArrayList();
        boolean findNext = true;
        while (!availableTraits.isEmpty() && findNext) {
            int randomIndex = random.nextInt(availableTraits.size());
            UniqueTrait randomPickedTrait = availableTraits.get(randomIndex);
            availableTraits.remove(randomIndex);
            restOfMoney -= randomPickedTrait.getPrice();
            randomPickedTraits.add(randomPickedTrait);
            int finalRestOfMoney = restOfMoney;
            availableTraits = availableTraits.stream()
                    .filter(tr -> tr.getPrice() <= finalRestOfMoney).collect(Collectors.toList());
            findNext = random.nextBoolean();
        }

        return randomPickedTraits;
    }

    protected abstract Enum<? extends ItemType> randomItemType();

    protected abstract void fillRequirements(T generatedItem);

    protected abstract int fillMajorityAndGetCost(int moneyLeft, T generatedItem, Enum<? extends ItemType> itemType);

    protected abstract T createEmpty();

    protected abstract UniqueTraitsRepository traitsRepository();

    public abstract Characteristic getMainCharacteristic();

    public abstract void setMainCharacteristic(Characteristic characteristic);
}

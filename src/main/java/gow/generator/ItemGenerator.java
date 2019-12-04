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

public abstract class ItemGenerator {

    protected Random random = new Random();

    public Item generate(int price) {
        return generate(price, randomItemType());
    }

    public Item generate(int price, ItemType itemType) {
        int moneyLeft = price;
        Item generatedItem = createEmpty();
        moneyLeft -= fillMajorityAndGetCost(moneyLeft, generatedItem, itemType);
        assert moneyLeft >= 0;
        moneyLeft -= fillTraitsAndGetCost(moneyLeft, generatedItem);
        assert moneyLeft >= 0;
        generatedItem.setPrice(price - moneyLeft);
        fillRequirements(generatedItem);
        return generatedItem;
    }

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

    protected abstract ItemType randomItemType();

    protected abstract void fillRequirements(Item generatedItem);

    protected abstract int fillMajorityAndGetCost(int moneyLeft, Item generatedItem, ItemType itemType);

    protected abstract Item createEmpty();

    protected abstract UniqueTraitsRepository traitsRepository();

    protected abstract Characteristic getMainCharacteristic();

    protected abstract void setMainCharacteristic(Characteristic characteristic);
}

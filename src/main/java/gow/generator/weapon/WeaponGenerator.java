package gow.generator.weapon;

import gow.generator.Characteristic;
import gow.generator.ItemClass;
import gow.generator.ItemGenerator;
import gow.generator.configuration.ItemType;
import gow.generator.configuration.weapon.WeaponType;
import gow.generator.trait.UniqueTraitsRepository;
import gow.generator.utils.RandomUtils;

import java.util.Random;

public class WeaponGenerator extends ItemGenerator<Weapon> {
    /**
     * @see gow.generator.armor.ArmorGenerator
     */
    private static final int MIN_DAMAGE_PERCENT = 80;
    private UniqueTraitsRepository traitsRepository;
    private Random random = new Random();
    private Characteristic mainCharacteristic;

    public WeaponGenerator(Characteristic mainCharacteristic) {
        this.traitsRepository = new UniqueTraitsRepository(ItemClass.WEAPON);
        this.mainCharacteristic = mainCharacteristic;
    }

    @Override
    protected float calcWeight(Weapon generatedItem) {
        return 0;
    }

    @Override
    protected Enum<? extends ItemType> randomItemType() {
        WeaponType[] weaponTypes = WeaponType.values();
        int randomIndex = random.nextInt(weaponTypes.length);
        return weaponTypes[randomIndex];
    }

    @Override
    protected void fillRequirements(Weapon generatedItem) {
        //todo implement
    }

    @Override
    protected int fillMajorityAndGetCost(int moneyLeft, Weapon generatedItem, Enum<? extends ItemType> weaponType) {
        try {
            int randomDamage = getRandomDamage(moneyLeft, (WeaponType) weaponType);
            generatedItem.setDamage(randomDamage);
            generatedItem.setWeaponType((WeaponType) weaponType);
            return WeaponPriceCalculator.priceOf(randomDamage, (WeaponType) weaponType);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Expected <Weapon> item and itemType, got " + generatedItem.getClass() +
                    " and " + weaponType.getClass(), e);
        }
    }

    @Override
    protected Weapon createEmpty() {
        return new Weapon();
    }

    private int getRandomDamage(int price, WeaponType weaponType) {
        int maxDamage = WeaponPriceCalculator.damageFor(price, weaponType);
        return Math.round(maxDamage * RandomUtils.getPercent(MIN_DAMAGE_PERCENT));
    }

    @Override
    protected UniqueTraitsRepository traitsRepository() {
        return traitsRepository;
    }

    @Override
    public Characteristic getMainCharacteristic() {
        return mainCharacteristic;
    }

    @Override
    public void setMainCharacteristic(Characteristic mainCharacteristic) {
        this.mainCharacteristic = mainCharacteristic;
    }
}

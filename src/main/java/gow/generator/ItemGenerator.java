package gow.generator;

import gow.Item;
import gow.MainCharacteristic;

interface ItemGenerator {
    Item generate(MainCharacteristic mainCharacteristic, int lowerPrice, int upperPrice);
}

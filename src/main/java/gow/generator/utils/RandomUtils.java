package gow.generator.utils;

import java.util.Random;

public final class RandomUtils {
    private static Random random = new Random();

    public static float getPercent(int lowerGuaranteedPercent) {
        return (100 - random.nextInt(100 - lowerGuaranteedPercent)) / 100f;
    }
}

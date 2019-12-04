package gow.generator.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import gow.generator.Characteristic;
import gow.generator.ItemClass;
import gow.generator.trait.Trait;
import gow.generator.trait.UniqueTrait;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public final class TraitsJsonParser {
    private static TraitsJsonParser parserInstance;
    private Set<UniqueTrait> existingTraits = Sets.newHashSet();

    private TraitsJsonParser() {
    }

    public static TraitsJsonParser getInstance() {
        if (parserInstance == null) {
            parserInstance = new TraitsJsonParser();
        }
        return parserInstance;
    }

    private static String getPath(ItemClass itemClass, Characteristic characteristic) {
        StringBuilder pathBuilder = new StringBuilder("static/unique_traits/");
        switch (itemClass) {
            case ARMOR:
                pathBuilder.append("armor/");
                break;
            case WEAPON:
                pathBuilder.append("weapon/");
                break;
            default:
                throw new UnsupportedOperationException("Unknown itemType: " + itemClass);
        }

        switch (characteristic) {
            case STRENGTH:
                pathBuilder.append("strength");
                break;
            case VITALITY:
                pathBuilder.append("vitality");
                break;
            case DEXTERITY:
                pathBuilder.append("dexterity");
                break;
            case INTELLIGENCE:
                pathBuilder.append("intelligence");
                break;
            default:
                throw new UnsupportedOperationException("Unknown characteristic: " + characteristic);
        }

        pathBuilder.append(".json");
        return pathBuilder.toString();
    }

    private Set<UniqueTrait> fetchTraits(ItemClass itemClass, Characteristic characteristic) {
        String path = getPath(itemClass, characteristic);
        InputStream jsonInputStream = getClass().getClassLoader().getResourceAsStream(path);
        ObjectMapper mapper = new ObjectMapper();
        Set<UniqueTrait> uniqueTraits = Sets.newHashSet();
        try {
            Trait[] traits = mapper.readValue(jsonInputStream, Trait[].class);
            for (Trait trait : traits) {
                uniqueTraits.add(new UniqueTrait(trait, itemClass, characteristic));
            }
        } catch (IOException e) {
            throw new BrokenJsonException("Got an exception while fetching traits of " +
                    "[" + itemClass + ";" + characteristic + "], path = " + path, e);
        }
        return uniqueTraits;
    }

    private Set<UniqueTrait> fetchAllTraits() {
        Set<UniqueTrait> traits = Sets.newHashSet();
        for (ItemClass itemClass : ItemClass.values()) {
            for (Characteristic characteristic : Characteristic.values()) {
                traits.addAll(fetchTraits(itemClass, characteristic));
            }
        }
        return traits;
    }

    public Set<UniqueTrait> lazyLoadTraits() {
        if (this.existingTraits.isEmpty()) {
            existingTraits = fetchAllTraits();
        }
        return existingTraits;
    }
}

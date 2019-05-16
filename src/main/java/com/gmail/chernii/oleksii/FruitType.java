package com.gmail.chernii.oleksii;

/**
 * Created by Space on 27.03.2019.
 */
public enum FruitType {
    APPLE("Apple"),
    APRICOT("Apricot"),
    BANANA("Banana"),
    AVOKADO("Avocado"),
    CHERRY("Cherry");

    FruitType(String type) {
        this.description = type;
    }

    private String description;

    @Override
    public String toString() {
        return description;
    }
}

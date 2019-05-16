package com.gmail.chernii.oleksii;


/**
 * Created by Space on 27.03.2019.
 */
public class Main {
    public static void main(String[] args) {
        FruitStore fruitStore = new FruitStore();
        addAndSave(fruitStore);

        fruitStore.getFruits().clear();
        load(fruitStore);
        fruitStore.getFruits().forEach(System.out::println);
    }

    public static void addAndSave(FruitStore fruitStore) {
        fruitStore.addFruits("json/firstSupply.json");
        fruitStore.addFruits("json/secondSupply.json");
        fruitStore.save("json/fruitStore.json");
    }

    public static void load(FruitStore fruitStore) {
        fruitStore.load("json/fruitStore.json");
    }
}

package com.gmail.chernii.oleksii;


import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Space on 27.03.2019.
 */
public class Main {
    public static void main(String[] args) {
        /*FruitStore fruitStore = new FruitStore();
        load(fruitStore);
        System.out.println(fruitStore.getFruits().size());
*/
        File file = new File("json/fruitStore.json");
        System.out.println(file.exists());
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 2, 31);
        Date date = calendar.getTime();
        System.out.println(date);

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

package com.gmail.chernii.oleksii;

import org.junit.Test;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FruitStoreTest {


    @Test
    public void testAddFruits() {
        FruitStore localFruitStore = new FruitStore();
        localFruitStore.addFruits("json/firstSupply.json");
        localFruitStore.addFruits("json/secondSupply.json");
        assertFalse(localFruitStore.getFruits().isEmpty());
    }

    @Test
    public void testSave() throws Exception {
        FruitStore localFruitStore = new FruitStore();
        Fruit fruit = new Fruit(FruitType.BANANA, 10, new Date(), 20);
        localFruitStore.add(fruit);
        localFruitStore.save("json/data.json");
        File file = new File("json/fruitStore.json");
        assertTrue(file.exists());
    }

    @Test
    public void testLoad() {
        FruitStore localFruitStore = new FruitStore();
        localFruitStore.load("json/data.json");
        assertFalse(localFruitStore.getFruits().isEmpty());
    }

    @Test
    public void testGetAvailableFruits() {
        FruitStore localFruitStore = new FruitStore();
        Fruit banana = new Fruit(FruitType.BANANA, 10, new Date(), 20);
        Fruit avocado = new Fruit(FruitType.AVOKADO, 5, new Date(), 20);
        localFruitStore.add(banana);
        localFruitStore.add(avocado);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 3, 8);
        Date date = calendar.getTime();
        assertEquals(localFruitStore.getAvailableFruits(date).size(), 1);
    }

    @Test
    public void testGetSpoiledFruits() {
        FruitStore localFruitStore = new FruitStore();
        Fruit banana = new Fruit(FruitType.BANANA, 10, new Date(), 20);
        Fruit avocado = new Fruit(FruitType.AVOKADO, 5, new Date(), 20);
        localFruitStore.add(banana);
        localFruitStore.add(avocado);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 3, 8);
        Date date = calendar.getTime();
        assertEquals(localFruitStore.getSpoiledFruits(date).size(), 1);
    }

    @Test
    public void testGetSpoiledFruitsType() {
        FruitStore localFruitStore = new FruitStore();
        Fruit banana = new Fruit(FruitType.BANANA, 10, new Date(), 20);
        Fruit avocado = new Fruit(FruitType.AVOKADO, 5, new Date(), 20);
        localFruitStore.add(banana);
        localFruitStore.add(avocado);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 3, 8);
        Date date = calendar.getTime();
        assertEquals(localFruitStore.getSpoiledFruits(date, FruitType.BANANA).size(), 0);
        assertEquals(localFruitStore.getSpoiledFruits(date, FruitType.AVOKADO).size(), 1);
    }

    @Test
    public void testGetAddedFruits() {
        FruitStore localFruitStore = new FruitStore();
        Fruit banana = new Fruit(FruitType.BANANA, 10, new Date(), 20);
        Fruit avocado = new Fruit(FruitType.AVOKADO, 5, new Date(), 20);
        localFruitStore.add(banana);
        localFruitStore.add(avocado);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 3, 8);
        Date date = calendar.getTime();
        assertEquals(localFruitStore.getAddedFruits(new Date()), 2);
        assertFalse(localFruitStore.getAddedFruits(date).isEmpty());
    }

    @Test
    public void testGetAddedFruitsType() {
        FruitStore localFruitStore = new FruitStore();
        Fruit banana = new Fruit(FruitType.BANANA, 10, new Date(), 20);
        Fruit avocado = new Fruit(FruitType.AVOKADO, 5, new Date(), 20);
        localFruitStore.add(banana);
        localFruitStore.add(avocado);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 3, 8);
        Date date = calendar.getTime();
        assertEquals(localFruitStore.getAddedFruits(date, FruitType.BANANA).size(), 1);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        assertTrue(localFruitStore.getAddedFruits(date, FruitType.BANANA).isEmpty());
    }
}

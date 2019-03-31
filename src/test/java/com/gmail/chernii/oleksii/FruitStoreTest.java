package com.gmail.chernii.oleksii;


import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class FruitStoreTest extends TestCase {
    private FruitStore fruitStore = new FruitStore();

    @Test
    public void testAddFruits() throws Exception {
        fruitStore.addFruits("json/firstSupply.json");
        fruitStore.addFruits("json/secondSupply.json");
        assertFalse(fruitStore.getFruits().isEmpty());
    }

    @Test
    public void testSave() throws Exception {
        fruitStore.addFruits("json/firstSupply.json");
        fruitStore.addFruits("json/secondSupply.json");
        fruitStore.save("json/data.json");
        File file = new File("json/fruitStore.json");
        assertTrue(file.exists());
    }

    @Test
    public void testLoad() throws Exception {
        FruitStore localFruitStore = new FruitStore();
        localFruitStore.load("json/data.json");
        assertFalse(localFruitStore.getFruits().isEmpty());
    }

    @Test
    public void testGetAvailableFruits() throws Exception {
        fruitStore.load("json/data.json");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 3, 31);
        Date date = calendar.getTime();
        assertEquals(fruitStore.getAvailableFruits(date).size(), 0);
    }

    @Test
    public void testGetSpoiledFruits() throws Exception {
        fruitStore.load("json/data.json");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 2, 31);
        Date date = calendar.getTime();
        assertEquals(fruitStore.getSpoiledFruits(date).size(), 5);
    }

    @Test
    public void testGetSpoiledFruitsType() throws Exception {
        fruitStore.load("json/data.json");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 2, 31);
        Date date = calendar.getTime();
        assertEquals(fruitStore.getSpoiledFruits(date, FruitType.CHERRY).size(), 1);
    }

    @Test
    public void testGetAddedFruits() throws Exception {
        fruitStore.load("json/data.json");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 2, 12);
        Date date = calendar.getTime();
        assertEquals(fruitStore.getAddedFruits(date).size(), 5);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        System.out.println(date+ " - " + fruitStore.getAddedFruits(date).size());
        assertTrue(fruitStore.getAddedFruits(date).isEmpty());
    }

    @Test
    public void testGetAddedFruitsType() throws Exception {
        fruitStore.load("json/data.json");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 2, 12);
        Date date = calendar.getTime();
        assertEquals(fruitStore.getAddedFruits(date, FruitType.BANANA).size(), 1);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        assertTrue(fruitStore.getAddedFruits(date, FruitType.BANANA).isEmpty());
    }
}

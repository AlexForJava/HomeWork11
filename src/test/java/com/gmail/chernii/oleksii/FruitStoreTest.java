package com.gmail.chernii.oleksii;


import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class FruitStoreTest extends TestCase {
    private FruitStore fruitStore = new FruitStore();
    private static final Logger logger = Logger.getLogger(FruitStoreTest.class);

    @Test
    public void testAddFruits() throws Exception {
        fruitStore.addFruits("json/firstSupply.json");
        fruitStore.addFruits("json/secondSupply.json");
        assertFalse(fruitStore.getFruits().isEmpty());
        logger.info("test addFruits() passed");
    }

    @Test
    public void testSave() throws Exception {
        fruitStore.addFruits("json/firstSupply.json");
        fruitStore.addFruits("json/secondSupply.json");
        fruitStore.save("json/data.json");
        File file = new File("json/fruitStore.json");
        assertTrue(file.exists());
        logger.info("test save() passed");
    }

    @Test
    public void testLoad() throws Exception {
        fruitStore.load("json/data.json");
        assertFalse(fruitStore.getFruits().isEmpty());
        logger.info("test load() passed");
    }

    @Test
    public void testGetAvailableFruits() throws Exception {
        fruitStore.load("json/data.json");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 3, 31);
        Date date = calendar.getTime();
        assertTrue(fruitStore.getAvailableFruits(date).size() == 5);
        logger.info("test getAvailableFruits(date) passed");
    }

    @Test
    public void testGetSpoiledFruits() throws Exception {
        fruitStore.load("json/data.json");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 2, 31);
        Date date = calendar.getTime();
        assertTrue(fruitStore.getSpoiledFruits(date).size() == 5);
        logger.info("test getSpoiledFruits(date) passed");
    }

    @Test
    public void testGetSpoiledFruitsType() throws Exception {
        fruitStore.load("json/data.json");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 2, 31);
        Date date = calendar.getTime();
        assertTrue(fruitStore.getSpoiledFruits(date, FruitType.CHERRY).size() == 1);
        logger.info("test getSpoiledFruits(date, type) passed");
    }

    @Test
    public void testGetAddedFruits() throws Exception {
        fruitStore.load("json/data.json");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 2, 12);
        Date date = calendar.getTime();
        assertTrue(fruitStore.getAddedFruits(date).size() == 5);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        assertTrue(fruitStore.getAddedFruits(date).isEmpty());
        logger.info("test getAddedFruits(date) passed");
    }

    @Test
    public void testGetAddedFruitsType() throws Exception {
        fruitStore.load("json/data.json");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 2, 12);
        Date date = calendar.getTime();
        assertTrue(fruitStore.getAddedFruits(date,FruitType.BANANA).size() == 1);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        assertTrue(fruitStore.getAddedFruits(date, FruitType.BANANA).isEmpty());
        logger.info("test getAddedFruits(date) passed");
    }
}